// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.alyssaburlton.swingtest.ComponentFindersKt;
import com.github.alyssaburlton.swingtest.SwingUtilitiesKt;

import abook.form.AbFormVersion;
import test.tool.AbTestCleanupExtension;

/**
 * テスト:バージョン情報フォーム
 */
@ExtendWith(AbTestCleanupExtension.class)
public class AbFormVersionTest {

	/**
	 * 画面を表示
	 */
	@Test
	public void abFormVersion() {

		// 画面を表示
		SwingUtilities.invokeLater(() -> {
			new AbFormVersion().setVisible(true);
		});
		SwingUtilitiesKt.flushEdt();

		// バージョン情報フォームを取得
		JDialog frame = ComponentFindersKt.findWindow(AbFormVersion.class, (w) -> {
			return w.isVisible();
		});
		assertNotNull(frame);

		// 正しく取得できたか確認
		assertEquals("Abookのバージョン情報", frame.getTitle());

		// *******************
		// アイコンは目視確認する
		// *******************

		// アプリを確認
		JLabel lblAppName = ComponentFindersKt.findChild(frame, JLabel.class, null, AbFormVersion.APP_NAME);
		assertNotNull(lblAppName);

		// バージョンを確認
		JLabel lblVersion = ComponentFindersKt.findChild(frame, JLabel.class, null, AbFormVersion.VERSION);
		assertNotNull(lblVersion);

		// コピーライト
		JLabel lblCopyright = ComponentFindersKt.findChild(frame, JLabel.class, null, AbFormVersion.COPYRIGHT);
		assertNotNull(lblCopyright);

		// アプリの説明
		JLabel lblDescription = ComponentFindersKt.findChild(frame, JLabel.class, null, AbFormVersion.DESCRIPTION);
		assertNotNull(lblDescription);

		// 後始末
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(false);
			frame.dispose();
		});
		SwingUtilitiesKt.flushEdt();
	}
}
