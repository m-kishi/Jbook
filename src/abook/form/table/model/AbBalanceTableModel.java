// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import abook.common.AbConstant.COL;
import abook.common.AbConstant.TYPE;
import abook.expense.AbExpense;

/**
 * 収支情報のテーブルモデル
 */
public class AbBalanceTableModel extends AbstractTableModel {

	/** 収支情報の内部データ */
	private ArrayList<Object[]> balances;

	/** 列ヘッダ定義 */
	private static final String[] COLUMNS = {
			"年度",
			"収入",
			"支出",
			"収支",
	};

	/**
	 * コンストラクタ
	 * 
	 * @param expenses 支出情報リスト
	 */
	public AbBalanceTableModel(List<AbExpense> expenses) {
		load(expenses);
	}

	/**
	 * 支出情報リストの読み込み
	 * 
	 * @param expenses 支出情報リスト
	 */
	public void load(List<AbExpense> expenses) {
		if (balances == null) {
			balances = new ArrayList<Object[]>();
		} else {
			balances.clear();
		}

		var groupByYear = expenses.stream().filter(exp -> !TYPE.PRIVATES.contains(exp.getType())).collect(
				Collectors.groupingBy(exp ->
						exp.getDate().getYear() + (exp.getDate().getMonthValue() < 4 ? -1 : 0),
						TreeMap::new,
						Collectors.toList()
				)
		);

		for (var yearEntry : groupByYear.entrySet()) {
			var groupByType = yearEntry.getValue().stream().collect(
					Collectors.groupingBy(AbExpense::getType, Collectors.summingInt(AbExpense::getCost))
			);

			int year = yearEntry.getKey();
			int earn = 0;
			int expense = 0;
			int balance = 0;
			for (var groupEntry : groupByType.entrySet()) {
				String type = groupEntry.getKey();
				Integer cost = groupEntry.getValue();

				if (TYPE.BALANCE.EARN.contains(type)) {
					earn += cost;
					balance += cost;
				}

				if (TYPE.BALANCE.EXPENSE.contains(type)) {
					expense += cost;
					balance -= cost;
				}
			}
			balances.add(new Object[] { year, earn, expense, balance });
		}

		// 合計
		if (!balances.isEmpty()) {
			balances.add(
					new Object[] {
							9999,
							balances.stream().mapToInt(bln -> (int) bln[COL.BALANCE.EARN]).sum(),
							balances.stream().mapToInt(bln -> (int) bln[COL.BALANCE.EXPENSE]).sum(),
							balances.stream().mapToInt(bln -> (int) bln[COL.BALANCE.BALANCE]).sum()
					}
			);
		}

		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return balances.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return balances.get(rowIndex)[columnIndex];
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMNS[columnIndex];
	}
}
