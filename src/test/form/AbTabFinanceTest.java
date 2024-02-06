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
						new AbExpense("2024-01-20", "名称1", TYPE.FNCE, "10000", ""),
						new AbExpense("2024-02-21", "名称2", TYPE.FNCE, "20000", ""),
						new AbExpense("2024-03-22", "名称3", TYPE.FNCE, "30000", ""),
						new AbExpense("2024-04-23", "名称4", TYPE.FNCE, "40000", ""),
						new AbExpense("2024-04-30", "　　X", TYPE.FOOD, "99999", ""),
						new AbExpense("2024-05-24", "名称5", TYPE.FNCE, "50000", ""),
						new AbExpense("2024-06-25", "名称6", TYPE.FNCE, "60000", ""),
						new AbExpense("2024-07-26", "名称7", TYPE.FNCE, "70000", ""),
						new AbExpense("2024-08-27", "名称8", TYPE.FNCE, "80000", "noteXX"),
						new AbExpense("2024-09-28", "名称9", TYPE.FNCE, "90000", ""),
						new AbExpense("2024-10-29", "名称0", TYPE.FNCE, "11000", "")
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
		assertEquals("日付", table.getColumnName(0));
		assertEquals("名称", table.getColumnName(1));
		assertEquals("金額", table.getColumnName(2));
		assertEquals("累計", table.getColumnName(3));
		assertEquals("備考", table.getColumnName(4));
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
		assertEquals(10, table.getRowCount());
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
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 日付を確認
		assertEquals(UTL.toLocalDate("2024-01-20"), table.getValueAt(0, COL.FINANCE.DATE));
		assertEquals(UTL.toLocalDate("2024-02-21"), table.getValueAt(1, COL.FINANCE.DATE));
		assertEquals(UTL.toLocalDate("2024-03-22"), table.getValueAt(2, COL.FINANCE.DATE));
		assertEquals(UTL.toLocalDate("2024-04-23"), table.getValueAt(3, COL.FINANCE.DATE));
		assertEquals(UTL.toLocalDate("2024-05-24"), table.getValueAt(4, COL.FINANCE.DATE));
		assertEquals(UTL.toLocalDate("2024-06-25"), table.getValueAt(5, COL.FINANCE.DATE));
		assertEquals(UTL.toLocalDate("2024-07-26"), table.getValueAt(6, COL.FINANCE.DATE));
		assertEquals(UTL.toLocalDate("2024-08-27"), table.getValueAt(7, COL.FINANCE.DATE));
		assertEquals(UTL.toLocalDate("2024-09-28"), table.getValueAt(8, COL.FINANCE.DATE));
		assertEquals(UTL.toLocalDate("2024-10-29"), table.getValueAt(9, COL.FINANCE.DATE));
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
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 名称を確認
		assertEquals("名称1", table.getValueAt(0, COL.FINANCE.NAME));
		assertEquals("名称2", table.getValueAt(1, COL.FINANCE.NAME));
		assertEquals("名称3", table.getValueAt(2, COL.FINANCE.NAME));
		assertEquals("名称4", table.getValueAt(3, COL.FINANCE.NAME));
		assertEquals("名称5", table.getValueAt(4, COL.FINANCE.NAME));
		assertEquals("名称6", table.getValueAt(5, COL.FINANCE.NAME));
		assertEquals("名称7", table.getValueAt(6, COL.FINANCE.NAME));
		assertEquals("名称8", table.getValueAt(7, COL.FINANCE.NAME));
		assertEquals("名称9", table.getValueAt(8, COL.FINANCE.NAME));
		assertEquals("名称0", table.getValueAt(9, COL.FINANCE.NAME));
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
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 金額を確認
		assertEquals(10000, table.getValueAt(0, COL.FINANCE.COST));
		assertEquals(20000, table.getValueAt(1, COL.FINANCE.COST));
		assertEquals(30000, table.getValueAt(2, COL.FINANCE.COST));
		assertEquals(40000, table.getValueAt(3, COL.FINANCE.COST));
		assertEquals(50000, table.getValueAt(4, COL.FINANCE.COST));
		assertEquals(60000, table.getValueAt(5, COL.FINANCE.COST));
		assertEquals(70000, table.getValueAt(6, COL.FINANCE.COST));
		assertEquals(80000, table.getValueAt(7, COL.FINANCE.COST));
		assertEquals(90000, table.getValueAt(8, COL.FINANCE.COST));
		assertEquals(11000, table.getValueAt(9, COL.FINANCE.COST));
	}

	/**
	 * 累計のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void privateWithTotal() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 収支を確認
		assertEquals( 10000, table.getValueAt(0, COL.FINANCE.TOTAL));
		assertEquals( 30000, table.getValueAt(1, COL.FINANCE.TOTAL));
		assertEquals( 60000, table.getValueAt(2, COL.FINANCE.TOTAL));
		assertEquals(100000, table.getValueAt(3, COL.FINANCE.TOTAL));
		assertEquals(150000, table.getValueAt(4, COL.FINANCE.TOTAL));
		assertEquals(210000, table.getValueAt(5, COL.FINANCE.TOTAL));
		assertEquals(280000, table.getValueAt(6, COL.FINANCE.TOTAL));
		assertEquals(360000, table.getValueAt(7, COL.FINANCE.TOTAL));
		assertEquals(450000, table.getValueAt(8, COL.FINANCE.TOTAL));
		assertEquals(461000, table.getValueAt(9, COL.FINANCE.TOTAL));
	}

	/**
	 * 備考のテスト
	 * 
	 * @throws AbException
	 */
	@Test
	public void privateWithNote() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 5);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 備考を確認
		assertEquals("", table.getValueAt(0, COL.FINANCE.NOTE));
		assertEquals("", table.getValueAt(1, COL.FINANCE.NOTE));
		assertEquals("", table.getValueAt(2, COL.FINANCE.NOTE));
		assertEquals("", table.getValueAt(3, COL.FINANCE.NOTE));
		assertEquals("", table.getValueAt(4, COL.FINANCE.NOTE));
		assertEquals("", table.getValueAt(5, COL.FINANCE.NOTE));
		assertEquals("", table.getValueAt(6, COL.FINANCE.NOTE));
		assertEquals("noteXX", table.getValueAt(7, COL.FINANCE.NOTE));
		assertEquals("", table.getValueAt(8, COL.FINANCE.NOTE));
		assertEquals("", table.getValueAt(9, COL.FINANCE.NOTE));
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
		changeTab(frame, 5);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 背景色の確認(8行目)
		int row = 7;
		for (int col = COL.FINANCE.DATE; col <= COL.FINANCE.NOTE; col++) {
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
		changeTab(frame, 5);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// 背景色の確認(1〜7, 9〜11行目)
		Color expected = UIManager.getColor("Table.background");
		for (int row = 0; row < 10; row++) {
			if (row == 7)
				continue;
			for (int col = COL.FINANCE.DATE; col <= COL.FINANCE.NOTE; col++) {
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
		changeTab(frame, 5);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// マウスイベント(座標はだいたい8行目の名称列辺り)
		MouseEvent e = EventFactoryKt.makeMouseEvent(frame, 0, 111, 123);

		// ツールチップを確認
		String tooltip = table.getToolTipText(e);
		assertEquals(String.format(FMT.NOTE, "noteXX"), tooltip);
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
		changeTab(frame, 5);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// 投資情報テーブルを取得
		JTable table = getFinanceTable(frame);
		assertNotNull(table);

		// マウスイベント(座標はだいたい1行目の名称列辺り)
		MouseEvent e = EventFactoryKt.makeMouseEvent(frame, 0, 141, 6);

		// ツールチップを確認
		String tooltip = table.getToolTipText(e);
		assertNull(tooltip);
	}
}
