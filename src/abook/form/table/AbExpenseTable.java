// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.table;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import abook.common.AbConstant.COL;
import abook.common.AbConstant.FMT;
import abook.common.AbUtility.MSG;
import abook.common.AbUtility.UTL;
import abook.form.AbFormMain;
import abook.form.table.editor.AbExpenseCostCellEditor;
import abook.form.table.editor.AbExpenseDateCellEditor;
import abook.form.table.editor.AbExpenseNameCellEditor;
import abook.form.table.editor.AbExpenseTypeCellEditor;
import abook.form.table.model.AbExpenseTableModel;
import abook.form.table.renderer.AbExpenseCostCellRenderer;
import abook.form.table.renderer.AbExpenseDateCellRenderer;
import abook.form.table.renderer.AbExpenseNameCellRenderer;
import abook.form.table.renderer.AbExpenseTypeCellRenderer;

/**
 * 支出情報テーブル
 */
public class AbExpenseTable extends JTable {

	/** 親のフレーム */
	private AbFormMain frame;

	/**
	 * コンストラクタ
	 * 
	 * @param frame 親のフレーム
	 * @param model テーブルモデル
	 */
	public AbExpenseTable(AbFormMain frame, AbExpenseTableModel model) {
		super(model);
		this.frame = frame;

		// テーブル設定
		setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn colDate = getColumnModel().getColumn(COL.EXPENSE.DATE);
		TableColumn colName = getColumnModel().getColumn(COL.EXPENSE.NAME);
		TableColumn colType = getColumnModel().getColumn(COL.EXPENSE.TYPE);
		TableColumn colCost = getColumnModel().getColumn(COL.EXPENSE.COST);
		TableColumn colNote = getColumnModel().getColumn(COL.EXPENSE.NOTE);
		AbExpenseDateCellRenderer cellDate = new AbExpenseDateCellRenderer();
		AbExpenseNameCellRenderer cellName = new AbExpenseNameCellRenderer();
		AbExpenseTypeCellRenderer cellType = new AbExpenseTypeCellRenderer();
		AbExpenseCostCellRenderer cellCost = new AbExpenseCostCellRenderer();
		colDate.setCellRenderer(cellDate);
		colName.setCellRenderer(cellName);
		colType.setCellRenderer(cellType);
		colCost.setCellRenderer(cellCost);
		colDate.setCellEditor(new AbExpenseDateCellEditor());
		colName.setCellEditor(new AbExpenseNameCellEditor());
		colType.setCellEditor(new AbExpenseTypeCellEditor());
		colCost.setCellEditor(new AbExpenseCostCellEditor());
		removeColumn(colNote);

		// テーブル列幅設定
		TableColumnModel columns = getColumnModel();
		columns.getColumn(COL.EXPENSE.DATE).setPreferredWidth(100);
		columns.getColumn(COL.EXPENSE.NAME).setPreferredWidth(134);
		columns.getColumn(COL.EXPENSE.TYPE).setPreferredWidth(68);
		columns.getColumn(COL.EXPENSE.COST).setPreferredWidth(80);

		// セル選択の設定
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		setRowSelectionAllowed(true);
		setCellSelectionEnabled(true);

		if (model.isEditable()) {
			// キー入力したらフォーカスをセルに設定する
			setSurrendersFocusOnKeystroke(true);

			// Ctrl + 8 or 0 でそれぞれ 8% と 10% の税込金額を計算する
			setKeyAction(KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.CTRL_DOWN_MASK), new TaxAction(8));
			setKeyAction(KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK), new TaxAction(10));

			// Ctrl + t で選択範囲の金額列の合計を表示する
			setKeyAction(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK), new SummaryAction());
		}
	}

	/**
	 * キー設定
	 * 
	 * @param key    キー
	 * @param action アクション
	 */
	private void setKeyAction(KeyStroke key, Action action) {
		registerKeyboardAction(action, key, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		registerKeyboardAction(action, key, JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	@Override
	public String getToolTipText(MouseEvent e) {
		int row = convertRowIndexToModel(rowAtPoint(e.getPoint()));
		String note = String.valueOf(getModel().getValueAt(row, COL.EXPENSE.NOTE));
		return UTL.isEmpty(note) ? null : String.format(FMT.NOTE, note);
	}

	@Override
	public boolean editCellAt(int row, int column, EventObject e) {
		boolean result = super.editCellAt(row, column, e);
		if (column != COL.EXPENSE.COST) {
			return result;
		}
		if (result) {
			if (e instanceof KeyEvent) {
				KeyEvent event = (KeyEvent) e;
				char c = event.getKeyChar();
				if (!Character.isISOControl(c)) {
					if (super.editorComp instanceof JTextField) {
						JTextField field = (JTextField) super.editorComp;
						field.selectAll();
					}
				}
			}
		}
		return result;
	}

	/**
	 * 税込金額の計算
	 */
	private class TaxAction extends AbstractAction {

		/** 税率 */
		private int taxRate;

		/**
		 * コンストラクタ
		 * 
		 * @param taxRate 税率
		 */
		public TaxAction(int taxRate) {
			this.taxRate = taxRate;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JTable table = (JTable) e.getSource();
			AbExpenseTableModel model = (AbExpenseTableModel) table.getModel();
			for (int row : table.getSelectedRows()) {
				for (int col : table.getSelectedColumns()) {
					Object cost = model.getValueAt(row, col);
					if (col != COL.EXPENSE.COST || cost == null) {
						continue;
					}

					int taxIncludedCost = (int) Math.round((Integer) cost * (1 + taxRate / 100.0));
					model.setValueAt(taxIncludedCost, row, col);
				}
			}
		}
	}

	/**
	 * 選択範囲の金額列の合計
	 */
	private class SummaryAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			int total = 0;
			boolean displayFlg = false;

			JTable table = (JTable) e.getSource();
			AbExpenseTableModel model = (AbExpenseTableModel) table.getModel();
			for (int row : table.getSelectedRows()) {
				for (int col : table.getSelectedColumns()) {
					Object cost = model.getValueAt(row, col);
					if (col != COL.EXPENSE.COST || cost == null) {
						continue;
					}
					total += (int) cost;
					displayFlg = true;
				}
			}

			if (displayFlg) {
				MSG.ok(frame, "合計", UTL.toCurrency(total, getLocale()));
			}
		}
	}
}
