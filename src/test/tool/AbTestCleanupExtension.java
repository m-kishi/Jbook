// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.tool;

import java.awt.Window;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * FrameやDialogをテストするときの終了処理
 * Java9以降はsun.awt.AppContextが非公開になっているため，SwingTestCleanupExtensionが使えない．
 * このクラスの実装により終了処理を代替するが，AppContextが使えないので一部の処理(Windowの解放など)を妥協している．
 */
public class AbTestCleanupExtension implements AfterEachCallback {

	@Override
	public void afterEach(ExtensionContext arg0) throws InterruptedException, InvocationTargetException {
		purgeWindows();
	}

	/**
	 * テストで表示したWindow(Frame or Dialog)を解放する
	 * 
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 */
	public void purgeWindows() throws InterruptedException, InvocationTargetException {

		// EDT(Event Dispatch Thread)上で処理して完了を待つ
		SwingUtilities.invokeAndWait(() -> {
			for (Window w : Window.getWindows()) {
				// テストが終わったWindowは非表示に設定
				w.setVisible(false);
				// リソースを解放(現状は意味なし)
				w.dispose();
			}
			// Java9以降は以下の処理が実行できない
			// AppContext.getAppContext().remove(Window.class);
		});
	}
}
