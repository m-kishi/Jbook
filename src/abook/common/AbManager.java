// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.common;

/**
 * 管理クラス
 */
public class AbManager {

	/**
	 * コンストラクタ
	 */
	private AbManager() {
	}

	/**
	 * 例外を投げる
	 * 
	 * @param message メッセージ
	 * @throws AbException
	 */
	public static void throwException(String message) throws AbException {
		throw new AbException(message);
	}

	/**
	 * メッセージ
	 */
	public static class MESSAGE {

		/** 設定ファイル読込エラー：%s */
		public static final String PROPERTIES_LOAD = "設定ファイル読込エラー：%s";
		/** 設定ファイル保存エラー：%s */
		public static final String PROPERTIES_STORE = "設定ファイル保存エラー：%s";

		/** 設定しました．アプリを再起動してください． */
		public static final String SETTING_COMPLETE = "<html>設定しました．<br>アプリを再起動してください．";
		/** 正常に登録しました． */
		public static final String ENTRY_COMPLETE = "正常に登録しました．";

		/** DBファイル作成エラー：%s */
		public static final String DB_FILE_CREATE = "DBファイル作成エラー：%s";
		/** DBファイル読込エラー：%s */
		public static final String DB_FILE_READ = "DBファイル読込エラー：%s";
		/** DBファイルエラー：%d行目：%s */
		public static final String DB_FILE_LOAD = "DBファイルエラー：%d行目：%s";
		/** DBファイル登録エラー：%s */
		public static final String DB_FILE_STORE = "DBファイル登録エラー：%s";
		/** %d行目：%s */
		public static final String EXPENSE_ERROR = "%d行目：%s";
		/** 列数が「%d」ではありません． */
		public static final String DB_FILE_FIELD = "列数が「%d」ではありません．";

		/** 日付がありません． */
		public static final String DATE_NULL = "日付がありません．";
		/** 日付が正しくありません． */
		public static final String DATE_FORMAT = "日付が正しくありません．";
		/** 名称がありません． */
		public static final String NAME_NULL = "名称がありません．";
		/** 種別がありません． */
		public static final String TYPE_NULL = "種別がありません．";
		/** 種別が正しくありません． */
		public static final String TYPE_WRONG = "種別が正しくありません．";
		/** 金額がありません． */
		public static final String COST_NULL = "金額がありません．";
		/** 金額がマイナスです． */
		public static final String COST_MINUS = "金額がマイナスです．";
		/** 金額が正しくありません． */
		public static final String COST_FORMAT = "金額が正しくありません．";
	}
}
