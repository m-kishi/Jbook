// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import abook.common.AbConstant.COL;
import abook.form.table.model.AbFinanceTableModel;
import abook.form.table.renderer.AbGeneralCostCellRenderer;
import abook.form.table.renderer.AbGeneralNameCellRenderer;
import abook.form.table.renderer.AbGeneralNoteCellRenderer;
import abook.form.table.renderer.AbGeneralYearCellRenderer;

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
		getTableHeader().setReorderingAllowed(false);
		TableColumn colYear = getColumnModel().getColumn(COL.FINANCE.YEAR);
		TableColumn colName = getColumnModel().getColumn(COL.FINANCE.NAME);
		TableColumn colNote = getColumnModel().getColumn(COL.FINANCE.NOTE);
		TableColumn colCost = getColumnModel().getColumn(COL.FINANCE.COST);
		AbGeneralYearCellRenderer cellDate = new AbGeneralYearCellRenderer();
		AbGeneralNameCellRenderer cellName = new AbGeneralNameCellRenderer();
		AbGeneralNoteCellRenderer cellNote = new AbGeneralNoteCellRenderer();
		AbGeneralCostCellRenderer cellCost = new AbGeneralCostCellRenderer();
		colYear.setCellRenderer(cellDate);
		colName.setCellRenderer(cellName);
		colNote.setCellRenderer(cellNote);
		colCost.setCellRenderer(cellCost);

		// テーブル列幅設定
		colYear.setResizable(false);
		colName.setResizable(false);
		colNote.setResizable(false);
		colCost.setResizable(false);
		colYear.setPreferredWidth(62);
		colName.setPreferredWidth(128);
		colNote.setPreferredWidth(128);
		colCost.setPreferredWidth(128);
	}
}
