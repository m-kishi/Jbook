// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.expense;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import abook.common.AbConstant.TYPE;
import abook.common.AbException;
import abook.common.AbManager.MESSAGE;
import abook.common.AbUtility.UTL;
import abook.expense.AbExpense;

/**
 * テスト:支出情報クラス
 */
public class AbExpenseTest {

	/** 日付 */
	private static String date;

	/** 名称 */
	private static String name;

	/** 種別 */
	private static String type;

	/** 金額 */
	private static String cost;

	/** 備考 */
	private static String note;

	/** 支出情報 */
	private static AbExpense expense;

	@BeforeEach
	public void setup() throws AbException {
		date = "2023-10-01";
		name = "name";
		type = "食費";
		cost = "100";
		note = "note";
		expense = new AbExpense(date, name, type, cost, note);
	}

	/**
	 * コンストラクタ
	 * 引数:日付がNULL
	 */
	@Test
	public void abExpenseWithDateNull() {
		AbException ex = assertThrows(AbException.class,
				() -> new AbExpense(null, name, type, cost, note)
		);
		assertEquals(MESSAGE.DATE_NULL, ex.getMessage());
	}

	/**
	 * コンストラクタ
	 * 引数:日付が空
	 */
	@Test
	public void abExpenseWithDateEmpty() {
		AbException ex = assertThrows(AbException.class,
				() -> new AbExpense("", name, type, cost, note)
		);
		assertEquals(MESSAGE.DATE_NULL, ex.getMessage());
	}

	/**
	 * コンストラクタ
	 * 引数:日付が正しくない
	 */
	@Test
	public void abExpenseWithDateInvalid() {
		AbException ex = assertThrows(AbException.class,
				() -> new AbExpense("2023-99-99", name, type, cost, note)
		);
		assertEquals(MESSAGE.DATE_FORMAT, ex.getMessage());
	}

	/**
	 * コンストラクタ
	 * 引数:名称がNULL
	 */
	@Test
	public void abExpenseWithNameNull() {
		AbException ex = assertThrows(AbException.class,
				() -> new AbExpense(date, null, type, cost, note)
		);
		assertEquals(MESSAGE.NAME_NULL, ex.getMessage());
	}

	/**
	 * コンストラクタ
	 * 引数:名称が空
	 */
	@Test
	public void abExpenseWithNameEmpty() {
		AbException ex = assertThrows(AbException.class,
				() -> new AbExpense(date, "", type, cost, note)
		);
		assertEquals(MESSAGE.NAME_NULL, ex.getMessage());
	}

	/**
	 * コンストラクタ
	 * 引数:種別がNULL
	 */
	@Test
	public void abExpenseWithTypeNull() {
		AbException ex = assertThrows(AbException.class,
				() -> new AbExpense(date, name, null, cost, note)
		);
		assertEquals(MESSAGE.TYPE_NULL, ex.getMessage());
	}

	/**
	 * コンストラクタ
	 * 引数:種別が空
	 */
	@Test
	public void abExpenseWithTypeEmpty() {
		AbException ex = assertThrows(AbException.class,
				() -> new AbExpense(date, name, "", cost, note)
		);
		assertEquals(MESSAGE.TYPE_NULL, ex.getMessage());
	}

	/**
	 * コンストラクタ
	 * 
	 * @param type 種別
	 */
	@ParameterizedTest
	@ValueSource(strings = {
			"食費",
			"外食費",
			"雑貨",
			"交際費",
			"交通費",
			"遊行費",
			"家賃",
			"光熱費",
			"通信費",
			"医療費",
			"保険料",
			"その他",
			"収入",
			"合計",
			"収支",
			"特入",
			"特支",
			"秘密入",
			"秘密出",
			"invalid"
	})
	public void abExpenseWithTypeWrong(String type) {
		if (TYPE.EXPENSES.contains(type)) {
			// 支出情報として指定可能な種別はエラーにならない
			assertDoesNotThrow(() -> new AbExpense(date, name, type, cost, note));
		} else {
			// 支出情報として指定不可能な種別はエラー
			AbException ex = assertThrows(AbException.class,
					() -> new AbExpense(date, name, type, cost, note)
			);
			// メッセージを確認
			assertEquals(MESSAGE.TYPE_WRONG, ex.getMessage());
		}
	}

