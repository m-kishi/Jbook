// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import abook.common.AbDBManager;
import abook.common.AbException;
import abook.expense.AbExpense;
import abook.expense.AbSummary;
import abook.expense.manager.AbSummaryManager;

/**
 * メイン画面フォーム
 */
public class AbFormMain extends JFrame implements WindowListener {

	/** 支出情報リスト */
	private List<AbExpense> expenses;

	/** 月次情報リスト */
	private List<AbSummary> summaries;

	/** 支出タブ */
	private AbTabExpense tabExpense;

	/** 月次タブ */
	private AbTabSummary tabSummary;

	/** 推移タブ */
	private AbTabGraphic tabGraphic;

	/** 収支タブ */
	private AbTabBalance tabBalance;

	/** 秘密タブ */
	private AbTabPrivate tabPrivate;

	/**
	 * コンストラクタ
	 * 
	 * @param dbFilePath DBファイルパス
	 * @throws AbException
	 */
	public AbFormMain(String dbFilePath) throws AbException {
		super();

		// フォーム
		setTitle("Abook");
		setSize(420, 364);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// メニュー
		setJMenuBar(new AbFormMenu(this));

		// タブ
		JTabbedPane tab = new JTabbedPane();
		expenses = AbDBManager.load(dbFilePath);
		summaries = AbSummaryManager.createSummaries(expenses);

		tabExpense = new AbTabExpense(this, expenses);
		tabSummary = new AbTabSummary(this, LocalDate.now(), summaries);
		tabGraphic = new AbTabGraphic(this, LocalDate.now(), summaries);
		tabBalance = new AbTabBalance(expenses);
		tabPrivate = new AbTabPrivate(expenses);

		// タブの切り替え設定
		tab.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				switch (tab.getSelectedIndex()) {
					// 支出タブ
					case 0:
						break;
					// 月次タブ
					case 1:
						break;
					// 推移タブ
					case 2:
						break;
					// 収支タブ
					case 3:
						tabBalance.reflesh();
						break;
					// 秘密タブ
					case 4:
						tabPrivate.reflesh();
						break;
				}
			}
		});

		tab.addTab("支出", tabExpense);
		tab.addTab("月次", tabSummary);
		tab.addTab("推移", tabGraphic);
		tab.addTab("収支", tabBalance);
		tab.addTab("秘密", tabPrivate);

		addWindowListener(this);
		getContentPane().add(tab, BorderLayout.CENTER);
	}

	/**
	 * 初期化(支出タブから呼び出される)
	 * 支出情報リストを更新してその他のタブを初期化する
	 * 
	 * @param expenses 支出情報リスト
	 */
	public void initialize(List<AbExpense> expenses) {
		this.expenses = expenses;
		summaries = AbSummaryManager.createSummaries(expenses);
		tabSummary.initialize(LocalDate.now(), summaries);
		tabGraphic.initialize(LocalDate.now(), summaries);
		tabBalance.initialize(expenses);
		tabPrivate.initialize(expenses);
	}

	/**
	 * 支出情報リスト取得
	 * 
	 * @return 支出情報リスト
	 */
	public List<AbExpense> getExpenses() {
		return expenses;
	}

	/**
	 * 月次情報リスト取得
	 * 
	 * @return 月次情報リスト
	 */
	public List<AbSummary> getSummaries() {
		return summaries;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		tabExpense.initialize();
		tabBalance.initialize(null);
		tabPrivate.initialize(null);
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
