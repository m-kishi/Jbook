// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.form.subform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.alyssaburlton.swingtest.ComponentFindersKt;

import abook.common.AbConstant.NAME;
import abook.common.AbException;
import abook.expense.AbExpense;
import abook.expense.AbSummary;
import abook.expense.manager.AbSummaryManager;
import abook.form.subform.AbSubformEnergy;
import test.tool.AbFormAbstract;
import test.tool.AbTestCleanupExtension;

/**
 * テスト:光熱費サブフォーム
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbSubformEnergyTest extends AbFormAbstract {

	/** 支出情報リスト */
	private static List<AbExpense> onlyEl;

	/** 支出情報リスト */
	private static List<AbExpense> onlyGs;

	/** 支出情報リスト */
	private static List<AbExpense> onlyWt;

	/** 支出情報リスト */
	private static List<AbExpense> energies;

	/** 支出情報リスト */
	private static List<AbExpense> onlyZero;

	@BeforeAll
	public static void testFixtureSetup() throws AbException, IOException {

		onlyEl = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2022-04-07", NAME.EL, "光熱費", "1600", ""),
						new AbExpense("2022-04-08", NAME.EL, "光熱費", "1700", ""),
						new AbExpense("2022-05-09", NAME.EL, "光熱費", "1800", ""),
						new AbExpense("2022-05-10", NAME.EL, "光熱費", "1900", ""),
						new AbExpense("2022-07-11", NAME.EL, "光熱費", "2000", ""),
						new AbExpense("2022-07-12", NAME.EL, "光熱費", "2100", ""),
						new AbExpense("2022-07-13", NAME.EL, "光熱費", "2200", ""),
						new AbExpense("2022-08-14", NAME.EL, "光熱費", "2300", ""),
						new AbExpense("2022-08-15", NAME.EL, "光熱費", "2400", ""),
						new AbExpense("2022-08-16", NAME.EL, "光熱費", "2500", ""),
						new AbExpense("2022-08-17", NAME.EL, "光熱費", "2600", ""),
						new AbExpense("2022-08-18", NAME.EL, "光熱費", "2700", ""),
						new AbExpense("2022-09-19", NAME.EL, "光熱費", "2800", ""),
						new AbExpense("2022-10-20", NAME.EL, "光熱費", "2900", ""),
						new AbExpense("2022-11-21", NAME.EL, "光熱費", "3000", ""),
						new AbExpense("2023-01-01", NAME.EL, "光熱費", "1000", ""),
						new AbExpense("2023-01-02", NAME.EL, "光熱費", "1100", ""),
						new AbExpense("2023-02-03", NAME.EL, "光熱費", "1200", ""),
						new AbExpense("2023-02-04", NAME.EL, "光熱費", "1300", ""),
						new AbExpense("2023-03-05", NAME.EL, "光熱費", "1400", ""),
						new AbExpense("2023-03-06", NAME.EL, "光熱費", "1500", "")
				)
		);

		onlyGs = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2022-04-04", NAME.GS, "光熱費", "2600", ""),
						new AbExpense("2022-05-05", NAME.GS, "光熱費", "2800", ""),
						new AbExpense("2022-06-06", NAME.GS, "光熱費", "3000", ""),
						new AbExpense("2022-07-07", NAME.GS, "光熱費", "3200", ""),
						new AbExpense("2022-08-08", NAME.GS, "光熱費", "3400", ""),
						new AbExpense("2022-09-09", NAME.GS, "光熱費", "3600", ""),
						new AbExpense("2022-10-10", NAME.GS, "光熱費", "3800", ""),
						new AbExpense("2022-11-11", NAME.GS, "光熱費", "4000", ""),
						new AbExpense("2022-12-12", NAME.GS, "光熱費", "4200", ""),
						new AbExpense("2023-01-13", NAME.GS, "光熱費", "4400", ""),
						new AbExpense("2023-02-14", NAME.GS, "光熱費", "4600", ""),
						new AbExpense("2023-03-15", NAME.GS, "光熱費", "4800", ""),
						new AbExpense("2023-04-16", NAME.GS, "光熱費", "5000", ""),
						new AbExpense("2023-05-17", NAME.GS, "光熱費", "5200", ""),
						new AbExpense("2023-06-18", NAME.GS, "光熱費", "5400", ""),
						new AbExpense("2023-07-19", NAME.GS, "光熱費", "5600", ""),
						new AbExpense("2023-08-20", NAME.GS, "光熱費", "5800", ""),
						new AbExpense("2023-09-21", NAME.GS, "光熱費", "6000", ""),
						new AbExpense("2023-10-22", NAME.GS, "光熱費", "6200", ""),
						new AbExpense("2023-11-23", NAME.GS, "光熱費", "6400", ""),
						new AbExpense("2023-12-24", NAME.GS, "光熱費", "6600", ""),
						new AbExpense("2024-01-01", NAME.GS, "光熱費", "2000", ""),
						new AbExpense("2024-02-02", NAME.GS, "光熱費", "2200", ""),
						new AbExpense("2024-03-03", NAME.GS, "光熱費", "2400", "")
				)
		);

		onlyWt = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2022-04-04", NAME.WT, "光熱費", "1300", ""),
						new AbExpense("2022-05-05", NAME.WT, "光熱費", "1400", ""),
						new AbExpense("2022-06-06", NAME.WT, "光熱費", "1500", ""),
						new AbExpense("2022-07-07", NAME.WT, "光熱費", "1600", ""),
						new AbExpense("2022-08-08", NAME.WT, "光熱費", "1700", ""),
						new AbExpense("2022-09-09", NAME.WT, "光熱費", "1800", ""),
						new AbExpense("2022-10-10", NAME.WT, "光熱費", "1900", ""),
						new AbExpense("2022-11-11", NAME.WT, "光熱費", "2000", ""),
						new AbExpense("2022-12-12", NAME.WT, "光熱費", "2100", ""),
						new AbExpense("2023-01-01", NAME.WT, "光熱費", "1000", ""),
						new AbExpense("2023-02-02", NAME.WT, "光熱費", "1100", ""),
						new AbExpense("2023-03-03", NAME.WT, "光熱費", "1200", ""),
						new AbExpense("2024-04-16", NAME.WT, "光熱費", "2500", ""),
						new AbExpense("2024-05-17", NAME.WT, "光熱費", "2600", ""),
						new AbExpense("2024-06-18", NAME.WT, "光熱費", "2700", ""),
						new AbExpense("2024-07-19", NAME.WT, "光熱費", "2800", ""),
						new AbExpense("2024-08-20", NAME.WT, "光熱費", "2900", ""),
						new AbExpense("2024-09-21", NAME.WT, "光熱費", "3000", ""),
						new AbExpense("2024-10-22", NAME.WT, "光熱費", "3100", ""),
						new AbExpense("2024-11-23", NAME.WT, "光熱費", "3200", ""),
						new AbExpense("2024-12-24", NAME.WT, "光熱費", "3300", ""),
						new AbExpense("2025-01-13", NAME.WT, "光熱費", "2200", ""),
						new AbExpense("2025-02-14", NAME.WT, "光熱費", "2300", ""),
						new AbExpense("2025-03-15", NAME.WT, "光熱費", "2400", "")
				)
		);

		energies = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2016-04-30", NAME.EL, "光熱費", "1804", ""),
						new AbExpense("2016-04-30", NAME.GS, "光熱費", "5422", ""),
						new AbExpense("2016-04-30", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2016-05-31", NAME.EL, "光熱費", "1359", ""),
						new AbExpense("2016-05-31", NAME.GS, "光熱費", "4462", ""),
						new AbExpense("2016-05-31", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2016-06-30", NAME.EL, "光熱費", "1550", ""),
						new AbExpense("2016-06-30", NAME.GS, "光熱費", "4659", ""),
						new AbExpense("2016-06-30", NAME.WT, "光熱費", "1896", ""),
						new AbExpense("2016-07-31", NAME.EL, "光熱費", "3780", ""),
						new AbExpense("2016-07-31", NAME.GS, "光熱費", "4363", ""),
						new AbExpense("2016-07-31", NAME.WT, "光熱費", "1896", ""),
						new AbExpense("2016-08-31", NAME.EL, "光熱費", "3853", ""),
						new AbExpense("2016-08-31", NAME.GS, "光熱費", "3969", ""),
						new AbExpense("2016-08-31", NAME.WT, "光熱費", "1896", ""),
						new AbExpense("2016-09-30", NAME.EL, "光熱費", "3143", ""),
						new AbExpense("2016-09-30", NAME.GS, "光熱費", "3771", ""),
						new AbExpense("2016-09-30", NAME.WT, "光熱費", "1896", ""),
						new AbExpense("2016-10-31", NAME.EL, "光熱費", "1416", ""),
						new AbExpense("2016-10-31", NAME.GS, "光熱費", "3820", ""),
						new AbExpense("2016-10-31", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2016-11-30", NAME.EL, "光熱費", "1250", ""),
						new AbExpense("2016-11-30", NAME.GS, "光熱費", "4857", ""),
						new AbExpense("2016-11-30", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2016-12-31", NAME.EL, "光熱費", "501", ""),
						new AbExpense("2016-12-31", NAME.GS, "光熱費", "1995", ""),
						new AbExpense("2016-12-31", NAME.WT, "光熱費", "1462", ""),
						new AbExpense("2017-01-31", NAME.EL, "光熱費", "708", ""),
						new AbExpense("2017-01-31", NAME.GS, "光熱費", "3031", ""),
						new AbExpense("2017-01-31", NAME.WT, "光熱費", "1462", ""),
						new AbExpense("2017-02-28", NAME.EL, "光熱費", "690", ""),
						new AbExpense("2017-02-28", NAME.GS, "光熱費", "3574", ""),
						new AbExpense("2017-02-28", NAME.WT, "光熱費", "1558", ""),
						new AbExpense("2017-03-31", NAME.EL, "光熱費", "1673", ""),
						new AbExpense("2017-03-31", NAME.GS, "光熱費", "6574", ""),
						new AbExpense("2017-03-31", NAME.WT, "光熱費", "1558", ""),
						new AbExpense("2017-04-30", NAME.EL, "光熱費", "1926", ""),
						new AbExpense("2017-04-30", NAME.GS, "光熱費", "6213", ""),
						new AbExpense("2017-04-30", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2017-05-31", NAME.EL, "光熱費", "1321", ""),
						new AbExpense("2017-05-31", NAME.GS, "光熱費", "4413", ""),
						new AbExpense("2017-05-31", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2017-06-30", NAME.EL, "光熱費", "1475", ""),
						new AbExpense("2017-06-30", NAME.GS, "光熱費", "4464", ""),
						new AbExpense("2017-06-30", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2017-07-31", NAME.EL, "光熱費", "2045", ""),
						new AbExpense("2017-07-31", NAME.GS, "光熱費", "4310", ""),
						new AbExpense("2017-07-31", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2017-08-31", NAME.EL, "光熱費", "3147", ""),
						new AbExpense("2017-08-31", NAME.GS, "光熱費", "3847", ""),
						new AbExpense("2017-08-31", NAME.WT, "光熱費", "1896", ""),
						new AbExpense("2017-09-30", NAME.EL, "光熱費", "2918", ""),
						new AbExpense("2017-09-30", NAME.GS, "光熱費", "3538", ""),
						new AbExpense("2017-09-30", NAME.WT, "光熱費", "1896", ""),
						new AbExpense("2017-10-31", NAME.EL, "光熱費", "1306", ""),
						new AbExpense("2017-10-31", NAME.GS, "光熱費", "3898", ""),
						new AbExpense("2017-10-31", NAME.WT, "光熱費", "1799", ""),
						new AbExpense("2017-11-30", NAME.EL, "光熱費", "1791", ""),
						new AbExpense("2017-11-30", NAME.GS, "光熱費", "5390", ""),
						new AbExpense("2017-11-30", NAME.WT, "光熱費", "1799", ""),
						new AbExpense("2017-12-31", NAME.EL, "光熱費", "1573", ""),
						new AbExpense("2017-12-31", NAME.GS, "光熱費", "5647", ""),
						new AbExpense("2017-12-31", NAME.WT, "光熱費", "1799", ""),
						new AbExpense("2018-01-31", NAME.EL, "光熱費", "1675", ""),
						new AbExpense("2018-01-31", NAME.GS, "光熱費", "5596", ""),
						new AbExpense("2018-01-31", NAME.WT, "光熱費", "1799", ""),
						new AbExpense("2018-02-28", NAME.EL, "光熱費", "1783", ""),
						new AbExpense("2018-02-28", NAME.GS, "光熱費", "5596", ""),
						new AbExpense("2018-02-28", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2018-03-31", NAME.EL, "光熱費", "1357", ""),
						new AbExpense("2018-03-31", NAME.GS, "光熱費", "5339", ""),
						new AbExpense("2018-03-31", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2018-04-30", NAME.EL, "光熱費", "1426", ""),
						new AbExpense("2018-04-30", NAME.GS, "光熱費", "5339", ""),
						new AbExpense("2018-04-30", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2018-05-31", NAME.EL, "光熱費", "1174", ""),
						new AbExpense("2018-05-31", NAME.GS, "光熱費", "4207", ""),
						new AbExpense("2018-05-31", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2018-06-30", NAME.EL, "光熱費", "1266", ""),
						new AbExpense("2018-06-30", NAME.GS, "光熱費", "4824", ""),
						new AbExpense("2018-06-30", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2018-07-31", NAME.EL, "光熱費", "2010", ""),
						new AbExpense("2018-07-31", NAME.GS, "光熱費", "4001", ""),
						new AbExpense("2018-07-31", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2018-08-31", NAME.EL, "光熱費", "2257", ""),
						new AbExpense("2018-08-31", NAME.GS, "光熱費", "3538", ""),
						new AbExpense("2018-08-31", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2018-09-30", NAME.EL, "光熱費", "1998", ""),
						new AbExpense("2018-09-30", NAME.GS, "光熱費", "3692", ""),
						new AbExpense("2018-09-30", NAME.WT, "光熱費", "1848", ""),
						new AbExpense("2018-10-31", NAME.EL, "光熱費", "1164", ""),
						new AbExpense("2018-10-31", NAME.GS, "光熱費", "4053", ""),
						new AbExpense("2018-10-31", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2018-11-30", NAME.EL, "光熱費", "1369", ""),
						new AbExpense("2018-11-30", NAME.GS, "光熱費", "4876", ""),
						new AbExpense("2018-11-30", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2018-12-31", NAME.EL, "光熱費", "1632", ""),
						new AbExpense("2018-12-31", NAME.GS, "光熱費", "4979", ""),
						new AbExpense("2018-12-31", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2019-01-31", NAME.EL, "光熱費", "1805", ""),
						new AbExpense("2019-01-31", NAME.GS, "光熱費", "5133", ""),
						new AbExpense("2019-01-31", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2019-02-29", NAME.EL, "光熱費", "1745", ""),
						new AbExpense("2019-02-29", NAME.GS, "光熱費", "5339", ""),
						new AbExpense("2019-02-29", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2019-03-31", NAME.EL, "光熱費", "1567", ""),
						new AbExpense("2019-03-31", NAME.GS, "光熱費", "5184", ""),
						new AbExpense("2019-03-31", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2019-04-30", NAME.EL, "光熱費", "1577", ""),
						new AbExpense("2019-04-30", NAME.GS, "光熱費", "5544", ""),
						new AbExpense("2019-04-30", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2019-05-31", NAME.EL, "光熱費", "1231", ""),
						new AbExpense("2019-05-31", NAME.GS, "光熱費", "4506", ""),
						new AbExpense("2019-05-31", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2019-06-30", NAME.EL, "光熱費", "1342", ""),
						new AbExpense("2019-06-30", NAME.GS, "光熱費", "4342", ""),
						new AbExpense("2019-06-30", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2019-07-31", NAME.EL, "光熱費", "2267", ""),
						new AbExpense("2019-07-31", NAME.GS, "光熱費", "3937", ""),
						new AbExpense("2019-07-31", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2019-08-31", NAME.EL, "光熱費", "2659", ""),
						new AbExpense("2019-08-31", NAME.GS, "光熱費", "3435", ""),
						new AbExpense("2019-08-31", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2019-09-30", NAME.EL, "光熱費", "2533", ""),
						new AbExpense("2019-09-30", NAME.GS, "光熱費", "3538", ""),
						new AbExpense("2019-09-30", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2019-10-31", NAME.EL, "光熱費", "1374", ""),
						new AbExpense("2019-10-31", NAME.GS, "光熱費", "4104", ""),
						new AbExpense("2019-10-31", NAME.WT, "光熱費", "1799", ""),
						new AbExpense("2019-11-30", NAME.EL, "光熱費", "1298", ""),
						new AbExpense("2019-11-30", NAME.GS, "光熱費", "5184", ""),
						new AbExpense("2019-11-30", NAME.WT, "光熱費", "1799", ""),
						new AbExpense("2019-12-31", NAME.EL, "光熱費", "1636", ""),
						new AbExpense("2019-12-31", NAME.GS, "光熱費", "5380", ""),
						new AbExpense("2019-12-31", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2020-01-31", NAME.EL, "光熱費", "2296", ""),
						new AbExpense("2020-01-31", NAME.GS, "光熱費", "5380", ""),
						new AbExpense("2020-01-31", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2020-02-28", NAME.EL, "光熱費", "2071", ""),
						new AbExpense("2020-02-28", NAME.GS, "光熱費", "5871", ""),
						new AbExpense("2020-02-28", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2020-03-31", NAME.EL, "光熱費", "1588", ""),
						new AbExpense("2020-03-31", NAME.GS, "光熱費", "5544", ""),
						new AbExpense("2020-03-31", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2020-04-30", NAME.EL, "光熱費", "1443", ""),
						new AbExpense("2020-04-30", NAME.GS, "光熱費", "5325", ""),
						new AbExpense("2020-04-30", NAME.WT, "光熱費", "1799", ""),
						new AbExpense("2020-05-31", NAME.EL, "光熱費", "1603", ""),
						new AbExpense("2020-05-31", NAME.GS, "光熱費", "4725", ""),
						new AbExpense("2020-05-31", NAME.WT, "光熱費", "1799", ""),
						new AbExpense("2020-06-30", NAME.EL, "光熱費", "1651", ""),
						new AbExpense("2020-06-30", NAME.GS, "光熱費", "3633", ""),
						new AbExpense("2020-06-30", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2020-07-31", NAME.EL, "光熱費", "2485", ""),
						new AbExpense("2020-07-31", NAME.GS, "光熱費", "3687", ""),
						new AbExpense("2020-07-31", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2020-08-31", NAME.EL, "光熱費", "3455", ""),
						new AbExpense("2020-08-31", NAME.GS, "光熱費", "3523", ""),
						new AbExpense("2020-08-31", NAME.WT, "光熱費", "1799", ""),
						new AbExpense("2020-09-30", NAME.EL, "光熱費", "1892", ""),
						new AbExpense("2020-09-30", NAME.GS, "光熱費", "3523", ""),
						new AbExpense("2020-09-30", NAME.WT, "光熱費", "1799", ""),
						new AbExpense("2020-10-31", NAME.EL, "光熱費", "2016", ""),
						new AbExpense("2020-10-31", NAME.GS, "光熱費", "3906", ""),
						new AbExpense("2020-10-31", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2020-11-30", NAME.EL, "光熱費", "1449", ""),
						new AbExpense("2020-11-30", NAME.GS, "光熱費", "4452", ""),
						new AbExpense("2020-11-30", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2020-12-31", NAME.EL, "光熱費", "1701", ""),
						new AbExpense("2020-12-31", NAME.GS, "光熱費", "4943", ""),
						new AbExpense("2020-12-31", NAME.WT, "光熱費", "1655", ""),
						new AbExpense("2021-01-31", NAME.EL, "光熱費", "1760", ""),
						new AbExpense("2021-01-31", NAME.GS, "光熱費", "5287", ""),
						new AbExpense("2021-01-31", NAME.WT, "光熱費", "1655", ""),
						new AbExpense("2021-02-28", NAME.EL, "光熱費", "1743", ""),
						new AbExpense("2021-02-28", NAME.GS, "光熱費", "5758", ""),
						new AbExpense("2021-02-28", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2021-03-31", NAME.EL, "光熱費", "1624", ""),
						new AbExpense("2021-03-31", NAME.GS, "光熱費", "5287", ""),
						new AbExpense("2021-03-31", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2021-04-30", NAME.EL, "光熱費", "1729", ""),
						new AbExpense("2021-04-30", NAME.GS, "光熱費", "5405", ""),
						new AbExpense("2021-04-30", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2021-05-31", NAME.EL, "光熱費", "1412", ""),
						new AbExpense("2021-05-31", NAME.GS, "光熱費", "4713", ""),
						new AbExpense("2021-05-31", NAME.WT, "光熱費", "1703", ""),
						new AbExpense("2021-06-30", NAME.EL, "光熱費", "1517", ""),
						new AbExpense("2021-06-30", NAME.GS, "光熱費", "4501", ""),
						new AbExpense("2021-06-30", NAME.WT, "光熱費", "1801", ""),
						new AbExpense("2021-07-31", NAME.EL, "光熱費", "2350", ""),
						new AbExpense("2021-07-31", NAME.GS, "光熱費", "4209", ""),
						new AbExpense("2021-07-31", NAME.WT, "光熱費", "1801", ""),
						new AbExpense("2021-08-31", NAME.EL, "光熱費", "2294", ""),
						new AbExpense("2021-08-31", NAME.GS, "光熱費", "3801", ""),
						new AbExpense("2021-08-31", NAME.WT, "光熱費", "1851", ""),
						new AbExpense("2021-09-30", NAME.EL, "光熱費", "2304", ""),
						new AbExpense("2021-09-30", NAME.GS, "光熱費", "4326", ""),
						new AbExpense("2021-09-30", NAME.WT, "光熱費", "1851", ""),
						new AbExpense("2021-10-31", NAME.EL, "光熱費", "1433", ""),
						new AbExpense("2021-10-31", NAME.GS, "光熱費", "4501", ""),
						new AbExpense("2021-10-31", NAME.WT, "光熱費", "1801", ""),
						new AbExpense("2021-11-30", NAME.EL, "光熱費", "1477", ""),
						new AbExpense("2021-11-30", NAME.GS, "光熱費", "5026", ""),
						new AbExpense("2021-11-30", NAME.WT, "光熱費", "1801", ""),
						new AbExpense("2021-12-31", NAME.EL, "光熱費", "1498", ""),
						new AbExpense("2021-12-31", NAME.GS, "光熱費", "5492", ""),
						new AbExpense("2021-12-31", NAME.WT, "光熱費", "1801", ""),
						new AbExpense("2022-01-31", NAME.EL, "光熱費", "1860", ""),
						new AbExpense("2022-01-31", NAME.GS, "光熱費", "5551", ""),
						new AbExpense("2022-01-31", NAME.WT, "光熱費", "1801", ""),
						new AbExpense("2022-02-28", NAME.EL, "光熱費", "1413", ""),
						new AbExpense("2022-02-28", NAME.GS, "光熱費", "6116", ""),
						new AbExpense("2022-02-28", NAME.WT, "光熱費", "1751", ""),
						new AbExpense("2022-03-31", NAME.EL, "光熱費", "1506", ""),
						new AbExpense("2022-03-31", NAME.GS, "光熱費", "5772", ""),
						new AbExpense("2022-03-31", NAME.WT, "光熱費", "1751", "")
				)
		);

		onlyZero = new ArrayList<AbExpense>(
				Arrays.asList(
						new AbExpense("2023-06-30", NAME.EL, "光熱費", "0", ""),
						new AbExpense("2023-06-30", NAME.GS, "光熱費", "0", ""),
						new AbExpense("2023-06-30", NAME.WT, "光熱費", "0", "")
				)
		);
	}

	/**
	 * 画面を表示
	 * テストのため一時的にモーダルをOFF
	 * 
	 * @param expenses 支出情報リスト
	 * @return 光熱費サブフォーム
	 */
	private AbSubformEnergy showForm(List<AbExpense> expenses) {
		List<AbSummary> summaries = AbSummaryManager.createSummaries(expenses);
		AbSubformEnergy frame = new AbSubformEnergy(summaries);
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
	 * 引数:月次情報リストがNULL
	 */
	@Test
	public void AbSubformEnergyWithNullSummaries() {
		// アプリでは処理しない
		assertThrows(NullPointerException.class,
				() -> new AbSubformEnergy(null)
		);
	}

	/**
	 * 画面を表示
	 * 引数:月次情報リストが空
	 */
	@Test
	public void AbSubformEnergyWithEmptySummaries() {
		assertDoesNotThrow(() -> new AbSubformEnergy(new ArrayList<AbSummary>()));
	}

	/**
	 * 初期表示
	 * タイトルを確認
	 */
	@Test
	public void initializeWithTitle() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// タイトルを確認
		assertEquals("光熱費", frame.getTitle());
	}

	/**
	 * 初期表示
	 * 
	 * @param name ID
	 */
	@ParameterizedTest
	@ValueSource(strings = { "TableEl", "TableGs", "TableWt" })
	public void initializeWithColumnName(String name) {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, name);
		assertNotNull(table);

		// テーブルのカラムを確認
		assertEquals("年度", table.getColumnName(0));
		String[] months = { "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月", "1月", "2月", "3月" };
		for (int i = 0; i < 12; i++) {
			assertEquals(months[i], table.getColumnName(i + 1));
		}
	}

	/**
	 * 初期表示
	 * 光熱費の支出情報がない
	 * 
	 * @param name ID
	 */
	@ParameterizedTest
	@ValueSource(strings = { "TableEl", "TableGs", "TableWt" })
	public void initializeWithCount(String name) {

		// 画面を表示
		AbSubformEnergy frame = showForm(new ArrayList<AbExpense>());

		// テーブルを取得
		JTable table = getTable(frame, name);
		assertNotNull(table);

		// 件数を確認
		assertEquals(0, table.getRowCount());
	}

	/**
	 * 件数の確認
	 * 電気代のみ
	 * 
	 * @param name     ID
	 * @param expected 件数
	 */
	@ParameterizedTest
	@CsvSource({
			"TableEl, 1",
			"TableGs, 1",
			"TableWt, 1",
	})
	public void initializeWithElCount(String name, int expected) {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyEl);

		// テーブルを取得
		JTable table = getTable(frame, name);
		assertNotNull(table);

		// 件数を確認
		assertEquals(expected, table.getRowCount());
	}

	/**
	 * 件数の確認
	 * ガス代のみ
	 * 
	 * @param name     ID
	 * @param expected 件数
	 */
	@ParameterizedTest
	@CsvSource({
			"TableEl, 2",
			"TableGs, 2",
			"TableWt, 2",
	})
	public void initializeWithGsCount(String name, int expected) {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyGs);

		// テーブルを取得
		JTable table = getTable(frame, name);
		assertNotNull(table);

		// 件数を確認
		assertEquals(expected, table.getRowCount());
	}

	/**
	 * 件数の確認
	 * 水道代のみ
	 * Abookでは3件だが，Jbookでは光熱費のない年度は表示しないので2件となる
	 * 
	 * @param name     ID
	 * @param expected 件数
	 */
	@ParameterizedTest
	@CsvSource({
			"TableEl, 2",
			"TableGs, 2",
			"TableWt, 2",
	})
	public void initializeWithWtCount(String name, int expected) {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyWt);

		// テーブルを取得
		JTable table = getTable(frame, name);
		assertNotNull(table);

		// 件数を確認
		assertEquals(expected, table.getRowCount());
	}

	/**
	 * 件数の確認
	 * 
	 * @param name     ID
	 * @param expected 件数
	 */
	@ParameterizedTest
	@CsvSource({
			"TableEl, 6",
			"TableGs, 6",
			"TableWt, 6",
	})
	public void initializeWithEnergyCount(String name, int expected) {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, name);
		assertNotNull(table);

		// 件数を確認
		assertEquals(expected, table.getRowCount());
	}

	/**
	 * 光熱費の確認
	 * 電気代のみ
	 */
	@Test
	public void costWithElOnly() {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyEl);

		// テーブルを取得
		JTable table = getTable(frame, "TableEl");
		assertNotNull(table);

		// 件数を確認
		assertEquals( 2022, table.getValueAt(0, 0));
		assertEquals( 3300, table.getValueAt(0, 1));
		assertEquals( 3700, table.getValueAt(0, 2));
		assertEquals(    0, table.getValueAt(0, 3));
		assertEquals( 6300, table.getValueAt(0, 4));
		assertEquals(12500, table.getValueAt(0, 5));
		assertEquals( 2800, table.getValueAt(0, 6));
		assertEquals( 2900, table.getValueAt(0, 7));
		assertEquals( 3000, table.getValueAt(0, 8));
		assertEquals(    0, table.getValueAt(0, 9));
		assertEquals( 2100, table.getValueAt(0, 10));
		assertEquals( 2500, table.getValueAt(0, 11));
		assertEquals( 2900, table.getValueAt(0, 12));
	}

	/**
	 * 光熱費の確認
	 * ガス代のみ
	 */
	@Test
	public void costWithGsOnly() {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyGs);

		// テーブルを取得
		JTable table = getTable(frame, "TableGs");
		assertNotNull(table);

		// 件数を確認(1行目)
		assertEquals(2022, table.getValueAt(0, 0));
		assertEquals(2600, table.getValueAt(0, 1));
		assertEquals(2800, table.getValueAt(0, 2));
		assertEquals(3000, table.getValueAt(0, 3));
		assertEquals(3200, table.getValueAt(0, 4));
		assertEquals(3400, table.getValueAt(0, 5));
		assertEquals(3600, table.getValueAt(0, 6));
		assertEquals(3800, table.getValueAt(0, 7));
		assertEquals(4000, table.getValueAt(0, 8));
		assertEquals(4200, table.getValueAt(0, 9));
		assertEquals(4400, table.getValueAt(0, 10));
		assertEquals(4600, table.getValueAt(0, 11));
		assertEquals(4800, table.getValueAt(0, 12));

		// 件数を確認(2行目)
		assertEquals(2023, table.getValueAt(1, 0));
		assertEquals(5000, table.getValueAt(1, 1));
		assertEquals(5200, table.getValueAt(1, 2));
		assertEquals(5400, table.getValueAt(1, 3));
		assertEquals(5600, table.getValueAt(1, 4));
		assertEquals(5800, table.getValueAt(1, 5));
		assertEquals(6000, table.getValueAt(1, 6));
		assertEquals(6200, table.getValueAt(1, 7));
		assertEquals(6400, table.getValueAt(1, 8));
		assertEquals(6600, table.getValueAt(1, 9));
		assertEquals(2000, table.getValueAt(1, 10));
		assertEquals(2200, table.getValueAt(1, 11));
		assertEquals(2400, table.getValueAt(1, 12));
	}

	/**
	 * 光熱費の確認
	 * 水道代のみ
	 */
	@Test
	public void costWithWtOnly() {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyWt);

		// テーブルを取得
		JTable table = getTable(frame, "TableWt");
		assertNotNull(table);

		// 件数を確認(1行目)
		assertEquals(2022, table.getValueAt(0, 0));
		assertEquals(1300, table.getValueAt(0, 1));
		assertEquals(1400, table.getValueAt(0, 2));
		assertEquals(1500, table.getValueAt(0, 3));
		assertEquals(1600, table.getValueAt(0, 4));
		assertEquals(1700, table.getValueAt(0, 5));
		assertEquals(1800, table.getValueAt(0, 6));
		assertEquals(1900, table.getValueAt(0, 7));
		assertEquals(2000, table.getValueAt(0, 8));
		assertEquals(2100, table.getValueAt(0, 9));
		assertEquals(1000, table.getValueAt(0, 10));
		assertEquals(1100, table.getValueAt(0, 11));
		assertEquals(1200, table.getValueAt(0, 12));

		// 件数を確認(2行目)
		assertEquals(2024, table.getValueAt(1, 0));
		assertEquals(2500, table.getValueAt(1, 1));
		assertEquals(2600, table.getValueAt(1, 2));
		assertEquals(2700, table.getValueAt(1, 3));
		assertEquals(2800, table.getValueAt(1, 4));
		assertEquals(2900, table.getValueAt(1, 5));
		assertEquals(3000, table.getValueAt(1, 6));
		assertEquals(3100, table.getValueAt(1, 7));
		assertEquals(3200, table.getValueAt(1, 8));
		assertEquals(3300, table.getValueAt(1, 9));
		assertEquals(2200, table.getValueAt(1, 10));
		assertEquals(2300, table.getValueAt(1, 11));
		assertEquals(2400, table.getValueAt(1, 12));
	}

	/**
	 * 光熱費の確認
	 * 2016年電気代
	 */
	@Test
	public void costWithEl2016() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableEl");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2016, table.getValueAt(0, 0));
		assertEquals(1804, table.getValueAt(0, 1));
		assertEquals(1359, table.getValueAt(0, 2));
		assertEquals(1550, table.getValueAt(0, 3));
		assertEquals(3780, table.getValueAt(0, 4));
		assertEquals(3853, table.getValueAt(0, 5));
		assertEquals(3143, table.getValueAt(0, 6));
		assertEquals(1416, table.getValueAt(0, 7));
		assertEquals(1250, table.getValueAt(0, 8));
		assertEquals( 501, table.getValueAt(0, 9));
		assertEquals( 708, table.getValueAt(0, 10));
		assertEquals( 690, table.getValueAt(0, 11));
		assertEquals(1673, table.getValueAt(0, 12));
	}

	/**
	 * 光熱費の確認
	 * 2017年電気代
	 */
	@Test
	public void costWithEl2017() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableEl");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2017, table.getValueAt(1, 0));
		assertEquals(1926, table.getValueAt(1, 1));
		assertEquals(1321, table.getValueAt(1, 2));
		assertEquals(1475, table.getValueAt(1, 3));
		assertEquals(2045, table.getValueAt(1, 4));
		assertEquals(3147, table.getValueAt(1, 5));
		assertEquals(2918, table.getValueAt(1, 6));
		assertEquals(1306, table.getValueAt(1, 7));
		assertEquals(1791, table.getValueAt(1, 8));
		assertEquals(1573, table.getValueAt(1, 9));
		assertEquals(1675, table.getValueAt(1, 10));
		assertEquals(1783, table.getValueAt(1, 11));
		assertEquals(1357, table.getValueAt(1, 12));
	}

	/**
	 * 光熱費の確認
	 * 2018年電気代
	 */
	@Test
	public void costWithEl2018() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableEl");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2018, table.getValueAt(2, 0));
		assertEquals(1426, table.getValueAt(2, 1));
		assertEquals(1174, table.getValueAt(2, 2));
		assertEquals(1266, table.getValueAt(2, 3));
		assertEquals(2010, table.getValueAt(2, 4));
		assertEquals(2257, table.getValueAt(2, 5));
		assertEquals(1998, table.getValueAt(2, 6));
		assertEquals(1164, table.getValueAt(2, 7));
		assertEquals(1369, table.getValueAt(2, 8));
		assertEquals(1632, table.getValueAt(2, 9));
		assertEquals(1805, table.getValueAt(2, 10));
		assertEquals(1745, table.getValueAt(2, 11));
		assertEquals(1567, table.getValueAt(2, 12));
	}

	/**
	 * 光熱費の確認
	 * 2019年電気代
	 */
	@Test
	public void costWithEl2019() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableEl");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2019, table.getValueAt(3, 0));
		assertEquals(1577, table.getValueAt(3, 1));
		assertEquals(1231, table.getValueAt(3, 2));
		assertEquals(1342, table.getValueAt(3, 3));
		assertEquals(2267, table.getValueAt(3, 4));
		assertEquals(2659, table.getValueAt(3, 5));
		assertEquals(2533, table.getValueAt(3, 6));
		assertEquals(1374, table.getValueAt(3, 7));
		assertEquals(1298, table.getValueAt(3, 8));
		assertEquals(1636, table.getValueAt(3, 9));
		assertEquals(2296, table.getValueAt(3, 10));
		assertEquals(2071, table.getValueAt(3, 11));
		assertEquals(1588, table.getValueAt(3, 12));
	}

	/**
	 * 光熱費の確認
	 * 2020年電気代
	 */
	@Test
	public void costWithEl2020() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableEl");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2020, table.getValueAt(4, 0));
		assertEquals(1443, table.getValueAt(4, 1));
		assertEquals(1603, table.getValueAt(4, 2));
		assertEquals(1651, table.getValueAt(4, 3));
		assertEquals(2485, table.getValueAt(4, 4));
		assertEquals(3455, table.getValueAt(4, 5));
		assertEquals(1892, table.getValueAt(4, 6));
		assertEquals(2016, table.getValueAt(4, 7));
		assertEquals(1449, table.getValueAt(4, 8));
		assertEquals(1701, table.getValueAt(4, 9));
		assertEquals(1760, table.getValueAt(4, 10));
		assertEquals(1743, table.getValueAt(4, 11));
		assertEquals(1624, table.getValueAt(4, 12));
	}

	/**
	 * 光熱費の確認
	 * 2021年電気代
	 */
	@Test
	public void costWithEl2021() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableEl");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2021, table.getValueAt(5, 0));
		assertEquals(1729, table.getValueAt(5, 1));
		assertEquals(1412, table.getValueAt(5, 2));
		assertEquals(1517, table.getValueAt(5, 3));
		assertEquals(2350, table.getValueAt(5, 4));
		assertEquals(2294, table.getValueAt(5, 5));
		assertEquals(2304, table.getValueAt(5, 6));
		assertEquals(1433, table.getValueAt(5, 7));
		assertEquals(1477, table.getValueAt(5, 8));
		assertEquals(1498, table.getValueAt(5, 9));
		assertEquals(1860, table.getValueAt(5, 10));
		assertEquals(1413, table.getValueAt(5, 11));
		assertEquals(1506, table.getValueAt(5, 12));
	}

	/**
	 * 光熱費の確認
	 * 2016年ガス代
	 */
	@Test
	public void costWithGs2016() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableGs");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2016, table.getValueAt(0, 0));
		assertEquals(5422, table.getValueAt(0, 1));
		assertEquals(4462, table.getValueAt(0, 2));
		assertEquals(4659, table.getValueAt(0, 3));
		assertEquals(4363, table.getValueAt(0, 4));
		assertEquals(3969, table.getValueAt(0, 5));
		assertEquals(3771, table.getValueAt(0, 6));
		assertEquals(3820, table.getValueAt(0, 7));
		assertEquals(4857, table.getValueAt(0, 8));
		assertEquals(1995, table.getValueAt(0, 9));
		assertEquals(3031, table.getValueAt(0, 10));
		assertEquals(3574, table.getValueAt(0, 11));
		assertEquals(6574, table.getValueAt(0, 12));
	}

	/**
	 * 光熱費の確認
	 * 2017年ガス代
	 */
	@Test
	public void costWithGs2017() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableGs");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2017, table.getValueAt(1, 0));
		assertEquals(6213, table.getValueAt(1, 1));
		assertEquals(4413, table.getValueAt(1, 2));
		assertEquals(4464, table.getValueAt(1, 3));
		assertEquals(4310, table.getValueAt(1, 4));
		assertEquals(3847, table.getValueAt(1, 5));
		assertEquals(3538, table.getValueAt(1, 6));
		assertEquals(3898, table.getValueAt(1, 7));
		assertEquals(5390, table.getValueAt(1, 8));
		assertEquals(5647, table.getValueAt(1, 9));
		assertEquals(5596, table.getValueAt(1, 10));
		assertEquals(5596, table.getValueAt(1, 11));
		assertEquals(5339, table.getValueAt(1, 12));
	}

	/**
	 * 光熱費の確認
	 * 2018年ガス代
	 */
	@Test
	public void costWithGs2018() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableGs");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2018, table.getValueAt(2, 0));
		assertEquals(5339, table.getValueAt(2, 1));
		assertEquals(4207, table.getValueAt(2, 2));
		assertEquals(4824, table.getValueAt(2, 3));
		assertEquals(4001, table.getValueAt(2, 4));
		assertEquals(3538, table.getValueAt(2, 5));
		assertEquals(3692, table.getValueAt(2, 6));
		assertEquals(4053, table.getValueAt(2, 7));
		assertEquals(4876, table.getValueAt(2, 8));
		assertEquals(4979, table.getValueAt(2, 9));
		assertEquals(5133, table.getValueAt(2, 10));
		assertEquals(5339, table.getValueAt(2, 11));
		assertEquals(5184, table.getValueAt(2, 12));
	}

	/**
	 * 光熱費の確認
	 * 2019年ガス代
	 */
	@Test
	public void costWithGs2019() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableGs");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2019, table.getValueAt(3, 0));
		assertEquals(5544, table.getValueAt(3, 1));
		assertEquals(4506, table.getValueAt(3, 2));
		assertEquals(4342, table.getValueAt(3, 3));
		assertEquals(3937, table.getValueAt(3, 4));
		assertEquals(3435, table.getValueAt(3, 5));
		assertEquals(3538, table.getValueAt(3, 6));
		assertEquals(4104, table.getValueAt(3, 7));
		assertEquals(5184, table.getValueAt(3, 8));
		assertEquals(5380, table.getValueAt(3, 9));
		assertEquals(5380, table.getValueAt(3, 10));
		assertEquals(5871, table.getValueAt(3, 11));
		assertEquals(5544, table.getValueAt(3, 12));
	}

	/**
	 * 光熱費の確認
	 * 2020年ガス代
	 */
	@Test
	public void costWithGs2020() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableGs");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2020, table.getValueAt(4, 0));
		assertEquals(5325, table.getValueAt(4, 1));
		assertEquals(4725, table.getValueAt(4, 2));
		assertEquals(3633, table.getValueAt(4, 3));
		assertEquals(3687, table.getValueAt(4, 4));
		assertEquals(3523, table.getValueAt(4, 5));
		assertEquals(3523, table.getValueAt(4, 6));
		assertEquals(3906, table.getValueAt(4, 7));
		assertEquals(4452, table.getValueAt(4, 8));
		assertEquals(4943, table.getValueAt(4, 9));
		assertEquals(5287, table.getValueAt(4, 10));
		assertEquals(5758, table.getValueAt(4, 11));
		assertEquals(5287, table.getValueAt(4, 12));
	}

	/**
	 * 光熱費の確認
	 * 2021年ガス代
	 */
	@Test
	public void costWithGs2021() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableGs");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2021, table.getValueAt(5, 0));
		assertEquals(5405, table.getValueAt(5, 1));
		assertEquals(4713, table.getValueAt(5, 2));
		assertEquals(4501, table.getValueAt(5, 3));
		assertEquals(4209, table.getValueAt(5, 4));
		assertEquals(3801, table.getValueAt(5, 5));
		assertEquals(4326, table.getValueAt(5, 6));
		assertEquals(4501, table.getValueAt(5, 7));
		assertEquals(5026, table.getValueAt(5, 8));
		assertEquals(5492, table.getValueAt(5, 9));
		assertEquals(5551, table.getValueAt(5, 10));
		assertEquals(6116, table.getValueAt(5, 11));
		assertEquals(5772, table.getValueAt(5, 12));
	}

	/**
	 * 光熱費の確認
	 * 2016年水道代
	 */
	@Test
	public void costWithWt2016() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableWt");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2016, table.getValueAt(0, 0));
		assertEquals(1848, table.getValueAt(0, 1));
		assertEquals(1848, table.getValueAt(0, 2));
		assertEquals(1896, table.getValueAt(0, 3));
		assertEquals(1896, table.getValueAt(0, 4));
		assertEquals(1896, table.getValueAt(0, 5));
		assertEquals(1896, table.getValueAt(0, 6));
		assertEquals(1848, table.getValueAt(0, 7));
		assertEquals(1848, table.getValueAt(0, 8));
		assertEquals(1462, table.getValueAt(0, 9));
		assertEquals(1462, table.getValueAt(0, 10));
		assertEquals(1558, table.getValueAt(0, 11));
		assertEquals(1558, table.getValueAt(0, 12));
	}

	/**
	 * 光熱費の確認
	 * 2017年水道代
	 */
	@Test
	public void costWithWt2017() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableWt");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2017, table.getValueAt(1, 0));
		assertEquals(1848, table.getValueAt(1, 1));
		assertEquals(1848, table.getValueAt(1, 2));
		assertEquals(1848, table.getValueAt(1, 3));
		assertEquals(1848, table.getValueAt(1, 4));
		assertEquals(1896, table.getValueAt(1, 5));
		assertEquals(1896, table.getValueAt(1, 6));
		assertEquals(1799, table.getValueAt(1, 7));
		assertEquals(1799, table.getValueAt(1, 8));
		assertEquals(1799, table.getValueAt(1, 9));
		assertEquals(1799, table.getValueAt(1, 10));
		assertEquals(1751, table.getValueAt(1, 11));
		assertEquals(1751, table.getValueAt(1, 12));
	}

	/**
	 * 光熱費の確認
	 * 2018年水道代
	 */
	@Test
	public void costWithWt2018() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableWt");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2018, table.getValueAt(2, 0));
		assertEquals(1703, table.getValueAt(2, 1));
		assertEquals(1703, table.getValueAt(2, 2));
		assertEquals(1848, table.getValueAt(2, 3));
		assertEquals(1848, table.getValueAt(2, 4));
		assertEquals(1848, table.getValueAt(2, 5));
		assertEquals(1848, table.getValueAt(2, 6));
		assertEquals(1751, table.getValueAt(2, 7));
		assertEquals(1751, table.getValueAt(2, 8));
		assertEquals(1751, table.getValueAt(2, 9));
		assertEquals(1751, table.getValueAt(2, 10));
		assertEquals(1703, table.getValueAt(2, 11));
		assertEquals(1703, table.getValueAt(2, 12));
	}

	/**
	 * 光熱費の確認
	 * 2019年水道代
	 */
	@Test
	public void costWithWt2019() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableWt");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2019, table.getValueAt(3, 0));
		assertEquals(1751, table.getValueAt(3, 1));
		assertEquals(1751, table.getValueAt(3, 2));
		assertEquals(1751, table.getValueAt(3, 3));
		assertEquals(1751, table.getValueAt(3, 4));
		assertEquals(1751, table.getValueAt(3, 5));
		assertEquals(1751, table.getValueAt(3, 6));
		assertEquals(1799, table.getValueAt(3, 7));
		assertEquals(1799, table.getValueAt(3, 8));
		assertEquals(1751, table.getValueAt(3, 9));
		assertEquals(1751, table.getValueAt(3, 10));
		assertEquals(1751, table.getValueAt(3, 11));
		assertEquals(1751, table.getValueAt(3, 12));
	}

	/**
	 * 光熱費の確認
	 * 2020年水道代
	 */
	@Test
	public void costWithWt2020() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableWt");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2020, table.getValueAt(4, 0));
		assertEquals(1799, table.getValueAt(4, 1));
		assertEquals(1799, table.getValueAt(4, 2));
		assertEquals(1703, table.getValueAt(4, 3));
		assertEquals(1703, table.getValueAt(4, 4));
		assertEquals(1799, table.getValueAt(4, 5));
		assertEquals(1799, table.getValueAt(4, 6));
		assertEquals(1703, table.getValueAt(4, 7));
		assertEquals(1703, table.getValueAt(4, 8));
		assertEquals(1655, table.getValueAt(4, 9));
		assertEquals(1655, table.getValueAt(4, 10));
		assertEquals(1703, table.getValueAt(4, 11));
		assertEquals(1703, table.getValueAt(4, 12));
	}

	/**
	 * 光熱費の確認
	 * 2021年水道代
	 */
	@Test
	public void costWithWt2021() {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// テーブルを取得
		JTable table = getTable(frame, "TableWt");
		assertNotNull(table);

		// 件数を確認
		assertEquals(2021, table.getValueAt(5, 0));
		assertEquals(1703, table.getValueAt(5, 1));
		assertEquals(1703, table.getValueAt(5, 2));
		assertEquals(1801, table.getValueAt(5, 3));
		assertEquals(1801, table.getValueAt(5, 4));
		assertEquals(1851, table.getValueAt(5, 5));
		assertEquals(1851, table.getValueAt(5, 6));
		assertEquals(1801, table.getValueAt(5, 7));
		assertEquals(1801, table.getValueAt(5, 8));
		assertEquals(1801, table.getValueAt(5, 9));
		assertEquals(1801, table.getValueAt(5, 10));
		assertEquals(1751, table.getValueAt(5, 11));
		assertEquals(1751, table.getValueAt(5, 12));
	}

	/**
	 * 最大値の確認
	 * 電気代のみ
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void maxWithOnlyEl() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyEl);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableEl");
		assertNotNull(table);

		// 1年分しかないためすべて最大値でも最小値でもない
		for (int col = 1; col <= 12; col++) {
			Component component = getTableCellRendererComponent(table, 0, col);
			assertEquals(Color.BLACK, component.getForeground());
		}
	}

	/**
	 * 最大値の確認
	 * ガス代のみ
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void maxWithOnlyGs() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyGs);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableGs");
		assertNotNull(table);

		// 各月の最大値を確認
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 1).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 2).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 3).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 4).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 5).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 6).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 7).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 8).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 9).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 10).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 11).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 12).getForeground());
	}

	/**
	 * 最大値の確認
	 * 水道代のみ
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void maxWithOnlyWt() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyWt);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableWt");
		assertNotNull(table);

		// 各月の最大値を確認
		for (int col = 1; col <= 12; col++) {
			Component component = getTableCellRendererComponent(table, 1, col);
			assertEquals(Color.RED, component.getForeground());
		}
	}

	/**
	 * 最小値の確認
	 * 電気代のみ
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void minWithOnlyEl() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyEl);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableEl");
		assertNotNull(table);

		// 1年分しかないためすべて最大値でも最小値でもない
		for (int col = 1; col <= 12; col++) {
			Component component = getTableCellRendererComponent(table, 0, col);
			assertEquals(Color.BLACK, component.getForeground());
		}
	}

	/**
	 * 最小値の確認
	 * ガス代のみ
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void minWithOnlyGs() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyGs);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableGs");
		assertNotNull(table);

		// 各月の最小値を確認
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 1).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 2).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 3).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 4).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 5).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 6).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 7).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 8).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 9).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 1, 10).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 1, 11).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 1, 12).getForeground());
	}

	/**
	 * 最小値の確認
	 * 水道代のみ
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void minWithOnlyWt() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyWt);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableWt");
		assertNotNull(table);

		// 各月の最小値を確認
		for (int col = 1; col <= 12; col++) {
			Component component = getTableCellRendererComponent(table, 0, col);
			assertEquals(Color.BLUE, component.getForeground());
		}
	}

	/**
	 * 最大値の確認
	 * 電気代
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void maxWithEl() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableEl");
		assertNotNull(table);

		// 各月の最大値を確認
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 1).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 4, 2).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 4, 3).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 4).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 5).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 6).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 4, 7).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 8).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 4, 9).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 3, 10).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 3, 11).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 12).getForeground());
	}

	/**
	 * 最大値の確認
	 * ガス代
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void maxWithGs() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableGs");
		assertNotNull(table);

		// 各月の最大値を確認
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 1).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 4, 2).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 2, 3).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 4).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 5).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 5, 6).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 5, 7).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 8).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 9).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 10).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 5, 11).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 12).getForeground());
	}

	/**
	 * 最大値の確認
	 * 水道代
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void maxWithWt() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableWt");
		assertNotNull(table);

		// 各月の最大値を確認
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 1).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 1).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 2).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 2).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 3).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 4).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 5).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 5).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 6).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 6).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 7).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 0, 8).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 5, 9).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 5, 10).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 11).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 3, 11).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 5, 11).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 1, 12).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 3, 12).getForeground());
		assertEquals(Color.RED, getTableCellRendererComponent(table, 5, 12).getForeground());
	}

	/**
	 * 最小値の確認
	 * 電気代
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void minWithEl() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableEl");
		assertNotNull(table);

		// 各月の最小値を確認
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 2, 1).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 2, 2).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 2, 3).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 2, 4).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 2, 5).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 4, 6).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 2, 7).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 8).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 9).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 10).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 11).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 1, 12).getForeground());
	}

	/**
	 * 最小値の確認
	 * ガス代
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void minWithGs() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableGs");
		assertNotNull(table);

		// 各月の最小値を確認
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 4, 1).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 2, 2).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 4, 3).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 4, 4).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 3, 5).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 4, 6).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 7).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 4, 8).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 9).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 10).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 11).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 2, 12).getForeground());
	}

	/**
	 * 最小値の確認
	 * 水道代
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void minWithWt() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(energies);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable table = getTable(frame, "TableWt");
		assertNotNull(table);

		// 各月の最小値を確認
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 2, 1).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 5, 1).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 2, 2).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 5, 2).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 4, 3).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 4, 4).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 3, 5).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 3, 6).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 4, 7).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 4, 8).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 9).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 10).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 11).getForeground());
		assertEquals(Color.BLUE, getTableCellRendererComponent(table, 0, 12).getForeground());
	}

	/**
	 * 0円の光熱費しかないときのテスト
	 * エラーにならずに表示されればそれでOK
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void errorNotOccuredWithOnlyZero() throws InterruptedException {

		// 画面を表示
		AbSubformEnergy frame = showForm(onlyZero);

		// ここで少し待たないとテーブルの内容が正しく取得できない
		Thread.sleep(500);

		// テーブルを取得
		JTable tableEl = getTable(frame, "TableEl");
		JTable tableGs = getTable(frame, "TableGs");
		JTable tableWt = getTable(frame, "TableWt");
		assertNotNull(tableEl);
		assertNotNull(tableGs);
		assertNotNull(tableWt);
	}
}
