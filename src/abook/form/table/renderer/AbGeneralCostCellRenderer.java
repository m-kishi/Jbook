// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import abook.common.AbUtility.UTL;

/**
 * 金額セル
 */
public class AbGeneralCostCellRenderer extends DefaultTableCellRenderer {

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
