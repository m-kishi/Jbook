// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.common;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import abook.common.AbConstant.FMT;

/**
 * ユーティリティクラス
 */
public class AbUtility {

	/**
	 * コンストラクタ
	 */
	private AbUtility() {
	}

	/**
	 * ユーティリティ
	 */
	public static class UTL {

		/**
		 * 空文字判定
		 * 
		 * @param string 文字列
		 * @return true:Null or Empty false:空文字でない
		 */
		public static boolean isEmpty(String string) {
			return (string == null || string.isEmpty());
		}

		/**
		 * 日付から文字列形式への変換
		 * 
		 * @param date 日付
		 * @return yyyy-MM-dd
		 */
		public static String toString(LocalDate date) {
			return date.format(DateTimeFormatter.ofPattern(FMT.DATE));
		}

		/**
		 * 日付から年月形式への変換
		 * 
		 * @param date 日付
		 * @return yyyy-MM
		 */
		public static String toMonthly(LocalDate date) {
			return date.format(DateTimeFormatter.ofPattern(FMT.MONTHLY_GROUP));
		}

		/**
		 * 日付から月形式へ変換
		 * 
		 * @param date 日付
		 * @return MM
		 */
		public static String toMonth(LocalDate date) {
			return date.format(DateTimeFormatter.ofPattern(FMT.MONTH));
		}

		/**
		 * 日付からタイトル文字列へ変換
		 * 
		 * @param date 日付
		 * @return yyyy年MM月
		 */
		public static String toTitle(LocalDate date) {
			return date.format(DateTimeFormatter.ofPattern(FMT.TITLE));
		}

		/**
		 * 文字列から日付へ変換
		 * 
		 * @param string 文字列
		 * @return 日付
		 */
		public static LocalDate toLocalDate(String string) {
			return LocalDate.parse(string, DateTimeFormatter.ofPattern(FMT.DATE));
		}

		/**
		 * 金額を通貨形式へ変換
		 * 
		 * @param cost   金額
		 * @param locale ロケール
		 * @return 通貨形式(¥9,999)
		 */
		public static String toCurrency(Object cost, Locale locale) {
			if (cost == null) {
				return null;
			}

			NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
			try {
				return nf.format(cost);
			} catch (IllegalArgumentException ex) {
				return null;
			}
		}

		/**
		 * ツールチップのための円マーク変換
		 * 
		 * @param note 備考
		 * @return 円マークを &yen; に変換
		 */
		public static String replaceYenMark(String note) {
			if (isEmpty(note)) {
				return "";
			}
			return note.replaceAll("¥", "&yen;");
		}
	}

	/**
	 * メッセージダイアログ
	 */
	public static class MSG {

		/**
		 * OKダイアログ
		 * 
		 * @param title   タイトル
		 * @param message メッセージ
		 */
		public static void ok(String title, String message) {
			ok(null, title, message);
		}

		/**
		 * OKダイアログ
		 * 
		 * @param frame   フレーム
		 * @param title   タイトル
		 * @param message メッセージ
		 */
		public static void ok(JFrame frame, String title, String message) {
			JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
		}

		/**
		 * エラーダイアログ
		 * 
		 * @param message メッセージ
		 */
		public static void error(String message) {
			error(null, message);
		}

		/**
		 * エラーメッセージ
		 * 
		 * @param frame   フレーム
		 * @param message メッセージ
		 */
		public static void error(JFrame frame, String message) {
			JOptionPane.showMessageDialog(frame, message, "エラー", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * システムエラーダイアログ
		 * 
		 * @param message メッセージ
		 */
		public static void abort(String message) {
			abort(null, message);
		}

		/**
		 * システムエラーダイアログ
		 * 
		 * @param frame   フレーム
		 * @param message メッセージ
		 */
		public static void abort(JFrame frame, String message) {
			JOptionPane.showMessageDialog(frame, message, "エラー", JOptionPane.ERROR_MESSAGE);
		}
	}
}