	/**
	 * コンストラクタ
	 * 引数:金額がNULL
	 */
	@Test
	public void abExpenseWithCostNull() {
		AbException ex = assertThrows(AbException.class,
				() -> new AbExpense(date, name, type, null, note)
		);
		assertEquals(MESSAGE.COST_NULL, ex.getMessage());
	}

	/**
	 * コンストラクタ
	 * 引数:金額が空
	 */
	@Test
	public void abExpenseWithCostEmpty() {
		AbException ex = assertThrows(AbException.class,
				() -> new AbExpense(date, name, type, "", note)
		);
		assertEquals(MESSAGE.COST_NULL, ex.getMessage());
	}

	/**
	 * コンストラクタ
	 * 引数:金額が正しくない
	 */
	@Test
	public void abExpenseWithCostInvalid() {
		AbException ex = assertThrows(AbException.class,
				() -> new AbExpense(date, name, type, "invalid", note)
		);
		assertEquals(MESSAGE.COST_FORMAT, ex.getMessage());
	}

	/**
	 * コンストラクタ
	 * 引数:金額がマイナス
	 */
	@Test
	public void abExpenseWithCostMinus() {
		AbException ex = assertThrows(AbException.class,
				() -> new AbExpense(date, name, type, "-100", note)
		);
		assertEquals(MESSAGE.COST_MINUS, ex.getMessage());
	}

	/**
	 * コンストラクタ
	 * 引数:備考がNULL
	 * 
	 * @throws AbException
	 */
	@Test
	public void abExpenseWithNoteNull() throws AbException {
		AbExpense expense = assertDoesNotThrow(
				() -> new AbExpense(date, name, type, cost, "")
		);
		assertEquals("", expense.getNote());
	}

	/**
	 * コンストラクタ
	 * 引数:備考が空
	 * 
	 * @throws AbException
	 */
	@Test
	public void abExpenseWithNoteEmpty() throws AbException {
		AbExpense expense = assertDoesNotThrow(
				() -> new AbExpense(date, name, type, cost, "")
		);
		assertEquals("", expense.getNote());
	}

	/**
	 * コンストラクタ
	 * 
	 * @throws AbException
	 */
	@Test
	public void abExpenseWithDate() throws AbException {
		assertEquals("2023-10-01", UTL.toString(expense.getDate()));
	}

	/**
	 * コンストラクタ
	 * 
	 * @throws AbException
	 */
	@Test
	public void abExpenseWithName() throws AbException {
		assertEquals("name", expense.getName());
	}

	/**
	 * コンストラクタ
	 * 
	 * @throws AbException
	 */
	@Test
	public void abExpenseWithType() throws AbException {
		assertEquals("食費", expense.getType());
	}

	/**
	 * コンストラクタ
	 * 
	 * @throws AbException
	 */
	@Test
	public void abExpenseWithCost() throws AbException {
		assertEquals(100, expense.getCost());
	}

	/**
	 * コンストラクタ
	 * 
	 * @throws AbException
	 */
	@Test
	public void abExpenseWithNote() throws AbException {
		assertEquals("note", expense.getNote());
	}

	/**
	 * DBファイル出力形式(CSV)
	 * 
	 * @throws AbException
	 */
	@Test
	public void toDBFileFormat() throws AbException {
		String dbFileFormat = "\"2023-10-01\",\"name\",\"食費\",\"100\",\"note\"";
		assertEquals(dbFileFormat, expense.toDBFileFormat());
	}
}
