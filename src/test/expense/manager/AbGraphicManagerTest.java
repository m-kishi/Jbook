// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.expense.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import abook.common.AbException;
import abook.expense.AbExpense;
import abook.expense.AbSummary;
import abook.expense.manager.AbGraphicManager;
import abook.expense.manager.AbSummaryManager;

/**
 * テスト:推移情報管理クラス
 */
public class AbGraphicManagerTest {

	/** 日付 */
	private static LocalDate date;

	/** 月次情報リスト */
	private static List<AbSummary> summaries;

	/** 期待値:推移情報管理クラス */
	private static AbGraphicManager manager;

	@BeforeAll
	public static void testFixtureSetup() throws AbException {
		List<AbExpense> expenses = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-10-01", "name1", "食費", "100", "note1")
				)
		);
		summaries = AbSummaryManager.createSummaries(expenses);
	}

	@BeforeEach
	public void setup() throws AbException {
		date = LocalDate.of(2023, 10, 1);
		manager = new AbGraphicManager(date, summaries);
	}

	/**
	 * コンストラクタ
	 * 引数:日付がNULL
	 */
	@Test
	public void AbGraphicManagerWithDateNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> new AbGraphicManager(null, summaries)
		);
	}

	/**
	 * コンストラクタ
	 * 引数:月次情報リストがNULL
	 */
	@Test
	public void AbGraphicManagerWithSummariesNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> new AbGraphicManager(date, null)
		);
	}

	/**
	 * コンストラクタ
	 * 引数:月次情報リストが空
	 */
	@Test
	public void AbGraphicManagerWithSummariesEmpty() {
		assertDoesNotThrow(() -> new AbGraphicManager(date, new ArrayList<AbSummary>()));
	}

	/**
	 * 現在の推移情報の日付
	 */
	@Test
	public void getDate() {
		assertEquals(date, manager.getDate());
	}

	/**
	 * タイトル
	 */
	@Test
	public void getTitle() {
		assertEquals("2023年10月", manager.getTitle());
	}

	/**
	 * 前年へシフト
	 */
	@Test
	public void setPrevYearWithOnce() {
		manager.setPrevYear();
		assertEquals("2022年10月", manager.getTitle());
	}

	/**
	 * 前年へシフト
	 */
	@Test
	public void setPrevYearWithTwice() {
		manager.setPrevYear();
		manager.setPrevYear();
		assertEquals("2021年10月", manager.getTitle());
	}

	/**
	 * 前年へシフト
	 */
	@Test
	public void setPrevYearWithThrice() {
		manager.setPrevYear();
		manager.setPrevYear();
		manager.setPrevYear();
		assertEquals("2020年10月", manager.getTitle());
	}

	/**
	 * 前月へシフト
	 */
	@Test
	public void setPrevMonthWithOnce() {
		manager.setPrevMonth();
		assertEquals("2023年09月", manager.getTitle());
	}

	/**
	 * 前月へシフト
	 */
	@Test
	public void setPrevMonthWithTwice() {
		manager.setPrevMonth();
		manager.setPrevMonth();
		assertEquals("2023年08月", manager.getTitle());
	}

	/**
	 * 前月へシフト
	 */
	@Test
	public void setPrevMonthWithThrice() {
		manager.setPrevMonth();
		manager.setPrevMonth();
		manager.setPrevMonth();
		assertEquals("2023年07月", manager.getTitle());
	}

	/**
	 * 翌月へシフト
	 */
	@Test
	public void setNextMonthWithOnce() {
		manager.setNextMonth();
		assertEquals("2023年11月", manager.getTitle());
	}

	/**
	 * 翌月へシフト
	 */
	@Test
	public void setNextMonthWithTwice() {
		manager.setNextMonth();
		manager.setNextMonth();
		assertEquals("2023年12月", manager.getTitle());
	}

	/**
	 * 翌月へシフト
	 */
	@Test
	public void setNextMonthWithThrice() {
		manager.setNextMonth();
		manager.setNextMonth();
		manager.setNextMonth();
		assertEquals("2024年01月", manager.getTitle());
	}

	/**
	 * 翌年へシフト
	 */
	@Test
	public void setNextYearWithOnce() {
		manager.setNextYear();
		assertEquals("2024年10月", manager.getTitle());
	}

	/**
	 * 翌年へシフト
	 */
	@Test
	public void setNextYearWithTwice() {
		manager.setNextYear();
		manager.setNextYear();
		assertEquals("2025年10月", manager.getTitle());
	}

	/**
	 * 翌年へシフト
	 */
	@Test
	public void setNextYearWithThrice() {
		manager.setNextYear();
		manager.setNextYear();
		manager.setNextYear();
		assertEquals("2026年10月", manager.getTitle());
	}
}
