// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import abook.common.AbConstant.FMT;
import abook.common.AbUtility.UTL;

/**
 * テスト:ユーティリティクラス
 */
public class AbUtilityTest {

	/**
	 * 空文字判定
	 * 引数:文字列がNULL
	 */
	@Test
	public void isEmptyWithStringNull() {
		assertTrue(UTL.isEmpty(null));
	}

	/**
	 * 空文字判定
	 * 引数:文字列が空
	 */
	@Test
	public void isEmptyWithStringEmpty() {
		assertTrue(UTL.isEmpty(""));
	}

	/**
	 * 空文字判定
	 * 
	 * @param string 文字列
	 */
	@ParameterizedTest
	@ValueSource(strings = { "2023-10-01", "name", "食費", "100", "note" })
	public void isEmpty(String string) {
		assertFalse(UTL.isEmpty(string));
	}

	/**
	 * 日付から文字列形式への変換
	 * 引数:日付がNULL
	 */
	@Test
	public void toStringWithDateNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> UTL.toString(null)
		);
	}

	/**
	 * 日付から文字列形式への変換
	 * 
	 * @param string 日付
	 * @throws DateTimeParseException
	 */
	@ParameterizedTest
	@ValueSource(strings = { "2023-10-01", "2023-12-31", "2024-02-29" })
	public void toString(String string) throws DateTimeParseException {
		LocalDate date = LocalDate.parse(string, DateTimeFormatter.ofPattern(FMT.DATE));
		assertEquals(string, UTL.toString(date));
	}

	/**
	 * 日付から年月形式への変換
	 * 引数:日付がNULL
	 */
	@Test
	public void toMonthlyWithDateNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> UTL.toMonthly(null)
		);
	}

	/**
	 * 日付から年月形式への変換
	 * 
	 * @param date     日付
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"2023-10-01, 2023-10",
			"2023-12-31, 2023-12",
			"2024-02-29, 2024-02"
	})
	public void toMonthly(LocalDate date, String expected) {
		assertEquals(expected, UTL.toMonthly(date));
	}

	/**
	 * 日付から月形式へ変換
	 */
	@Test
	public void toMonthWithDateNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> UTL.toMonth(null)
		);
	}

	/**
	 * 日付から月形式へ変換
	 * 
	 * @param date     日付
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"2023-10-01, 10",
			"2023-12-31, 12",
			"2024-02-29, 02"
	})
	public void toMonth(LocalDate date, String expected) {
		assertEquals(expected, UTL.toMonth(date));
	}

	/**
	 * 日付からタイトル文字列へ変換
	 */
	@Test
	public void toTitleWithDateNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> UTL.toTitle(null)
		);
	}

	/**
	 * 日付からタイトル文字列へ変換
	 * 
	 * @param date     日付
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"2023-10-01, 2023年10月",
			"2023-12-31, 2023年12月",
			"2024-02-29, 2024年02月"
	})
	public void toTitle(LocalDate date, String expected) {
		assertEquals(expected, UTL.toTitle(date));
	}

	/**
	 * 文字列から日付へ変換
	 * 引数:文字列がNULL
	 */
	@Test
	public void toLocalDateWithStringNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> UTL.toLocalDate(null)
		);
	}

	/**
	 * 文字列から日付へ変換
	 * 引数:文字列が空
	 */
	@Test
	public void toLocalDateWithStringEmpty() {
		// アプリでは処理しない
		assertThrows(DateTimeParseException.class,
				() -> UTL.toLocalDate("")
		);
	}

	/**
	 * 文字列から日付へ変換
	 * 引数:文字列の形式が不正
	 */
	@Test
	public void toLocalDateWithInvalidFormat() {
		// アプリでは処理しない
		assertThrows(DateTimeParseException.class,
				() -> UTL.toLocalDate("invalid format")
		);
	}

	/**
	 * 日付からタイトル文字列へ変換
	 * 
	 * @param expected 期待値
	 * @param string   文字列
	 */
	@ParameterizedTest
	@CsvSource({
			"2023-10-01, 2023-10-01",
			"2023-12-31, 2023-12-31",
			"2024-02-29, 2024-02-29"
	})
	public void toLocalDate(LocalDate expected, String string) {
		assertEquals(expected, UTL.toLocalDate(string));
	}

	/**
	 * 金額を通貨形式へ変換
	 * 引数:金額がNULL
	 */
	@Test
	public void toCurrencyWithCostNull() {
		assertNull(UTL.toCurrency(null, Locale.getDefault()));
	}

	/**
	 * 金額を通貨形式へ変換
	 * 引数:金額が正しくない
	 */
	@Test
	public void toCurrencyWithLocaleInvalid() {
		assertNull(UTL.toCurrency("invalid", Locale.getDefault()));
	}

	/**
	 * 金額を通貨形式へ変換
	 * 
	 * @param cost     金額
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource(delimiterString = "¥t", value = {
			"100¥t￥100",
			"1000¥t￥1,000",
			"1000000¥t￥1,000,000"
	})
	public void toCurrency(int cost, String expected) {
		assertEquals(expected, UTL.toCurrency(cost, Locale.getDefault()));
	}

	/**
	 * ツールチップのための円マーク変換
	 * 引数:備考がNULL
	 */
	@Test
	public void replayYenMarkWithNoteNull() {
		assertEquals("", UTL.replaceYenMark(null));
	}

	/**
	 * ツールチップのための円マーク変換
	 * 引数:備考が空
	 */
	@Test
	public void replayYenMarkWithNoteEmpty() {
		assertEquals("", UTL.replaceYenMark(""));
	}

	/**
	 * ツールチップのための円マーク変換
	 * 
	 * @param note     備考
	 * @param expected 期待値
	 */
	@ParameterizedTest
	@CsvSource({
			"¥, &yen;",
			"¥¥¥, &yen;&yen;&yen;",
			"¥100 ¥200 ¥300, &yen;100 &yen;200 &yen;300"
	})
	public void replayYenMark(String note, String expected) {
		assertEquals(expected, UTL.replaceYenMark(note));
	}
}
