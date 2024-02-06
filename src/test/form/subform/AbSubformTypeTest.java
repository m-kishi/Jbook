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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.UIManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.alyssaburlton.swingtest.ComponentFindersKt;
import com.github.alyssaburlton.swingtest.EventFactoryKt;

import abook.common.AbConstant.COL;
import abook.common.AbConstant.COLOR;
import abook.common.AbConstant.FMT;
import abook.common.AbConstant.TYPE;
import abook.common.AbException;
import abook.expense.AbExpense;
import abook.form.subform.AbSubformType;
import test.tool.AbFormAbstract;
import test.tool.AbTestCleanupExtension;
import test.tool.AbTestTool;

/**
 * テスト:種別サブフォーム
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbSubformTypeTest extends AbFormAbstract {

	/** 日付 */
	private static LocalDate date;

	/** 支出情報リスト */
	private static List<AbExpense> expenses;

	@BeforeAll
	public static void testFixtureSetup() throws AbException, IOException {
		date = LocalDate.of(2023, 10, 1);
		expenses = AbTestTool.getExpenses(new ArrayList<String[]>() {
			{
				add(new String[] { "2023-09-26", "name01", TYPE.FOOD, "1000" });
				add(new String[] { "2023-09-27", "name02", TYPE.OTFD, "1100" });
				add(new String[] { "2023-09-28", "name03", TYPE.GOOD, "1200" });
				add(new String[] { "2023-10-01", "name04", TYPE.FOOD, "1300" });
				add(new String[] { "2023-10-02", "name05", TYPE.OTFD, "1400" });
				add(new String[] { "2023-10-03", "name06", TYPE.GOOD, "1500" });
				add(new String[] { "2023-10-28", "name07", TYPE.FOOD, "1600" });
				add(new String[] { "2023-10-29", "name08", TYPE.OTFD, "1700" });
				add(new String[] { "2023-10-30", "name09", TYPE.GOOD, "1800" });
				add(new String[] { "2023-10-31", "name10", TYPE.FOOD, "1900" });
				add(new String[] { "2023-10-31", "nameXX", TYPE.FOOD, "2300", "noteXX" });
				add(new String[] { "2023-11-01", "name11", TYPE.FOOD, "2000" });
				add(new String[] { "2023-11-02", "name12", TYPE.OTFD, "2100" });
				add(new String[] { "2023-11-03", "name13", TYPE.GOOD, "2200" });
			}
		});
	}

	/**
	 * 画面を表示
	 * テストのため一時的にモーダルをOFF
	 * 
	 * @param date     日付
	 * @param type     種別
	 * @param expenses 支出情報リスト
	 * @return 種別サブフォーム
	 */
	private AbSubformType showForm(LocalDate date, String type, List<AbExpense> expenses) {
		AbSubformType frame = new AbSubformType(date, type, expenses);
		frame.setModal(false);
		frame.setVisible(true);
		return frame;
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
	 * 画面を表示
	 * 引数:支出情報リストがNULL
	 */
	@Test
	public void AbSubformSearchWithNullExpenses() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> new AbSubformType(date, TYPE.FOOD, null)
		);
	}

	/**
	 * 画面を表示
	 * 引数:支出情報リストが空
	 */
	@Test
	public void AbSubformSearchWithEmptyExpenses() {
		assertDoesNotThrow(() -> new AbSubformType(date, TYPE.FOOD, new ArrayList<AbExpense>()));
	}

	/**
	 * 初期表示
	 * タイトルを確認
	 */
	@Test
	public void initializeWithTitle() {

		// 画面を表示
		AbSubformType frame = showForm(date, TYPE.FOOD, expenses);

		// タイトルを確認
		assertEquals(TYPE.FOOD, frame.getTitle());
	}

	/**
	 * 初期表示
	 * 支出情報テーブルのカラムを確認
	 */
	@Test
	public void initializeWithColumnName() {

		// 画面を表示
		AbSubformType frame = showForm(date, TYPE.FOOD, expenses);

		// 支出情報テーブルを取得
		JTable table = getTable(frame, "ExpenseTable");
		assertNotNull(table);

		// 収支情報テーブルのカラムを確認
		assertEquals("日付", table.getColumnName(0));
		assertEquals("名称", table.getColumnName(1));
		assertEquals("種別", table.getColumnName(2));
		assertEquals("金額", table.getColumnName(3));
		assertEquals("備考", table.getColumnName(4));
	}

	/**
	 * 初期表示
	 * 支出情報テーブルの件数を確認
	 * 
	 * @throws AbException
	 */
	@Test
	public void initializeWithExpenseTable() throws AbException {

		// 画面を表示
		AbSubformType frame = showForm(date, TYPE.FOOD, expenses);

		// 支出情報テーブルを取得
		JTable table = getTable(frame, "ExpenseTable");
		assertNotNull(table);

		List<AbExpense> expected = AbTestTool.getExpenses(new ArrayList<String[]>() {
			{
				add(new String[] { "2023-10-01", "name04", TYPE.FOOD, "1300" });
				add(new String[] { "2023-10-28", "name07", TYPE.FOOD, "1600" });
				add(new String[] { "2023-10-31", "name10", TYPE.FOOD, "1900" });
				add(new String[] { "2023-10-31", "nameXX", TYPE.FOOD, "2300", "noteXX" });
			}
		});

		// 件数を確認
		assertEquals(expected.size(), table.getRowCount());

		// 表示内容を確認
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(expected.get(i).getDate(), table.getValueAt(i, COL.EXPENSE.DATE));
			assertEquals(expected.get(i).getName(), table.getValueAt(i, COL.EXPENSE.NAME));
			assertEquals(expected.get(i).getType(), table.getValueAt(i, COL.EXPENSE.TYPE));
			assertEquals(expected.get(i).getCost(), table.getValueAt(i, COL.EXPENSE.COST));
			assertEquals(expected.get(i).getNote(), table.getValueAt(i, COL.EXPENSE.NOTE));
		}
	}

	/**
	 * 初期表示
	 * 支出情報テーブルの件数を確認(データなし)
	 */
	@Test
	public void initializeWithExpenseTableNoData() {

		// 画面を表示
		AbSubformType frame = showForm(date, TYPE.FOOD, new ArrayList<AbExpense>());

		// 支出情報テーブルを取得
		JTable table = getTable(frame, "ExpenseTable");
		assertNotNull(table);

		// 件数を確認
		assertEquals(0, table.getRowCount());
	}

	/**
	 * 初期表示
	 * 支出情報テーブルの件数を確認(日付範囲外)
	 */
	@Test
	public void initializeWithExpenseTableOutOfDate() {

		// 画面を表示
		LocalDate dt = LocalDate.of(2024, 1, 1);
		AbSubformType frame = showForm(dt, TYPE.FOOD, expenses);

		// 支出情報テーブルを取得
		JTable table = getTable(frame, "ExpenseTable");
		assertNotNull(table);

		// 件数を確認
		assertEquals(0, table.getRowCount());
	}

	/**
	 * 初期表示
	 * 支出情報テーブルの件数を確認(種別なし)
	 */
	@Test
	public void initializeWithExpenseTableNothingType() {

		// 画面を表示
		AbSubformType frame = showForm(date, TYPE.HOUS, expenses);

		// 支出情報テーブルを取得
		JTable table = getTable(frame, "ExpenseTable");
		assertNotNull(table);

		// 件数を確認
		assertEquals(0, table.getRowCount());
	}

	/**
	 * 背景色の確認
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void backgroundColorWithNote() throws InterruptedException {

		// 画面を表示
		AbSubformType frame = showForm(date, TYPE.FOOD, expenses);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// 支出情報テーブルを取得
		JTable table = getTable(frame, "ExpenseTable");
		assertNotNull(table);

		// 背景色の確認
		for (int row = 0; row < table.getRowCount(); row++) {
			for (int col = COL.EXPENSE.DATE; col <= COL.EXPENSE.NOTE; col++) {
				Component component = getTableCellRendererComponent(table, row, col);
				if (row == 3) {
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
		AbSubformType frame = showForm(date, TYPE.FOOD, expenses);

		// 支出情報テーブルを取得
		JTable table = getTable(frame, "ExpenseTable");
		assertNotNull(table);

		// マウスイベント
		MouseEvent e = EventFactoryKt.makeMouseEvent(frame, 0, 151, 57);

		// ツールチップを確認
		String tooltip = table.getToolTipText(e);
		assertEquals(String.format(FMT.NOTE, "noteXX"), tooltip);
	}

	/**
	 * ツールチップを確認
	 * 備考なし
	 */
	@Test
	public void getTooltipWithNothingNote() {

		// 画面を表示
		AbSubformType frame = showForm(date, TYPE.FOOD, expenses);

		// 支出情報テーブルを取得
		JTable table = getTable(frame, "ExpenseTable");
		assertNotNull(table);

		// マウスイベント
		MouseEvent e = EventFactoryKt.makeMouseEvent(frame, 0, 151, 42);

		// ツールチップを確認
		String tooltip = table.getToolTipText(e);
		assertNull(tooltip);
	}
}
