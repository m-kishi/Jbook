// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
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
			"年",
			"名称",
			"備考",
			"金額",
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

		// 年＋名称＋備考
		var grouped = expenses.stream().filter(exp -> TYPE.FNCE.equals(exp.getType())).collect(
			Collectors.groupingBy(exp ->
				exp.getDate().getYear(), TreeMap::new,
				Collectors.groupingBy(
						AbExpense::getName, TreeMap::new,
						Collectors.groupingBy(
								AbExpense::getNote, TreeMap::new,
								Collectors.summingInt(AbExpense::getCost)
						)
				)
			)
		);
		for (var yearEntry : grouped.entrySet()) {
			for (var nameEntry : yearEntry.getValue().entrySet()) {
				for (var noteEntry : nameEntry.getValue().entrySet()) {
					this.finances.add(new Object[] {
							yearEntry.getKey(),
							nameEntry.getKey(),
							noteEntry.getKey(),
							noteEntry.getValue(),
					});
				}
			}
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
