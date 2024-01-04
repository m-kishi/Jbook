// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table;

import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import abook.common.AbConstant.COL;
import abook.common.AbConstant.FMT;
import abook.common.AbUtility.UTL;
import abook.form.table.model.AbPrivateTableModel;
import abook.form.table.renderer.AbExpenseCostCellRenderer;
import abook.form.table.renderer.AbExpenseDateCellRenderer;
import abook.form.table.renderer.AbExpenseNameCellRenderer;

/**
 * 秘密収支情報テーブル
 */
public class AbPrivateTable extends JTable {

	/**
	 * コンストラクタ
	 * 
	 * @param model テーブルモデル
	 */
	public AbPrivateTable(AbPrivateTableModel model) {
		super(model);
		setName("PrivateTable");

		// テーブル設定
		setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn colDate = getColumnModel().getColumn(COL.PRIVATE.DATE);
		TableColumn colName = getColumnModel().getColumn(COL.PRIVATE.NAME);
		TableColumn colCost = getColumnModel().getColumn(COL.PRIVATE.COST);
		TableColumn colNote = getColumnModel().getColumn(COL.PRIVATE.NOTE);
		TableColumn colBalance = getColumnModel().getColumn(COL.PRIVATE.BALANCE);
		AbExpenseDateCellRenderer cellDate = new AbExpenseDateCellRenderer();
		AbExpenseNameCellRenderer cellName = new AbExpenseNameCellRenderer();
		AbExpenseCostCellRenderer cellCost = new AbExpenseCostCellRenderer();
		AbExpenseCostCellRenderer cellBalance = new AbExpenseCostCellRenderer();
		colDate.setCellRenderer(cellDate);
		colName.setCellRenderer(cellName);
		colCost.setCellRenderer(cellCost);
		colBalance.setCellRenderer(cellBalance);
		removeColumn(colNote);

		// テーブル列幅設定
		TableColumnModel columns = getColumnModel();
		columns.getColumn(COL.BALANCE.YEAR).setPreferredWidth(92);
		columns.getColumn(COL.BALANCE.EARN).setPreferredWidth(120);
		columns.getColumn(COL.BALANCE.EXPENSE).setPreferredWidth(80);
		columns.getColumn(COL.BALANCE.BALANCE).setPreferredWidth(90);
	}

	@Override
	public String getToolTipText(MouseEvent e) {
		int row = convertRowIndexToModel(rowAtPoint(e.getPoint()));
		String note = String.valueOf(getModel().getValueAt(row, COL.EXPENSE.NOTE));
		return UTL.isEmpty(note) ? null : String.format(FMT.NOTE, UTL.replaceYenMark(note));
	}
}
