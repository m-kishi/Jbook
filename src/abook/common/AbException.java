// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.common;

/**
 * 例外クラス
 */
public class AbException extends Exception {

	/**
	 * コンストラクタ
	 * AbManager から new するためにアクセス修飾子はなし
	 * 
	 * @param message メッセージ
	 */
	AbException(String message) {
		super(message);
	}
}
