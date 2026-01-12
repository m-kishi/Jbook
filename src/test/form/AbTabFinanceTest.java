// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import abook.common.AbConstant.COL;
import abook.common.AbConstant.TYPE;
import abook.common.AbException;
import abook.expense.AbExpense;
import abook.form.AbFormMain;
import test.tool.AbFormAbstractMain;
import test.tool.AbTestCleanupExtension;
import test.tool.AbTestTool;

/**
 * テスト:投資タブ
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbTabFinanceTest extends AbFormAbstractMain {

	/** DBファイル */
	private static final String DB_FILE_EXIST = "AbTabFinanceTestExist.db";

	/** DBファイル */
	private static final String DB_FILE_EMPTY = "AbTabFinanceTestEmpty.db";

	@BeforeAll
	public static void testFixtureSetup() throws AbException, IOException {

		List<AbExpense> dbFileExist = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2024-01-20", "名称１", TYPE.FNCE, "10001", "備考１"),
						new AbExpense("2024-02-21", "名称２", TYPE.FNCE, "20001", "備考１"),
						new AbExpense("2024-03-22", "名称１", TYPE.FNCE, "10002", "備考２"),
						new AbExpense("2024-04-23", "名称２", TYPE.FNCE, "20002", "備考１"),
						new AbExpense("2024-04-30", "ＸＸＸ", TYPE.FOOD, "99999", ""),
						new AbExpense("2024-05-11", "名称１", TYPE.FNCE, "10003", "備考２"),
						new AbExpense("2024-06-12", "名称２", TYPE.FNCE, "20003", "備考２"),
						new AbExpense("2024-07-13", "名称１", TYPE.FNCE, "10004", "備考１"),
						new AbExpense("2024-08-31", "名称２", TYPE.FNCE, "20004", "備考２"),
						new AbExpense("2024-09-30", "ＸＸＸ", TYPE.FOOD, "99999", ""),
						new AbExpense("2025-05-01", "名称１", TYPE.FNCE, "30001", "備考１"),
						new AbExpense("2025-06-02", "名称２", TYPE.FNCE, "40001", "備考１"),
						new AbExpense("2025-07-03", "名称１", TYPE.FNCE, "30002", "備考１"),
						new AbExpense("2025-08-04", "名称２", TYPE.FNCE, "40002", "備考２"),
						new AbExpense("2025-09-05", "ＸＸＸ", TYPE.FOOD, "90000", ""),
						new AbExpense("2025-09-25", "名称１", TYPE.FNCE, "30003", "備考２"),
						new AbExpense("2025-10-26", "名称２", TYPE.FNCE, "40003", "備考１"),
						new AbExpense("2025-11-27", "名称１", TYPE.FNCE, "30004", "備考２"),
						new AbExpense("2025-12-28", "名称２", TYPE.FNCE, "40004", "備考２")
				)
		);

		// DBファイルを作成
		AbTestTool.createDBFile(DB_FILE_EXIST, dbFileExist);
	}

	@AfterAll
	public static void testFixtureTearDown() throws IOException {

		// DBファイルが存在するなら削除
		AbTestTool.deleteDBFile(DB_FILE_EXIST);
		AbTestTool.deleteDBFile(DB_FILE_EMPTY);
	}

	/**
	 * 初期表示の確認
	 * 
	 * @throws AbException
	 */
	@Test
	public void initializeWithColumnName() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EMPTY);

		// タブを切り替え
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 投資情報テーブルのカラムを確認
		assertEquals("年"  , table.getColumnName(COL.FINANCE.YEAR));
		assertEquals("名称", table.getColumnName(COL.FINANCE.NAME));
		assertEquals("備考", table.getColumnName(COL.FINANCE.NOTE));
		assertEquals("金額", table.getColumnName(COL.FINANCE.COST));
	}

	/**
	 * 初期表示の確認
	 * データなし
	 * 
	 * @throws AbException
	 */
	@Test
	public void initializeWithEmpty() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EMPTY);

		// タブを切り替え
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 件数を確認
		assertEquals(0, table.getRowCount());
	}

	/**
	 * 初期表示の確認
	 * 件数の確認
	 * 
	 * @throws AbException
	 */
	@Test
	public void initializeWithCount() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 件数を確認
		assertEquals(8, table.getRowCount());
	}

	/**
	 * 年のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void financeWithDate() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 日付を確認
		assertEquals(2024, table.getValueAt(0, COL.FINANCE.YEAR));
		assertEquals(2024, table.getValueAt(1, COL.FINANCE.YEAR));
		assertEquals(2024, table.getValueAt(2, COL.FINANCE.YEAR));
		assertEquals(2024, table.getValueAt(3, COL.FINANCE.YEAR));
		assertEquals(2025, table.getValueAt(4, COL.FINANCE.YEAR));
		assertEquals(2025, table.getValueAt(5, COL.FINANCE.YEAR));
		assertEquals(2025, table.getValueAt(6, COL.FINANCE.YEAR));
		assertEquals(2025, table.getValueAt(7, COL.FINANCE.YEAR));
	}

	/**
	 * 名称のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void financeWithName() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 名称を確認
		assertEquals("名称１", table.getValueAt(0, COL.FINANCE.NAME));
		assertEquals("名称１", table.getValueAt(1, COL.FINANCE.NAME));
		assertEquals("名称２", table.getValueAt(2, COL.FINANCE.NAME));
		assertEquals("名称２", table.getValueAt(3, COL.FINANCE.NAME));
		assertEquals("名称１", table.getValueAt(4, COL.FINANCE.NAME));
		assertEquals("名称１", table.getValueAt(5, COL.FINANCE.NAME));
		assertEquals("名称２", table.getValueAt(6, COL.FINANCE.NAME));
		assertEquals("名称２", table.getValueAt(7, COL.FINANCE.NAME));
	}

	/**
	 * 備考のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void financeWithNote() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 備考を確認
		assertEquals("備考１", table.getValueAt(0, COL.FINANCE.NOTE));
		assertEquals("備考２", table.getValueAt(1, COL.FINANCE.NOTE));
		assertEquals("備考１", table.getValueAt(2, COL.FINANCE.NOTE));
		assertEquals("備考２", table.getValueAt(3, COL.FINANCE.NOTE));
		assertEquals("備考１", table.getValueAt(4, COL.FINANCE.NOTE));
		assertEquals("備考２", table.getValueAt(5, COL.FINANCE.NOTE));
		assertEquals("備考１", table.getValueAt(6, COL.FINANCE.NOTE));
		assertEquals("備考２", table.getValueAt(7, COL.FINANCE.NOTE));
	}

	/**
	 * 金額のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void financeWithCost() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 金額を確認
		assertEquals(20005, table.getValueAt(0, COL.FINANCE.COST));
		assertEquals(20005, table.getValueAt(1, COL.FINANCE.COST));
		assertEquals(40003, table.getValueAt(2, COL.FINANCE.COST));
		assertEquals(40007, table.getValueAt(3, COL.FINANCE.COST));
		assertEquals(60003, table.getValueAt(4, COL.FINANCE.COST));
		assertEquals(60007, table.getValueAt(5, COL.FINANCE.COST));
		assertEquals(80004, table.getValueAt(6, COL.FINANCE.COST));
		assertEquals(80006, table.getValueAt(7, COL.FINANCE.COST));
	}
}
