// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import abook.common.AbConstant.COL;
import abook.common.AbException;
import abook.form.AbFormMain;
import test.tool.AbFormAbstractMain;
import test.tool.AbTestCleanupExtension;
import test.tool.AbTestTool;

/**
 * テスト:収支タブ
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbTabBalanceTest extends AbFormAbstractMain {

	/** DBファイル */
	private static final String DB_FILE_EXIST = "AbTabBalanceTestExist.db";

	/** DBファイル */
	private static final String DB_FILE_EMPTY = "AbTabBalanceTestEmpty.db";

	@BeforeAll
	public static void testFixtureSetup() throws AbException, IOException {

		List<String[]> dbFileExist = new ArrayList<String[]>() {
			{
				add(new String[] { "2022-04-01", "name", "収入　", "800000" });
				add(new String[] { "2022-04-01", "name", "収入　", "900000" });
				add(new String[] { "2022-04-01", "name", "食費　", "200000" });
				add(new String[] { "2022-04-01", "name", "食費　", "350000" });
				add(new String[] { "2022-04-01", "name", "特出　", " 80000" });
				add(new String[] { "2022-04-01", "name", "特出　", " 50000" });
				add(new String[] { "2022-04-30", "name", "特入　", " 10000" });
				add(new String[] { "2022-04-30", "name", "秘密入", " 11000" });
				add(new String[] { "2023-03-01", "name", "収入　", "200000" });
				add(new String[] { "2023-03-01", "name", "収入　", "400000" });
				add(new String[] { "2023-03-01", "name", "食費　", "300000" });
				add(new String[] { "2023-03-01", "name", "食費　", "250000" });
				add(new String[] { "2023-03-01", "name", "特出　", " 20000" });
				add(new String[] { "2023-03-01", "name", "特出　", " 30000" });
				add(new String[] { "2023-03-31", "name", "特入　", " 15000" });
				add(new String[] { "2023-03-31", "name", "秘密出", " 10000" });
				add(new String[] { "2023-04-01", "name", "収入　", "600000" });
				add(new String[] { "2023-04-01", "name", "収入　", "800000" });
				add(new String[] { "2023-04-01", "name", "食費　", "200000" });
				add(new String[] { "2023-04-01", "name", "食費　", "250000" });
				add(new String[] { "2023-04-01", "name", "特出　", " 20000" });
				add(new String[] { "2023-04-01", "name", "特出　", " 40000" });
				add(new String[] { "2023-04-30", "name", "特入　", "  1000" });
				add(new String[] { "2023-04-30", "name", "秘密入", " 30000" });
				add(new String[] { "2024-03-01", "name", "収入　", "300000" });
				add(new String[] { "2024-03-01", "name", "収入　", "700000" });
				add(new String[] { "2024-03-01", "name", "食費　", "100000" });
				add(new String[] { "2024-03-01", "name", "食費　", "450000" });
				add(new String[] { "2024-03-01", "name", "特出　", " 60000" });
				add(new String[] { "2024-03-01", "name", "特出　", " 18000" });
				add(new String[] { "2024-03-31", "name", "特入　", "  2000" });
				add(new String[] { "2024-03-31", "name", "秘密出", "  9000" });
				add(new String[] { "2024-04-01", "name", "収入　", "     0" });
				add(new String[] { "2024-04-01", "name", "収入　", "     0" });
				add(new String[] { "2024-04-01", "name", "食費　", "300000" });
				add(new String[] { "2024-04-01", "name", "食費　", "900000" });
				add(new String[] { "2024-04-01", "name", "特出　", " 80000" });
				add(new String[] { "2024-04-01", "name", "特出　", " 20000" });
				add(new String[] { "2024-04-01", "name", "秘密入", " 30000" });
			}
		};

		// DBファイルを作成
		AbTestTool.createDBFileFromParams(DB_FILE_EXIST, dbFileExist);
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
		changeTab(frame, 3);

		// 収支情報テーブルを取得
		JTable table = getBalanceTable(frame);
		assertNotNull(table);

		// 収支情報テーブルのカラムを確認
		assertEquals("年度", table.getColumnName(0));
		assertEquals("収入", table.getColumnName(1));
		assertEquals("支出", table.getColumnName(2));
		assertEquals("収支", table.getColumnName(3));
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
		changeTab(frame, 3);

		// 収支情報テーブルを取得
		JTable table = getBalanceTable(frame);
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
		changeTab(frame, 3);

		// 収支情報テーブルを取得
		JTable table = getBalanceTable(frame);
		assertNotNull(table);

		// 件数を確認
		assertEquals(4, table.getRowCount());
	}

	/**
	 * 年度のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void balanceWithYear() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 3);

		// 収支情報テーブルを取得
		JTable table = getBalanceTable(frame);
		assertNotNull(table);

		// 年度を確認
		assertEquals(2022, table.getValueAt(0, COL.BALANCE.YEAR));
		assertEquals(2023, table.getValueAt(1, COL.BALANCE.YEAR));
		assertEquals(2024, table.getValueAt(2, COL.BALANCE.YEAR));
		assertEquals(9999, table.getValueAt(3, COL.BALANCE.YEAR));
	}

	/**
	 * 収入のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void balanceWithEarn() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 3);

		// 収支情報テーブルを取得
		JTable table = getBalanceTable(frame);
		assertNotNull(table);

		// 収入を確認
		assertEquals(2325000, table.getValueAt(0, COL.BALANCE.EARN));
		assertEquals(2403000, table.getValueAt(1, COL.BALANCE.EARN));
		assertEquals(      0, table.getValueAt(2, COL.BALANCE.EARN));
		assertEquals(4728000, table.getValueAt(3, COL.BALANCE.EARN));
	}

	/**
	 * 支出のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void balanceWithExpense() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 3);

		// 収支情報テーブルを取得
		JTable table = getBalanceTable(frame);
		assertNotNull(table);

		// 支出を確認
		assertEquals(1280000, table.getValueAt(0, COL.BALANCE.EXPENSE));
		assertEquals(1138000, table.getValueAt(1, COL.BALANCE.EXPENSE));
		assertEquals(1300000, table.getValueAt(2, COL.BALANCE.EXPENSE));
		assertEquals(3718000, table.getValueAt(3, COL.BALANCE.EXPENSE));
	}

	/**
	 * 収支のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void balanceWithBalance() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 3);

		// 収支情報テーブルを取得
		JTable table = getBalanceTable(frame);
		assertNotNull(table);

		// 収支を確認
		assertEquals( 1045000, table.getValueAt(0, COL.BALANCE.BALANCE));
		assertEquals( 1265000, table.getValueAt(1, COL.BALANCE.BALANCE));
		assertEquals(-1300000, table.getValueAt(2, COL.BALANCE.BALANCE));
		assertEquals( 1010000, table.getValueAt(3, COL.BALANCE.BALANCE));
	}
}
