// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.form;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.github.alyssaburlton.swingtest.ComponentFindersKt;
import com.github.alyssaburlton.swingtest.ComponentInteractionsKt;
import com.github.alyssaburlton.swingtest.EventFactoryKt;
import com.github.alyssaburlton.swingtest.SwingUtilitiesKt;

import abook.common.AbConstant.TYPE;
import abook.common.AbException;
import abook.common.AbUtility.UTL;
import abook.form.AbFormMain;
import abook.form.AbTabSummary.CostLabel;
import abook.form.AbTabSummary.TypeLabel;
import abook.form.subform.AbSubformType;
import test.tool.AbFormAbstractMain;
import test.tool.AbTestCleanupExtension;
import test.tool.AbTestTool;

/**
 * テスト:月次タブ
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbTabSummaryTest extends AbFormAbstractMain {

	/** DBファイル */
	private static final String DB_FILE_EXIST = "AbTabSummaryTestCount.db";

	/** DBファイル */
	private static final String DB_FILE_EMPTY = "AbTabSummaryTestEmpty.db";

	/** 金額ラベルのID */
	private static final String[] TYPES = {
			"LblCostFood",
			"LblCostOtfd",
			"LblCostGood",
			"LblCostFrnd",
			"LblCostTrfc",
			"LblCostPaly",
			"LblCostHous",
			"LblCostEngy",
			"LblCostCnct",
			"LblCostMedi",
			"LblCostInsu",
			"LblCostOthr",
			"LblCostTtal",
			"LblCostBlnc",
	};

	@BeforeAll
	public static void testFixtureSetup() throws AbException, IOException {

		LocalDate dt = LocalDate.now();
		String tdy = UTL.toString(dt);
		String py1 = UTL.toString(dt.minusYears(1));
		String py2 = UTL.toString(dt.minusYears(2));
		String pm1 = UTL.toString(dt.minusMonths(1));
		String pm2 = UTL.toString(dt.minusMonths(2));
		String ny1 = UTL.toString(dt.plusYears(1));
		String ny2 = UTL.toString(dt.plusYears(2));
		String nm1 = UTL.toString(dt.plusMonths(1));
		String nm2 = UTL.toString(dt.plusMonths(2));

		List<String[]> dbFileExist = new ArrayList<String[]>() {
			{
				add(new String[] { tdy, "name", TYPE.FOOD, " 10000" });
				add(new String[] { tdy, "name", TYPE.OTFD, " 15000" });
				add(new String[] { tdy, "name", TYPE.GOOD, "  1200" });
				add(new String[] { tdy, "name", TYPE.FRND, "  5000" });
				add(new String[] { tdy, "name", TYPE.TRFC, "   200" });
				add(new String[] { tdy, "name", TYPE.PLAY, "  3000" });
				add(new String[] { tdy, "name", TYPE.HOUS, " 40000" });
				add(new String[] { tdy, "name", TYPE.ENGY, "  8000" });
				add(new String[] { tdy, "name", TYPE.CNCT, "  2000" });
				add(new String[] { tdy, "name", TYPE.MEDI, "  1700" });
				add(new String[] { tdy, "name", TYPE.INSU, "  2700" });
				add(new String[] { tdy, "name", TYPE.OTHR, "   500" });
				add(new String[] { tdy, "name", TYPE.EARN, "150000" });
				add(new String[] { tdy, "name", TYPE.PRVI, " 20000" });
				add(new String[] { tdy, "name", TYPE.PRVO, " 10000" });
				add(new String[] { tdy, "name", TYPE.FNCE, "100000" });
				add(new String[] { py1, "name", TYPE.FOOD, " 10100" });
				add(new String[] { py1, "name", TYPE.OTFD, " 15100" });
				add(new String[] { py1, "name", TYPE.GOOD, "  1300" });
				add(new String[] { py1, "name", TYPE.FRND, "  5100" });
				add(new String[] { py1, "name", TYPE.TRFC, "   300" });
				add(new String[] { py1, "name", TYPE.PLAY, "  3100" });
				add(new String[] { py1, "name", TYPE.HOUS, " 40100" });
				add(new String[] { py1, "name", TYPE.ENGY, "  8100" });
				add(new String[] { py1, "name", TYPE.CNCT, "  2100" });
				add(new String[] { py1, "name", TYPE.MEDI, "  1800" });
				add(new String[] { py1, "name", TYPE.INSU, "  2800" });
				add(new String[] { py1, "name", TYPE.OTHR, "   600" });
				add(new String[] { py1, "name", TYPE.EARN, "150100" });
				add(new String[] { py1, "name", TYPE.PRVI, " 20000" });
				add(new String[] { py1, "name", TYPE.PRVO, " 10000" });
				add(new String[] { py1, "name", TYPE.FNCE, "200000" });
				add(new String[] { py2, "name", TYPE.FOOD, " 10200" });
				add(new String[] { py2, "name", TYPE.OTFD, " 15200" });
				add(new String[] { py2, "name", TYPE.GOOD, "  1400" });
				add(new String[] { py2, "name", TYPE.FRND, "  5200" });
				add(new String[] { py2, "name", TYPE.TRFC, "   400" });
				add(new String[] { py2, "name", TYPE.PLAY, "  3200" });
				add(new String[] { py2, "name", TYPE.HOUS, " 40200" });
				add(new String[] { py2, "name", TYPE.ENGY, "  8200" });
				add(new String[] { py2, "name", TYPE.CNCT, "  2200" });
				add(new String[] { py2, "name", TYPE.MEDI, "  1900" });
				add(new String[] { py2, "name", TYPE.INSU, "  2900" });
				add(new String[] { py2, "name", TYPE.OTHR, "   700" });
				add(new String[] { py2, "name", TYPE.EARN, "150200" });
				add(new String[] { py2, "name", TYPE.PRVI, " 20000" });
				add(new String[] { py2, "name", TYPE.PRVO, " 10000" });
				add(new String[] { py2, "name", TYPE.FNCE, "300000" });
				add(new String[] { pm1, "name", TYPE.FOOD, " 10300" });
				add(new String[] { pm1, "name", TYPE.OTFD, " 15300" });
				add(new String[] { pm1, "name", TYPE.GOOD, "  1500" });
				add(new String[] { pm1, "name", TYPE.FRND, "  5300" });
				add(new String[] { pm1, "name", TYPE.TRFC, "   500" });
				add(new String[] { pm1, "name", TYPE.PLAY, "  3300" });
				add(new String[] { pm1, "name", TYPE.HOUS, " 40300" });
				add(new String[] { pm1, "name", TYPE.ENGY, "  8300" });
				add(new String[] { pm1, "name", TYPE.CNCT, "  2300" });
				add(new String[] { pm1, "name", TYPE.MEDI, "  2000" });
				add(new String[] { pm1, "name", TYPE.INSU, "  3000" });
				add(new String[] { pm1, "name", TYPE.OTHR, "   800" });
				add(new String[] { pm1, "name", TYPE.EARN, "150300" });
				add(new String[] { pm1, "name", TYPE.PRVI, " 20000" });
				add(new String[] { pm1, "name", TYPE.PRVO, " 10000" });
				add(new String[] { pm1, "name", TYPE.FNCE, "300000" });
				add(new String[] { pm2, "name", TYPE.FOOD, " 10400" });
				add(new String[] { pm2, "name", TYPE.OTFD, " 15400" });
				add(new String[] { pm2, "name", TYPE.GOOD, "  1600" });
				add(new String[] { pm2, "name", TYPE.FRND, "  5400" });
				add(new String[] { pm2, "name", TYPE.TRFC, "   600" });
				add(new String[] { pm2, "name", TYPE.PLAY, "  3400" });
				add(new String[] { pm2, "name", TYPE.HOUS, " 40400" });
				add(new String[] { pm2, "name", TYPE.ENGY, "  8400" });
				add(new String[] { pm2, "name", TYPE.CNCT, "  2400" });
				add(new String[] { pm2, "name", TYPE.MEDI, "  2100" });
				add(new String[] { pm2, "name", TYPE.INSU, "  3100" });
				add(new String[] { pm2, "name", TYPE.OTHR, "   900" });
				add(new String[] { pm2, "name", TYPE.EARN, "150400" });
				add(new String[] { pm2, "name", TYPE.PRVI, " 20000" });
				add(new String[] { pm2, "name", TYPE.PRVO, " 10000" });
				add(new String[] { pm2, "name", TYPE.FNCE, "400000" });
				add(new String[] { nm1, "name", TYPE.FOOD, " 10500" });
				add(new String[] { nm1, "name", TYPE.OTFD, " 15500" });
				add(new String[] { nm1, "name", TYPE.GOOD, "  1700" });
				add(new String[] { nm1, "name", TYPE.FRND, "  5500" });
				add(new String[] { nm1, "name", TYPE.TRFC, "   700" });
				add(new String[] { nm1, "name", TYPE.PLAY, "  3500" });
				add(new String[] { nm1, "name", TYPE.HOUS, " 40500" });
				add(new String[] { nm1, "name", TYPE.ENGY, "  8500" });
				add(new String[] { nm1, "name", TYPE.CNCT, "  2500" });
				add(new String[] { nm1, "name", TYPE.MEDI, "  2200" });
				add(new String[] { nm1, "name", TYPE.INSU, "  3200" });
				add(new String[] { nm1, "name", TYPE.OTHR, "  1000" });
				add(new String[] { nm1, "name", TYPE.EARN, "150500" });
				add(new String[] { nm1, "name", TYPE.PRVI, " 20000" });
				add(new String[] { nm1, "name", TYPE.PRVO, " 10000" });
				add(new String[] { nm1, "name", TYPE.FNCE, "500000" });
				add(new String[] { nm2, "name", TYPE.FOOD, " 10600" });
				add(new String[] { nm2, "name", TYPE.OTFD, " 15600" });
				add(new String[] { nm2, "name", TYPE.GOOD, "  1800" });
				add(new String[] { nm2, "name", TYPE.FRND, "  5600" });
				add(new String[] { nm2, "name", TYPE.TRFC, "   800" });
				add(new String[] { nm2, "name", TYPE.PLAY, "  3600" });
				add(new String[] { nm2, "name", TYPE.HOUS, " 40600" });
				add(new String[] { nm2, "name", TYPE.ENGY, "  8600" });
				add(new String[] { nm2, "name", TYPE.CNCT, "  2600" });
				add(new String[] { nm2, "name", TYPE.MEDI, "  2300" });
				add(new String[] { nm2, "name", TYPE.INSU, "  3300" });
				add(new String[] { nm2, "name", TYPE.OTHR, "  1100" });
				add(new String[] { nm2, "name", TYPE.EARN, "150600" });
				add(new String[] { nm2, "name", TYPE.PRVI, " 20000" });
				add(new String[] { nm2, "name", TYPE.PRVO, " 10000" });
				add(new String[] { nm2, "name", TYPE.FNCE, "600000" });
				add(new String[] { ny1, "name", TYPE.FOOD, " 10700" });
				add(new String[] { ny1, "name", TYPE.OTFD, " 15700" });
				add(new String[] { ny1, "name", TYPE.GOOD, "  1900" });
				add(new String[] { ny1, "name", TYPE.FRND, "  5700" });
				add(new String[] { ny1, "name", TYPE.TRFC, "   900" });
				add(new String[] { ny1, "name", TYPE.PLAY, "  3700" });
				add(new String[] { ny1, "name", TYPE.HOUS, " 40700" });
				add(new String[] { ny1, "name", TYPE.ENGY, "  8700" });
				add(new String[] { ny1, "name", TYPE.CNCT, "  2700" });
				add(new String[] { ny1, "name", TYPE.MEDI, "  2400" });
				add(new String[] { ny1, "name", TYPE.INSU, "  3400" });
				add(new String[] { ny1, "name", TYPE.OTHR, "  1200" });
				add(new String[] { ny1, "name", TYPE.EARN, "150700" });
				add(new String[] { ny1, "name", TYPE.PRVI, " 20000" });
				add(new String[] { ny1, "name", TYPE.PRVO, " 10000" });
				add(new String[] { ny1, "name", TYPE.FNCE, "700000" });
				add(new String[] { ny2, "name", TYPE.FOOD, " 10800" });
				add(new String[] { ny2, "name", TYPE.OTFD, " 15800" });
				add(new String[] { ny2, "name", TYPE.GOOD, "  2000" });
				add(new String[] { ny2, "name", TYPE.FRND, "  5800" });
				add(new String[] { ny2, "name", TYPE.TRFC, "  1000" });
				add(new String[] { ny2, "name", TYPE.PLAY, "  3800" });
				add(new String[] { ny2, "name", TYPE.HOUS, " 40800" });
				add(new String[] { ny2, "name", TYPE.ENGY, "  8800" });
				add(new String[] { ny2, "name", TYPE.CNCT, "  2800" });
				add(new String[] { ny2, "name", TYPE.MEDI, "  2500" });
				add(new String[] { ny2, "name", TYPE.INSU, "  3500" });
				add(new String[] { ny2, "name", TYPE.OTHR, "  1300" });
				add(new String[] { ny2, "name", TYPE.EARN, "150800" });
				add(new String[] { ny2, "name", TYPE.PRVI, " 20000" });
				add(new String[] { ny2, "name", TYPE.PRVO, " 10000" });
				add(new String[] { ny2, "name", TYPE.FNCE, "800000" });
			}
		};

		// DBファイルを作成
		AbTestTool.createDBFileFromParams(DB_FILE_EXIST, dbFileExist);
	}

	@AfterAll
	public static void testFixtureTearDown() throws IOException {

		// DBファイルが存在するなら削除
		AbTestTool.deleteDBFile(DB_FILE_EXIST);
		AbTestTool.deleteDBFile(DB_FILE_EMPTY);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前年ボタンをクリック
		clickSummaryPrevYear(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前年ボタンをクリック
		clickSummaryPrevYear(frame);
		clickSummaryPrevYear(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前年ボタンをクリック
		clickSummaryPrevYear(frame);
		clickSummaryPrevYear(frame);
		clickSummaryPrevYear(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前月ボタンをクリック
		clickSummaryPrevMonth(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前月ボタンをクリック
		clickSummaryPrevMonth(frame);
		clickSummaryPrevMonth(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前月ボタンをクリック
		clickSummaryPrevMonth(frame);
		clickSummaryPrevMonth(frame);
		clickSummaryPrevMonth(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌月ボタンをクリック
		clickSummaryNextMonth(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌月ボタンをクリック
		clickSummaryNextMonth(frame);
		clickSummaryNextMonth(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌月ボタンをクリック
		clickSummaryNextMonth(frame);
		clickSummaryNextMonth(frame);
		clickSummaryNextMonth(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌年ボタンをクリック
		clickSummaryNextYear(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌年ボタンをクリック
		clickSummaryNextYear(frame);
		clickSummaryNextYear(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
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
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌年ボタンをクリック
		clickSummaryNextYear(frame);
		clickSummaryNextYear(frame);
		clickSummaryNextYear(frame);

		// タイトルを取得
		JLabel title = getSummaryTitle(frame);
		assertNotNull(title);

		// タイトルを確認
		String expected = UTL.toTitle(LocalDate.now().plusYears(3));
		assertEquals(expected, title.getText());
	}

	/**
	 * 金額ラベル
	 * 初期表示の確認
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithInitial() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 金額を確認
		int[] expected = { 10000, 15000, 1200, 5000, 200, 3000, 40000, 8000, 2000, 1700, 2700, 500, 89300, 60700 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 前年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithPrevYearOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前年ボタンをクリック
		clickSummaryPrevYear(frame);

		// 金額を確認
		int[] expected = { 10100, 15100, 1300, 5100, 300, 3100, 40100, 8100, 2100, 1800, 2800, 600, 90500, 59600 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 前年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithPrevYearTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前年ボタンをクリック
		clickSummaryPrevYear(frame);
		clickSummaryPrevYear(frame);

		// 金額を確認
		int[] expected = { 10200, 15200, 1400, 5200, 400, 3200, 40200, 8200, 2200, 1900, 2900, 700, 91700, 58500 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 前年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithPrevYearThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前年ボタンをクリック
		clickSummaryPrevYear(frame);
		clickSummaryPrevYear(frame);
		clickSummaryPrevYear(frame);

		// 金額を確認
		int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 前月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithPrevMonthOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前月ボタンをクリック
		clickSummaryPrevMonth(frame);

		// 金額を確認
		int[] expected = { 10300, 15300, 1500, 5300, 500, 3300, 40300, 8300, 2300, 2000, 3000, 800, 92900, 57400 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 前月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithPrevMonthTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前月ボタンをクリック
		clickSummaryPrevMonth(frame);
		clickSummaryPrevMonth(frame);

		// 金額を確認
		int[] expected = { 10400, 15400, 1600, 5400, 600, 3400, 40400, 8400, 2400, 2100, 3100, 900, 94100, 56300 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 前月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithPrevMonthThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 前月ボタンをクリック
		clickSummaryPrevMonth(frame);
		clickSummaryPrevMonth(frame);
		clickSummaryPrevMonth(frame);

		// 金額を確認
		int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 翌月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithNextMonthOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌月ボタンをクリック
		clickSummaryNextMonth(frame);

		// 金額を確認
		int[] expected = { 10500, 15500, 1700, 5500, 700, 3500, 40500, 8500, 2500, 2200, 3200, 1000, 95300, 55200 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 翌月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithNextMonthTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌月ボタンをクリック
		clickSummaryNextMonth(frame);
		clickSummaryNextMonth(frame);

		// 金額を確認
		int[] expected = { 10600, 15600, 1800, 5600, 800, 3600, 40600, 8600, 2600, 2300, 3300, 1100, 96500, 54100 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 翌月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithNextMonthThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌月ボタンをクリック
		clickSummaryNextMonth(frame);
		clickSummaryNextMonth(frame);
		clickSummaryNextMonth(frame);

		// 金額を確認
		int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 翌年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithNextYearOnce() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌年ボタンをクリック
		clickSummaryNextYear(frame);

		// 金額を確認
		int[] expected = { 10700, 15700, 1900, 5700, 900, 3700, 40700, 8700, 2700, 2400, 3400, 1200, 97700, 53000 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 翌年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithNextYearTwice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌年ボタンをクリック
		clickSummaryNextYear(frame);
		clickSummaryNextYear(frame);

		// 金額を確認
		int[] expected = { 10800, 15800, 2000, 5800, 1000, 3800, 40800, 8800, 2800, 2500, 3500, 1300, 98900, 51900 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル
	 * 翌年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costWithNextYearThrice() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌年ボタンをクリック
		clickSummaryNextYear(frame);
		clickSummaryNextYear(frame);
		clickSummaryNextYear(frame);

		// 金額を確認
		int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル(データなし)
	 * 初期表示の確認
	 * 
	 * @throws AbException
	 */
	@Test
	public void costEmptyWithInitial() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EMPTY);

		// タブを切り替え
		changeTab(frame, 1);

		// 金額を確認
		int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル(データなし)
	 * 前年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costEmptyWithPrevYear() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EMPTY);

		// タブを切り替え
		changeTab(frame, 1);

		// 前年ボタンをクリック
		clickSummaryPrevYear(frame);

		// 金額を確認
		int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル(データなし)
	 * 前月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costEmptyWithPrevMonth() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EMPTY);

		// タブを切り替え
		changeTab(frame, 1);

		// 前月ボタンをクリック
		clickSummaryPrevMonth(frame);

		// 金額を確認
		int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル(データなし)
	 * 翌月へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costEmptyWithNextMonth() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EMPTY);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌月ボタンをクリック
		clickSummaryNextMonth(frame);

		// 金額を確認
		int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * 金額ラベル(データなし)
	 * 翌年へ切り替え
	 * 
	 * @throws AbException
	 */
	@Test
	public void costEmptyWithNextYear() throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EMPTY);

		// タブを切り替え
		changeTab(frame, 1);

		// 翌年ボタンをクリック
		clickSummaryNextYear(frame);

		// 金額を確認
		int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < TYPES.length; i++) {

			// 金額を取得
			CostLabel label = getCostLabel(frame, TYPES[i]);

			// 金額を確認
			assertEquals(UTL.toCurrency(expected[i], frame.getLocale()), label.getText());
		}
	}

	/**
	 * マウスオン・オフの確認
	 * 
	 * @param name ID
	 * @param type 種別
	 * @throws AbException
	 */
	@ParameterizedTest
	@CsvSource({
			"LblTypeFood, 食費",
			"LblTypeOtfd, 外食費",
			"LblTypeGood, 雑貨",
			"LblTypeFrnd, 交際費",
			"LblTypeTrfc, 交通費",
			"LblTypePaly, 遊行費",
			"LblTypeHous, 家賃",
			"LblTypeEngy, 光熱費",
			"LblTypeCnct, 通信費",
			"LblTypeMedi, 医療費",
			"LblTypeInsu, 保険料",
			"LblTypeOthr, その他",
	})
	public void mouseOnOffWithTypeLabel(String name, String type) throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EMPTY);

		// タブを切り替え
		changeTab(frame, 1);

		// ラベルを取得
		TypeLabel label = getTypeLabel(frame, name);
		assertNotNull(label);

		// マウスオン
		ComponentInteractionsKt.doHover(label);

		// 下線リンクになることを確認
		assertEquals(String.format("<html><u>%s</u></html>", type), label.getText());

		// マウスオフ
		ComponentInteractionsKt.doHoverAway(label);

		// 元に戻ることを確認
		assertEquals(type, label.getText());
	}

	/**
	 * マウスオン・オフの確認
	 * 
	 * @param name ID
	 * @param type 種別
	 * @throws AbException
	 */
	@ParameterizedTest
	@CsvSource({
			"LblTypeTtal, 合計",
			"LblTypeBlnc, 収支"
	})
	public void mouseOnOffWithNotTargetTypeLabel(String name, String type) throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EMPTY);

		// タブを切り替え
		changeTab(frame, 1);

		// ラベルを取得
		TypeLabel label = getTypeLabel(frame, name);
		assertNotNull(label);

		// マウスオン
		ComponentInteractionsKt.doHover(label);

		// マウスオンしても変わらないことを確認
		assertEquals(type, label.getText());

		// マウスオフ
		ComponentInteractionsKt.doHoverAway(label);

		// 変わらないことを確認
		assertEquals(type, label.getText());
	}

	/**
	 * マウスオン・オフの確認
	 * 
	 * @param name ID
	 * @param type 種別
	 * @throws AbException
	 */
	@ParameterizedTest
	@CsvSource({
			"LblCostFood, 10000",
			"LblCostOtfd, 15000",
			"LblCostGood,  1200",
			"LblCostFrnd,  5000",
			"LblCostTrfc,   200",
			"LblCostPaly,  3000",
			"LblCostHous, 40000",
			"LblCostEngy,  8000",
			"LblCostCnct,  2000",
			"LblCostMedi,  1700",
			"LblCostInsu,  2700",
			"LblCostOthr,   500",
	})
	public void mouseOnOffWithCostLabel(String name, int cost) throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// ラベルを取得
		CostLabel label = getCostLabel(frame, name);
		assertNotNull(label);

		// マウスオン
		ComponentInteractionsKt.doHover(label);

		// 通貨形式
		String currency = UTL.toCurrency(cost, frame.getLocale());

		// 下線リンクになることを確認
		assertEquals(String.format("<html><u>%s</u></html>", currency), label.getText());

		// マウスオフ
		ComponentInteractionsKt.doHoverAway(label);

		// 元に戻ることを確認
		assertEquals(currency, label.getText());
	}

	/**
	 * マウスオン・オフの確認
	 * 
	 * @param name ID
	 * @param type 種別
	 * @throws AbException
	 */
	@ParameterizedTest
	@CsvSource({
			"LblCostTtal, 89300",
			"LblCostBlnc, 60700"
	})
	public void mouseOnOffWithNotTargetCostLabel(String name, int cost) throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// ラベルを取得
		CostLabel label = getCostLabel(frame, name);
		assertNotNull(label);

		// マウスオン
		ComponentInteractionsKt.doHover(label);

		// 通貨形式
		String currency = UTL.toCurrency(cost, frame.getLocale());

		// マウスオンしても変わらないことを確認
		assertEquals(currency, label.getText());

		// マウスオフ
		ComponentInteractionsKt.doHoverAway(label);

		// 変わらないことを確認
		assertEquals(currency, label.getText());
	}

	/**
	 * 種別クリックの確認
	 * 
	 * @param name ID
	 * @param type 種別
	 * @throws AbException
	 */
	@ParameterizedTest
	@CsvSource({
			"LblTypeFood, 食費",
			"LblTypeOtfd, 外食費",
			"LblTypeGood, 雑貨",
			"LblTypeFrnd, 交際費",
			"LblTypeTrfc, 交通費",
			"LblTypePaly, 遊行費",
			"LblTypeHous, 家賃",
			"LblTypeEngy, 光熱費",
			"LblTypeCnct, 通信費",
			"LblTypeMedi, 医療費",
			"LblTypeInsu, 保険料",
			"LblTypeOthr, その他",
	})
	public void clickWithTypeLabel(String name, String type) throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// ラベルを取得
		TypeLabel label = getTypeLabel(frame, name);
		assertNotNull(label);

		// ラベルをクリック
		SwingUtilities.invokeLater(() -> {
			MouseListener listener = label.getMouseListeners()[0];
			listener.mouseClicked(EventFactoryKt.makeMouseEvent(label, 0, 0, 0));
		});
		SwingUtilitiesKt.flushEdt();

		// 種別サブフォームを取得
		JDialog subform = ComponentFindersKt.findWindow(AbSubformType.class, (w) -> {
			return w.isVisible();
		});
		assertNotNull(subform);

		// 正しく取得できたか確認
		assertEquals(type, subform.getTitle());

		// 後始末
		SwingUtilities.invokeLater(() -> {
			subform.setVisible(false);
			subform.dispose();
		});
		SwingUtilitiesKt.flushEdt();
	}

	/**
	 * 種別クリックの確認
	 * 
	 * @param name ID
	 * @param type 種別
	 * @throws AbException
	 */
	@ParameterizedTest
	@CsvSource({
			"LblTypeTtal, 合計",
			"LblTypeBlnc, 収支",
	})
	public void clickWithNotTargetTypeLabel(String name, String type) throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// ラベルを取得
		TypeLabel label = getTypeLabel(frame, name);
		assertNotNull(label);

		// ラベルをクリック
		SwingUtilities.invokeLater(() -> {
			MouseListener listener = label.getMouseListeners()[0];
			listener.mouseClicked(EventFactoryKt.makeMouseEvent(label, 0, 0, 0));
		});
		SwingUtilitiesKt.flushEdt();

		// 種別サブフォームを取得
		JDialog subform = ComponentFindersKt.findWindow(AbSubformType.class, (w) -> {
			return w.isVisible();
		});

		// 種別サブフォームは表示されていないことを確認
		assertNull(subform);
	}

	/**
	 * 金額クリックの確認
	 * 
	 * @param name ID
	 * @param type 種別
	 * @throws AbException
	 */
	@ParameterizedTest
	@CsvSource({
			"LblCostFood, 食費",
			"LblCostOtfd, 外食費",
			"LblCostGood, 雑貨",
			"LblCostFrnd, 交際費",
			"LblCostTrfc, 交通費",
			"LblCostPaly, 遊行費",
			"LblCostHous, 家賃",
			"LblCostEngy, 光熱費",
			"LblCostCnct, 通信費",
			"LblCostMedi, 医療費",
			"LblCostInsu, 保険料",
			"LblCostOthr, その他",
	})
	public void clickWithCostLabel(String name, String type) throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// ラベルを取得
		CostLabel label = getCostLabel(frame, name);
		assertNotNull(label);

		// ラベルをクリック
		SwingUtilities.invokeLater(() -> {
			MouseListener listener = label.getMouseListeners()[0];
			listener.mouseClicked(EventFactoryKt.makeMouseEvent(label, 0, 0, 0));
		});
		SwingUtilitiesKt.flushEdt();

		// 種別サブフォームを取得
		JDialog subform = ComponentFindersKt.findWindow(AbSubformType.class, (w) -> {
			return w.isVisible();
		});
		assertNotNull(subform);

		// 正しく取得できたか確認
		assertEquals(type, subform.getTitle());

		// 後始末
		SwingUtilities.invokeLater(() -> {
			subform.setVisible(false);
			subform.dispose();
		});
		SwingUtilitiesKt.flushEdt();
	}

	/**
	 * 金額クリックの確認
	 * 
	 * @param name ID
	 * @param type 種別
	 * @throws AbException
	 */
	@ParameterizedTest
	@CsvSource({
			"LblCostTtal, 合計",
			"LblCostBlnc, 収支",
	})
	public void clickWithNotTargetCostLabel(String name, String type) throws AbException {

		// 画面を表示
		AbFormMain frame = showFormMain(DB_FILE_EXIST);

		// タブを切り替え
		changeTab(frame, 1);

		// ラベルを取得
		CostLabel label = getCostLabel(frame, name);
		assertNotNull(label);

		// ラベルをクリック
		SwingUtilities.invokeLater(() -> {
			MouseListener listener = label.getMouseListeners()[0];
			listener.mouseClicked(EventFactoryKt.makeMouseEvent(label, 0, 0, 0));
		});
		SwingUtilitiesKt.flushEdt();

		// 種別サブフォームを取得
		JDialog subform = ComponentFindersKt.findWindow(AbSubformType.class, (w) -> {
			return w.isVisible();
		});

		// 種別サブフォームは表示されていないことを確認
		assertNull(subform);
	}
}
