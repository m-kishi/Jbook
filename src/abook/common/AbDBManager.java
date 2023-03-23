// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import abook.common.AbConstant.COL;
import abook.common.AbManager.MESSAGE;
import abook.expense.AbExpense;

/**
 * DBファイル管理クラス
 */
public class AbDBManager {

	/** フィールド数 */
	private static final int FIELD_SIZE = 5;

	/**
	 * コンストラクタ
	 */
	private AbDBManager() {
	}

	/**
	 * DBファイル読み込み
	 * 
	 * @param dbFilePath DBファイルパス
	 * @return 支出情報リスト
	 * @throws AbException
	 */
	public static List<AbExpense> load(String dbFilePath) throws AbException {
		List<AbExpense> expenses = new ArrayList<AbExpense>();

		// DBファイルが存在しないときは新規作成
		Path path = Paths.get(dbFilePath);
		if (Files.notExists(path)) {
			try {
				Files.createFile(path);
			} catch (Exception ex) {
				String message = String.format(MESSAGE.DB_FILE_CREATE, ex.getMessage());
				AbManager.throwException(message);
			}
		}

		// DBファイル読み込み
		int line = 0;
		try {
			Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(dbFilePath), "UTF-8"));
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(r);
			for (CSVRecord record : records) {
				line++;
				if (record.size() != FIELD_SIZE) {
					AbManager.throwException(String.format(MESSAGE.DB_FILE_FIELD, FIELD_SIZE));
				}
				expenses.add(
						new AbExpense(
								record.get(COL.EXPENSE.DATE),
								record.get(COL.EXPENSE.NAME),
								record.get(COL.EXPENSE.TYPE),
								record.get(COL.EXPENSE.COST),
								record.get(COL.EXPENSE.NOTE)
						)
				);
			}
		} catch (AbException ex) {
			String message = String.format(MESSAGE.DB_FILE_LOAD, line, ex.getMessage());
			AbManager.throwException(message);
		} catch (Exception ex) {
			String message = String.format(MESSAGE.DB_FILE_READ, ex.getMessage());
			AbManager.throwException(message);
		}

		return expenses;
	}

	/**
	 * DBファイル書き出し
	 * 
	 * @param dbFilePath DBファイルパス
	 * @param expenses   支出情報リスト
	 * @throws AbException
	 */
	public static void store(String dbFilePath, List<AbExpense> expenses) throws AbException {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dbFilePath), "UTF-8")));
			for (AbExpense expense : expenses) {
				pw.println(expense.toDBFileFormat());
			}
			pw.close();
		} catch (IOException ex) {
			String message = String.format(MESSAGE.DB_FILE_STORE, ex.getMessage());
			AbManager.throwException(message);
		}
	}
}
