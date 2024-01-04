// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.expense.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import abook.common.AbConstant.TYPE;
import abook.common.AbException;
import abook.expense.AbExpense;
import abook.expense.AbSummary;
import abook.expense.manager.AbSummaryManager;

/**
 * テスト:月次情報管理クラス
 */
public class AbSummaryManagerTest {

	/** 日付 */
	private static LocalDate date;

	/** 月次情報リスト */
	private static List<AbSummary> summaries;

	/** 月次情報管理クラス */
	private static AbSummaryManager manager;

	@BeforeAll
	public static void testFixtureSetup() throws AbException {
		List<AbExpense> expenses = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-10-01", "名称Ａ", TYPE.FOOD, "100", ""),
						new AbExpense("2023-10-02", "名称Ｂ", TYPE.FOOD, "100", ""),
						new AbExpense("2023-10-03", "名称Ｃ", TYPE.OTFD, "200", ""),
						new AbExpense("2023-10-04", "名称Ｄ", TYPE.OTFD, "200", ""),
						new AbExpense("2023-10-05", "名称Ｅ", TYPE.GOOD, "300", ""),
						new AbExpense("2023-10-06", "名称Ｆ", TYPE.GOOD, "300", ""),
						new AbExpense("2023-10-07", "名称Ｇ", TYPE.FRND, "400", ""),
						new AbExpense("2023-10-08", "名称Ｈ", TYPE.FRND, "400", ""),
						new AbExpense("2023-10-09", "名称Ｉ", TYPE.TRFC, "450", ""),
						new AbExpense("2023-10-10", "名称Ｊ", TYPE.TRFC, "450", ""),
						new AbExpense("2023-10-11", "名称Ｋ", TYPE.PLAY, "475", ""),
						new AbExpense("2023-10-12", "名称Ｌ", TYPE.PLAY, "475", ""),
						new AbExpense("2023-10-13", "名称Ｍ", TYPE.HOUS, "30000", ""),
						new AbExpense("2023-10-14", "電気代", TYPE.ENGY, "1000", ""),
						new AbExpense("2023-10-15", "ガス代", TYPE.ENGY, "3000", ""),
						new AbExpense("2023-10-16", "水道代", TYPE.ENGY, "2000", ""),
						new AbExpense("2023-10-17", "名称Ｎ", TYPE.CNCT, "1800", ""),
						new AbExpense("2023-10-18", "名称Ｏ", TYPE.MEDI, "2400", ""),
						new AbExpense("2023-10-19", "名称Ｐ", TYPE.INSU, "3200", ""),
						new AbExpense("2023-10-20", "名称Ｑ", TYPE.OTHR, "150", ""),
						new AbExpense("2023-10-21", "名称Ｒ", TYPE.OTHR, "150", ""),
						new AbExpense("2023-10-22", "名称Ｓ", TYPE.EARN, "100000", ""),
						new AbExpense("2023-10-23", "名称Ｔ", TYPE.EARN, "100000", ""),
						new AbExpense("2023-10-24", "名称Ｕ", TYPE.BNUS, "55000", ""),
						new AbExpense("2023-10-25", "名称Ｖ", TYPE.BNUS, "55000", ""),
						new AbExpense("2023-10-26", "名称Ｗ", TYPE.SPCL, "4400", ""),
						new AbExpense("2023-10-27", "名称Ｘ", TYPE.SPCL, "4400", ""),
						new AbExpense("2023-10-28", "名称Ｙ", TYPE.PRVI, "1800", ""),
						new AbExpense("2023-10-29", "名称Ｚ", TYPE.PRVI, "1800", ""),
						new AbExpense("2023-10-30", "名称１", TYPE.PRVO, "1400", ""),
						new AbExpense("2023-10-31", "名称２", TYPE.PRVO, "1400", ""),
						new AbExpense("2022-10-01", "名称Ａ", TYPE.FOOD, "1100", ""),
						new AbExpense("2022-10-01", "名称Ｂ", TYPE.EARN, "11000", ""),
						new AbExpense("2021-10-01", "名称Ｃ", TYPE.FOOD, "1200", ""),
						new AbExpense("2021-10-01", "名称Ｄ", TYPE.EARN, "12000", ""),
						new AbExpense("2020-10-01", "名称Ｅ", TYPE.FOOD, "1300", ""),
						new AbExpense("2020-10-01", "名称Ｆ", TYPE.EARN, "13000", ""),
						new AbExpense("2023-09-01", "名称Ａ", TYPE.FOOD, "1110", ""),
						new AbExpense("2023-09-01", "名称Ｂ", TYPE.EARN, "11100", ""),
						new AbExpense("2023-08-01", "名称Ｃ", TYPE.FOOD, "1220", ""),
						new AbExpense("2023-08-01", "名称Ｄ", TYPE.EARN, "12200", ""),
						new AbExpense("2023-07-01", "名称Ｅ", TYPE.FOOD, "1330", ""),
						new AbExpense("2023-07-01", "名称Ｆ", TYPE.EARN, "13300", ""),
						new AbExpense("2023-11-01", "名称Ａ", TYPE.FOOD, "1111", ""),
						new AbExpense("2023-11-01", "名称Ｂ", TYPE.EARN, "11110", ""),
						new AbExpense("2023-12-01", "名称Ｃ", TYPE.FOOD, "1222", ""),
						new AbExpense("2023-12-01", "名称Ｄ", TYPE.EARN, "12220", ""),
						new AbExpense("2024-01-01", "名称Ｅ", TYPE.FOOD, "1333", ""),
						new AbExpense("2024-01-01", "名称Ｆ", TYPE.EARN, "13330", ""),
						new AbExpense("2024-10-01", "名称Ａ", TYPE.FOOD, "10000", ""),
						new AbExpense("2024-10-01", "名称Ｂ", TYPE.EARN, "30000", ""),
						new AbExpense("2025-10-01", "名称Ｃ", TYPE.FOOD, "20000", ""),
						new AbExpense("2025-10-01", "名称Ｄ", TYPE.EARN, "50000", ""),
						new AbExpense("2026-10-01", "名称Ｅ", TYPE.FOOD, "30000", ""),
						new AbExpense("2026-10-01", "名称Ｆ", TYPE.EARN, "70000", "")
				)
		);
		summaries = AbSummaryManager.createSummaries(expenses);
	}

	@BeforeEach
	public void setup() throws AbException {
		date = LocalDate.of(2023, 10, 1);
		manager = new AbSummaryManager(date, summaries);
	}

	/**
	 * コンストラクタ
	 * 引数:日付がNULL
	 */
	@Test
	public void AbSummaryManagerWithDateNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> new AbSummaryManager(null, summaries)
		);
	}

	/**
	 * コンストラクタ
	 * 引数:月次情報リストがNULL
	 */
	@Test
	public void AbSummaryManagerWithSummariesNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> new AbSummaryManager(date, null)
		);
	}

	/**
	 * コンストラクタ
	 * 引数:月次情報リストが空
	 */
	@Test
	public void AbSummaryManagerWithSummariesEmpty() {
		assertDoesNotThrow(() -> new AbSummaryManager(date, new ArrayList<AbSummary>()));
	}

	/**
	 * 現在の月次情報の日付
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
	 * 金額取得
	 * 引数:種別がNULL
	 */
	@Test
	public void getCostWithTypeNull() {
		assertEquals(0, manager.getCost(null));
	}

	/**
	 * 金額取得
	 * 引数:種別が空
	 */
	@Test
	public void getCostWithTypeEmpty() {
		assertEquals(0, manager.getCost(""));
	}

	/**
	 * 金額取得
	 * 引数:種別が存在しない
	 */
	@Test
	public void getCostWithTypeNotMatch() {
		assertEquals(0, manager.getCost("not match"));
	}

	/**
	 * 金額取得
	 * 
	 * @param type     種別
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"食費　,    200",
			"外食費,    400",
			"雑貨　,    600",
			"交際費,    800",
			"交通費,    900",
			"遊行費,    950",
			"家賃　,  30000",
			"光熱費,   6000",
			"通信費,   1800",
			"医療費,   2400",
			"保険料,   3200",
			"その他,    300",
			"収入　, 200000",
			"特入　, 110000",
			"特出　,   8800",
			"秘密入,   3600",
			"秘密出,   2800",
	})
	public void getCost(String type, int expected) {
		assertEquals(expected, manager.getCost(type.strip()));
	}

	/**
	 * 金額取得
	 * 合計は月次情報の対象種別のみ
	 */
	@Test
	public void getCostWithTtal() {
		assertEquals(47550, manager.getCost("合計"));
	}

	/**
	 * 金額取得
	 * 収支 = 収入 - 合計
	 */
	@Test
	public void getCostWithBlnc() {
		int expected = 200000 - 47550;
		assertEquals(expected, manager.getCost("収支"));
	}

	/**
	 * 前年へ切り替え
	 */
	@Test
	public void setPrevYearWithOnce() {
		manager.setPrevYear();
		assertEquals("2022年10月", manager.getTitle());
		assertEquals( 1100, manager.getCost("食費"));
		assertEquals(11000, manager.getCost("収入"));
		assertEquals( 1100, manager.getCost("合計"));
		assertEquals( 9900, manager.getCost("収支"));
	}

	/**
	 * 前年へ切り替え
	 */
	@Test
	public void setPrevYearWithTwice() {
		manager.setPrevYear();
		manager.setPrevYear();
		assertEquals("2021年10月", manager.getTitle());
		assertEquals( 1200, manager.getCost("食費"));
		assertEquals(12000, manager.getCost("収入"));
		assertEquals( 1200, manager.getCost("合計"));
		assertEquals(10800, manager.getCost("収支"));
	}

	/**
	 * 前年へ切り替え
	 */
	@Test
	public void setPrevYearWithThrice() {
		manager.setPrevYear();
		manager.setPrevYear();
		manager.setPrevYear();
		assertEquals("2020年10月", manager.getTitle());
		assertEquals( 1300, manager.getCost("食費"));
		assertEquals(13000, manager.getCost("収入"));
		assertEquals( 1300, manager.getCost("合計"));
		assertEquals(11700, manager.getCost("収支"));
	}

	/**
	 * 前月へ切り替え
	 */
	@Test
	public void setPrevMonthWithOnce() {
		manager.setPrevMonth();
		assertEquals("2023年09月", manager.getTitle());
		assertEquals( 1110, manager.getCost("食費"));
		assertEquals(11100, manager.getCost("収入"));
		assertEquals( 1110, manager.getCost("合計"));
		assertEquals( 9990, manager.getCost("収支"));
	}

	/**
	 * 前月へ切り替え
	 */
	@Test
	public void setPrevMonthWithTwice() {
		manager.setPrevMonth();
		manager.setPrevMonth();
		assertEquals("2023年08月", manager.getTitle());
		assertEquals( 1220, manager.getCost("食費"));
		assertEquals(12200, manager.getCost("収入"));
		assertEquals( 1220, manager.getCost("合計"));
		assertEquals(10980, manager.getCost("収支"));
	}

	/**
	 * 前月へ切り替え
	 */
	@Test
	public void setPrevMonthWithThrice() {
		manager.setPrevMonth();
		manager.setPrevMonth();
		manager.setPrevMonth();
		assertEquals("2023年07月", manager.getTitle());
		assertEquals( 1330, manager.getCost("食費"));
		assertEquals(13300, manager.getCost("収入"));
		assertEquals( 1330, manager.getCost("合計"));
		assertEquals(11970, manager.getCost("収支"));
	}

	/**
	 * 翌月へ切り替え
	 */
	@Test
	public void setNextMonthWithOnce() {
		manager.setNextMonth();
		assertEquals("2023年11月", manager.getTitle());
		assertEquals( 1111, manager.getCost("食費"));
		assertEquals(11110, manager.getCost("収入"));
		assertEquals( 1111, manager.getCost("合計"));
		assertEquals( 9999, manager.getCost("収支"));
	}

	/**
	 * 翌月へ切り替え
	 */
	@Test
	public void setNextMonthWithTwice() {
		manager.setNextMonth();
		manager.setNextMonth();
		assertEquals("2023年12月", manager.getTitle());
		assertEquals( 1222, manager.getCost("食費"));
		assertEquals(12220, manager.getCost("収入"));
		assertEquals( 1222, manager.getCost("合計"));
		assertEquals(10998, manager.getCost("収支"));
	}

	/**
	 * 翌月へ切り替え
	 */
	@Test
	public void setNextMonthWithThrice() {
		manager.setNextMonth();
		manager.setNextMonth();
		manager.setNextMonth();
		assertEquals("2024年01月", manager.getTitle());
		assertEquals( 1333, manager.getCost("食費"));
		assertEquals(13330, manager.getCost("収入"));
		assertEquals( 1333, manager.getCost("合計"));
		assertEquals(11997, manager.getCost("収支"));
	}

	/**
	 * 翌年へ切り替え
	 */
	@Test
	public void setNextYearWithOnce() {
		manager.setNextYear();
		assertEquals("2024年10月", manager.getTitle());
		assertEquals(10000, manager.getCost("食費"));
		assertEquals(30000, manager.getCost("収入"));
		assertEquals(10000, manager.getCost("合計"));
		assertEquals(20000, manager.getCost("収支"));
	}

	/**
	 * 翌年へ切り替え
	 */
	@Test
	public void setNextYearWithTwice() {
		manager.setNextYear();
		manager.setNextYear();
		assertEquals("2025年10月", manager.getTitle());
		assertEquals(20000, manager.getCost("食費"));
		assertEquals(50000, manager.getCost("収入"));
		assertEquals(20000, manager.getCost("合計"));
		assertEquals(30000, manager.getCost("収支"));
	}

	/**
	 * 翌年へ切り替え
	 */
	@Test
	public void setNextYearWithThrice() {
		manager.setNextYear();
		manager.setNextYear();
		manager.setNextYear();
		assertEquals("2026年10月", manager.getTitle());
		assertEquals(30000, manager.getCost("食費"));
		assertEquals(70000, manager.getCost("収入"));
		assertEquals(30000, manager.getCost("合計"));
		assertEquals(40000, manager.getCost("収支"));
	}

	/**
	 * 月次情報リスト生成
	 * 引数:支出情報リストがNULL
	 */
	@Test
	public void createSummariesWithExpensesNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> AbSummaryManager.createSummaries(null)
		);
	}

	/**
	 * 月次情報リスト生成
	 * 引数:支出情報リストが空
	 */
	@Test
	public void createSummariesWithExpensesEmpty() {
		var result = assertDoesNotThrow(
				() -> AbSummaryManager.createSummaries(new ArrayList<AbExpense>())
		);
		assertEquals(0, result.size());
	}
}
