// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.expense.manager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import abook.common.AbConstant.FMT;
import abook.common.AbUtility.UTL;
import abook.expense.AbExpense;
import abook.expense.AbSummary;

/**
 * 月次情報管理クラス
 */
public class AbSummaryManager {

	/** 月次情報 */
	private AbSummary summary;

	/** 月次情報リスト */
	private List<AbSummary> summaries;

	/**
	 * コンストラクタ
	 * 
	 * @param date      日付
	 * @param summaries 月次情報リスト
	 */
	public AbSummaryManager(LocalDate date, List<AbSummary> summaries) {
		this.summaries = summaries;
		setCurrentSummary(date);
	}

	/**
	 * 月次情報設定
	 * 
	 * @param date 対象日付
	 */
	public void setCurrentSummary(LocalDate date) {
		summary = new AbSummary(date, new ArrayList<AbExpense>());
		var currentSummary = summaries.stream().filter(sum ->
				sum.getYear() == date.getYear() && sum.getMonth() == date.getMonthValue()
		).findFirst();
		currentSummary.ifPresent(sum -> summary = sum);
	}

	/**
	 * 現在の月次情報の日付
	 * 
	 * @return 現在の月次情報の日付
	 */
	public LocalDate getCurrentDate() {
		return LocalDate.of(summary.getYear(), summary.getMonth(), 1);
	}

	/**
	 * タイトル
	 * 
	 * @return yyyy年MM月
	 */
	public String getTitle() {
		return UTL.toTitle(getCurrentDate());
	}

	/**
	 * 金額取得
	 * 
	 * @param type 種別
	 * @return 金額
	 */
	public int getCost(String type) {
		return summary.getCostByType(type);
	}

	/**
	 * 前年へ切り替え
	 */
	public void setPrevYear() {
		LocalDate date = getCurrentDate().minusYears(1);
		setCurrentSummary(date);
	}

	/**
	 * 前月へ切り替え
	 */
	public void setPrevMonth() {
		LocalDate date = getCurrentDate().minusMonths(1);
		setCurrentSummary(date);
	}

	/**
	 * 翌月へ切り替え
	 */
	public void setNextMonth() {
		LocalDate date = getCurrentDate().plusMonths(1);
		setCurrentSummary(date);
	}

	/**
	 * 翌年へ切り替え
	 */
	public void setNextYear() {
		LocalDate date = getCurrentDate().plusYears(1);
		setCurrentSummary(date);
	}

	/**
	 * 月次情報リスト生成
	 * 
	 * @param expenses 支出情報リスト
	 * @return 月次情報リスト
	 */
	public static List<AbSummary> createSummaries(List<AbExpense> expenses) {
		List<AbSummary> summaries = new ArrayList<AbSummary>();
		var groupByMonth = expenses.stream().collect(Collectors.groupingBy(exp -> UTL.toMonthly(exp.getDate())));
		for (var monthEntry : groupByMonth.entrySet()) {
			LocalDate date = LocalDate.parse(monthEntry.getKey() + "-01", DateTimeFormatter.ofPattern(FMT.DATE));
			summaries.add(new AbSummary(date, monthEntry.getValue()));
		}
		return summaries;
	}
}
