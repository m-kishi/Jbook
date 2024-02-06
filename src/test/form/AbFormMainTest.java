// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import abook.common.AbException;
import abook.common.AbManager.MESSAGE;
import abook.form.AbFormMain;
import test.tool.AbFormAbstractMain;
import test.tool.AbTestCleanupExtension;
import test.tool.AbTestTool;

/**
 * テスト:メイン画面フォーム
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbFormMainTest extends AbFormAbstractMain {

	/** DBファイル */
	private static final String DB_FILE = "AbFormMainTest.db";

	/** DBファイル */
	private static final String DB_FILE_INVALID = "AbFormMainTestInvalid.db";

	@BeforeAll
	public static void testFixtureSetup() throws AbException, IOException {

		// 支出情報リスト
		List<String[]> expected = new ArrayList<String[]>() {
			{
				add(new String[] { "2023-10-01", "name1", "食費", "100", "note1" });
			}
		};

		// DBファイルを作成
		AbTestTool.createDBFileFromParams(DB_FILE, expected);
		AbTestTool.createInvalidDate(DB_FILE_INVALID);
	}

	@AfterAll
	public static void testFixtureTearDown() throws IOException {

		// DBファイルが存在するなら削除
		AbTestTool.deleteDBFile(DB_FILE);
		AbTestTool.deleteDBFile(DB_FILE_INVALID);
	}

	/**
	 * 画面を表示
	 * 
	 * @throws AbException
	 */
	@Test
	public void abFormMain() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);
		assertTrue(frame.isVisible());

		// タブを取得
		JTabbedPane tab = getTab(frame);
		assertNotNull(tab);

		// タブの数を確認
		assertEquals(6, tab.getTabCount());

		// 初期タブを確認
		assertEquals(0, tab.getSelectedIndex());

		// タブのタイトルを確認
		assertEquals("支出", tab.getTitleAt(0));
		assertEquals("月次", tab.getTitleAt(1));
		assertEquals("推移", tab.getTitleAt(2));
		assertEquals("収支", tab.getTitleAt(3));
		assertEquals("秘密", tab.getTitleAt(4));
		assertEquals("投資", tab.getTitleAt(5));
	}

	/**
	 * 画面を表示
	 * DBファイルの日付が正しくない
	 */
	@Test
	public void abFormMainWithLoadError() {

		// 画面を表示
		AbException ex = assertThrows(AbException.class,
				() -> showFormMain(DB_FILE_INVALID)
		);

		// メッセージを確認
		String message = String.format(MESSAGE.DB_FILE_LOAD, 1, MESSAGE.DATE_FORMAT);
		assertEquals(message, ex.getMessage());
	}
}
