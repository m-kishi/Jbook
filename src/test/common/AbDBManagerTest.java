// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import abook.common.AbDBManager;
import abook.common.AbException;
import abook.common.AbManager.MESSAGE;
import abook.expense.AbExpense;
import junitx.framework.FileAssert;
import test.tool.AbTestTool;

/**
 * テスト:DBファイル管理クラス
 */
public class AbDBManagerTest {

	/** 支出情報リスト */
	private static List<String[]> expected;

	/** DBファイル */
	private static final String DB_FILE_NO_DATE = "AbDBManagerTestNoData.db";

	/** DBファイル */
	private static final String DB_FILE_IN_DATE = "AbDBManagerTestInData.db";

	/** DBファイル */
	private static final String DB_FILE_MORE_FIELD = "AbDBManagerTestMoreField.db";

	/** DBファイル */
	private static final String DB_FILE_LESS_FIELD = "AbDBManagerTestLessField.db";

	/** DBファイル */
	private static final String DB_FILE_INVALID_FORMAT = "AbDBManagerTestInvalidFormat.db";

	/** DBファイル */
	private static final String DB_FILE_OUT_DATE = "AbDBManagerTestOutData.db";

	/** DBファイル */
	private static final String DB_FILE_NOT_EXIST = "AbDBManagerTestNotExist.db";

	/** DBファイル */
	private static final String DB_FILE_INPUT_EMPTY = "AbDBManagerTestInputEmpty.db";

	/** DBファイル */
	private static final String DB_FILE_OUTPUT_EMPTY = "AbDBManagerTestOutputEmpty.db";

	/** DBファイル */
	private static final String DB_FILE_OUTPUT_NULL_EXPENSES = "AbDBManagerTestOutputNullExpenses.db";

	@BeforeAll
	public static void testFixtureSetup() throws AbException, IOException {

		expected = new ArrayList<String[]>() {{
				add(new String[] { "2023-10-01", "name1", "食費", "100", "note1" });
				add(new String[] { "2023-10-02", "name2", "雑貨", "200", "note2" });
				add(new String[] { "2023-10-03", "name3", "家賃", "300", "note3" });
		}};

		// DBファイルを作成
		AbTestTool.createDBFileFromParams(DB_FILE_IN_DATE, expected);
		AbTestTool.createMoreField(DB_FILE_MORE_FIELD);
		AbTestTool.createLessField(DB_FILE_LESS_FIELD);
		AbTestTool.createInvalidFormat(DB_FILE_INVALID_FORMAT);

		// DBファイルが存在するなら削除
		AbTestTool.deleteDBFile(DB_FILE_NO_DATE);
		AbTestTool.deleteDBFile(DB_FILE_OUT_DATE);
		AbTestTool.deleteDBFile(DB_FILE_NOT_EXIST);
		AbTestTool.deleteDBFile(DB_FILE_INPUT_EMPTY);
		AbTestTool.deleteDBFile(DB_FILE_OUTPUT_EMPTY);
		AbTestTool.deleteDBFile(DB_FILE_OUTPUT_NULL_EXPENSES);
	}

	@AfterAll
	public static void testFixtureTearDown() throws IOException {

		// DBファイルが存在するなら削除
		AbTestTool.deleteDBFile(DB_FILE_NO_DATE);
		AbTestTool.deleteDBFile(DB_FILE_IN_DATE);
		AbTestTool.deleteDBFile(DB_FILE_OUT_DATE);
		AbTestTool.deleteDBFile(DB_FILE_NOT_EXIST);
		AbTestTool.deleteDBFile(DB_FILE_MORE_FIELD);
		AbTestTool.deleteDBFile(DB_FILE_LESS_FIELD);
		AbTestTool.deleteDBFile(DB_FILE_INVALID_FORMAT);
		AbTestTool.deleteDBFile(DB_FILE_INPUT_EMPTY);
		AbTestTool.deleteDBFile(DB_FILE_OUTPUT_EMPTY);
		AbTestTool.deleteDBFile(DB_FILE_OUTPUT_NULL_EXPENSES);
	}

