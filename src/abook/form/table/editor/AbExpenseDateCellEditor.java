// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.editor;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.TableCellEditor;

import abook.common.AbConstant.FMT;

/**
 * 日付のエディタ
 */
public class AbExpenseDateCellEditor extends AbstractCellEditor implements TableCellEditor {

	/** 上下矢印で日付選択 */
	private final JSpinner spinner = new JSpinner(new SpinnerDateModel());

	/**
	 * コンストラクタ
	 */
	public AbExpenseDateCellEditor() {
		super();

		DateEditor editor = new DateEditor(spinner, FMT.DATE);
		spinner.setEditor(editor);
		spinner.setBorder(BorderFactory.createEmptyBorder());
		setArrowButtonEnable(false);

		editor.getTextField().setHorizontalAlignment(JFormattedTextField.LEFT);
		editor.getTextField().addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				setArrowButtonEnable(true);
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						// 日にち部分を範囲選択
						JTextField field = (JTextField) e.getComponent();
						field.setCaretPosition(8);
						field.setSelectionStart(8);
						field.setSelectionEnd(10);
					}
				});
			}

			@Override
			public void focusLost(FocusEvent e) {
				setArrowButtonEnable(false);
			}
		});
	}

	/**
	 * spinner の上下矢印の表示設定
	 * 
	 * @param flag true:表示 false:非表示
	 */
	protected final void setArrowButtonEnable(boolean flag) {
		for (Component c : spinner.getComponents()) {
			if (c instanceof JButton) {
				((JButton) c).setEnabled(flag);
			}
		}
	}

	@Override
	public Object getCellEditorValue() {
		return ((Date) spinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		spinner.setValue(Date.from(((LocalDate) value).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return spinner;
	}
}
