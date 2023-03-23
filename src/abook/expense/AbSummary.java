// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import abook.common.AbConstant.NAME;
import abook.common.AbConstant.TYPE;
import abook.common.AbUtility.UTL;

/**
 * 月次情報クラス
 */
public class AbSummary {

	/** 年 */
	private int year;

	/** 月 */
	private int month;

	/** 種別ごとの合計 */
	private Map<String, Integer> summaryByType;

	/** 名称ごとの合計 */
	private Map<String, Integer> summaryByName;

	/**
	 * コンストラクタ
	 * 
	 * @param date     日付
	 * @param expenses 支出情報リスト
	 */
	public AbSummary(LocalDate date, List<AbExpense> expenses) {
		year = date.getYear();
		month = date.getMonthValue();
		summaryByType = summaryByType(expenses);
		summaryByName = summaryByName(expenses);
	}

	/**
	 * 種別ごとに合計を計算
	 * 
	 * @param expenses 支出情報リスト
	 * @return 種別ごとの合計
	 */
	private Map<String, Integer> summaryByType(List<AbExpense> expenses) {

		// 種別ごとの集計
		var result = expenses.stream().collect(
				Collectors.groupingBy(AbExpense::getType, Collectors.summingInt(AbExpense::getCost))
		);

		// 合計
		int total = expenses.stream().filter(exp ->
				TYPE.SUMMARIES.contains(exp.getType())).mapToInt(AbExpense::getCost
		).sum();
		result.put(TYPE.TTAL, total);

		// 収支
		int earn = result.containsKey(TYPE.EARN) ? result.get(TYPE.EARN) : 0;
		int balance = earn - total;
		result.put(TYPE.BLNC, balance);

		return result;
	}

	/**
	 * 名称ごとに合計を計算
	 * 
	 * @param expenses 支出情報リスト
	 * @return 名称ごとの合計
	 */
	private Map<String, Integer> summaryByName(List<AbExpense> expenses) {
		var result = expenses.stream().filter(exp ->
				NAME.ENERGY.contains(exp.getName())
		).collect(Collectors.groupingBy(AbExpense::getName, Collectors.summingInt(AbExpense::getCost)));
		return result;
	}

	/**
	 * getter(年)
	 * 
	 * @return 年
	 */
	public int getYear() {
		return year;
	}

	/**
	 * getter(月)
	 * 
	 * @return 月
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * 合計を取得
	 * 
	 * @param type 種別
	 * @return 合計
	 */
	public int getCostByType(String type) {
		if (UTL.isEmpty(type)) {
			return 0;
		}
		return summaryByType.containsKey(type) ? summaryByType.get(type) : 0;
	}

	/**
	 * 合計を取得
	 * 
	 * @param name 名称
	 * @return 合計
	 */
	public int getCostByName(String name) {
		if (UTL.isEmpty(name)) {
			return 0;
		}
		return summaryByName.containsKey(name) ? summaryByName.get(name) : 0;
	}
}
