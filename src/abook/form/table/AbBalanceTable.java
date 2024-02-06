// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import abook.common.AbConstant.COL;
import abook.form.table.model.AbBalanceTableModel;
import abook.form.table.renderer.AbGeneralCostCellRenderer;
import abook.form.table.renderer.AbGeneralYearCellRenderer;

/**
 * 収支情報テーブル
 */
public class AbBalanceTable extends JTable {

	/**
	 * コンストラクタ
	 * 
	 * @param model テーブルモデル
	 */
	public AbBalanceTable(AbBalanceTableModel model) {
		super(model);
		setName("BalanceTable");

		// テーブル設定
		setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn colYear = getColumnModel().getColumn(COL.BALANCE.YEAR);
		TableColumn colEarn = getColumnModel().getColumn(COL.BALANCE.EARN);
		TableColumn colExpense = getColumnModel().getColumn(COL.BALANCE.EXPENSE);
		TableColumn colBalance = getColumnModel().getColumn(COL.BALANCE.BALANCE);
		TableColumn colFinance = getColumnModel().getColumn(COL.BALANCE.FINANCE);
		AbGeneralYearCellRenderer cellYear = new AbGeneralYearCellRenderer();
		AbGeneralCostCellRenderer cellEarn = new AbGeneralCostCellRenderer();
		AbGeneralCostCellRenderer cellExpense = new AbGeneralCostCellRenderer();
		AbGeneralCostCellRenderer cellBalance = new AbGeneralCostCellRenderer();
		AbGeneralCostCellRenderer cellFinance = new AbGeneralCostCellRenderer();
		colYear.setCellRenderer(cellYear);
		colEarn.setCellRenderer(cellEarn);
		colExpense.setCellRenderer(cellExpense);
		colBalance.setCellRenderer(cellBalance);
		colFinance.setCellRenderer(cellFinance);

		// テーブル列幅設定
		TableColumnModel columns = getColumnModel();
		columns.getColumn(COL.BALANCE.YEAR).setPreferredWidth(62);
		columns.getColumn(COL.BALANCE.EARN).setPreferredWidth(96);
		columns.getColumn(COL.BALANCE.EXPENSE).setPreferredWidth(96);
		columns.getColumn(COL.BALANCE.BALANCE).setPreferredWidth(96);
		columns.getColumn(COL.BALANCE.FINANCE).setPreferredWidth(96);
	}
}
