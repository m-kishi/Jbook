// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import abook.expense.AbExpense;
import abook.form.table.AbBalanceTable;
import abook.form.table.model.AbBalanceTableModel;

/**
 * 収支タブ
 */
public class AbTabBalance extends JPanel {

	/** テーブル */
	private AbBalanceTable table;

	/** テーブルモデル */
	private AbBalanceTableModel model;

	/** スクロール領域 */
	private JScrollPane scrollPane;

	/** 再描画のフラグ */
	private boolean isReflesh;

	/**
	 * コンストラクタ
	 * 
	 * @param expenses 支出情報リスト
	 */
	public AbTabBalance(List<AbExpense> expenses) {
		super();

		isReflesh = true;

		// テーブル設定
		model = new AbBalanceTableModel(expenses);
		table = new AbBalanceTable(model);

		// テーブル領域をスクロールさせるためのPane
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(scrollPane.getBorder(), new EmptyBorder(0, 0, 0, 1)));

		// 余白設定
		setLayout(new BorderLayout());
		setBorder(new CompoundBorder(getBorder(), new EmptyBorder(5, 5, 5, 5)));

		add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * 画面初期表示時の初期化処理
	 * 
	 * @param expenses 支出情報リスト
	 */
	public void initialize(List<AbExpense> expenses) {
		isReflesh = true;
		if (expenses != null) {
			model.load(expenses);
		}
	}

	/**
	 * 画面の再描画
	 */
	public void reflesh() {
		if (isReflesh) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
					scrollBar.setValue(scrollBar.getMaximum());
				}
			});
		}
		isReflesh = false;
	}
}
