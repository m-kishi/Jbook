// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import abook.common.AbConstant.COL;
import abook.common.AbConstant.COLOR;
import abook.common.AbUtility.UTL;

/**
 * 備考セル
 */
public class AbExpenseNoteCellRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column
	) {
		String note = String.valueOf(table.getModel().getValueAt(row, COL.EXPENSE.NOTE));
		if (!UTL.isEmpty(note)) {
			setBackground(COLOR.NOTE_BACKGROUND);
		} else {
			setBackground(UIManager.getColor("Table.background"));
		}

		Component renderer = super.getTableCellRendererComponent(
				table,
				value,
				isSelected,
				hasFocus,
				row,
				column
		);

		return renderer;
	}
}
