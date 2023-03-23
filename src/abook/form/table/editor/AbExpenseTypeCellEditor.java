// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.editor;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicComboBoxUI;

import abook.common.AbConstant.TYPE;

/**
 * 種別のエディタ
 */
public class AbExpenseTypeCellEditor extends DefaultCellEditor {

	/** 選択肢の支出情報 */
	private static JComboBox<String> comboBox = new JComboBox<String>(
			TYPE.EXPENSES.toArray(new String[TYPE.EXPENSES.size()])
	) {
		@Override
		public void updateUI() {
			super.updateUI();
			setBorder(BorderFactory.createEmptyBorder());
			setUI(new BasicComboBoxUI() {
				@Override
				protected JButton createArrowButton() {
					JButton button = super.createArrowButton();
					button.setContentAreaFilled(false);
					button.setBorder(BorderFactory.createEmptyBorder());
					return button;
				}
			});
		}
	};

	/**
	 * コンストラクタ
	 */
	public AbExpenseTypeCellEditor() {
		super(comboBox);

		// 中央揃え
		((JLabel) comboBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

		// クリックしてドロップダウンリストを開くとたまに開かないことがあって動作が安定しないため
		// ダブルクリックしないと開かないように設定
		setClickCountToStart(2);
	}
}
