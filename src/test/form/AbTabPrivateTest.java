// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.form;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.UIManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.alyssaburlton.swingtest.EventFactoryKt;

import abook.common.AbConstant.COL;
import abook.common.AbConstant.COLOR;
import abook.common.AbConstant.FMT;
import abook.common.AbConstant.TYPE;
import abook.common.AbException;
import abook.common.AbUtility.UTL;
import abook.expense.AbExpense;
import abook.form.AbFormMain;
import test.tool.AbTestTool;
import test.tool.AbFormAbstractMain;
import test.tool.AbTestCleanupExtension;

/**
 * テスト:秘密タブ
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbTabPrivateTest extends AbFormAbstractMain {

	/** DBファイル */
	private static final String DB_FILE_EXIST = "AbTabPrivateTestExist.db";

	/** DBファイル */
	private static final String DB_FILE_EMPTY = "AbTabPrivateTestEmpty.db";

	@BeforeAll
	public static void testFixtureSetup() throws AbException, IOException {

		List<AbExpense> dbFileExist = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-04-01", "private01", TYPE.PRVI, "10000", ""),
						new AbExpense("2023-05-01", "private02", TYPE.PRVI, "20000", ""),
						new AbExpense("2024-06-01", "private03", TYPE.PRVI, "30000", ""),
						new AbExpense("2024-06-30", "dummy    ", TYPE.TRFC, "35000", ""),
						new AbExpense("2024-07-01", "private04", TYPE.PRVO, "40000", ""),
						new AbExpense("2025-08-01", "private05", TYPE.PRVI, "50000", ""),
						new AbExpense("2025-09-01", "private06", TYPE.PRVI, "60000", ""),
						new AbExpense("2025-09-30", "dummy    ", TYPE.SPCL, "65000", ""),
						new AbExpense("2026-10-01", "private07", TYPE.PRVI, "70000", ""),
						new AbExpense("2026-11-01", "private08", TYPE.PRVO, "80000", "note08")
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
		changeTab(frame, 4);

		// 秘密収支情報テーブルを取得
		JTable table = getPrivateTable(frame);
		assertNotNull(table);

		// 収支情報テーブルのカラムを確認
		assertEquals("日付", table.getColumnName(0));
		assertEquals("名称", table.getColumnName(1));
		assertEquals("金額", table.getColumnName(2));
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
		changeTab(frame, 4);

		// 秘密収支情報テーブルを取得
		JTable table = getPrivateTable(frame);
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
		changeTab(frame, 4);

		// 秘密収支情報テーブルを取得
		JTable table = getPrivateTable(frame);
		assertNotNull(table);

		// 件数を確認
		assertEquals(8, table.getRowCount());
	}

	/**
	 * 日付のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void privateWithDate() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 4);

		// 秘密収支情報テーブルを取得
		JTable table = getPrivateTable(frame);
		assertNotNull(table);

		// 日付を確認
		assertEquals(UTL.toLocalDate("2023-04-01"), table.getValueAt(0, COL.PRIVATE.DATE));
		assertEquals(UTL.toLocalDate("2023-05-01"), table.getValueAt(1, COL.PRIVATE.DATE));
		assertEquals(UTL.toLocalDate("2024-06-01"), table.getValueAt(2, COL.PRIVATE.DATE));
		assertEquals(UTL.toLocalDate("2024-07-01"), table.getValueAt(3, COL.PRIVATE.DATE));
		assertEquals(UTL.toLocalDate("2025-08-01"), table.getValueAt(4, COL.PRIVATE.DATE));
		assertEquals(UTL.toLocalDate("2025-09-01"), table.getValueAt(5, COL.PRIVATE.DATE));
		assertEquals(UTL.toLocalDate("2026-10-01"), table.getValueAt(6, COL.PRIVATE.DATE));
		assertEquals(UTL.toLocalDate("2026-11-01"), table.getValueAt(7, COL.PRIVATE.DATE));
	}

	/**
	 * 名称のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void privateWithName() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 4);

		// 秘密収支情報テーブルを取得
		JTable table = getPrivateTable(frame);
		assertNotNull(table);

		// 名称を確認
		assertEquals("private01", table.getValueAt(0, COL.PRIVATE.NAME));
		assertEquals("private02", table.getValueAt(1, COL.PRIVATE.NAME));
		assertEquals("private03", table.getValueAt(2, COL.PRIVATE.NAME));
		assertEquals("private04", table.getValueAt(3, COL.PRIVATE.NAME));
		assertEquals("private05", table.getValueAt(4, COL.PRIVATE.NAME));
		assertEquals("private06", table.getValueAt(5, COL.PRIVATE.NAME));
		assertEquals("private07", table.getValueAt(6, COL.PRIVATE.NAME));
		assertEquals("private08", table.getValueAt(7, COL.PRIVATE.NAME));
	}

	/**
	 * 金額のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void privateWithCost() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 4);

		// 秘密収支情報テーブルを取得
		JTable table = getPrivateTable(frame);
		assertNotNull(table);

		// 金額を確認
		assertEquals( 10000, table.getValueAt(0, COL.PRIVATE.COST));
		assertEquals( 20000, table.getValueAt(1, COL.PRIVATE.COST));
		assertEquals( 30000, table.getValueAt(2, COL.PRIVATE.COST));
		assertEquals(-40000, table.getValueAt(3, COL.PRIVATE.COST));
		assertEquals( 50000, table.getValueAt(4, COL.PRIVATE.COST));
		assertEquals( 60000, table.getValueAt(5, COL.PRIVATE.COST));
		assertEquals( 70000, table.getValueAt(6, COL.PRIVATE.COST));
		assertEquals(-80000, table.getValueAt(7, COL.PRIVATE.COST));
	}

	/**
	 * 収支のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void privateWithBalance() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 4);

		// 秘密収支情報テーブルを取得
		JTable table = getPrivateTable(frame);
		assertNotNull(table);

		// 収支を確認
		assertEquals( 10000, table.getValueAt(0, COL.PRIVATE.BALANCE));
		assertEquals( 30000, table.getValueAt(1, COL.PRIVATE.BALANCE));
		assertEquals( 60000, table.getValueAt(2, COL.PRIVATE.BALANCE));
		assertEquals( 20000, table.getValueAt(3, COL.PRIVATE.BALANCE));
		assertEquals( 70000, table.getValueAt(4, COL.PRIVATE.BALANCE));
		assertEquals(130000, table.getValueAt(5, COL.PRIVATE.BALANCE));
		assertEquals(200000, table.getValueAt(6, COL.PRIVATE.BALANCE));
		assertEquals(120000, table.getValueAt(7, COL.PRIVATE.BALANCE));
	}

	/**
	 * 背景色の確認
	 * 備考あり
	 * 
	 * @throws AbException
	 * @throws InterruptedException
	 */
	@Test
	public void backgroundColorWithNote() throws AbException, InterruptedException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 4);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// 秘密収支情報テーブルを取得
		JTable table = getPrivateTable(frame);
		assertNotNull(table);

		// 背景色の確認(8行目)
		int row = 7;
		for (int col = COL.PRIVATE.DATE; col <= COL.PRIVATE.BALANCE; col++) {
			Component component = getTableCellRendererComponent(table, row, col);
			assertEquals(COLOR.NOTE_BACKGROUND, component.getBackground());
		}
	}

	/**
	 * 背景色の確認
	 * 備考なし
	 * 
	 * @throws AbException
	 * @throws InterruptedException
	 */
	@Test
	public void backgroundColorWithNothingNote() throws AbException, InterruptedException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 4);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// 秘密収支情報テーブルを取得
		JTable table = getPrivateTable(frame);
		assertNotNull(table);

		// 背景色の確認(1〜7行目)
		Color expected = UIManager.getColor("Table.background");
		for (int row = 0; row < 7; row++) {
			for (int col = COL.PRIVATE.DATE; col <= COL.PRIVATE.BALANCE; col++) {
				Component component = getTableCellRendererComponent(table, row, col);
				assertEquals(expected, component.getBackground());
			}
		}
	}

	/**
	 * ツールチップを確認
	 * 備考あり
	 * 
	 * @throws AbException
	 * @throws InterruptedException
	 */
	@Test
	public void getTooltipWithNote() throws AbException, InterruptedException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 4);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// 秘密収支情報テーブルを取得
		JTable table = getPrivateTable(frame);
		assertNotNull(table);

		// マウスイベント(座標はだいたい8行目の名称列辺り)
		MouseEvent e = EventFactoryKt.makeMouseEvent(frame, 0, 111, 123);

		// ツールチップを確認
		String tooltip = table.getToolTipText(e);
		assertEquals(String.format(FMT.NOTE, "note08"), tooltip);
	}

	/**
	 * ツールチップを確認
	 * 備考なし
	 * 
	 * @throws AbException
	 * @throws InterruptedException
	 */
	@Test
	public void getTooltipWithNothingNote() throws AbException, InterruptedException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 4);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// 秘密収支情報テーブルを取得
		JTable table = getPrivateTable(frame);
		assertNotNull(table);

		// マウスイベント(座標はだいたい1行目の名称列辺り)
		MouseEvent e = EventFactoryKt.makeMouseEvent(frame, 0, 141, 6);

		// ツールチップを確認
		String tooltip = table.getToolTipText(e);
		assertNull(tooltip);
	}
}
