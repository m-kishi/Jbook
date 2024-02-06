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
import abook.form.table.model.AbFinanceTableModel;
import abook.form.table.renderer.AbExpenseCostCellRenderer;
import abook.form.table.renderer.AbExpenseDateCellRenderer;
import abook.form.table.renderer.AbExpenseNameCellRenderer;
import abook.form.table.renderer.AbExpenseNoteCellRenderer;

/**
 * 投資情報テーブル
 */
public class AbFinanceTable extends JTable {

	/**
	 * コンストラクタ
	 * 
	 * @param model テーブルモデル
	 */
	public AbFinanceTable(AbFinanceTableModel model) {
		super(model);
		setName("FinanceTable");

		// テーブル設定
		setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn colDate = getColumnModel().getColumn(COL.FINANCE.DATE);
		TableColumn colName = getColumnModel().getColumn(COL.FINANCE.NAME);
		TableColumn colCost = getColumnModel().getColumn(COL.FINANCE.COST);
		TableColumn colNote = getColumnModel().getColumn(COL.FINANCE.NOTE);
		TableColumn colTotal = getColumnModel().getColumn(COL.FINANCE.TOTAL);
		AbExpenseDateCellRenderer cellDate = new AbExpenseDateCellRenderer();
		AbExpenseNameCellRenderer cellName = new AbExpenseNameCellRenderer();
		AbExpenseCostCellRenderer cellCost = new AbExpenseCostCellRenderer();
		AbExpenseNoteCellRenderer cellNote = new AbExpenseNoteCellRenderer();
		AbExpenseCostCellRenderer cellBalance = new AbExpenseCostCellRenderer();
		colDate.setCellRenderer(cellDate);
		colName.setCellRenderer(cellName);
		colCost.setCellRenderer(cellCost);
		colNote.setCellRenderer(cellNote);
		colTotal.setCellRenderer(cellBalance);

		// テーブル列幅設定
		TableColumnModel columns = getColumnModel();
		columns.getColumn(COL.FINANCE.DATE).setPreferredWidth(92);
		columns.getColumn(COL.FINANCE.NAME).setPreferredWidth(120);
		columns.getColumn(COL.FINANCE.COST).setPreferredWidth(80);
		columns.getColumn(COL.FINANCE.NOTE).setPreferredWidth(64);
		columns.getColumn(COL.FINANCE.TOTAL).setPreferredWidth(90);
	}

	@Override
	public String getToolTipText(MouseEvent e) {
		int row = convertRowIndexToModel(rowAtPoint(e.getPoint()));
		String note = String.valueOf(getModel().getValueAt(row, COL.FINANCE.NOTE));
		return UTL.isEmpty(note) ? null : String.format(FMT.NOTE, UTL.replaceYenMark(note));
	}
}
