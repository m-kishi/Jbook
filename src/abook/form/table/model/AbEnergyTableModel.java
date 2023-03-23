// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import abook.expense.AbSummary;

/**
 * 光熱費のテーブルモデル
 */
public class AbEnergyTableModel extends AbstractTableModel {

	/** 光熱費の内部データ */
	private ArrayList<Object[]> energy;

	/** 各月の最大値 */
	private int[] max = new int[12];

	/** 各月の最小値 */
	private int[] min = new int[12];

	/** 列ヘッダ定義 */
	private static final String[] COLUMNS = {
			"年度",
			"4月",
			"5月",
			"6月",
			"7月",
			"8月",
			"9月",
			"10月",
			"11月",
			"12月",
			"1月",
			"2月",
			"3月",
	};

	/**
	 * コンストラクタ
	 * 
	 * @param name      名称(電気代・ガス代・水道代)
	 * @param summaries 月次情報リスト
	 */
	public AbEnergyTableModel(String name, List<AbSummary> summaries) {
		energy = new ArrayList<Object[]>();

		// 年度に振り分け
		var groupByYear = summaries.stream().collect(
				Collectors.groupingBy(sum ->
						sum.getYear() + (sum.getMonth() < 4 ? -1 : 0),
						TreeMap::new,
						Collectors.toList()
				)
		);
		for (var entry : groupByYear.entrySet()) {
			int year = entry.getKey();

			// 月ごとに分割(月ごとの summary は 1 件ずつしかない)
			var groupByMonth = entry.getValue().stream().collect(
					Collectors.groupingBy(sum -> sum.getMonth(), Collectors.summingInt(sum -> sum.getCostByName(name)))
			);
			energy.add(new Object[] {
					year,
					groupByMonth.containsKey( 4) ? groupByMonth.get( 4) : 0,
					groupByMonth.containsKey( 5) ? groupByMonth.get( 5) : 0,
					groupByMonth.containsKey( 6) ? groupByMonth.get( 6) : 0,
					groupByMonth.containsKey( 7) ? groupByMonth.get( 7) : 0,
					groupByMonth.containsKey( 8) ? groupByMonth.get( 8) : 0,
					groupByMonth.containsKey( 9) ? groupByMonth.get( 9) : 0,
					groupByMonth.containsKey(10) ? groupByMonth.get(10) : 0,
					groupByMonth.containsKey(11) ? groupByMonth.get(11) : 0,
					groupByMonth.containsKey(12) ? groupByMonth.get(12) : 0,
					groupByMonth.containsKey( 1) ? groupByMonth.get( 1) : 0,
					groupByMonth.containsKey( 2) ? groupByMonth.get( 2) : 0,
					groupByMonth.containsKey( 3) ? groupByMonth.get( 3) : 0,
			});
		}

		// 最大値と最小値を取得
		Arrays.fill(max, Integer.MIN_VALUE);
		Arrays.fill(min, Integer.MAX_VALUE);
		for (Object[] eachYear : energy) {
			for (int i = 0; i < 12; i++) {
				int cost = (int) eachYear[i + 1];
				if (cost == 0) {
					// 0 は最小扱いしない
					continue;
				}
				max[i] = Math.max(max[i], (int) eachYear[i + 1]);
				min[i] = Math.min(min[i], (int) eachYear[i + 1]);
			}
		}
	}

	@Override
	public int getRowCount() {
		return energy.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return energy.get(rowIndex)[columnIndex];
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMNS[columnIndex];
	}

	/**
	 * 最大値の判定
	 * 
	 * @param cost 金額
	 * @param idx  列(0:4月 1:5月 2:6月 ... 11:3月)
	 * @return true:最大値 false:最大値でない
	 */
	public boolean isMax(int cost, int idx) {
		return max[idx] == cost && min[idx] != cost;
	}

	/**
	 * 最小値の判定
	 * 
	 * @param cost 金額
	 * @param idx  列(0:4月 1:5月 2:6月 ... 11:3月)
	 * @return true:最小値 false:最小値でない
	 */
	public boolean isMin(int cost, int idx) {
		return min[idx] == cost && max[idx] != cost;
	}
}
