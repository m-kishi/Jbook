// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import abook.common.AbException;
import abook.common.AbManager.MESSAGE;
import abook.common.AbUtility.MSG;
import abook.form.subform.AbSubformSearch;
import abook.property.AbProperty;

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

		JMenu menuFile = new JMenu("ファイル");
		JMenu menuData = new JMenu("データ");
		JMenu menuHelp = new JMenu("ヘルプ");
		add(menuFile);
		add(menuData);
		add(menuHelp);

		JMenuItem menuItemFile = new JMenuItem("DBファイル");
		JMenuItem menuItemQuit = new JMenuItem("終了");
		JMenuItem menuItemSearch = new JMenuItem("検索");
		JMenuItem menuItemVersion = new JMenuItem("バージョン情報");
		menuItemFile.addActionListener(new FileActionListener());
		menuItemQuit.addActionListener(new QuitActionListener());
		menuItemSearch.addActionListener(new SearchActionListener());
		menuItemVersion.addActionListener(new VersionActionListener());

		menuFile.add(menuItemFile);
		menuFile.add(menuItemQuit);
		menuData.add(menuItemSearch);
		menuHelp.add(menuItemVersion);
	}

	/**
	 * DBファイルメニュー
	 */
	private class FileActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			int state = fileChooser.showOpenDialog(frame);
			if (state == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					AbProperty.storeDBFilePath(file.getAbsolutePath());
					MSG.ok(frame, "確認", MESSAGE.SETTING_COMPLETE);
					System.exit(0);
				} catch (AbException ex) {
					MSG.abort(frame, ex.getMessage());
				}
			}
		}
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
			AbSubformSearch form = new AbSubformSearch(frame, frame.getExpenses());
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