	/**
	 * DBファイル読み込み
	 * 引数:DBファイルパスがNULL
	 */
	@Test
	public void loadWithDBFilePathNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> AbDBManager.load(null)
		);
	}

	/**
	 * DBファイル読み込み
	 * 引数:DBファイルパスが空
	 */
	@Test
	public void loadWithDBFilePathEmpty() {

		// アプリでは処理しない
		AbException ex = assertThrows(AbException.class,
				() -> AbDBManager.load("")
		);

		// メッセージの確認
		String message = String.format(MESSAGE.DB_FILE_READ, " (No such file or directory)");
		assertEquals(message, ex.getMessage());
	}

	/**
	 * DBファイル読み込み
	 * 引数:DBファイルが存在しない
	 * 
	 * @throws AbException
	 */
	@Test
	public void loadWithNotExist() throws AbException {

		// DBファイルが存在しないことを確認
		Path path = Paths.get(DB_FILE_NOT_EXIST);
		assertTrue(Files.notExists(path));

		// DBファイルの読み込み
		List<AbExpense> expenses = AbDBManager.load(DB_FILE_NOT_EXIST);

		// 支出情報リストは0件
		assertEquals(0, expenses.size());

		// DBファイルが新規作成される
		assertTrue(Files.exists(path));
	}

	/**
	 * DBファイル読み込み
	 * DBファイルの作成に失敗
	 */
	@Test
	public void loadWithCreateError() {

		// DBファイルパス
		String dbFilePath = "/InvalidDirectory/Abook.db";

		// DBファイルが存在しないことを確認
		Path path = Paths.get(dbFilePath);
		assertTrue(Files.notExists(path));

		// DBファイルの作成に失敗
		AbException ex = assertThrows(AbException.class,
				() -> AbDBManager.load(dbFilePath)
		);

		// メッセージの確認
		String message = String.format(MESSAGE.DB_FILE_CREATE, "/InvalidDirectory/Abook.db");
		assertEquals(message, ex.getMessage());
	}

	/**
	 * DBファイル読み込み
	 * DBファイルのフィールド数が多い
	 */
	@Test
	public void loadWithMoreField() {

		// フィールド数が多い
		AbException ex = assertThrows(AbException.class,
				() -> AbDBManager.load(DB_FILE_MORE_FIELD)
		);

		// メッセージの確認
		String innerMessage = String.format(MESSAGE.DB_FILE_FIELD, AbDBManager.FIELD_SIZE);
		String message = String.format(MESSAGE.DB_FILE_LOAD, 1, innerMessage);
		assertEquals(message, ex.getMessage());
	}

	/**
	 * DBファイル読み込み
	 * DBファイルのフィールド数が少ない
	 */
	@Test
	public void loadWithLessField() {

		// フィールド数が少ない
		AbException ex = assertThrows(AbException.class,
				() -> AbDBManager.load(DB_FILE_LESS_FIELD)
		);

		// メッセージの確認
		String innerMessage = String.format(MESSAGE.DB_FILE_FIELD, AbDBManager.FIELD_SIZE);
		String message = String.format(MESSAGE.DB_FILE_LOAD, 1, innerMessage);
		assertEquals(message, ex.getMessage());
	}

	/**
	 * DBファイル読み込み
	 * DBファイルのフォーマットが正しくない
	 */
	@Test
	public void loadWithInvalidFormat() {

		// 読み込みはできるためフィールド数の判定に引っかかる
		AbException ex = assertThrows(AbException.class,
				() -> AbDBManager.load(DB_FILE_INVALID_FORMAT)
		);

		// メッセージの確認
		String innerMessage = String.format(MESSAGE.DB_FILE_FIELD, AbDBManager.FIELD_SIZE);
		String message = String.format(MESSAGE.DB_FILE_LOAD, 1, innerMessage);
		assertEquals(message, ex.getMessage());
	}

	/**
	 * DBファイル読み込み
	 * DBファイルにデータなし
	 * 
	 * @throws AbException
	 */
	@Test
	public void loadWithNoData() throws AbException {

		// DBファイル読み込み
		List<AbExpense> expenses = AbDBManager.load(DB_FILE_NO_DATE);

		// 支出情報リストは0件
		assertEquals(0, expenses.size());
	}

	/**
	 * DBファイル読み込み
	 * 読み込み件数の確認
	 * 
	 * @throws AbException
	 */
	@Test
	public void loadWithExpenseCount() throws AbException {

		// DBファイル読み込み
		List<AbExpense> expenses = AbDBManager.load(DB_FILE_IN_DATE);

		// 読み込み件数の確認
		assertEquals(3, expenses.size());
	}

	/**
	 * DBファイル読み込み
	 * 
	 * @throws AbException
	 */
	@Test
	public void load() throws AbException {

		// DBファイル読み込み
		List<AbExpense> expenses = AbDBManager.load(DB_FILE_IN_DATE);

		// 読み込み件数の確認
		assertEquals(expected.size(), expenses.size());

		// 読み込んだフィールドの確認
		for (int i = 0; i < expected.size(); i++) {
			AbExpense expense = AbTestTool.getExpense(expected.get(i));
			assertEquals(expense.getDate(), expenses.get(i).getDate());
			assertEquals(expense.getName(), expenses.get(i).getName());
			assertEquals(expense.getType(), expenses.get(i).getType());
			assertEquals(expense.getCost(), expenses.get(i).getCost());
			assertEquals(expense.getNote(), expenses.get(i).getNote());
		}
	}

	/**
	 * DBファイル書き出し
	 * 引数:DBファイルパスがNULL
	 */
	@Test
	public void storeWithDBFilePathNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> AbDBManager.load(null)
		);
	}

	/**
	 * DBファイル書き出し
	 * 引数:DBファイルパスが空
	 */
	@Test
	public void storeWithDBFilePathEmpty() {

		// アプリでは処理しない
		AbException ex = assertThrows(AbException.class,
				() -> AbDBManager.store("", null)
		);

		// メッセージの確認
		String message = String.format(MESSAGE.DB_FILE_STORE, " (No such file or directory)");
		assertEquals(message, ex.getMessage());
	}

	/**
	 * DBファイル書き出し
	 * 引数:支出情報リストがNULL
	 */
	@Test
	public void storeWithExpensesNull() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> AbDBManager.store(DB_FILE_OUTPUT_NULL_EXPENSES, null)
		);
	}

	/**
	 * DBファイル書き出し
	 * 引数:支出情報リストが空
	 * 
	 * @throws AbException
	 */
	@Test
	public void storeWithExpensesEmpty() throws AbException {

		// 空ファイルを作っておく
		AbDBManager.load(DB_FILE_INPUT_EMPTY);

		// DBファイル書き出し
		AbDBManager.store(DB_FILE_OUTPUT_EMPTY, new ArrayList<AbExpense>());

		// DBファイルの確認
		FileAssert.assertEquals(Paths.get(DB_FILE_INPUT_EMPTY).toFile(), Paths.get(DB_FILE_OUTPUT_EMPTY).toFile());
	}

	/**
	 * DBファイル書き出し
	 * 
	 * @throws AbException
	 */
	@Test
	public void store() throws AbException {

		// DBファイル書き出し
		List<AbExpense> expenses = new ArrayList<AbExpense>();
		for (int i = 0; i < expected.size(); i++) {
			expenses.add(AbTestTool.getExpense(expected.get(i)));
		}
		AbDBManager.store(DB_FILE_OUT_DATE, expenses);

		// DBファイルが存在することを確認
		Path path = Paths.get(DB_FILE_OUT_DATE);
		assertTrue(Files.exists(path));

		// DBファイルの確認
		Path expectedPath = Paths.get(DB_FILE_IN_DATE);
		FileAssert.assertEquals(expectedPath.toFile(), path.toFile());
	}
}
