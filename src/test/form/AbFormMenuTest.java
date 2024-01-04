// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.alyssaburlton.swingtest.ComponentFindersKt;
import com.github.alyssaburlton.swingtest.SwingUtilitiesKt;

import abook.common.AbException;
import abook.form.AbFormMain;
import abook.form.AbFormVersion;
import abook.form.subform.AbSubformEnergy;
import abook.form.subform.AbSubformSearch;
import test.tool.AbTestTool;
import test.tool.AbFormAbstractMain;
import test.tool.AbTestCleanupExtension;

/**
 * テスト:メニューバー
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbFormMenuTest extends AbFormAbstractMain {

	/** DBファイル */
	private static final String DB_FILE = "AbFormMenuTest.db";

	@AfterAll
	public static void testFixtureTearDown() throws IOException {

		// DBファイルが存在するなら削除
		AbTestTool.deleteDBFile(DB_FILE);
	}

	/**
	 * 終了メニュー
	 * 
	 * @throws AbException
	 */
	@Ignore
	public void menuQuitClick() throws AbException {
		// System.exitのテストはできないので無視する
	}

	/**
	 * 検索メニュー
	 * 
	 * @throws AbException
	 */
	@Test
	public void menuSearchClick() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// 検索メニューを取得
		JMenuItem search = getMenuItemSearch(frame);

		// 検索メニューをクリック
		SwingUtilities.invokeLater(() -> {
			ActionListener listener = search.getActionListeners()[0];
			listener.actionPerformed(new ActionEvent(search, 0, null));
		});
		SwingUtilitiesKt.flushEdt();

		// 検索サブフォームを取得
		JDialog subform = ComponentFindersKt.findWindow(AbSubformSearch.class, (w) -> {
			return w.isVisible();
		});
		assertNotNull(subform);

		// 正しく取得できたか確認
		assertEquals("支出検索", subform.getTitle());

		// 後始末
		SwingUtilities.invokeLater(() -> {
			subform.setVisible(false);
			subform.dispose();
		});
		SwingUtilitiesKt.flushEdt();
	}

	/**
	 * 光熱費メニュー
	 * 
	 * @throws AbException
	 */
	@Test
	public void menuEnergyClick() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// 光熱費メニューを取得
		JMenuItem energy = getMenuItemEnergy(frame);

		// 光熱費メニューをクリック
		SwingUtilities.invokeLater(() -> {
			ActionListener listener = energy.getActionListeners()[0];
			listener.actionPerformed(new ActionEvent(energy, 0, null));
		});
		SwingUtilitiesKt.flushEdt();

		// 光熱費サブフォームを取得
		JDialog subform = ComponentFindersKt.findWindow(AbSubformEnergy.class, (w) -> {
			return w.isVisible();
		});
		assertNotNull(subform);

		// 正しく取得できたか確認
		assertEquals("光熱費", subform.getTitle());

		// 後始末
		SwingUtilities.invokeLater(() -> {
			subform.setVisible(false);
			subform.dispose();
		});
		SwingUtilitiesKt.flushEdt();
	}

	/**
	 * バージョン情報メニュー
	 * 
	 * @throws AbException
	 */
	@Test
	public void menuVersionClick() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// バージョン情報メニューを取得
		JMenuItem version = getMenuItemVersion(frame);

		// バージョン情報メニューをクリック
		SwingUtilities.invokeLater(() -> {
			ActionListener listener = version.getActionListeners()[0];
			listener.actionPerformed(new ActionEvent(version, 0, null));
		});
		SwingUtilitiesKt.flushEdt();

		// バージョン情報フォームを取得
		JDialog form = ComponentFindersKt.findWindow(AbFormVersion.class, (w) -> {
			return w.isVisible();
		});
		assertNotNull(form);

		// 正しく取得できたか確認
		assertEquals("Abookのバージョン情報", form.getTitle());

		// 後始末
		SwingUtilities.invokeLater(() -> {
			form.setVisible(false);
			form.dispose();
		});
		SwingUtilitiesKt.flushEdt();
	}
}
