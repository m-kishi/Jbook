// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.expense;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import abook.common.AbConstant.TYPE;
import abook.common.AbException;
import abook.expense.AbExpense;
import abook.expense.AbSummary;

/**
 * テスト:月次情報クラス
 */
public class AbSummaryTest {

	/** 日付 */
	private static LocalDate date;

	/** 支出情報リスト */
	private static List<AbExpense> expenses;

	/** 月次情報 */
	private static AbSummary summary;

	@BeforeAll
	public static void testFixtureSetup() throws AbException {
		expenses = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-10-01", "名称Ａ", TYPE.FOOD, "100", ""),
						new AbExpense("2023-10-01", "名称Ａ", TYPE.FOOD, "100", ""),
						new AbExpense("2023-10-01", "名称Ｂ", TYPE.OTFD, "200", ""),
						new AbExpense("2023-10-01", "名称Ｂ", TYPE.OTFD, "300", ""),
						new AbExpense("2023-10-01", "名称Ｃ", TYPE.GOOD, "400", ""),
						new AbExpense("2023-10-01", "名称Ｃ", TYPE.GOOD, "400", ""),
						new AbExpense("2023-10-01", "名称Ｄ", TYPE.FRND, "500", ""),
						new AbExpense("2023-10-01", "名称Ｄ", TYPE.FRND, "600", ""),
						new AbExpense("2023-10-01", "名称Ｅ", TYPE.TRFC, "700", ""),
						new AbExpense("2023-10-01", "名称Ｅ", TYPE.TRFC, "700", ""),
						new AbExpense("2023-10-01", "名称Ｆ", TYPE.PLAY, "800", ""),
						new AbExpense("2023-10-01", "名称Ｆ", TYPE.PLAY, "900", ""),
						new AbExpense("2023-10-01", "名称Ｇ", TYPE.HOUS, "9999", ""),
						new AbExpense("2023-10-01", "電気代", TYPE.ENGY, "1000", ""),
						new AbExpense("2023-10-01", "ガス代", TYPE.ENGY, "2000", ""),
						new AbExpense("2023-10-01", "水道代", TYPE.ENGY, "3000", ""),
						new AbExpense("2023-10-01", "名称Ｈ", TYPE.CNCT, "100", ""),
						new AbExpense("2023-10-01", "名称Ｉ", TYPE.MEDI, "200", ""),
						new AbExpense("2023-10-01", "名称Ｊ", TYPE.INSU, "300", ""),
						new AbExpense("2023-10-01", "名称Ｋ", TYPE.OTHR, "400", ""),
						new AbExpense("2023-10-01", "名称Ｌ", TYPE.EARN, "50000", ""),
						new AbExpense("2023-10-01", "名称Ｌ", TYPE.EARN, "50000", ""),
						new AbExpense("2023-10-01", "名称Ｍ", TYPE.BNUS, "3000", ""),
						new AbExpense("2023-10-01", "名称Ｍ", TYPE.BNUS, "3000", ""),
						new AbExpense("2023-10-01", "名称Ｎ", TYPE.SPCL, "2000", ""),
						new AbExpense("2023-10-01", "名称Ｎ", TYPE.SPCL, "2000", ""),
						new AbExpense("2023-10-01", "名称Ｏ", TYPE.PRVI, "7000", ""),
						new AbExpense("2023-10-01", "名称Ｏ", TYPE.PRVI, "7000", ""),
						new AbExpense("2023-10-01", "名称Ｐ", TYPE.PRVO, "3000", ""),
						new AbExpense("2023-10-01", "名称Ｐ", TYPE.PRVO, "3000", "")
				)
		);

		date = LocalDate.of(2023, 10, 1);
		summary = new AbSummary(date, expenses);
	}

	/**
	 * コンストラクタ
	 * 引数:日付がNULL
	 */
	@Test
	public void AbSummaryWithDateNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> new AbSummary(null, expenses)
		);
	}

	/**
	 * コンストラクタ
	 * 引数:支出情報リストがNULL
	 */
	@Test
	public void AbSummaryWithExpenseNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> new AbSummary(date, null)
		);
	}

	/**
	 * コンストラクタ
	 * 引数:支出情報リストが空
	 */
	@Test
	public void AbSummaryWithExpenseEmpty() {
		assertDoesNotThrow(() -> new AbSummary(date, new ArrayList<AbExpense>()));
	}

	/**
	 * getter(年)
	 */
	@Test
	public void getYear() {
		assertEquals(2023, summary.getYear());
	}

	/**
	 * getter(月)
	 */
	@Test
	public void getMonth() {
		assertEquals(10, summary.getMonth());
	}

	/**
	 * 合計を取得
	 * 引数:種別がNULL
	 */
	@Test
	public void getCostByTypeWithTypeNull() {
		assertEquals(0, summary.getCostByType(null));
	}

	/**
	 * 合計を取得
	 * 引数:種別が空
	 */
	@Test
	public void getCostByTypeWithTypeEmpty() {
		assertEquals(0, summary.getCostByType(""));
	}

	/**
	 * 合計を取得
	 * 引数:種別が存在しない
	 */
	@Test
	public void getCostByTypeWithTypeNotMatch() {
		assertEquals(0, summary.getCostByType("not match"));
	}

	/**
	 * 合計を取得
	 * 
	 * @param type     種別
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"食費　,    200",
			"外食費,    500",
			"雑貨　,    800",
			"交際費,   1100",
			"交通費,   1400",
			"遊行費,   1700",
			"家賃　,   9999",
			"光熱費,   6000",
			"通信費,    100",
			"医療費,    200",
			"保険料,    300",
			"その他,    400",
			"収入　, 100000",
			"特入　,   6000",
			"特出　,   4000",
			"秘密入,  14000",
			"秘密出,   6000",
	})
	public void getCostByType(String type, int expected) {
		assertEquals(expected, summary.getCostByType(type.strip()));
	}

	/**
	 * 合計を取得
	 * 合計は月次情報の対象種別のみ
	 */
	@Test
	public void getCostByTypeWithTtal() {
		assertEquals(22699, summary.getCostByType("合計"));
	}

	/**
	 * 合計を取得
	 * 収支 = 収入 - 合計
	 */
	@Test
	public void getCostByTypeWithBlnc() {
		int expected = 100000 - 22699;
		assertEquals(expected, summary.getCostByType("収支"));
	}

	/**
	 * 合計を取得
	 * 引数:名称がNULL
	 */
	@Test
	public void getCostByNameWithNameNull() {
		assertEquals(0, summary.getCostByName(null));
	}

	/**
	 * 合計を取得
	 * 引数:名称が空
	 */
	@Test
	public void getCostByNameWithNameEmpty() {
		assertEquals(0, summary.getCostByName(""));
	}

	/**
	 * 合計を取得
	 * 引数:名称が存在しない
	 */
	public void getCostByNameWithNameNotMatch() {
		assertEquals(0, summary.getCostByName("not match"));
	}

	/**
	 * 合計を取得
	 * 光熱費の確認
	 * 
	 * @param name     名称
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"電気代, 1000",
			"ガス代, 2000",
			"水道代, 3000",
	})
	public void getCostByNameWithEngy(String name, int expected) {
		assertEquals(expected, summary.getCostByName(name));
	}

	/**
	 * 合計を取得
	 * 光熱費以外の種別は0
	 * 
	 * @param name 名称
	 */
	@ParameterizedTest
	@ValueSource(strings = { "名称Ａ", "名称Ｂ", "名称Ｃ" })
	public void getCostByNameWithoutEngy(String name) {
		assertEquals(0, summary.getCostByName(name));
	}
}
