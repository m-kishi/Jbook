// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table;

import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import abook.common.AbConstant.COL;
import abook.common.AbConstant.FMT;
import abook.common.AbUtility.UTL;
import abook.form.table.model.AbPrivateTableModel;
import abook.form.table.renderer.AbExpenseCostCellRenderer;
import abook.form.table.renderer.AbExpenseDateCellRenderer;
import abook.form.table.renderer.AbExpenseNameCellRenderer;
import abook.form.table.renderer.AbExpenseNoteCellRenderer;

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
		getTableHeader().setReorderingAllowed(false);
		TableColumn colDate = getColumnModel().getColumn(COL.PRIVATE.DATE);
		TableColumn colName = getColumnModel().getColumn(COL.PRIVATE.NAME);
		TableColumn colCost = getColumnModel().getColumn(COL.PRIVATE.COST);
		TableColumn colNote = getColumnModel().getColumn(COL.PRIVATE.NOTE);
		TableColumn colBalance = getColumnModel().getColumn(COL.PRIVATE.BALANCE);
		AbExpenseDateCellRenderer cellDate = new AbExpenseDateCellRenderer();
		AbExpenseNameCellRenderer cellName = new AbExpenseNameCellRenderer();
		AbExpenseCostCellRenderer cellCost = new AbExpenseCostCellRenderer();
		AbExpenseNoteCellRenderer cellNote = new AbExpenseNoteCellRenderer();
		AbExpenseCostCellRenderer cellBalance = new AbExpenseCostCellRenderer();
		colDate.setCellRenderer(cellDate);
		colName.setCellRenderer(cellName);
		colCost.setCellRenderer(cellCost);
		colNote.setCellRenderer(cellNote);
		colBalance.setCellRenderer(cellBalance);

		// テーブル列幅設定
		colDate.setResizable(false);
		colName.setResizable(false);
		colCost.setResizable(false);
		colNote.setResizable(false);
		colBalance.setResizable(false);
		colDate.setPreferredWidth(92);
		colName.setPreferredWidth(120);
		colCost.setPreferredWidth(80);
		colNote.setPreferredWidth(64);
		colBalance.setPreferredWidth(90);
	}

	@Override
	public String getToolTipText(MouseEvent e) {
		int row = convertRowIndexToModel(rowAtPoint(e.getPoint()));
		String note = String.valueOf(getModel().getValueAt(row, COL.PRIVATE.NOTE));
		return UTL.isEmpty(note) ? null : String.format(FMT.NOTE, UTL.replaceYenMark(note));
	}
}
