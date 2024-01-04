// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.expense;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import abook.common.AbConstant.TYPE;
import abook.common.AbException;
import abook.expense.AbComplete;
import abook.expense.AbExpense;

/**
 * テスト:自動補完クラス
 */
public class AbCompleteTest {

	/** 自動補完クラス */
	private static AbComplete complete;

	@BeforeAll
	public static void testFixtureSetup() throws AbException {
		List<AbExpense> expenses = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-10-01", "name1", TYPE.FOOD, "100", ""),
						new AbExpense("2023-10-01", "name2", TYPE.OTFD, "200", ""),
						new AbExpense("2023-10-01", "name2", TYPE.OTFD, "200", ""),
						new AbExpense("2023-10-02", "name3", TYPE.FOOD, "300", ""),
						new AbExpense("2023-10-02", "name3", TYPE.GOOD, "400", ""),
						new AbExpense("2023-10-03", "name3", TYPE.GOOD, "400", ""),
						new AbExpense("2023-10-04", "name4", TYPE.FRND, "501", ""),
						new AbExpense("2023-10-05", "name4", TYPE.FRND, "502", ""),
						new AbExpense("2023-10-06", "name4", TYPE.FRND, "503", ""),
						new AbExpense("2023-10-04", "name4", TYPE.TRFC, "600", ""),
						new AbExpense("2023-10-05", "name4", TYPE.TRFC, "600", ""),
						new AbExpense("2023-10-04", "name4", TYPE.PLAY, "700", ""),
						new AbExpense("2023-10-05", "name4", TYPE.PLAY, "700", ""),
						new AbExpense("2023-10-06", "name5", TYPE.GOOD, "100", ""),
						new AbExpense("2023-10-07", "name5", TYPE.GOOD, "100", ""),
						new AbExpense("2023-10-08", "name5", TYPE.HOUS, "200", ""),
						new AbExpense("2023-10-09", "name5", TYPE.HOUS, "200", ""),
						new AbExpense("2023-10-10", "name6", TYPE.ENGY, "300", ""),
						new AbExpense("2023-10-11", "name6", TYPE.ENGY, "301", ""),
						new AbExpense("2023-10-12", "name6", TYPE.ENGY, "302", ""),
						new AbExpense("2023-10-10", "name6", TYPE.CNCT, "401", ""),
						new AbExpense("2023-10-31", "name6", TYPE.CNCT, "402", ""),
						new AbExpense("2023-10-12", "name6", TYPE.CNCT, "403", ""),
						new AbExpense("2023-10-10", "name6", TYPE.MEDI, "500", ""),
						new AbExpense("2023-10-11", "name6", TYPE.MEDI, "500", ""),
						new AbExpense("2023-10-12", "name6", TYPE.MEDI, "500", "")
				)
		);
		complete = new AbComplete(expenses);
	}

	/**
	 * コンストラクタ
	 * 引数:支出情報リストがNULL
	 */
	@Test
	public void abCompleteWithExpenseNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> new AbComplete(null)
		);
	}

	/**
	 * コンストラクタ
	 * 引数:支出情報リストが空
	 */
	@Test
	public void abCompleteWithExpenseEmpty() {
		assertDoesNotThrow(() -> new AbComplete(new ArrayList<AbExpense>()));
	}

	/**
	 * 種別取得
	 * 引数:名称がNULL
	 */
	@Test
	public void getTypeWithNameNull() {
		assertEquals("", complete.getType(null));
	}

	/**
	 * 種別取得
	 * 引数:名称が空
	 */
	@Test
	public void getTypeWithNameEmpty() {
		assertEquals("", complete.getType(""));
	}

	/**
	 * 種別取得
	 * 引数:名称が存在しない
	 */
	@Test
	public void getTypeWithNameNotMatch() {
		assertEquals("", complete.getType("not match"));
	}

	/**
	 * 種別取得
	 * 名称と種別が1対1
	 * 
	 * @param name     名称
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"name1, 食費",
			"name2, 外食費",
	})
	public void getTypeWithSingleType(String name, String expected) {
		assertEquals(expected, complete.getType(name));
	}

	/**
	 * 種別取得
	 * 名称と種別が1対N
	 * 
	 * @param name     名称
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"name3, 雑貨",
			"name4, 交際費",
	})
	public void getTypeWithMultipleType(String name, String expected) {
		// 登録回数の多い種別が返る
		assertEquals(expected, complete.getType(name));
	}

	/**
	 * 種別取得
	 * 名称と種別が1対Nで種別の登録回数が同数
	 * 
	 * @param name     名称
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"name5, 家賃",
			"name6, 通信費",
	})
	public void getTypeWithMultipleTypeAndSameCount(String name, String expected) {
		// 最新の種別が返る
		assertEquals(expected, complete.getType(name));
	}

	/**
	 * 金額取得
	 * 引数:名称がNULL
	 */
	@Test
	public void getCostWithNameNull() {
		assertNull(complete.getCost(null, "食費"));
	}

	/**
	 * 金額取得
	 * 引数:名称が空
	 */
	@Test
	public void getCostWithNameEmpty() {
		assertNull(complete.getCost("", "食費"));
	}

	/**
	 * 金額取得
	 * 引数:種別がNULL
	 */
	@Test
	public void getCostWithTypeNull() {
		assertNull(complete.getCost("name1", null));

	}

	/**
	 * 金額取得
	 * 引数:種別が空
	 */
	@Test
	public void getCostWithTypeEmpty() {
		assertNull(complete.getCost("name1", ""));

	}

	/**
	 * 金額取得
	 * 引数:名称が存在しない
	 */
	@Test
	public void getCostWithNameNotMatch() {
		assertNull(complete.getCost("not match", "食費"));
	}

	/**
	 * 金額取得
	 * 引数:種別が存在しない
	 */
	@Test
	public void getCostWithTypeNotMatch() {
		assertNull(complete.getCost("name1", "not match"));
	}

	/**
	 * 金額取得
	 * 名称+種別と金額が1:1
	 * 
	 * @param name     名称
	 * @param type     種別
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"name1, 食費, 100",
			"name3, 食費, 300",
			"name3, 雑貨, 400",
	})
	public void getCostWithSingleCost(String name, String type, Integer expected) {
		assertEquals(expected, complete.getCost(name, type));
	}

	/**
	 * 金額取得
	 * 名称+種別と金額が1対N
	 * 
	 * @param name     名称
	 * @param type     種別
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"name4, 交際費, 503",
			"name6, 光熱費, 302",
			"name6, 通信費, 402",
	})
	public void getCostWithMultipleCost(String name, String type, Integer expected) {
		assertEquals(expected, complete.getCost(name, type));
	}
}
