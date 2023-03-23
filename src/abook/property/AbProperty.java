// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.property;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import abook.common.AbException;
import abook.common.AbManager;
import abook.common.AbManager.MESSAGE;
import abook.common.AbUtility.UTL;

/**
 * 設定ファイル管理クラス
 */
public class AbProperty {

	/** 設定ファイル */
	private static Properties settings;

	/** 設定ファイル名 */
	private static final String PROPERTY_FILE = "Abook.properties";

	/** 設定ファイルのキー */
	private static final String PROPERTY_KEY = "db";

	/** デフォルトのDBファイル */
	private static final String ABOOK_DB = "Abook.db";

	/**
	 * コンストラクタ
	 */
	private AbProperty() {
	}

	/**
	 * 設定ファイル読み込み
	 * 
	 * @throws AbException
	 */
	private static void loadProperty() throws AbException {
		try {
			Path path = Paths.get(PROPERTY_FILE);
			if (Files.notExists(path)) {
				Files.createFile(path);
			}

			settings = new Properties();
			FileInputStream in = null;
			try {
				in = new FileInputStream(PROPERTY_FILE);
				settings.load(in);
			} finally {
				if (in != null) {
					in.close();
				}
			}
		} catch (IOException ex) {
			String message = String.format(MESSAGE.PROPERTIES_LOAD, ex.getMessage());
			AbManager.throwException(message);
		}
	}

	/**
	 * DBファイルパス取得
	 * 
	 * @return DBファイルパス
	 * @throws AbException
	 */
	public static String getDBFilePath() throws AbException {
		if (settings == null) {
			loadProperty();
		}
		String dbFilePath = settings.getProperty(PROPERTY_KEY);
		return UTL.isEmpty(dbFilePath) ? ABOOK_DB : dbFilePath;
	}

	/**
	 * DBファイルパス保存
	 * 
	 * @param file DBファイルパス
	 * @throws AbException
	 */
	public static void storeDBFilePath(String file) throws AbException {
		FileOutputStream out = null;
		try {
			try {
				out = new FileOutputStream(PROPERTY_FILE);
				settings.setProperty(PROPERTY_KEY, file);
				settings.store(out, "Abook Properties");
			} finally {
				if (out != null) {
					out.close();
				}

			}
		} catch (IOException ex) {
			String message = String.format(MESSAGE.PROPERTIES_STORE, ex.getMessage());
			AbManager.throwException(message);
		}
	}
}
