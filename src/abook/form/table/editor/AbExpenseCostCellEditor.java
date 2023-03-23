// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import abook.common.AbUtility.UTL;

/**
 * 金額のエディタ
 */
public class AbExpenseCostCellEditor extends DefaultCellEditor {

	/** 金額 */
	private Integer cost = null;

	/**
	 * コンストラクタ
	 */
	public AbExpenseCostCellEditor() {
		super(new JTextField());

		setClickCountToStart(1);

		JTextField field = (JTextField) getComponent();
		field.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));
		field.setBorder(BorderFactory.createEmptyBorder());
		field.setHorizontalAlignment(JLabel.RIGHT);
	}

	/**
	 * 入力された値を返す
	 */
	@Override
	public Object getCellEditorValue() {
		return cost;
	}

	/**
	 * 編集開始時に呼ばれる
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		// 初期化
		cost = null;

		// 枠は不要
		((JComponent) getComponent()).setBorder(BorderFactory.createEmptyBorder());

		// 文字列として表示
		String stringValue = (value == null ? "" : String.valueOf(value));
		Component component = super.getTableCellEditorComponent(table, stringValue, isSelected, row, column);
		if (component instanceof JTextField) {
			// このタイミングで全選択(フォーカスゲット時では思うように動作しないため)
			JTextField field = (JTextField) component;
			field.selectAll();
			return field;
		}

		return component;
	}

	/**
	 * 編集完了時に呼ばれる
	 * super.stopCellEditing()を呼び出すと正常終了となる
	 */
	@Override
	public boolean stopCellEditing() {

		// 編集された値を取得
		String value = String.valueOf(super.getCellEditorValue());

		// int型へ変換できるか確認
		if (!UTL.isEmpty(value)) {
			try {
				cost = Integer.parseInt(value);
			} catch (NumberFormatException ex) {
				// エラーなら赤枠を表示
				((JComponent) getComponent()).setBorder(new LineBorder(Color.RED));
				return false;
			}
		}

		return super.stopCellEditing();
	}
}
