// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.tool;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import com.github.alyssaburlton.swingtest.ChildInteractionsKt;
import com.github.alyssaburlton.swingtest.ComponentFindersKt;

import abook.common.AbException;
import abook.form.AbFormMain;
import abook.form.AbTabSummary.CostLabel;
import abook.form.AbTabSummary.TypeLabel;

/**
 * フォーム用抽象クラス
 * フォームで共通する処理をまとめる
 */
public abstract class AbFormAbstractMain extends AbFormAbstract {

	/**
	 * 画面を表示
	 * 
	 * @param dbFilePath DBファイルパス
	 * @return メイン画面フォーム
	 * @throws AbException
	 */
	protected AbFormMain showFormMain(String dbFilePath) throws AbException {
		AbFormMain frame = new AbFormMain(dbFilePath);
		frame.setVisible(true);
		return frame;
	}

	/**
	 * 終了メニュー取得
	 * 
	 * @param frame 親のフレーム
	 * @return 終了メニュー
	 */
	protected JMenuItem getMenuItemQuit(AbFormMain frame) {
		return getMenuItem(frame, "MenuFile", "MenuItemQuit");
	}

	/**
	 * 検索メニュー取得
	 * 
	 * @param frame 親のフレーム
	 * @return 検索メニュー
	 */
	protected JMenuItem getMenuItemSearch(AbFormMain frame) {
		return getMenuItem(frame, "MenuData", "MenuItemSearch");
	}

	/**
	 * 光熱費メニュー取得
	 * 
	 * @param frame 親のフレーム
	 * @return 光熱費メニュー
	 */
	protected JMenuItem getMenuItemEnergy(AbFormMain frame) {
		return getMenuItem(frame, "MenuData", "MenuItemEnergy");
	}

	/**
	 * バージョン情報メニュー取得
	 * 
	 * @param frame 親のフレーム
	 * @return バージョン情報メニュー
	 */
	protected JMenuItem getMenuItemVersion(AbFormMain frame) {
		return getMenuItem(frame, "MenuHelp", "MenuItemVersion");
	}

	/**
	 * メニュー取得
	 * 
	 * @param frame    親のフレーム
	 * @param menuName メニューID
	 * @param itemName メニューアイテムID
	 * @return メニュー
	 */
	private JMenuItem getMenuItem(AbFormMain frame, String menuName, String itemName) {

		// 画面からメニューバーを取得
		JMenuBar menuBar = ComponentFindersKt.findChild(frame, JMenuBar.class, "AbFormMenu");

		// メニューバーからメニューを取得
		JMenu menu = ComponentFindersKt.findChild(menuBar, JMenu.class, menuName);

		// 該当のメニューアイテムを取得
		for (int i = 0; i < menu.getItemCount(); i++) {
			JMenuItem item = menu.getItem(i);
			if (item.getName().equals(itemName)) {
				return item;
			}
		}

		return null;
	}

	/**
	 * タブを取得
	 * 
	 * @param frame 親のフレーム
	 * @return タブ
	 */
	protected JTabbedPane getTab(AbFormMain frame) {
		return ComponentFindersKt.findChild(frame, JTabbedPane.class, null, null);
	}

	/**
	 * タブを切り替え
	 * 
	 * @param frame 親のフレーム
	 * @param index インデックス
	 */
	protected void changeTab(AbFormMain frame, int index) {
		JTabbedPane tab = getTab(frame);
		tab.setSelectedIndex(index);
	}

	/**
	 * 支出情報テーブルを取得
	 * 
	 * @param frame 親のフレーム
	 * @return 支出情報テーブル
	 */
	protected JTable getExpenseTable(AbFormMain frame) {
		return ComponentFindersKt.findChild(frame, JTable.class, "ExpenseTable");
	}

	/**
	 * 支出タブのスクロール領域を取得
	 * 
	 * @param frame 親のフレーム
	 * @return スクロール領域
	 */
	protected JScrollPane getExpenseScrollPane(AbFormMain frame) {
		return ComponentFindersKt.findChild(frame, JScrollPane.class, "ScrollExpenseTable");
	}

	/**
	 * 行追加ボタンをクリック
	 * 
	 * @param frame 親のフレーム
	 */
	protected void clickAddRowButton(AbFormMain frame) {
		ChildInteractionsKt.clickChild(frame, JButton.class, null, "+");
	}

	/**
	 * 登録ボタンをクリック
	 * 
	 * @param frame 親のフレーム
	 */
	protected void clickEntryButton(AbFormMain frame) {
		ChildInteractionsKt.clickChild(frame, JButton.class, null, "登録", true);
	}

