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
 * 秘密収支情報のテーブルモデル
 */
public class AbPrivateTableModel extends AbstractTableModel {

	/** 秘密収支情報の内部データ */
	private ArrayList<Object[]> privates;

	/** 列ヘッダ定義 */
	private static final String[] COLUMNS = {
			"日付",
			"名称",
			"金額",
			"収支",
			"備考",
	};

	/**
	 * コンストラクタ
	 * 
	 * @param expenses 支出情報リスト
	 */
	public AbPrivateTableModel(List<AbExpense> expenses) {
		load(expenses);
	}

	/**
	 * 支出情報リストの読み込み
	 * 
	 * @param expenses 支出情報リスト
	 */
	public void load(List<AbExpense> expenses) {
		if (this.privates == null) {
			this.privates = new ArrayList<Object[]>();
		} else {
			this.privates.clear();
		}

		int balance = 0;
		var privates = expenses.stream().filter(exp ->
				TYPE.PRIVATES.contains(exp.getType())
		).collect(Collectors.toList());
		for (AbExpense expense : privates) {
			int cost = (expense.getType().equals(TYPE.PRVO) ? -1 : 1) * expense.getCost();
			balance += cost;
			this.privates.add(
					new Object[] {
							expense.getDate(),
							expense.getName(),
							cost,
							balance,
							expense.getNote()
					}
			);
		}
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return privates.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return privates.get(rowIndex)[columnIndex];
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMNS[columnIndex];
	}
}
