// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table.renderer;

import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import abook.form.table.model.AbEnergyTableModel;

/**
 * 光熱費の金額列
 */
public class AbEnergyCostCellRenderer extends DefaultTableCellRenderer {

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

		String cost = null;
		if (value != null) {
			NumberFormat nf = NumberFormat.getCurrencyInstance(getLocale());
			try {
				cost = nf.format(value);
			} catch (IllegalArgumentException ex) {
				cost = null;
			}
		}

		setForeground(Color.BLACK);
		AbEnergyTableModel model = (AbEnergyTableModel) table.getModel();
		if (model.isMax((int) value, column - 1)) {
			setForeground(Color.RED);
		}
		if (model.isMin((int) value, column - 1)) {
			setForeground(Color.BLUE);
		}

		Component renderer = super.getTableCellRendererComponent(
				table,
				cost,
				isSelected,
				hasFocus,
				row,
				column
		);

		return renderer;
	}
}
