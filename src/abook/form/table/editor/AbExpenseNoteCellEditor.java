// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.editor;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

/**
 * 備考のエディタ
 */
public class AbExpenseNoteCellEditor extends DefaultCellEditor {

	/**
	 * コンストラクタ
	 */
	public AbExpenseNoteCellEditor() {
		super(new JTextField());

		setClickCountToStart(1);

		JTextField field = (JTextField) getComponent();
		field.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));
		field.setBorder(BorderFactory.createEmptyBorder());
		field.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// フォーカス取得時に全選択
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						JTextField field = (JTextField) e.getComponent();
						field.selectAll();
					}
				});
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
		});
	}
}
