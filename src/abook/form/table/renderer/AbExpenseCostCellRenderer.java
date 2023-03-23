// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import abook.common.AbConstant.COL;
import abook.common.AbUtility.UTL;

/**
 * 金額セル
 */
public class AbExpenseCostCellRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column
	) {
		setHorizontalAlignment(JLabel.RIGHT);

		String note = String.valueOf(table.getModel().getValueAt(row, COL.EXPENSE.NOTE));
		if (!UTL.isEmpty(note)) {
			setBackground(new Color(222, 252, 231));
		} else {
			setBackground(UIManager.getColor("Table.background"));
		}

		Component renderer = super.getTableCellRendererComponent(
				table,
				UTL.toCurrency(value, getLocale()),
				isSelected,
				hasFocus,
				row,
				column
		);

		return renderer;
	}
}
