// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.subform;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import abook.common.AbConstant.TYPE;
import abook.common.AbUtility.UTL;
import abook.expense.AbExpense;
import abook.form.AbFormMain;
import abook.form.table.AbExpenseTable;
import abook.form.table.model.AbExpenseTableModel;

/**
 * 検索サブフォーム
 */
public class AbSubformSearch extends JDialog {

	/** テーブル */
	private AbExpenseTable table;

	/** テーブルモデル */
	private AbExpenseTableModel model;

	/** スクロール領域 */
	private JScrollPane scrollPane;

	/** 名称コンボボックス */
	private JComboBox<String> cmbName;

	/** 種別コンボボックス */
	private JComboBox<String> cmbType;

	/** 支出情報リスト */
	private List<AbExpense> expenses;

	/**
	 * コンストラクタ
	 * 
	 * @param frame    親のフレーム
	 * @param expenses 支出情報リスト
	 */
	public AbSubformSearch(AbFormMain frame, List<AbExpense> expenses) {
		super();
		this.expenses = expenses;

		// フォーム
		setTitle("支出検索");
		setModal(true);
		setSize(414, 346);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// 検索条件エリア
		JPanel conditionArea = new JPanel();

		// 名称コンボボックス
		List<String> names = new ArrayList<String>(
				expenses.stream().collect(Collectors.groupingBy(AbExpense::getName)).keySet()
		);
		names = names.stream().sorted().collect(Collectors.toList());
		names.add(0, "");
		cmbName = new JComboBox<String>(names.toArray(new String[] {}));
		cmbName.setEditable(true);
		cmbName.setPreferredSize(new Dimension(253, (int) cmbName.getPreferredSize().getHeight()));
		cmbName.setMaximumRowCount(17);
		conditionArea.add(cmbName);

		// 種別コンボボックス
		List<String> types = new ArrayList<String>(TYPE.EXPENSES);
		types.add(0, "");
		cmbType = new JComboBox<String>(types.toArray(new String[] {}));
		cmbType.setPreferredSize(new Dimension(80, (int) cmbType.getPreferredSize().getHeight()));
		cmbType.setMaximumRowCount(17);
		conditionArea.add(cmbType);

		// 検索ボタン
		JButton btnSearch = new JButton("検索");
		btnSearch.addActionListener(new SearchActionListener());
		conditionArea.add(btnSearch);

		// テーブル設定
		model = new AbExpenseTableModel(new ArrayList<AbExpense>(), false);
		table = new AbExpenseTable(frame, model);

		// スクロール領域
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(scrollPane.getBorder(), new EmptyBorder(0, 0, 0, 1)));

		// 余白設定(スクロール領域をラップしてそのラップ領域に余白を設定)
		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.add(scrollPane, BorderLayout.CENTER);
		wrapper.setBorder(new CompoundBorder(wrapper.getBorder(), new EmptyBorder(0, 5, 5, 5)));

		setLayout(new BorderLayout());
		add(conditionArea, BorderLayout.NORTH);
		add(wrapper, BorderLayout.CENTER);
	}

	/**
	 * 検索ボタン
	 */
	private class SearchActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			List<AbExpense> result = expenses;
			String name = String.valueOf(cmbName.getSelectedItem());
			String type = String.valueOf(cmbType.getSelectedItem());

			// 名称で絞り込み
			if (!UTL.isEmpty(name)) {
				result = result.stream().filter(exp -> exp.getName().contains(name)).collect(Collectors.toList());
			}

			// 種別で絞り込み
			if (!UTL.isEmpty(type)) {
				result = result.stream().filter(exp -> exp.getType().equals(type)).collect(Collectors.toList());
			}

			// 検索結果を読み込み
			model.load(result);

			// 先頭行へスクロール
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
					scrollBar.setValue(scrollBar.getMinimum());
				}
			});
		}
	}
}
