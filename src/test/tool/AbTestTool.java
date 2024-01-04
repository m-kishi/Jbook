// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.tool;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import abook.common.AbConstant.COL;
import abook.common.AbException;
import abook.expense.AbExpense;

/**
 * テストツール
 */
public class AbTestTool {

	/**
	 * 支出情報を取得
	 * 
	 * @param fields 支出情報の各項目
	 * @return 支出情報
	 * @throws AbException
	 */
	public static AbExpense getExpense(String[] fields) throws AbException {
		return new AbExpense(
				fields[COL.EXPENSE.DATE].strip(),
				fields[COL.EXPENSE.NAME].strip(),
				fields[COL.EXPENSE.TYPE].strip(),
				fields[COL.EXPENSE.COST].strip(),
				(fields.length == 5) ? fields[COL.EXPENSE.NOTE].strip() : "");
	}

	/**
	 * 支出情報リストを取得
	 * 
	 * @param params パラメタリスト
	 * @return 支出情報リスト
	 * @throws AbException
	 */
	public static List<AbExpense> getExpenses(List<String[]> params) throws AbException {
		List<AbExpense> expenses = new ArrayList<AbExpense>();
		for (String[] fields : params) {
			expenses.add(getExpense(fields));
		}
		return expenses;
	}

	/**
	 * DBファイルを作成
	 * 
	 * @param dbFilePath DBファイルパス
	 * @param params     パラメタリスト
	 * @return 支出情報リスト
	 * @throws AbException
	 * @throws IOException
	 */
	public static List<AbExpense> createDBFileFromParams(String dbFilePath, List<String[]> params) throws AbException, IOException {

		// DBファイルを作成
		List<AbExpense> expenses = getExpenses(params);
		AbTestTool.createDBFile(dbFilePath, expenses);

		return expenses;
	}

	/**
	 * DBファイルを作成
	 * 
	 * @param dbFilePath DBファイルパス
	 * @param expenses   支出情報リスト
	 * @throws IOException
	 */
	public static void createDBFile(String dbFilePath, List<AbExpense> expenses) throws IOException {
		PrintWriter pw = getPrintWriter(dbFilePath);
		for (AbExpense expense : expenses) {
			pw.println(expense.toDBFileFormat());
		}
		pw.close();
	}

	/**
	 * フィールド数の多いDBファイルを作成
	 * 
	 * @param dbFilePath DBファイルパス
	 * @throws IOException
	 */
	public static void createMoreField(String dbFilePath) throws IOException {
		PrintWriter pw = getPrintWriter(dbFilePath);
		pw.println("\"date\",\"name\",\"type\",\"cost\",\"note\",\"more\"");
		pw.close();
	}

	/**
	 * フィールド数の少ないDBファイルを作成
	 * 
	 * @param dbFilePath DBファイルパス
	 * @throws IOException
	 */
	public static void createLessField(String dbFilePath) throws IOException {
		PrintWriter pw = getPrintWriter(dbFilePath);
		pw.println("\"date\",\"name\",\"type\",\"cost\"");
		pw.close();
	}

	/**
	 * フォーマットが正しくないDBファイルを作成
	 * 
	 * @param dbFilePath DBファイルパス
	 * @throws IOException
	 */
	public static void createInvalidFormat(String dbFilePath) throws IOException {
		PrintWriter pw = getPrintWriter(dbFilePath);
		pw.println("=============");
		pw.println("InvalidFormat");
		pw.println("=============");
		pw.close();
	}

	/**
	 * 日付が不正なDBファイルを作成
	 * 
	 * @param dbFilePath DBファイルパス
	 * @throws IOException
	 */
	public static void createInvalidDate(String dbFilePath) throws IOException {
		PrintWriter pw = getPrintWriter(dbFilePath);
		pw.println("\"2023-99-99\",\"name1\",\"食費\",\"100\",\"\"");
		pw.close();
	}

	/**
	 * PrintWriterを取得
	 * 
	 * @param dbFilePath DBファイルパス
	 * @return PrintWriter
	 * @throws IOException
	 */
	private static PrintWriter getPrintWriter(String dbFilePath) throws IOException {
		return new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dbFilePath), "UTF-8"))
		);
	}

	/**
	 * DBファイルを削除
	 * 
	 * @param dbFilePath DBファイルパス
	 * @throws IOException
	 */
	public static void deleteDBFile(String dbFilePath) throws IOException {
		Path path = Paths.get(dbFilePath);
		if (Files.exists(path)) {
			Files.delete(path);
		}
	}
}
