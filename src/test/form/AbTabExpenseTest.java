// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableModel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.alyssaburlton.swingtest.ChildInteractionsKt;
import com.github.alyssaburlton.swingtest.ComponentFindersKt;
import com.github.alyssaburlton.swingtest.EventFactoryKt;
import com.github.alyssaburlton.swingtest.SwingUtilitiesKt;

import abook.common.AbConstant.COL;
import abook.common.AbConstant.COLOR;
import abook.common.AbConstant.FMT;
import abook.common.AbException;
import abook.common.AbManager.MESSAGE;
import abook.common.AbUtility.UTL;
import abook.expense.AbExpense;
import abook.form.AbFormMain;
import junitx.framework.FileAssert;
import test.tool.AbFormAbstractMain;
import test.tool.AbTestCleanupExtension;
import test.tool.AbTestTool;

/**
 * テスト:支出タブ
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbTabExpenseTest extends AbFormAbstractMain {

	/** DBファイル */
	private static final String DB_FILE_EXIST = "AbTabExpenseTestCount.db";

	/** DBファイル */
	private static final String DB_FILE_EMPTY = "AbTabExpenseTestEmpty.db";

	/** DBファイル */
	private static final String DB_FILE_ENTRY = "AbTabExpenseTestEntry.db";

	/** DBファイル */
	private static final String DB_FILE_SCROLL = "AbTabExpenseTestScroll.db";

	/** DBファイル */
	private static final String DB_FILE_BG_COLOR = "AbTabExpenseTestBgColor.db";

	/** DBファイル */
	private static final String DB_FILE_COMPLETE = "AbTabExpenseTestComplete.db";

	/** DBファイル */
	private static final String DB_FILE_TAX_ACTION = "AbTabExpenseTestTaxAction.db";

	/** DBファイル */
	private static final String DB_FILE_ENTRY_NO_DATA = "AbTabExpenseTestEntryNoData.db";

	/** DBファイル */
	private static final String DB_FILE_ENTRY_IGNORED = "AbTabExpenseTestEntryIgnored.db";

	/** DBファイル */
	private static final String DB_FILE_ENTRY_EXPECTED = "AbTabExpenseTestEntryExpected.db";

	/** DBファイル */
	private static final String DB_FILE_ENTRY_EXPECTED2 = "AbTabExpenseTestEntryExpected2.db";

	/** 支出情報リスト */
	private static List<AbExpense> dbFileExist;

	@BeforeAll
	public static void testFixtureSetup() throws AbException, IOException {

		dbFileExist = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-10-01", "name01", "食費", "100", ""),
						new AbExpense("2023-10-02", "name02", "食費", "200", "note02"),
						new AbExpense("2023-10-03", "name03", "食費", "300", "note03"),
						new AbExpense("2023-10-04", "name04", "食費", "400", "note04"),
						new AbExpense("2023-10-05", "name05", "食費", "500", "note05"),
						new AbExpense("2023-10-06", "name06", "雑貨", "600", "note06"),
						new AbExpense("2023-10-07", "name07", "雑貨", "700", "note07"),
						new AbExpense("2023-10-08", "name08", "雑貨", "800", "note08"),
						new AbExpense("2023-10-09", "name09", "雑貨", "900", "note09"),
						new AbExpense("2023-10-10", "name10", "雑貨", "999", "note10")
				)
		);

		List<AbExpense> dbFileEntry = new ArrayList<AbExpense>(
				Arrays.asList(new AbExpense("2023-10-01", "name01", "外食費", "100", "note1"))
		);

		List<AbExpense> dbFileScroll = new ArrayList<AbExpense>();
		for (int i = 0; i < 1000; i++) {
			dbFileScroll.add(new AbExpense("2023-10-01", "name", "食費", "100", ""));
		}

		List<AbExpense> dbFileBgColor = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-10-01", "name01", "食費", "100", "note1:¥999"),
						new AbExpense("2023-10-02", "name02", "食費", "100", ""),
						new AbExpense("2023-10-03", "name03", "食費", "100", "note3:¥500"),
						new AbExpense("2023-10-04", "name04", "食費", "100", "")
				)
		);

		List<AbExpense> dbFileComplete = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-10-01", "name1", "食費", "100", ""),
						new AbExpense("2023-10-02", "name2", "食費", "300", ""),
						new AbExpense("2023-10-03", "name2", "雑貨", "500", ""),
						new AbExpense("2023-10-03", "name3", "食費", "111", ""),
						new AbExpense("2023-10-04", "name3", "食費", "222", ""),
						new AbExpense("2023-10-04", "name4", "交際費", "501", ""),
						new AbExpense("2023-10-05", "name4", "交際費", "502", ""),
						new AbExpense("2023-10-06", "name4", "交際費", "503", ""),
						new AbExpense("2023-10-04", "name4", "交通費", "600", ""),
						new AbExpense("2023-10-05", "name4", "交通費", "600", ""),
						new AbExpense("2023-10-04", "name4", "遊行費", "700", ""),
						new AbExpense("2023-10-05", "name4", "遊行費", "700", "")
				)
		);

		List<AbExpense> dbFileTaxAction = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-10-01", "name01", "食費", "100", ""),
						new AbExpense("2023-10-02", "name02", "食費", "200", ""),
						new AbExpense("2023-10-03", "name03", "食費", "300", ""),
						new AbExpense("2023-10-04", "name04", "食費", "400", ""),
						new AbExpense("2023-10-05", "name05", "食費", "500", "")
				)
		);

		List<AbExpense> dbFileEntryExpected = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-10-01", "name01", "外食費", "100", "note1"),
						new AbExpense("2023-10-02", "name02", "交通費", "200", "note02"),
						new AbExpense("2023-10-03", "name03", "交際費", "300", "note03"),
						new AbExpense("2023-10-04", "name04", "遊行費", "400", "note04"),
						new AbExpense("2023-10-05", "name05", "通信費", "500", "note05")
				)
		);

		List<AbExpense> dbFileEntryExpected2 = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-10-01", "name01", "食費", "100", "備考"),
						new AbExpense("2023-10-02", "name02", "食費", "200", "備考"),
						new AbExpense("2023-10-06", "name06", "食費", "600", "備考")
				)
		);

		// DBファイルを作成
		AbTestTool.createDBFile(DB_FILE_EXIST, dbFileExist);
		AbTestTool.createDBFile(DB_FILE_ENTRY, dbFileEntry);
		AbTestTool.createDBFile(DB_FILE_SCROLL, dbFileScroll);
		AbTestTool.createDBFile(DB_FILE_BG_COLOR, dbFileBgColor);
		AbTestTool.createDBFile(DB_FILE_COMPLETE, dbFileComplete);
		AbTestTool.createDBFile(DB_FILE_TAX_ACTION, dbFileTaxAction);
		AbTestTool.createDBFile(DB_FILE_ENTRY_EXPECTED, dbFileEntryExpected);
		AbTestTool.createDBFile(DB_FILE_ENTRY_EXPECTED2, dbFileEntryExpected2);
	}

	@AfterAll
	public static void testFixtureTearDown() throws IOException {

		// DBファイルが存在するなら削除
		AbTestTool.deleteDBFile(DB_FILE_EXIST);
		AbTestTool.deleteDBFile(DB_FILE_EMPTY);
		AbTestTool.deleteDBFile(DB_FILE_ENTRY);
		AbTestTool.deleteDBFile(DB_FILE_SCROLL);
		AbTestTool.deleteDBFile(DB_FILE_BG_COLOR);
		AbTestTool.deleteDBFile(DB_FILE_COMPLETE);
		AbTestTool.deleteDBFile(DB_FILE_TAX_ACTION);
		AbTestTool.deleteDBFile(DB_FILE_ENTRY_NO_DATA);
		AbTestTool.deleteDBFile(DB_FILE_ENTRY_IGNORED);
		AbTestTool.deleteDBFile(DB_FILE_ENTRY_EXPECTED);
		AbTestTool.deleteDBFile(DB_FILE_ENTRY_EXPECTED2);
	}

	/**
	 * 初期表示
	 */
	@Nested
	class Initialize {

		/**
		 * 初期表示の確認
		 * 
		 * @throws AbException
		 */
		@Test
		public void initializeWithColumnName() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_EMPTY);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 支出情報テーブルのカラムを確認
			assertEquals("日付", table.getColumnName(0));
			assertEquals("名称", table.getColumnName(1));
			assertEquals("種別", table.getColumnName(2));
			assertEquals("金額", table.getColumnName(3));
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

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
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

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 件数を確認
			assertEquals(10, table.getRowCount());
		}

		/**
		 * 初期表示の確認
		 * 支出情報の確認
		 * 
		 * @throws AbException
		 */
		@Test
		public void initializeWithExpense() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_EXIST);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// セルの値を確認
			for (int i = 0; i < dbFileExist.size(); i++) {
				LocalDate date = (LocalDate) table.getValueAt(i, 0);
				String name = (String) table.getValueAt(i, 1);
				String type = (String) table.getValueAt(i, 2);
				int cost = (int) table.getValueAt(i, 3);
				String note = (String) table.getValueAt(i, 4);
				assertEquals(dbFileExist.get(i).getDate(), date);
				assertEquals(dbFileExist.get(i).getName(), name);
				assertEquals(dbFileExist.get(i).getType(), type);
				assertEquals(dbFileExist.get(i).getCost(), cost);
				assertEquals(dbFileExist.get(i).getNote(), note);
			}
		}

		/**
		 * 初期表示の確認
		 * スクロールバーの確認
		 * 
		 * @throws AbException
		 * @throws InterruptedException
		 */
		@Test
		public void initializeWithScrollBar() throws AbException, InterruptedException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_SCROLL);

			// 画面の表示が完了するまでこのスレッドは待機
			Thread.sleep(2000);

			// スクロール領域を取得
			JScrollPane scrollPane = getExpenseScrollPane(frame);
			assertNotNull(scrollPane);

			// スクロールバーを取得
			JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
			assertNotNull(scrollBar);

			// スクロール位置が初期(=0)でないことを確認
			assertNotEquals(0, scrollBar.getValue());

			// スクロール位置を確認
			// 厳密に最大値と等しくはならないのでとりあえず差が250以内ならOKとする
			int diff = scrollBar.getMaximum() - scrollBar.getValue();
			assertTrue(250 - Math.abs(diff) >= 0);
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
			AbFormMain frame = showFormMain(DB_FILE_BG_COLOR);

			// ここで少し待たないとテーブルの内容が正しく取得できない
			Thread.sleep(500);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 背景色の確認(1,3行目)
			for (int row = 0; row <= 2; row++) {
				if (row == 1)
					continue;
				for (int col = COL.EXPENSE.DATE; col <= COL.EXPENSE.NOTE; col++) {
					Component component = getTableCellRendererComponent(table, row, col);
					assertEquals(COLOR.NOTE_BACKGROUND, component.getBackground());
				}
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
			AbFormMain frame = showFormMain(DB_FILE_BG_COLOR);

			// ここで少し待たないとテーブルの内容が正しく取得できない
			Thread.sleep(500);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 背景色の確認(2,4行目)
			Color expected = UIManager.getColor("Table.background");
			for (int row = 1; row <= 3; row++) {
				if (row == 2)
					continue;
				for (int col = COL.EXPENSE.DATE; col <= COL.EXPENSE.NOTE; col++) {
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
			AbFormMain frame = showFormMain(DB_FILE_BG_COLOR);

			// ここで少し待たないとテーブルの内容が正しく取得できない
			Thread.sleep(500);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// マウスイベント(座標はだいたい1行目の名称列辺り)
			MouseEvent e = EventFactoryKt.makeMouseEvent(frame, 0, 148, 7);

			// ツールチップを確認
			String tooltip = table.getToolTipText(e);
			assertEquals(String.format(FMT.NOTE, UTL.replaceYenMark("note1:¥999")), tooltip);
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
			AbFormMain frame = showFormMain(DB_FILE_BG_COLOR);

			// ここで少し待たないとテーブルの内容が正しく取得できない
			Thread.sleep(500);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// マウスイベント(座標はだいたい2行目の名称列辺り)
			MouseEvent e = EventFactoryKt.makeMouseEvent(frame, 0, 127, 24);

			// ツールチップを確認
			String tooltip = table.getToolTipText(e);
			assertNull(tooltip);
		}
	}

	/**
	 * 行追加ボタン
	 */
	@Nested
	class AddRowButton {

		/**
		 * 行追加ボタン(1回)
		 * 
		 * @throws AbException
		 */
		@Test
		public void addRowButtonWithOnce() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_EXIST);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 行追加前の件数を確認
			assertEquals(dbFileExist.size(), table.getRowCount());

			// 行追加ボタンをクリック
			clickAddRowButton(frame);

			// 行追加後の件数を確認
			assertEquals(dbFileExist.size() + 30, table.getRowCount());
		}

		/**
		 * 行追加ボタン(2回)
		 * 
		 * @throws AbException
		 */
		@Test
		public void addRowButtonWithTwice() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_EXIST);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 行追加前の件数を確認
			assertEquals(dbFileExist.size(), table.getRowCount());

			// 行追加ボタンを2回クリック
			clickAddRowButton(frame);
			clickAddRowButton(frame);

			// 行追加後の件数を確認
			assertEquals(dbFileExist.size() + 30 * 2, table.getRowCount());
		}

		/**
		 * 行追加ボタン
		 * 追加行の日付の初期値
		 * 
		 * @throws AbException
		 */
		@Test
		public void addRowButtonWithInitialDate() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_EXIST);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 行追加位置
			int initRowCount = table.getRowCount();

			// 行追加ボタンをクリック
			clickAddRowButton(frame);

			// 追加行の日付をそれぞれ確認
			String expected = UTL.toString(LocalDate.now());
			for (int i = initRowCount; i < table.getRowCount() - 1; i++) {
				assertEquals(expected, UTL.toString((LocalDate) table.getModel().getValueAt(i, COL.EXPENSE.DATE)));
			}
		}

	}

	/**
	 * 支出情報の編集
	 */
	@Nested
	class TableEdit {

		/**
		 * 自動補完の確認
		 * 名称が存在しない
		 * 
		 * @throws AbException
		 */
		@Test
		public void completeWithNotMatch() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_COMPLETE);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 行追加ボタンをクリック
			clickAddRowButton(frame);

			// 支出情報を入力
			TableModel model = table.getModel();
			int row = model.getRowCount() - 1;
			model.setValueAt("not match", row, COL.EXPENSE.NAME);

			// 種別を確認
			assertEquals("", model.getValueAt(row, COL.EXPENSE.TYPE));

			// 金額を確認
			assertEquals("", model.getValueAt(row, COL.EXPENSE.COST));
		}

		/**
		 * 自動補完の確認
		 * 保管されない場合のクリア
		 * 
		 * @throws AbException
		 */
		@Test
		public void completeWithClear() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_COMPLETE);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 支出情報を入力
			TableModel model = table.getModel();
			model.setValueAt("not match", 0, COL.EXPENSE.NAME);

			// 種別がクリアされる
			assertEquals("", model.getValueAt(0, COL.EXPENSE.TYPE));

			// 金額がクリアされる
			assertEquals("", model.getValueAt(0, COL.EXPENSE.COST));
		}

		/**
		 * 自動補完の確認
		 * 名称:種別:金額 = 1:1:1
		 * 
		 * @throws AbException
		 */
		@Test
		public void completeWithSingleType() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_COMPLETE);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 行追加ボタンをクリック
			clickAddRowButton(frame);

			// 支出情報を入力
			TableModel model = table.getModel();
			int row = model.getRowCount() - 1;
			model.setValueAt("name1", row, COL.EXPENSE.NAME);

			// 種別を確認
			assertEquals("食費", model.getValueAt(row, COL.EXPENSE.TYPE));

			// 金額を確認
			assertEquals(100, model.getValueAt(row, COL.EXPENSE.COST));
		}

		/**
		 * 自動補完の確認
		 * 名称:種別:金額 = 1:2:1
		 * 
		 * @throws AbException
		 */
		@Test
		public void completeWithSameNumberType() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_COMPLETE);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 行追加ボタンをクリック
			clickAddRowButton(frame);

			// 支出情報を入力
			TableModel model = table.getModel();
			int row = model.getRowCount() - 1;
			model.setValueAt("name2", row, COL.EXPENSE.NAME);

			// 種別を確認(同数なら最新日の種別)
			assertEquals("雑貨", model.getValueAt(row, COL.EXPENSE.TYPE));

			// 金額を確認
			assertEquals(500, model.getValueAt(row, COL.EXPENSE.COST));
		}

		/**
		 * 自動補完の確認
		 * 名称:種別:金額 = 1:1:2
		 * 
		 * @throws AbException
		 */
		@Test
		public void completeWithSameNumberCost() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_COMPLETE);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 行追加ボタンをクリック
			clickAddRowButton(frame);

			// 支出情報を入力
			TableModel model = table.getModel();
			int row = model.getRowCount() - 1;
			model.setValueAt("name3", row, COL.EXPENSE.NAME);

			// 種別を確認
			assertEquals("食費", model.getValueAt(row, COL.EXPENSE.TYPE));

			// 金額を確認(同数なら最新日の金額)
			assertEquals(222, model.getValueAt(row, COL.EXPENSE.COST));
		}

		/**
		 * 自動補完の確認
		 * 名称:種別:金額 = 1:N:N
		 * 
		 * @throws AbException
		 */
		@Test
		public void completeWithMultiTypeAndCost() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_COMPLETE);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 行追加ボタンをクリック
			clickAddRowButton(frame);

			// 支出情報を入力
			TableModel model = table.getModel();
			int row = model.getRowCount() - 1;
			model.setValueAt("name4", row, COL.EXPENSE.NAME);

			// 種別を確認(最多の種別)
			assertEquals("交際費", model.getValueAt(row, COL.EXPENSE.TYPE));

			// 金額を確認(最多の種別の中で最新の日付の金額)
			assertEquals(503, model.getValueAt(row, COL.EXPENSE.COST));
		}
	}

	/**
	 * 消費税計算
	 */
	@Nested
	class TaxAction {

		/**
		 * 消費税 8%
		 * 範囲選択なし
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction8WithNoSelection() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// セル選択をクリア
			table.clearSelection();

			// Ctrl+8
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			TableModel model = table.getModel();
			for (int i = 0; i < 5; i++) {
				assertEquals(100 * (i + 1), model.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 消費税 8%
		 * 金額列の選択なし
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction8WithoutCostColumn() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 金額列以外を選択(1〜5行目，日付〜種別列までを選択)
			table.changeSelection(0, COL.EXPENSE.DATE, false, false);
			table.addRowSelectionInterval(1, 4);
			table.addColumnSelectionInterval(COL.EXPENSE.NAME, COL.EXPENSE.TYPE);

			// Ctrl+8
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			TableModel model = table.getModel();
			for (int i = 0; i < 5; i++) {
				assertEquals(100 * (i + 1), model.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 消費税 8%
		 * 金額単一セル
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction8WithSingleCell() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 金額セルを選択
			table.changeSelection(0, COL.EXPENSE.COST, false, false);

			// Ctrl+8
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			TableModel model = table.getModel();
			assertEquals(108, model.getValueAt(0, COL.EXPENSE.COST));
			for (int i = 1; i < 5; i++) {
				assertEquals(100 * (i + 1), model.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 消費税 8%
		 * 金額複数セル
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction8WithMultiCell() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 金額セルを選択
			table.changeSelection(0, COL.EXPENSE.COST, false, false);
			table.addRowSelectionInterval(1, 4);

			// Ctrl+8
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			TableModel model = table.getModel();
			for (int i = 0; i < 5; i++) {
				assertEquals((int) (100 * 1.08 * (i + 1)), model.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 消費税 8%
		 * 金額列以外のセルを含む
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction8WithIncludeOtherCell() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// セルを選択(1〜5行目，日付〜金額列までを選択)
			table.changeSelection(0, COL.EXPENSE.DATE, false, false);
			table.addRowSelectionInterval(1, 4);
			table.addColumnSelectionInterval(COL.EXPENSE.NAME, COL.EXPENSE.COST);

			// Ctrl+8
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			TableModel model = table.getModel();
			for (int i = 0; i < 5; i++) {
				assertEquals((int) (100 * 1.08 * (i + 1)), model.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 消費税 8%
		 * 金額列に空白を含む
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction8WithIncludeEmptyCost() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 3行目の金額をクリア
			TableModel model = table.getModel();
			model.setValueAt(null, 2, COL.EXPENSE.COST);

			// 金額セルを選択
			table.changeSelection(0, COL.EXPENSE.COST, false, false);
			table.addRowSelectionInterval(1, 4);

			// Ctrl+8
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			for (int i = 0; i < 5; i++) {
				if (i == 2) {
					assertNull(model.getValueAt(i, COL.EXPENSE.COST));
				} else {
					assertEquals((int) (100 * 1.08 * (i + 1)), model.getValueAt(i, COL.EXPENSE.COST));
				}
			}
		}

		/**
		 * 消費税 8%
		 * 四捨五入の確認
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction8WithRoundingOff() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 金額を設定(切り捨て(0.4)，切り上げ(.5) 切り上げ(.8〜))
			TableModel model = table.getModel();
			model.setValueAt(105, 0, COL.EXPENSE.COST);
			model.setValueAt(107, 1, COL.EXPENSE.COST);
			model.setValueAt(110, 2, COL.EXPENSE.COST);

			// 金額セルを選択
			table.changeSelection(0, COL.EXPENSE.COST, false, false);
			table.addRowSelectionInterval(1, 2);

			// Ctrl+8
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			assertEquals(113, model.getValueAt(0, COL.EXPENSE.COST));
			assertEquals(116, model.getValueAt(1, COL.EXPENSE.COST));
			assertEquals(119, model.getValueAt(2, COL.EXPENSE.COST));
		}

		/**
		 * 消費税 10%
		 * 範囲選択なし
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction10WithNoSelection() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// セル選択をクリア
			table.clearSelection();

			// Ctrl+0
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			TableModel model = table.getModel();
			for (int i = 0; i < 5; i++) {
				assertEquals(100 * (i + 1), model.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 消費税 10%
		 * 金額列の選択なし
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction10WithoutCostColumn() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 金額列以外を選択(1〜5行目，日付〜種別列までを選択)
			table.changeSelection(0, COL.EXPENSE.DATE, false, false);
			table.addRowSelectionInterval(1, 4);
			table.addColumnSelectionInterval(COL.EXPENSE.NAME, COL.EXPENSE.TYPE);

			// Ctrl+0
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			TableModel model = table.getModel();
			for (int i = 0; i < 5; i++) {
				assertEquals(100 * (i + 1), model.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 消費税 10%
		 * 金額単一セル
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction10WithSingleCell() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 金額セルを選択
			table.changeSelection(0, COL.EXPENSE.COST, false, false);

			// Ctrl+0
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			TableModel model = table.getModel();
			assertEquals(110, model.getValueAt(0, COL.EXPENSE.COST));
			for (int i = 1; i < 5; i++) {
				assertEquals(100 * (i + 1), model.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 消費税 10%
		 * 金額複数セル
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction10WithMultiCell() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 金額セルを選択
			table.changeSelection(0, COL.EXPENSE.COST, false, false);
			table.addRowSelectionInterval(1, 4);

			// Ctrl+0
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			TableModel model = table.getModel();
			for (int i = 0; i < 5; i++) {
				assertEquals((int) (100 * 1.10 * (i + 1)), model.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 消費税 10%
		 * 金額列以外のセルを含む
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction10WithIncludeOtherCell() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// セルを選択(1〜5行目，日付〜金額列までを選択)
			table.changeSelection(0, COL.EXPENSE.DATE, false, false);
			table.addRowSelectionInterval(1, 4);
			table.addColumnSelectionInterval(COL.EXPENSE.NAME, COL.EXPENSE.COST);

			// Ctrl+0
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			TableModel model = table.getModel();
			for (int i = 0; i < 5; i++) {
				assertEquals((int) (100 * 1.10 * (i + 1)), model.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 消費税 10%
		 * 金額列に空白を含む
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction10WithIncludeEmptyCost() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 3行目の金額をクリア
			TableModel model = table.getModel();
			model.setValueAt(null, 2, COL.EXPENSE.COST);

			// 金額セルを選択
			table.changeSelection(0, COL.EXPENSE.COST, false, false);
			table.addRowSelectionInterval(1, 4);

			// Ctrl+0
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			for (int i = 0; i < 5; i++) {
				if (i == 2) {
					assertNull(model.getValueAt(i, COL.EXPENSE.COST));
				} else {
					assertEquals((int) (100 * 1.10 * (i + 1)), model.getValueAt(i, COL.EXPENSE.COST));
				}
			}
		}

		/**
		 * 消費税 10%
		 * 四捨五入の確認
		 * 
		 * @throws AbException
		 */
		@Test
		public void taxAction10WithRoundingOff() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 金額を設定(切り捨て(0.4)，切り上げ(.5) 切り上げ(.9〜))
			TableModel model = table.getModel();
			model.setValueAt(104, 0, COL.EXPENSE.COST);
			model.setValueAt(105, 1, COL.EXPENSE.COST);
			model.setValueAt(119, 2, COL.EXPENSE.COST);

			// 金額セルを選択
			table.changeSelection(0, COL.EXPENSE.COST, false, false);
			table.addRowSelectionInterval(1, 2);

			// Ctrl+0
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// 金額を確認
			assertEquals(114, model.getValueAt(0, COL.EXPENSE.COST));
			assertEquals(116, model.getValueAt(1, COL.EXPENSE.COST));
			assertEquals(131, model.getValueAt(2, COL.EXPENSE.COST));
		}
	}

	/**
	 * 金額列の合計
	 */
	@Nested
	class SummaryAction {

		/**
		 * 金額列の合計
		 * 範囲選択なし
		 * 
		 * @throws AbException
		 */
		@Test
		public void summaryActionWithNoSelection() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// セル選択をクリア
			table.clearSelection();

			// Ctrl+t
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// ダイアログが表示されないことを確認
			JDialog dialog = ComponentFindersKt.findWindow(JDialog.class, (w) -> {
				return w.isVisible();
			});
			assertNull(dialog);
		}

		/**
		 * 金額列の合計
		 * 金額列の選択なし
		 * 
		 * @throws AbException
		 */
		@Test
		public void summaryActionWithoutCostColumn() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 金額列以外を選択(1〜5行目，日付〜種別列までを選択)
			table.changeSelection(0, COL.EXPENSE.DATE, false, false);
			table.addRowSelectionInterval(1, 4);
			table.addColumnSelectionInterval(COL.EXPENSE.NAME, COL.EXPENSE.TYPE);

			// Ctrl+t
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// ダイアログが表示されないことを確認
			JDialog dialog = ComponentFindersKt.findWindow(JDialog.class, (w) -> {
				return w.isVisible();
			});
			assertNull(dialog);
		}

		/**
		 * 金額列の合計
		 * 金額単一セル
		 * 
		 * @throws AbException
		 */
		@Test
		public void summaryActionWithSingleCell() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 金額セルを選択
			table.changeSelection(0, COL.EXPENSE.COST, false, false);

			// Ctrl+t
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// メッセージダイアログを取得
			JDialog dialog = ComponentFindersKt.findWindow(JDialog.class, (w) -> {
				return w.isVisible();
			});
			assertNotNull(dialog);

			// 正しいダイアログが取得できたか確認
			assertEquals("合計", dialog.getTitle());

			// GUI と違ってデフォルトロケールは取得できないので明示的に取得
			Locale locale = Locale.forLanguageTag("ja-JP");

			// 合計を確認
			JLabel label = ComponentFindersKt.findChild(dialog, JLabel.class, "OptionPane.label");
			assertNotNull(label);
			assertEquals(UTL.toCurrency(100, locale), label.getText());

			// OKボタンをクリックしてダイアログを閉じる
			ChildInteractionsKt.clickChild(dialog, JButton.class, "OptionPane.button", "OK", true);
			SwingUtilitiesKt.flushEdt();
		}

		/**
		 * 金額列の合計
		 * 金額複数セル
		 * 
		 * @throws AbException
		 */
		@Test
		public void summaryActionWithMultiCell() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 金額セルを選択
			table.changeSelection(0, COL.EXPENSE.COST, false, false);
			table.addRowSelectionInterval(1, 4);

			// Ctrl+t
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// メッセージダイアログを取得
			JDialog dialog = ComponentFindersKt.findWindow(JDialog.class, (w) -> {
				return w.isVisible();
			});
			assertNotNull(dialog);

			// 正しいダイアログが取得できたか確認
			assertEquals("合計", dialog.getTitle());

			// GUI と違ってデフォルトロケールは取得できないので明示的に取得
			Locale locale = Locale.forLanguageTag("ja-JP");

			// 合計を確認
			JLabel label = ComponentFindersKt.findChild(dialog, JLabel.class, "OptionPane.label");
			assertNotNull(label);
			assertEquals(UTL.toCurrency(1500, locale), label.getText());

			// OKボタンをクリックしてダイアログを閉じる
			ChildInteractionsKt.clickChild(dialog, JButton.class, "OptionPane.button", "OK", true);
			SwingUtilitiesKt.flushEdt();
		}

		/**
		 * 金額列の合計
		 * 金額列以外のセルを含む
		 * 
		 * @throws AbException
		 */
		@Test
		public void summaryActionWithIncludeOtherCell() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// セルを選択(1〜5行目，日付〜金額列までを選択)
			table.changeSelection(0, COL.EXPENSE.DATE, false, false);
			table.addRowSelectionInterval(1, 4);
			table.addColumnSelectionInterval(COL.EXPENSE.NAME, COL.EXPENSE.COST);

			// Ctrl+t
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// メッセージダイアログを取得
			JDialog dialog = ComponentFindersKt.findWindow(JDialog.class, (w) -> {
				return w.isVisible();
			});
			assertNotNull(dialog);

			// 正しいダイアログが取得できたか確認
			assertEquals("合計", dialog.getTitle());

			// GUI と違ってデフォルトロケールは取得できないので明示的に取得
			Locale locale = Locale.forLanguageTag("ja-JP");

			// 合計を確認
			JLabel label = ComponentFindersKt.findChild(dialog, JLabel.class, "OptionPane.label");
			assertNotNull(label);
			assertEquals(UTL.toCurrency(1500, locale), label.getText());

			// OKボタンをクリックしてダイアログを閉じる
			ChildInteractionsKt.clickChild(dialog, JButton.class, "OptionPane.button", "OK", true);
			SwingUtilitiesKt.flushEdt();
		}

		/**
		 * 金額列の合計
		 * 金額列に空白を含む
		 * 
		 * @throws AbException
		 */
		@Test
		public void summaryActionWithIncludeEmptyCost() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_TAX_ACTION);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 3行目の金額をクリア
			TableModel model = table.getModel();
			model.setValueAt(null, 2, COL.EXPENSE.COST);

			// 金額セルを選択
			table.changeSelection(0, COL.EXPENSE.COST, false, false);
			table.addRowSelectionInterval(1, 4);

			// Ctrl+t
			SwingUtilities.invokeLater(() -> {
				KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
				table.getActionForKeyStroke(keyStroke).actionPerformed(new ActionEvent(table, 0, null));
			});
			SwingUtilitiesKt.flushEdt();

			// メッセージダイアログを取得
			JDialog dialog = ComponentFindersKt.findWindow(JDialog.class, (w) -> {
				return w.isVisible();
			});
			assertNotNull(dialog);

			// 正しいダイアログが取得できたか確認
			assertEquals("合計", dialog.getTitle());

			// GUI と違ってデフォルトロケールは取得できないので明示的に取得
			Locale locale = Locale.forLanguageTag("ja-JP");

			// 合計を確認
			JLabel label = ComponentFindersKt.findChild(dialog, JLabel.class, "OptionPane.label");
			assertNotNull(label);
			assertEquals(UTL.toCurrency(1200, locale), label.getText());

			// OKボタンをクリックしてダイアログを閉じる
			ChildInteractionsKt.clickChild(dialog, JButton.class, "OptionPane.button", "OK", true);
			SwingUtilitiesKt.flushEdt();
		}
	}

	/**
	 * 登録ボタン
	 */
	@Nested
	class EntryButton {

		/**
		 * 登録ボタン
		 * 
		 * @throws AbException
		 */
		@Test
		public void entryButtonWithSuccess() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_ENTRY);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 行追加ボタンをクリック
			clickAddRowButton(frame);

			// 支出情報を入力
			TableModel model = table.getModel();
			String[] types = new String[] { "交通費", "交際費", "遊行費", "通信費" };
			for (int i = 2; i <= 5; i++) {
				LocalDate date = LocalDate.of(2023, 10, i);
				String name = String.format("name%02d", i);
				String type = types[i - 2];
				int cost = 100 * i;
				String note = String.format("note%02d", i);
				model.setValueAt(date, i - 1, COL.EXPENSE.DATE);
				model.setValueAt(name, i - 1, COL.EXPENSE.NAME);
				model.setValueAt(type, i - 1, COL.EXPENSE.TYPE);
				model.setValueAt(cost, i - 1, COL.EXPENSE.COST);
				model.setValueAt(note, i - 1, COL.EXPENSE.NOTE);
			}

			// 登録ボタンをクリック
			clickEntryButton(frame);

			// メッセージダイアログを取得
			JDialog dialog = ComponentFindersKt.findWindow(JDialog.class, (w) -> {
				return w.isVisible();
			});
			assertNotNull(dialog);

			// 正しいダイアログが取得できたか確認
			assertEquals("登録完了", dialog.getTitle());

			// メッセージを確認
			JLabel label = ComponentFindersKt.findChild(dialog, JLabel.class, "OptionPane.label");
			assertNotNull(label);
			assertEquals(MESSAGE.ENTRY_COMPLETE, label.getText());

			// OKボタンをクリックしてダイアログを閉じる
			ChildInteractionsKt.clickChild(dialog, JButton.class, "OptionPane.button", "OK", true);
			SwingUtilitiesKt.flushEdt();

			// DBファイルを確認
			FileAssert.assertEquals(Paths.get(DB_FILE_ENTRY_EXPECTED).toFile(), Paths.get(DB_FILE_ENTRY).toFile());
		}

		/**
		 * 登録ボタン
		 * 支出情報0件
		 * 
		 * @throws AbException
		 * @throws IOException
		 */
		@Test
		public void entryButtonWithNoData() throws AbException, IOException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_ENTRY_NO_DATA);

			// 登録ボタンをクリック
			clickEntryButton(frame);

			// メッセージダイアログを取得
			JDialog dialog = ComponentFindersKt.findWindow(JDialog.class, (w) -> {
				return w.isVisible();
			});
			assertNotNull(dialog);

			// 正しいダイアログが取得できたか確認
			assertEquals("登録完了", dialog.getTitle());

			// メッセージを確認
			JLabel label = ComponentFindersKt.findChild(dialog, JLabel.class, "OptionPane.label");
			assertNotNull(label);
			assertEquals(MESSAGE.ENTRY_COMPLETE, label.getText());

			// OKボタンをクリックしてダイアログを閉じる
			ChildInteractionsKt.clickChild(dialog, JButton.class, "OptionPane.button", "OK", true);
			SwingUtilitiesKt.flushEdt();

			// DBファイルを確認
			AbTestTool.createDBFile(DB_FILE_EMPTY, new ArrayList<AbExpense>());
			FileAssert.assertEquals(Paths.get(DB_FILE_EMPTY).toFile(), Paths.get(DB_FILE_ENTRY_NO_DATA).toFile());
		}

		/**
		 * 登録ボタン
		 * 空欄のある行は無視される
		 * 
		 * @throws AbException
		 */
		@Test
		public void entryButtonWithIgnoreEmptyCell() throws AbException {

			// 画面を表示
			AbFormMain frame = showFormMain(DB_FILE_ENTRY_IGNORED);

			// 支出情報テーブルを取得
			JTable table = getExpenseTable(frame);
			assertNotNull(table);

			// 行追加ボタンをクリック
			clickAddRowButton(frame);

			// 支出情報を入力
			TableModel model = table.getModel();
			for (int i = 1; i <= 6; i++) {
				LocalDate date = LocalDate.of(2023, 10, i);
				String name = String.format("name%02d", i);
				String type = "食費";
				Integer cost = 100 * i;
				String note = "備考";

				// 日付は画面上から空欄にできないため，それ以外の列で確認
				switch (i) {
					case 3: name = ""; break;
					case 4: type = ""; break;
					case 5: cost = null; break;
					default:
						break;
				}
				model.setValueAt(date, i - 1, COL.EXPENSE.DATE);
				model.setValueAt(name, i - 1, COL.EXPENSE.NAME);
				model.setValueAt(type, i - 1, COL.EXPENSE.TYPE);
				model.setValueAt(cost, i - 1, COL.EXPENSE.COST);
				model.setValueAt(note, i - 1, COL.EXPENSE.NOTE);
			}

			// 登録ボタンをクリック
			clickEntryButton(frame);

			// メッセージダイアログを取得
			JDialog dialog = ComponentFindersKt.findWindow(JDialog.class, (w) -> {
				return w.isVisible();
			});
			assertNotNull(dialog);

			// 正しいダイアログが取得できたか確認
			assertEquals("登録完了", dialog.getTitle());

			// メッセージを確認
			JLabel label = ComponentFindersKt.findChild(dialog, JLabel.class, "OptionPane.label");
			assertNotNull(label);
			assertEquals(MESSAGE.ENTRY_COMPLETE, label.getText());

			// OKボタンをクリックしてダイアログを閉じる
			ChildInteractionsKt.clickChild(dialog, JButton.class, "OptionPane.button", "OK", true);
			SwingUtilitiesKt.flushEdt();

			// DBファイルを確認
			FileAssert.assertEquals(Paths.get(DB_FILE_ENTRY_EXPECTED2).toFile(), Paths.get(DB_FILE_ENTRY_IGNORED).toFile());
		}
	}
}
