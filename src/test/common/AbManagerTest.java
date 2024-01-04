// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import abook.common.AbException;
import abook.common.AbManager;

/**
 * テスト:管理クラス
 */
public class AbManagerTest {

	/**
	 * 例外を投げる
	 */
	@Test
	public void throwException() {

		// 例外を投げる
		String message = "AbException message";
		AbException ex = assertThrows(AbException.class,
				() -> AbManager.throwException(message)
		);

		// メッセージを確認
		assertEquals(message, ex.getMessage());
	}
}
