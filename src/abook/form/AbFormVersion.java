// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * バージョン情報フォーム
 */
public class AbFormVersion extends JDialog {

	/** アプリ */
	private final String APP_NAME = "Abook";

	/** タイトル */
	private final String TITLE = "Abookのバージョン情報";

	/** バージョン */
	private final String VERSION = "Version 1.0.0";

	/** コピーライト */
	private final String COPYRIGHT = "©︎ 2022 https://github.com/m-kishi";

	/** アプリの説明 */
	private final String DESCRIPTION = "This is my account book application.";

	/**
	 * コンストラクタ
	 */
	public AbFormVersion() {
		super();

		// フォーム
		setTitle(TITLE);
		setModal(true);
		setSize(320, 130);
		setResizable(false);
		setLocationRelativeTo(null);

		// バージョン情報の表示エリア
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel(layout);

		// アイコン
		ImageIcon icon = null;
		try {
			ClassLoader loader = getClass().getClassLoader();
			BufferedImage bufferedImage = ImageIO.read(loader.getResource("abook/resource/image/abook.png"));
			Image image = bufferedImage.getScaledInstance(52, 52, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (Exception ex) {
			// 無視
		}
		JLabel lblIcon = new JLabel(icon);
		panel.add(lblIcon);

		// アプリ
		VersionLabel lblAppName = new VersionLabel(APP_NAME);
		panel.add(lblAppName);

		// バージョン
		VersionLabel lblVersion = new VersionLabel(VERSION);
		panel.add(lblVersion);

		// コピーライト
		VersionLabel lblCopyright = new VersionLabel(COPYRIGHT);
		panel.add(lblCopyright);

		// アプリの説明
		VersionLabel lblDescription = new VersionLabel(DESCRIPTION);
		panel.add(lblDescription);

		// レイアウトの配置設定
		layout.putConstraint(SpringLayout.NORTH, lblIcon, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblIcon, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, lblAppName, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblAppName, 10, SpringLayout.EAST, lblIcon);
		layout.putConstraint(SpringLayout.NORTH, lblVersion, 2, SpringLayout.SOUTH, lblAppName);
		layout.putConstraint(SpringLayout.WEST, lblVersion, 10, SpringLayout.EAST, lblIcon);
		layout.putConstraint(SpringLayout.NORTH, lblCopyright, 2, SpringLayout.SOUTH, lblVersion);
		layout.putConstraint(SpringLayout.WEST, lblCopyright, 10, SpringLayout.EAST, lblIcon);
		layout.putConstraint(SpringLayout.NORTH, lblDescription, 2, SpringLayout.SOUTH, lblCopyright);
		layout.putConstraint(SpringLayout.WEST, lblDescription, 10, SpringLayout.EAST, lblIcon);

		add(panel, BorderLayout.CENTER);
	}

	/**
	 * バージョン情報フォーム用のカスタムラベル
	 */
	private class VersionLabel extends JLabel {

		/**
		 * コンストラクタ
		 * 
		 * @param text テキスト
		 */
		public VersionLabel(String text) {
			super(text);
			setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		}
	}
}
