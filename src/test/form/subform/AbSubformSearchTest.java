// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.form.subform;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.alyssaburlton.swingtest.ChildInteractionsKt;
import com.github.alyssaburlton.swingtest.ComponentFindersKt;
import com.github.alyssaburlton.swingtest.EventFactoryKt;

import abook.common.AbConstant.COL;
import abook.common.AbConstant.COLOR;
import abook.common.AbConstant.FMT;
import abook.common.AbConstant.TYPE;
import abook.common.AbException;
import abook.expense.AbExpense;
import abook.form.subform.AbSubformSearch;
import test.tool.AbFormAbstract;
import test.tool.AbTestCleanupExtension;
import test.tool.AbTestTool;

/**
 * テスト:検索サブフォーム
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbSubformSearchTest extends AbFormAbstract {

	/** 支出情報リスト */
	private static List<AbExpense> expenses;

	@BeforeAll
	public static void testFixtureSetup() throws AbException, IOException {
		expenses = AbTestTool.getExpenses(new ArrayList<String[]>() {
			{
				add(new String[] { "2023-10-01", "ＣおにぎりＤ", TYPE.FOOD, "500" });
				add(new String[] { "2023-10-02", "Ｂおにぎり　", TYPE.FOOD, "400" });
				add(new String[] { "2023-10-03", "おにぎり　　", TYPE.FOOD, "100", "note1" });
				add(new String[] { "2023-10-04", "おにぎり　　", TYPE.FOOD, "200" });
				add(new String[] { "2023-10-05", "おにぎりＡ　", TYPE.FOOD, "300" });
				add(new String[] { "2023-10-06", "おにぎり　　", TYPE.OTFD, "101" });
				add(new String[] { "2023-10-07", "おにぎり　　", TYPE.OTFD, "202" });
				add(new String[] { "2023-10-08", "おにぎりＡ　", TYPE.TRFC, "303" });
				add(new String[] { "2023-10-09", "Ｂおにぎり　", TYPE.FRND, "404" });
				add(new String[] { "2023-10-10", "ＣおにぎりＤ", TYPE.INSU, "505" });
			}
		});
	}

	/**
	 * 画面を表示
	 * テストのため一時的にモーダルをOFF
	 * 
	 * @param expenses 支出情報リスト
	 * @return 検索サブフォーム
	 */
	private AbSubformSearch showForm(List<AbExpense> expenses) {
		AbSubformSearch frame = new AbSubformSearch(expenses);
		frame.setModal(false);
		frame.setVisible(true);
		return frame;
	}

	/**
	 * コンボボックスを取得
	 * 
	 * @param frame 親のフレーム
	 * @param name  ID
	 * @return コンボボックス
	 */
	@SuppressWarnings("unchecked")
	private JComboBox<String> getComboBox(JDialog frame, String name) {
		return ComponentFindersKt.findChild(frame, JComboBox.class, name);
	}

	/**
	 * テーブルを取得
	 * 
	 * @param frame 親のフレーム
	 * @param name  ID
	 * @return テーブル
	 */
	private JTable getTable(JDialog frame, String name) {
		return ComponentFindersKt.findChild(frame, JTable.class, name);
	}

	/**
	 * 初期表示
	 */
	@Nested
	class Initialize {

		/**
		 * 画面を表示
		 * 引数:支出情報リストがNULL
		 */
		@Test
		public void AbSubformSearchWithNullExpenses() {
			// アプリでは処理しない
			assertThrows(NullPointerException.class,
					() -> new AbSubformSearch(null)
			);
		}

		/**
		 * 画面を表示
		 * 引数:支出情報リストが空
		 */
		@Test
		public void AbSubformSearchWithEmptyExpenses() {
			assertDoesNotThrow(() -> new AbSubformSearch(new ArrayList<AbExpense>()));
		}

		/**
		 * 初期表示
		 * タイトルを確認
		 */
		@Test
		public void initializeWithTitle() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// タイトルを確認
			assertEquals("支出検索", frame.getTitle());
		}

		/**
		 * 初期表示
		 * 名称コンボボックスを確認(支出情報リストが空)
		 */
		@Test
		public void initializeWithNameComboBoxEmpty() {

			// 画面を表示
			AbSubformSearch frame = showForm(new ArrayList<AbExpense>());

			// 名称コンボボックスを取得
			JComboBox<String> cmb = getComboBox(frame, "CmbName");
			assertNotNull(cmb);

			// 件数を確認
			assertEquals(1, cmb.getItemCount());

			// 名称を確認
			assertEquals("", cmb.getItemAt(0));

			// 初期選択を確認
			assertEquals(0, cmb.getSelectedIndex());
		}

		/**
		 * 初期表示
		 * 名称コンボボックスを確認
		 */
		@Test
		public void initializeWithNameComboBox() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 名称コンボボックスを取得
			JComboBox<String> cmb = getComboBox(frame, "CmbName");
			assertNotNull(cmb);

			// 名称はソートされる
			String[] expected = new String[] {
					"",
					"おにぎり",
					"おにぎりＡ",
					"Ｂおにぎり",
					"ＣおにぎりＤ",
			};

			// 件数を確認
			assertEquals(expected.length, cmb.getItemCount());

			// 名称を確認
			for (int i = 0; i < expected.length; i++) {
				assertEquals(expected[i], cmb.getItemAt(i));
			}

			// 初期選択を確認
			assertEquals(0, cmb.getSelectedIndex());
		}

		/**
		 * 初期表示
		 * 種別コンボボックスを確認
		 */
		@Test
		public void initializeWithTypeComboBox() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 種別コンボボックスを取得
			JComboBox<String> cmb = getComboBox(frame, "CmbType");
			assertNotNull(cmb);

			List<String> expected = new ArrayList<String>(TYPE.EXPENSES);
			expected.add(0, "");

			// 件数を確認
			assertEquals(expected.size(), cmb.getItemCount());

			// 種別を確認
			for (int i = 0; i < expected.size(); i++) {
				assertEquals(expected.get(i), cmb.getItemAt(i));
			}

			// 初期選択を確認
			assertEquals(0, cmb.getSelectedIndex());
		}

		/**
		 * 初期表示
		 * 支出情報テーブルのカラムを確認
		 */
		@Test
		public void initializeWithColumnName() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			// 収支情報テーブルのカラムを確認
			assertEquals("日付", table.getColumnName(0));
			assertEquals("名称", table.getColumnName(1));
			assertEquals("種別", table.getColumnName(2));
			assertEquals("金額", table.getColumnName(3));
		}

		/**
		 * 初期表示
		 * 支出情報テーブルの件数を確認
		 */
		@Test
		public void initializeWithExpenseTable() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			// 件数を確認
			assertEquals(0, table.getRowCount());
		}
	}

	/**
	 * 検索
	 */
	@Nested
	class Search {

		/**
		 * 検索
		 * 名称:なし
		 * 種別:なし
		 */
		@Test
		public void searchWithEmptyName() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			// 件数を確認
			assertEquals(expenses.size(), table.getRowCount());

			// 検索結果を確認
			for (int i = 0; i < expenses.size(); i++) {
				assertEquals(expenses.get(i).getDate(), table.getValueAt(i, COL.EXPENSE.DATE));
				assertEquals(expenses.get(i).getName(), table.getValueAt(i, COL.EXPENSE.NAME));
				assertEquals(expenses.get(i).getType(), table.getValueAt(i, COL.EXPENSE.TYPE));
				assertEquals(expenses.get(i).getCost(), table.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 検索
		 * 名称:ヒットしない
		 * 種別:なし
		 */
		@Test
		public void searchWithNotFound() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 名称コンボボックスを取得
			JComboBox<String> cmb = getComboBox(frame, "CmbName");
			assertNotNull(cmb);

			// 名称を設定
			cmb.setSelectedItem("not found");

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			// 件数を確認
			assertEquals(0, table.getRowCount());
		}

		/**
		 * 検索
		 * 名称:完全一致
		 * 種別:なし
		 * 
		 * @throws AbException
		 */
		@Test
		public void searchWithExactMatch() throws AbException {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 名称コンボボックスを取得
			JComboBox<String> cmb = getComboBox(frame, "CmbName");
			assertNotNull(cmb);

			// 名称を設定
			cmb.setSelectedItem("おにぎり");

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			List<AbExpense> expected = AbTestTool.getExpenses(new ArrayList<String[]>() {
				{
					add(new String[] { "2023-10-03", "おにぎり", TYPE.FOOD, "100" });
					add(new String[] { "2023-10-04", "おにぎり", TYPE.FOOD, "200" });
					add(new String[] { "2023-10-06", "おにぎり", TYPE.OTFD, "101" });
					add(new String[] { "2023-10-07", "おにぎり", TYPE.OTFD, "202" });
				}
			});

			// 件数を確認
			assertEquals(expected.size(), table.getRowCount());

			// 検索結果を確認
			for (int i = 0; i < expected.size(); i++) {
				assertEquals(expected.get(i).getDate(), table.getValueAt(i, COL.EXPENSE.DATE));
				assertEquals(expected.get(i).getName(), table.getValueAt(i, COL.EXPENSE.NAME));
				assertEquals(expected.get(i).getType(), table.getValueAt(i, COL.EXPENSE.TYPE));
				assertEquals(expected.get(i).getCost(), table.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 検索
		 * 名称:部分一致
		 * 種別:なし
		 */
		@Test
		public void searchWithPartialMatch() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 名称コンボボックスを取得
			JComboBox<String> cmb = getComboBox(frame, "CmbName");
			assertNotNull(cmb);

			// 名称を設定
			cmb.setSelectedItem("おにぎ");

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			// 件数を確認
			assertEquals(expenses.size(), table.getRowCount());

			// 検索結果を確認
			for (int i = 0; i < expenses.size(); i++) {
				assertEquals(expenses.get(i).getDate(), table.getValueAt(i, COL.EXPENSE.DATE));
				assertEquals(expenses.get(i).getName(), table.getValueAt(i, COL.EXPENSE.NAME));
				assertEquals(expenses.get(i).getType(), table.getValueAt(i, COL.EXPENSE.TYPE));
				assertEquals(expenses.get(i).getCost(), table.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 検索
		 * 名称:前方一致
		 * 種別:なし
		 * 
		 * @throws AbException
		 */
		@Test
		public void searchWithPartialMatchForward() throws AbException {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 名称コンボボックスを取得
			JComboBox<String> cmb = getComboBox(frame, "CmbName");
			assertNotNull(cmb);

			// 名称を設定
			cmb.setSelectedItem("Ｂ");

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			List<AbExpense> expected = AbTestTool.getExpenses(new ArrayList<String[]>() {
				{
					add(new String[] { "2023-10-02", "Ｂおにぎり　", TYPE.FOOD, "400" });
					add(new String[] { "2023-10-09", "Ｂおにぎり　", TYPE.FRND, "404" });
				}
			});

			// 件数を確認
			assertEquals(expected.size(), table.getRowCount());

			// 検索結果を確認
			for (int i = 0; i < expected.size(); i++) {
				assertEquals(expected.get(i).getDate(), table.getValueAt(i, COL.EXPENSE.DATE));
				assertEquals(expected.get(i).getName(), table.getValueAt(i, COL.EXPENSE.NAME));
				assertEquals(expected.get(i).getType(), table.getValueAt(i, COL.EXPENSE.TYPE));
				assertEquals(expected.get(i).getCost(), table.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 検索
		 * 名称:後方一致
		 * 種別:なし
		 * 
		 * @throws AbException
		 */
		@Test
		public void searchWithPartialMatchBackward() throws AbException {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 名称コンボボックスを取得
			JComboBox<String> cmb = getComboBox(frame, "CmbName");
			assertNotNull(cmb);

			// 名称を設定
			cmb.setSelectedItem("Ｄ");

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			List<AbExpense> expected = AbTestTool.getExpenses(new ArrayList<String[]>() {
				{
					add(new String[] { "2023-10-01", "ＣおにぎりＤ", TYPE.FOOD, "500" });
					add(new String[] { "2023-10-10", "ＣおにぎりＤ", TYPE.INSU, "505" });
				}
			});

			// 件数を確認
			assertEquals(expected.size(), table.getRowCount());

			// 検索結果を確認
			for (int i = 0; i < expected.size(); i++) {
				assertEquals(expected.get(i).getDate(), table.getValueAt(i, COL.EXPENSE.DATE));
				assertEquals(expected.get(i).getName(), table.getValueAt(i, COL.EXPENSE.NAME));
				assertEquals(expected.get(i).getType(), table.getValueAt(i, COL.EXPENSE.TYPE));
				assertEquals(expected.get(i).getCost(), table.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 検索
		 * 名称:なし
		 * 種別:指定
		 * 
		 * @throws AbException
		 */
		@Test
		public void searchWithType() throws AbException {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 種別コンボボックスを取得
			JComboBox<String> cmb = getComboBox(frame, "CmbType");
			assertNotNull(cmb);

			// 種別を指定
			cmb.setSelectedItem(TYPE.OTFD);

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			List<AbExpense> expected = AbTestTool.getExpenses(new ArrayList<String[]>() {
				{
					add(new String[] { "2023-10-06", "おにぎり　　", TYPE.OTFD, "101" });
					add(new String[] { "2023-10-07", "おにぎり　　", TYPE.OTFD, "202" });
				}
			});

			// 件数を確認
			assertEquals(expected.size(), table.getRowCount());

			// 検索結果を確認
			for (int i = 0; i < expected.size(); i++) {
				assertEquals(expected.get(i).getDate(), table.getValueAt(i, COL.EXPENSE.DATE));
				assertEquals(expected.get(i).getName(), table.getValueAt(i, COL.EXPENSE.NAME));
				assertEquals(expected.get(i).getType(), table.getValueAt(i, COL.EXPENSE.TYPE));
				assertEquals(expected.get(i).getCost(), table.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 検索
		 * 名称:なし
		 * 種別:ヒットしない
		 */
		@Test
		public void searchWithTypeNotFound() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 種別コンボボックスを取得
			JComboBox<String> cmb = getComboBox(frame, "CmbType");
			assertNotNull(cmb);

			// 種別を指定
			cmb.setSelectedItem(TYPE.EARN);

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			// 件数を確認
			assertEquals(0, table.getRowCount());
		}

		/**
		 * 検索
		 * 名称:部分一致
		 * 種別:指定
		 * 
		 * @throws AbException
		 */
		@Test
		public void searchWithPartialNameAndType() throws AbException {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 名称コンボボックスを取得
			JComboBox<String> cmbName = getComboBox(frame, "CmbName");
			assertNotNull(cmbName);

			// 名称を設定
			cmbName.setSelectedItem("おにぎ");

			// 種別コンボボックスを取得
			JComboBox<String> cmbType = getComboBox(frame, "CmbType");
			assertNotNull(cmbType);

			// 種別を指定
			cmbType.setSelectedItem(TYPE.FOOD);

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			List<AbExpense> expected = AbTestTool.getExpenses(new ArrayList<String[]>() {
				{
					add(new String[] { "2023-10-01", "ＣおにぎりＤ", TYPE.FOOD, "500" });
					add(new String[] { "2023-10-02", "Ｂおにぎり　", TYPE.FOOD, "400" });
					add(new String[] { "2023-10-03", "おにぎり　　", TYPE.FOOD, "100", "note1" });
					add(new String[] { "2023-10-04", "おにぎり　　", TYPE.FOOD, "200" });
					add(new String[] { "2023-10-05", "おにぎりＡ　", TYPE.FOOD, "300" });
				}
			});

			// 件数を確認
			assertEquals(expected.size(), table.getRowCount());

			// 検索結果を確認
			for (int i = 0; i < expected.size(); i++) {
				assertEquals(expected.get(i).getDate(), table.getValueAt(i, COL.EXPENSE.DATE));
				assertEquals(expected.get(i).getName(), table.getValueAt(i, COL.EXPENSE.NAME));
				assertEquals(expected.get(i).getType(), table.getValueAt(i, COL.EXPENSE.TYPE));
				assertEquals(expected.get(i).getCost(), table.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 検索
		 * 名称:完全一致
		 * 種別:指定
		 * 
		 * @throws AbException
		 */
		@Test
		public void searchWithMatchNameAndType() throws AbException {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 名称コンボボックスを取得
			JComboBox<String> cmbName = getComboBox(frame, "CmbName");
			assertNotNull(cmbName);

			// 名称を設定
			cmbName.setSelectedItem("おにぎり");

			// 種別コンボボックスを取得
			JComboBox<String> cmbType = getComboBox(frame, "CmbType");
			assertNotNull(cmbType);

			// 種別を指定
			cmbType.setSelectedItem(TYPE.FOOD);

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			List<AbExpense> expected = AbTestTool.getExpenses(new ArrayList<String[]>() {
				{
					add(new String[] { "2023-10-03", "おにぎり　　", TYPE.FOOD, "100", "note1" });
					add(new String[] { "2023-10-04", "おにぎり　　", TYPE.FOOD, "200" });
				}
			});

			// 件数を確認
			assertEquals(expected.size(), table.getRowCount());

			// 検索結果を確認
			for (int i = 0; i < expected.size(); i++) {
				assertEquals(expected.get(i).getDate(), table.getValueAt(i, COL.EXPENSE.DATE));
				assertEquals(expected.get(i).getName(), table.getValueAt(i, COL.EXPENSE.NAME));
				assertEquals(expected.get(i).getType(), table.getValueAt(i, COL.EXPENSE.TYPE));
				assertEquals(expected.get(i).getCost(), table.getValueAt(i, COL.EXPENSE.COST));
			}
		}

		/**
		 * 検索
		 * スクロールバーの確認
		 */
		@Test
		public void searchWithScrollBar() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// スクロール領域を取得
			JScrollPane scrollPane = ComponentFindersKt.findChild(frame, JScrollPane.class, "ScrollExpenseTable");
			assertNotNull(scrollPane);

			// スクロールバーを取得
			JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
			assertNotNull(scrollBar);

			// スクロール位置を確認
			assertEquals(0, scrollBar.getValue());
		}

		/**
		 * 背景色の確認
		 */
		@Test
		public void backgroundColorWithNote() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			// 背景色の確認
			for (int row = 0; row < table.getRowCount(); row++) {
				for (int col = COL.EXPENSE.DATE; col <= COL.EXPENSE.COST; col++) {
					Component component = getTableCellRendererComponent(table, row, col);
					if (row == 2) {
						assertEquals(COLOR.NOTE_BACKGROUND, component.getBackground());
					} else {
						assertEquals(UIManager.getColor("Table.background"), component.getBackground());
					}
				}
			}
		}

		/**
		 * ツールチップを確認
		 * 備考あり
		 */
		@Test
		public void getTooltipWithNote() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			// マウスイベント
			MouseEvent e = EventFactoryKt.makeMouseEvent(frame, 0, 151, 42);

			// ツールチップを確認
			String tooltip = table.getToolTipText(e);
			assertEquals(String.format(FMT.NOTE, "note1"), tooltip);
		}

		/**
		 * ツールチップを確認
		 * 備考なし
		 */
		@Test
		public void getTooltipWithNothingNote() {

			// 画面を表示
			AbSubformSearch frame = showForm(expenses);

			// 検索ボタンをクリック
			ChildInteractionsKt.clickChild(frame, JButton.class, null, "検索", true);

			// 支出情報テーブルを取得
			JTable table = getTable(frame, "ExpenseTable");
			assertNotNull(table);

			// マウスイベント
			MouseEvent e = EventFactoryKt.makeMouseEvent(frame, 0, 151, 75);

			// ツールチップを確認
			String tooltip = table.getToolTipText(e);
			assertNull(tooltip);
		}
	}
}
