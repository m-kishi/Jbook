// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import abook.common.AbUtility.MSG;
import abook.form.AbFormMain;
import abook.property.AbProperty;

/**
 * アプリケーション
 */
public class App {

	/**
	 * アプリケーションメイン
	 * 
	 * @param args 引数(使用しない)
	 */
	public static void main(String[] args) {
		try {
			// Mac デフォルトの見た目はダイアログにバグがあるため swing デフォルトの見た目を設定
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			// テーブルヘッダのフォントをここで設定
			Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 12);
			UIManager.put("TableHeader.font", new FontUIResource(font));

			// 設定ファイルからDBファイルパスを読み込み
			String dbFilePath = AbProperty.getDBFilePath();

			// メインフォームを表示
			AbFormMain form = new AbFormMain(dbFilePath);
			form.setVisible(true);

		} catch (Exception ex) {
			MSG.abort(ex.getMessage());
			System.exit(-1);
		}
	}
}
