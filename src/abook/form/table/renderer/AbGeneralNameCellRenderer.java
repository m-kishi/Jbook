// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 名称セル
 */
public class AbGeneralNameCellRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column
	) {
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
