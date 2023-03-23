// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import abook.common.AbDBManager;
import abook.common.AbException;
import abook.expense.AbExpense;

/**
 * メイン画面フォーム
 */
public class AbFormMain extends JFrame implements WindowListener {

	/** 支出情報リスト */
	private List<AbExpense> expenses;

	/** 支出タブ */
	private AbTabExpense tabExpense;

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
		tabExpense = new AbTabExpense(this, expenses);

		// タブの切り替え設定
		tab.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				switch (tab.getSelectedIndex()) {
					// 支出タブ
					case 0:
						break;
				}
			}
		});

		tab.addTab("支出", tabExpense);

		addWindowListener(this);
		getContentPane().add(tab, BorderLayout.CENTER);
	}

	/**
	 * 初期化
	 * 支出情報リストの更新
	 * 
	 * @param expenses 支出情報リスト
	 */
	public void initialize(List<AbExpense> expenses) {
		this.expenses = expenses;
	}

	/**
	 * 支出情報リスト取得
	 * 
	 * @return 支出情報リスト
	 */
	public List<AbExpense> getExpenses() {
		return expenses;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		tabExpense.initialize();
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
