// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import abook.common.AbConstant.COL;
import abook.common.AbException;
import abook.common.AbManager;
import abook.common.AbManager.MESSAGE;
import abook.common.AbUtility.UTL;
import abook.expense.AbComplete;
import abook.expense.AbExpense;

/**
 * 支出情報のテーブルモデル
 */
public class AbExpenseTableModel extends AbstractTableModel implements TableModelListener {

	/** 支出情報の内部データ */
	private ArrayList<Object[]> expenses;

	/** 自動補完 */
	private AbComplete complete;

	/** 編集可否 */
	private boolean isEditable;

	/** 追加行数 */
	private static final int NEW_ROW_SIZE = 30;

	/** 列ヘッダ定義 */
	private static final String[] COLUMNS = {
			"日付",
			"名称",
			"種別",
			"金額",
			"備考",
	};

	/**
	 * コンストラクタ
	 * 
	 * @param expenses   支出情報リスト
	 * @param isEditable 編集可否
	 */
	public AbExpenseTableModel(List<AbExpense> expenses, boolean isEditable) {
		load(expenses);
		addTableModelListener(this);
		this.isEditable = isEditable;
	}

	/**
	 * 支出情報リストの読み込み
	 * 
	 * @param expenses 支出情報リスト
	 */
	public void load(List<AbExpense> expenses) {
		if (this.expenses == null) {
			this.expenses = new ArrayList<Object[]>();
		} else {
			this.expenses.clear();
		}
		for (AbExpense expense : expenses) {
			this.expenses.add(
					new Object[] {
							expense.getDate(),
							expense.getName(),
							expense.getType(),
							expense.getCost(),
							expense.getNote()
					});
		}
		complete = new AbComplete(expenses);
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return expenses.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return expenses.get(rowIndex)[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return expenses.get(0)[columnIndex].getClass();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMNS[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return isEditable;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		expenses.get(rowIndex)[columnIndex] = value;
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	/**
	 * 編集可否
	 * 
	 * @return true:編集可能 false:編集不可
	 */
	public boolean isEditable() {
		return isEditable;
	}

	/**
	 * 行追加
	 */
	public void addRow() {
		for (int i = 0; i < NEW_ROW_SIZE; i++) {
			expenses.add(new Object[] { LocalDate.now(), "", "", "", "" });
		}
		fireTableRowsInserted(0, expenses.size() - 1);
	}

	/**
	 * 支出情報リストを取得
	 * 
	 * @return 支出情報リスト
	 * @throws AbException
	 */
	public List<AbExpense> getExpenses() throws AbException {
		int line = 0;
		List<AbExpense> expenses = new ArrayList<AbExpense>();
		try {
			for (Object[] expense : this.expenses) {
				String date = UTL.toString((LocalDate) expense[COL.EXPENSE.DATE]);
				String name = Objects.toString(expense[COL.EXPENSE.NAME], "");
				String type = Objects.toString(expense[COL.EXPENSE.TYPE], "");
				String cost = Objects.toString(expense[COL.EXPENSE.COST], "");
				String note = Objects.toString(expense[COL.EXPENSE.NOTE], "");

				List<String> args = new ArrayList<String>(Arrays.asList(date, name, type, cost));
				if (args.stream().allMatch(arg -> !UTL.isEmpty(arg))) {
					line++;
					expenses.add(new AbExpense(date, name, type, cost, note));
				}
			}
		} catch (AbException ex) {
			String message = String.format(MESSAGE.EXPENSE_ERROR, line, ex.getMessage());
			AbManager.throwException(message);
		}
		return expenses;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int col = e.getColumn();
		int row = e.getFirstRow();

		// 名称列 => 種別の自動補完
		if (col == COL.EXPENSE.NAME) {
			String name = String.valueOf(getValueAt(row, col));
			String type = complete.getType(name);
			if (type != null) {
				setValueAt(type, row, COL.EXPENSE.TYPE);
			}
			if (UTL.isEmpty(type)) {
				setValueAt("", row, COL.EXPENSE.COST);
			}
		}

		// 種別列 => 金額の自動補完
		if (col == COL.EXPENSE.TYPE) {
			String name = String.valueOf(getValueAt(row, COL.EXPENSE.NAME));
			String type = String.valueOf(getValueAt(row, col));
			Integer cost = complete.getCost(name, type);
			if (cost != null) {
				setValueAt(cost, row, COL.EXPENSE.COST);
			}
			if (UTL.isEmpty(name) && UTL.isEmpty(type)) {
				setValueAt("", row, COL.EXPENSE.COST);
			}
		}
	}
}
