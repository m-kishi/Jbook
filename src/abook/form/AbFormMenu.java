// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import abook.form.subform.AbSubformEnergy;
import abook.form.subform.AbSubformSearch;

/**
 * メニューバー
 */
public class AbFormMenu extends JMenuBar {

	/** 親のフレーム */
	private AbFormMain frame;

	/**
	 * コンストラクタ
	 * 
	 * @param frame 親のフレーム
	 */
	public AbFormMenu(AbFormMain frame) {
		super();
		this.frame = frame;
		setName("AbFormMenu");

		JMenu menuFile = createMenu("MenuFile", "ファイル");
		JMenu menuData = createMenu("MenuData", "データ");
		JMenu menuHelp = createMenu("MenuHelp", "ヘルプ");
		add(menuFile);
		add(menuData);
		add(menuHelp);

		JMenuItem menuItemQuit = createMenuItem("MenuItemQuit", "終了", new QuitActionListener());
		JMenuItem menuItemSearch = createMenuItem("MenuItemSearch", "検索", new SearchActionListener());
		JMenuItem menuItemEnergy = createMenuItem("MenuItemEnergy", "光熱費", new EnergyActionListener());
		JMenuItem menuItemVersion = createMenuItem("MenuItemVersion", "バージョン情報", new VersionActionListener());

		menuFile.add(menuItemQuit);
		menuData.add(menuItemSearch);
		menuData.add(menuItemEnergy);
		menuHelp.add(menuItemVersion);
	}

	/**
	 * メニュー生成
	 * 
	 * @param name ID
	 * @param text テキスト
	 * @return メニュー
	 */
	private JMenu createMenu(String name, String text) {
		JMenu menu = new JMenu(text);
		menu.setName(name);
		return menu;
	}

	/**
	 * メニューアイテム生成
	 * 
	 * @param name     ID
	 * @param text     テキスト
	 * @param listener クリック時のアクション
	 * @return メニューアイテム
	 */
	private JMenuItem createMenuItem(String name, String text, ActionListener listener) {
		JMenuItem menuItem = new JMenuItem(text);
		menuItem.setName(name);
		menuItem.addActionListener(listener);
		return menuItem;
	}

	/**
	 * 終了メニュー
	 */
	private class QuitActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/**
	 * 検索メニュー
	 */
	private class SearchActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AbSubformSearch form = new AbSubformSearch(frame.getExpenses());
			form.setVisible(true);
		}
	}

	/**
	 * 光熱費メニュー
	 */
	private class EnergyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AbSubformEnergy form = new AbSubformEnergy(frame.getSummaries());
			form.setVisible(true);
		}
	}

	/**
	 * バージョン情報メニュー
	 */
	private class VersionActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AbFormVersion form = new AbFormVersion();
			form.setVisible(true);
		}
	}
}
