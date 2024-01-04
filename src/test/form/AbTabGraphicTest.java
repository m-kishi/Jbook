// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JLabel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import abook.common.AbException;
import abook.common.AbUtility.UTL;
import abook.form.AbFormMain;
import test.tool.AbTestTool;
import test.tool.AbFormAbstractMain;
import test.tool.AbTestCleanupExtension;

/**
 * テスト:推移タブ
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbTabGraphicTest extends AbFormAbstractMain {

	/** DBファイル */
	private static final String DB_FILE = "AbTabGraphicTest.db";

	@AfterAll
	public static void testFixtureTearDown() throws IOException {

		// DBファイルが存在するなら削除
		AbTestTool.deleteDBFile(DB_FILE);
	}

	/**
	 * タイトル
	 * 初期表示の確認
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithInitial() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now());
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 前年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithPrevYearOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前年ボタンをクリック
		clickGraphicPrevYear(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().minusYears(1));
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 前年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithPrevYearTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前年ボタンをクリック
		clickGraphicPrevYear(frame);
		clickGraphicPrevYear(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().minusYears(2));
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 前年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithPrevYearThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前年ボタンをクリック
		clickGraphicPrevYear(frame);
		clickGraphicPrevYear(frame);
		clickGraphicPrevYear(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().minusYears(3));
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 前月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithPrevMonthOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前月ボタンをクリック
		clickGraphicPrevMonth(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().minusMonths(1));
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 前月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithPrevMonthTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前月ボタンをクリック
		clickGraphicPrevMonth(frame);
		clickGraphicPrevMonth(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().minusMonths(2));
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 前月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithPrevMonthThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前月ボタンをクリック
		clickGraphicPrevMonth(frame);
		clickGraphicPrevMonth(frame);
		clickGraphicPrevMonth(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().minusMonths(3));
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 翌月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithNextMonthOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌月ボタンをクリック
		clickGraphicNextMonth(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().plusMonths(1));
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 翌月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithNextMonthTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌月ボタンをクリック
		clickGraphicNextMonth(frame);
		clickGraphicNextMonth(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().plusMonths(2));
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 翌月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithNextMonthThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌月ボタンをクリック
		clickGraphicNextMonth(frame);
		clickGraphicNextMonth(frame);
		clickGraphicNextMonth(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().plusMonths(3));
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 翌年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithNextYearOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌年ボタンをクリック
		clickGraphicNextYear(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().plusYears(1));
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 翌年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithNextYearTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌年ボタンをクリック
		clickGraphicNextYear(frame);
		clickGraphicNextYear(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().plusYears(2));
		assertEquals(expected, title.getText());
	}

	/**
	 * タイトル
	 * 翌年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void titleWithNextYearThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌年ボタンをクリック
		clickGraphicNextYear(frame);
		clickGraphicNextYear(frame);
		clickGraphicNextYear(frame);

		// タイトルを取得
		JLabel title = getGraphicTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().plusYears(3));
		assertEquals(expected, title.getText());
	}

	/**
	 * ラベル
	 * 初期表示の確認
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithInitial() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now();
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 前年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithPrevYearOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前年ボタンをクリック
		clickGraphicPrevYear(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().minusYears(1);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 前年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithPrevYearTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前年ボタンをクリック
		clickGraphicPrevYear(frame);
		clickGraphicPrevYear(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().minusYears(2);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 前年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithPrevYearThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前年ボタンをクリック
		clickGraphicPrevYear(frame);
		clickGraphicPrevYear(frame);
		clickGraphicPrevYear(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().minusYears(3);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 前月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithPrevMonthOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前月ボタンをクリック
		clickGraphicPrevMonth(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().minusMonths(1);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 前月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithPrevMonthTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前月ボタンをクリック
		clickGraphicPrevMonth(frame);
		clickGraphicPrevMonth(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().minusMonths(2);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 前月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithPrevMonthThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 前月ボタンをクリック
		clickGraphicPrevMonth(frame);
		clickGraphicPrevMonth(frame);
		clickGraphicPrevMonth(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().minusMonths(3);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 翌月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithNextMonthOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌月ボタンをクリック
		clickGraphicNextMonth(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().plusMonths(1);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 翌月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithNextMonthTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌月ボタンをクリック
		clickGraphicNextMonth(frame);
		clickGraphicNextMonth(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().plusMonths(2);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 翌月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithNextMonthThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌月ボタンをクリック
		clickGraphicNextMonth(frame);
		clickGraphicNextMonth(frame);
		clickGraphicNextMonth(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().plusMonths(3);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 翌年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithNextYearOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌年ボタンをクリック
		clickGraphicNextYear(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().plusYears(1);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 翌年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithNextYearTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌年ボタンをクリック
		clickGraphicNextYear(frame);
		clickGraphicNextYear(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().plusYears(2);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}

	/**
	 * ラベル
	 * 翌年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void labelWithNextYearThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE);

		// タブを切り替え
		changeTab(frame, 2);

		// 翌年ボタンをクリック
		clickGraphicNextYear(frame);
		clickGraphicNextYear(frame);
		clickGraphicNextYear(frame);

		// 各ラベルを取得
		JLabel x1 = getGraphicLabel(frame, "LabelX1");
		JLabel x2 = getGraphicLabel(frame, "LabelX2");
		JLabel x3 = getGraphicLabel(frame, "LabelX3");
		JLabel x4 = getGraphicLabel(frame, "LabelX4");
		JLabel x5 = getGraphicLabel(frame, "LabelX5");
		JLabel x6 = getGraphicLabel(frame, "LabelX6");
		assertNotNull(x1);
		assertNotNull(x2);
		assertNotNull(x3);
		assertNotNull(x4);
		assertNotNull(x5);
		assertNotNull(x6);

		// 各ラベルを確認
		LocalDate date = LocalDate.now().plusYears(3);
		assertEquals(UTL.toMonth(date.minusMonths(10)), x1.getText());
		assertEquals(UTL.toMonth(date.minusMonths(8)), x2.getText());
		assertEquals(UTL.toMonth(date.minusMonths(6)), x3.getText());
		assertEquals(UTL.toMonth(date.minusMonths(4)), x4.getText());
		assertEquals(UTL.toMonth(date.minusMonths(2)), x5.getText());
		assertEquals(UTL.toMonth(date), x6.getText());
	}
}
