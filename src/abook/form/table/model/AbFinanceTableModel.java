// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import abook.common.AbConstant.TYPE;
import abook.expense.AbExpense;

/**
 * 投資情報のテーブルモデル
 */
public class AbFinanceTableModel extends AbstractTableModel {

	/** 投資情報の内部データ */
	private ArrayList<Object[]> finances;

	/** 列ヘッダ定義 */
	private static final String[] COLUMNS = {
			"日付",
			"名称",
			"金額",
			"累計",
			"備考",
	};

	/**
	 * コンストラクタ
	 * 
	 * @param expenses 支出情報リスト
	 */
	public AbFinanceTableModel(List<AbExpense> expenses) {
		load(expenses);
	}

	/**
	 * 支出情報リストの読み込み
	 * 
	 * @param expenses 支出情報リスト
	 */
	public void load(List<AbExpense> expenses) {
		if (this.finances == null) {
			this.finances = new ArrayList<Object[]>();
		} else {
			this.finances.clear();
		}

		int total = 0;
		var finances = expenses.stream().filter(exp -> TYPE.FNCE.equals(exp.getType())).collect(Collectors.toList());
		for (AbExpense expense : finances) {
			int cost = expense.getCost();
			total += cost;
			this.finances.add(
					new Object[] {
							expense.getDate(),
							expense.getName(),
							cost,
							total,
							expense.getNote()
					});
		}
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return finances.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return finances.get(rowIndex)[columnIndex];
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMNS[columnIndex];
	}
}
