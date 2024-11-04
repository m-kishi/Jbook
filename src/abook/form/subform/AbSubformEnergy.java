// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.subform;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import abook.common.AbConstant.NAME;
import abook.expense.AbSummary;
import abook.form.table.model.AbEnergyTableModel;
import abook.form.table.renderer.AbEnergyCostCellRenderer;
import abook.form.table.renderer.AbGeneralYearCellRenderer;

/**
 * 光熱費サブフォーム
 */
public class AbSubformEnergy extends JDialog implements WindowListener {

	/** テーブル(電気代) */
	private JTable tableEl;

	/** テーブル(ガス代) */
	private JTable tableGs;

	/** テーブル(水道代) */
	private JTable tableWt;

	/** スクロール領域(電気代) */
	private JScrollPane scrollPaneEl;

	/** スクロール領域(ガス代) */
	private JScrollPane scrollPaneGs;

	/** スクロール領域(水道代) */
	private JScrollPane scrollPaneWt;

	/**
	 * コンストラクタ
	 * 
	 * @param summaries 月次情報リスト
	 */
	public AbSubformEnergy(List<AbSummary> summaries) {
		super();

		// フォーム
		setTitle("光熱費");
		setModal(true);
		setSize(866, 360);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// タブ
		JTabbedPane tab = new JTabbedPane();
		setTabEl(tab, summaries);
		setTabGs(tab, summaries);
		setTabWt(tab, summaries);

		addWindowListener(this);
		getContentPane().add(tab, BorderLayout.CENTER);
	}

	/**
	 * 電気代タブ
	 * 
	 * @param tab       タブ
	 * @param summaries 月次情報リスト
	 */
	private void setTabEl(JTabbedPane tab, List<AbSummary> summaries) {
		JPanel tabEl = new JPanel();
		tab.addTab(NAME.EL, tabEl);

		// テーブルモデル
		AbEnergyTableModel model = new AbEnergyTableModel(NAME.EL, summaries);
		tableEl = new JTable(model);
		tableEl.setName("TableEl");

		// テーブル設定
		Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 12);
		tableEl.setFont(font);
		tableEl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableEl.getTableHeader().setReorderingAllowed(false);

		// 列設定(年度)
		TableColumn colYear = tableEl.getColumnModel().getColumn(0);
		colYear.setCellRenderer(new AbGeneralYearCellRenderer());
		colYear.setResizable(false);
		colYear.setPreferredWidth(50);

		// 列設定(月)
		for (int i = 1; i <= 12; i++) {
			TableColumn colCost = tableEl.getColumnModel().getColumn(i);
			colCost.setCellRenderer(new AbEnergyCostCellRenderer());
			colCost.setResizable(false);
			colCost.setPreferredWidth(65);
		}

		// テーブル領域をスクロールさせるためのPane
		scrollPaneEl = new JScrollPane(tableEl);
		scrollPaneEl.setBorder(new CompoundBorder(scrollPaneEl.getBorder(), new EmptyBorder(0, 0, 0, 0)));

		// 余白設定
		tabEl.setLayout(new BorderLayout());
		tabEl.setBorder(new CompoundBorder(tabEl.getBorder(), new EmptyBorder(5, 5, 5, 5)));

		tabEl.add(scrollPaneEl, BorderLayout.CENTER);
	}

	/**
	 * ガス代タブ
	 * 
	 * @param tab       タブ
	 * @param summaries 月次情報リスト
	 */
	private void setTabGs(JTabbedPane tab, List<AbSummary> summaries) {
		JPanel tabGs = new JPanel();
		tab.addTab(NAME.GS, tabGs);

		// テーブルモデル
		AbEnergyTableModel model = new AbEnergyTableModel(NAME.GS, summaries);
		tableGs = new JTable(model);
		tableGs.setName("TableGs");

		// テーブル設定
		Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 12);
		tableGs.setFont(font);
		tableGs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableGs.getTableHeader().setReorderingAllowed(false);

		// 列設定(年度)
		TableColumn colYear = tableGs.getColumnModel().getColumn(0);
		colYear.setCellRenderer(new AbGeneralYearCellRenderer());
		colYear.setResizable(false);
		colYear.setPreferredWidth(50);

		// 列設定(月)
		for (int i = 1; i <= 12; i++) {
			TableColumn colCost = tableGs.getColumnModel().getColumn(i);
			colCost.setCellRenderer(new AbEnergyCostCellRenderer());
			colCost.setResizable(false);
			colCost.setPreferredWidth(65);
		}

		// テーブル領域をスクロールさせるためのPane
		scrollPaneGs = new JScrollPane(tableGs);
		scrollPaneGs.setBorder(new CompoundBorder(scrollPaneGs.getBorder(), new EmptyBorder(0, 0, 0, 0)));

		// 余白設定
		tabGs.setLayout(new BorderLayout());
		tabGs.setBorder(new CompoundBorder(tabGs.getBorder(), new EmptyBorder(5, 5, 5, 5)));

		tabGs.add(scrollPaneGs, BorderLayout.CENTER);
	}

	/**
	 * 水道代タブ
	 * 
	 * @param tab       タブ
	 * @param summaries 月次情報リスト
	 */
	private void setTabWt(JTabbedPane tab, List<AbSummary> summaries) {
		JPanel tabWt = new JPanel();
		tab.addTab(NAME.WT, tabWt);

		// テーブルモデル
		AbEnergyTableModel model = new AbEnergyTableModel(NAME.WT, summaries);
		tableWt = new JTable(model);
		tableWt.setName("TableWt");

		// テーブル設定
		Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 12);
		tableWt.setFont(font);
		tableWt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableWt.getTableHeader().setReorderingAllowed(false);

		// 列設定(年度)
		TableColumn colYear = tableWt.getColumnModel().getColumn(0);
		colYear.setCellRenderer(new AbGeneralYearCellRenderer());
		colYear.setResizable(false);
		colYear.setPreferredWidth(50);

		// 列設定(月)
		for (int i = 1; i <= 12; i++) {
			TableColumn colCost = tableWt.getColumnModel().getColumn(i);
			colCost.setCellRenderer(new AbEnergyCostCellRenderer());
			colCost.setResizable(false);
			colCost.setPreferredWidth(65);
		}

		// テーブル領域をスクロールさせるためのPane
		scrollPaneWt = new JScrollPane(tableWt);
		scrollPaneWt.setBorder(new CompoundBorder(scrollPaneWt.getBorder(), new EmptyBorder(0, 0, 0, 0)));

		// 余白設定
		tabWt.setLayout(new BorderLayout());
		tabWt.setBorder(new CompoundBorder(tabWt.getBorder(), new EmptyBorder(5, 5, 5, 5)));

		tabWt.add(scrollPaneWt, BorderLayout.CENTER);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JScrollBar scrollBarEl = scrollPaneEl.getVerticalScrollBar();
				scrollBarEl.setValue(scrollBarEl.getMaximum());

				JScrollBar scrollBarGs = scrollPaneGs.getVerticalScrollBar();
				scrollBarGs.setValue(scrollBarGs.getMaximum());

				JScrollBar scrollBarWt = scrollPaneWt.getVerticalScrollBar();
				scrollBarWt.setValue(scrollBarWt.getMaximum());
			}
		});
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}
