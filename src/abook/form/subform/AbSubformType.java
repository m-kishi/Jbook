// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form.subform;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import abook.expense.AbExpense;
import abook.form.table.AbExpenseTable;
import abook.form.table.model.AbExpenseTableModel;

/**
 * 種別サブフォーム
 */
public class AbSubformType extends JDialog {

	/**
	 * コンストラクタ
	 * 
	 * @param date 日付
	 * @param type 種別
	 * @param expenses 支出情報リスト
	 */
	public AbSubformType(LocalDate date, String type, List<AbExpense> expenses) {
		super();

		// フォーム
		setTitle(type);
		setModal(true);
		setSize(414, 280);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// 該当年月の該当種別に絞り込み
		var filteredExpenses = expenses.stream().filter(exp ->
				exp.getDate().getYear() == date.getYear() &&
				exp.getDate().getMonth() == date.getMonth() &&
				exp.getType().equals(type)
		).collect(Collectors.toList());

		// テーブルモデル
		AbExpenseTableModel model = new AbExpenseTableModel(filteredExpenses, false);
		AbExpenseTable table = new AbExpenseTable(null, model);

		// テーブル領域をスクロールさせるためのPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(scrollPane.getBorder(), new EmptyBorder(0, 0, 0, 1)));

		// 余白設定(スクロール領域をラップしてそのラップ領域に余白を設定)
		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.add(scrollPane, BorderLayout.CENTER);
		wrapper.setBorder(new CompoundBorder(wrapper.getBorder(), new EmptyBorder(5, 5, 5, 5)));

		add(wrapper, BorderLayout.CENTER);
	}
}