	/**
	 * タイトルを取得
	 * 
	 * @param frame 親のフレーム
	 * @return タイトル
	 */
	protected JLabel getSummaryTitle(AbFormMain frame) {
		return ComponentFindersKt.findChild(frame, JLabel.class, "TabSummaryTitle");
	}

	/**
	 * 前年ボタンをクリック
	 * 
	 * @param frame 親のフレーム
	 */
	protected void clickSummaryPrevYear(AbFormMain frame) {
		ChildInteractionsKt.clickChild(frame, JButton.class, "SummaryPrevYearButton", null, true);
	}

	/**
	 * 前月ボタンをクリック
	 * 
	 * @param frame 親のフレーム
	 */
	protected void clickSummaryPrevMonth(AbFormMain frame) {
		ChildInteractionsKt.clickChild(frame, JButton.class, "SummaryPrevMonthButton", null, true);
	}

	/**
	 * 翌月ボタンをクリック
	 * 
	 * @param frame 親のフレーム
	 */
	protected void clickSummaryNextMonth(AbFormMain frame) {
		ChildInteractionsKt.clickChild(frame, JButton.class, "SummaryNextMonthButton", null, true);
	}

	/**
	 * 翌年ボタンをクリック
	 * 
	 * @param frame 親のフレーム
	 */
	protected void clickSummaryNextYear(AbFormMain frame) {
		ChildInteractionsKt.clickChild(frame, JButton.class, "SummaryNextYearButton", null, true);
	}

	/**
	 * 種別ラベルを取得
	 * 
	 * @param frame 親のフレーム
	 * @param name  ID
	 * @return 種別ラベル
	 */
	protected TypeLabel getTypeLabel(AbFormMain frame, String name) {
		return ComponentFindersKt.findChild(frame, TypeLabel.class, name);
	}

	/**
	 * 金額ラベルを取得
	 * 
	 * @param frame 親のフレーム
	 * @param name  ID
	 * @return 金額ラベル
	 */
	protected CostLabel getCostLabel(AbFormMain frame, String name) {
		return ComponentFindersKt.findChild(frame, CostLabel.class, name);
	}

	/**
	 * タイトルを取得
	 * 
	 * @param frame 親のフレーム
	 * @return タイトル
	 */
	protected JLabel getGraphicTitle(AbFormMain frame) {
		return ComponentFindersKt.findChild(frame, JLabel.class, "TabGraphicTitle");
	}

	/**
	 * 前年ボタンをクリック
	 * 
	 * @param frame 親のフレーム
	 */
	protected void clickGraphicPrevYear(AbFormMain frame) {
		ChildInteractionsKt.clickChild(frame, JButton.class, "GraphicPrevYearButton", null, true);
	}

	/**
	 * 前月ボタンをクリック
	 * 
	 * @param frame 親のフレーム
	 */
	protected void clickGraphicPrevMonth(AbFormMain frame) {
		ChildInteractionsKt.clickChild(frame, JButton.class, "GraphicPrevMonthButton", null, true);
	}

	/**
	 * 翌月ボタンをクリック
	 * 
	 * @param frame 親のフレーム
	 */
	protected void clickGraphicNextMonth(AbFormMain frame) {
		ChildInteractionsKt.clickChild(frame, JButton.class, "GraphicNextMonthButton", null, true);
	}

	/**
	 * 翌年ボタンをクリック
	 * 
	 * @param frame 親のフレーム
	 */
	protected void clickGraphicNextYear(AbFormMain frame) {
		ChildInteractionsKt.clickChild(frame, JButton.class, "GraphicNextYearButton", null, true);
	}

	/**
	 * グラフタブのラベルを取得
	 * 
	 * @param frame 親のフレーム
	 * @param name  ID
	 * @return ラベル
	 */
	protected JLabel getGraphicLabel(AbFormMain frame, String name) {
		return ComponentFindersKt.findChild(frame, JLabel.class, name);
	}

	/**
	 * 収支情報テーブルを取得
	 * 
	 * @param frame 親のフレーム
	 * @return 収支情報テーブル
	 */
	protected JTable getBalanceTable(AbFormMain frame) {
		return ComponentFindersKt.findChild(frame, JTable.class, "BalanceTable");
	}

	/**
	 * 秘密収支情報テーブルを取得
	 * 
	 * @param frame 親のフレーム
	 * @return 秘密収支情報テーブル
	 */
	protected JTable getPrivateTable(AbFormMain frame) {
		return ComponentFindersKt.findChild(frame, JTable.class, "PrivateTable");

	}
}
