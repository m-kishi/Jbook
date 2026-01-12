// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import abook.common.AbDBManager;
import abook.common.AbException;
import abook.common.AbManager.MESSAGE;
import abook.common.AbUtility.MSG;
import abook.expense.AbExpense;
import abook.form.table.AbExpenseTable;
import abook.form.table.model.AbExpenseTableModel;

/**
 * 支出タブ
 */
public class AbTabExpense extends JPanel {

	/** 親のフレーム */
	private AbFormMain frame;

	/** テーブル */
	private AbExpenseTable table;

	/** テーブルモデル */
	private AbExpenseTableModel model;

	/** スクロール領域 */
	private JScrollPane scrollPane;

	/**
	 * コンストラクタ
	 * 
	 * @param frame    親のフレーム
	 * @param expenses 支出情報リスト
	 */
	public AbTabExpense(AbFormMain frame, List<AbExpense> expenses) {
		super();
		this.frame = frame;

		// ボタンエリア左寄せ領域
		JPanel btnLArea = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 5));

		// ボタンエリア右寄せ領域
		JPanel btnRArea = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 5));

		// ボタンエリア
		JPanel buttonArea = new JPanel(new BorderLayout());
		buttonArea.add(btnLArea, BorderLayout.WEST);
		buttonArea.add(btnRArea, BorderLayout.EAST);

		// 登録ボタン
		JButton btnEntry = new JButton("登録");
		btnEntry.addActionListener(new EntryActionListener());
		btnRArea.add(btnEntry);

		// 行追加ボタン
		JButton btnAddRow = new JButton("+");
		btnAddRow.addActionListener(new AddRowActionListener());
		btnRArea.add(btnAddRow);

		// 月次ボタン
		JButton btnMonthly = new JButton("月次");
		btnMonthly.addActionListener(new MonthlyActionListener());
		btnLArea.add(btnMonthly);

		// テーブル設定
		model = new AbExpenseTableModel(expenses, true);
		table = new AbExpenseTable(frame, model);

		// スクロール領域
		scrollPane = new JScrollPane(table);
		scrollPane.setName("ScrollExpenseTable");
		scrollPane.setBorder(new CompoundBorder(scrollPane.getBorder(), new EmptyBorder(0, 0, 0, 1)));

		// 余白設定
		setLayout(new BorderLayout());
		setBorder(new CompoundBorder(getBorder(), new EmptyBorder(0, 5, 5, 5)));

		add(buttonArea, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * 画面初期表示時の初期化処理
	 */
	public void initialize() {
		JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
		scrollBar.setValue(scrollBar.getMaximum());
	}

	/**
	 * 行追加ボタン
	 */
	private class AddRowActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.addRow();
		}
	}

	/**
	 * 月次ボタン
	 */
	private class MonthlyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.monthly();
			initialize();
		}
	}

	/**
	 * 登録ボタン
	 */
	private class EntryActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				List<AbExpense> expenses = model.getExpenses();
				AbDBManager.store(frame.getDBFilePath(), expenses);

				// 再読み込みしてメッセージ表示
				model.load(expenses);
				MSG.ok(frame, "登録完了", MESSAGE.ENTRY_COMPLETE);

				// 初期表示
				initialize();

				// 支出情報リストの更新と各タブの初期化
				frame.initialize(expenses);

			} catch (AbException ex) {
				table.scrollRectToVisible(
					table.getCellRect(model.getErrorRowIdx() - 1, 0, true)
				);
				MSG.error(frame, ex.getMessage());
			}
		}
	}
}
