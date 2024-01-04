// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import abook.common.AbUtility.UTL;
import abook.expense.AbSummary;
import abook.expense.manager.AbGraphicManager;

/**
 * 推移タブ
 * キャンパスサイズ W:345, H:214
 */
public class AbTabGraphic extends JPanel {

	/** タイトル */
	private JLabel lblTitle;

	/** グラフ描画領域 */
	private GraphicCanvas canvas;

	/** 推移情報管理クラス */
	private AbGraphicManager manager;

	/** 月ラベル */
	private JLabel lblX1;

	/** 月ラベル */
	private JLabel lblX2;

	/** 月ラベル */
	private JLabel lblX3;

	/** 月ラベル */
	private JLabel lblX4;

	/** 月ラベル */
	private JLabel lblX5;

	/** 月ラベル */
	private JLabel lblX6;

	/**
	 * コンストラクタ
	 * 
	 * @param frame     親のフレーム
	 * @param date      日付
	 * @param summaries 月次情報リスト
	 */
	public AbTabGraphic(AbFormMain frame, LocalDate date, List<AbSummary> summaries) {
		super();
		setComponent(date);
		initialize(date, summaries);
	}

	/**
	 * コンポーネントの生成
	 * 
	 * @param date 日付
	 */
	private void setComponent(LocalDate date) {

		// ヘッダエリアの中央揃え
		FlowLayout headerLayout = new FlowLayout();
		headerLayout.setAlignment(FlowLayout.CENTER);

		// ヘッダエリア
		JPanel headerArea = new JPanel();
		headerArea.setLayout(headerLayout);

		// ボタンのフォント・サイズ・余白
		Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 6);
		Dimension size = new Dimension(20, 20);
		Insets margin = new Insets(0, 0, 0, 0);

		// 前年ボタン
		JButton btnPrevYear = new JButton("<<");
		btnPrevYear.setName("GraphicPrevYearButton");
		btnPrevYear.setFont(font);
		btnPrevYear.setMargin(margin);
		btnPrevYear.setPreferredSize(size);
		btnPrevYear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setPrevYear();
				repaint(manager.getDate());
			}
		});
		headerArea.add(btnPrevYear);

		// 前月ボタン
		JButton btnPrevMonth = new JButton("<");
		btnPrevMonth.setName("GraphicPrevMonthButton");
		btnPrevMonth.setFont(font);
		btnPrevMonth.setMargin(margin);
		btnPrevMonth.setPreferredSize(size);
		btnPrevMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setPrevMonth();
				repaint(manager.getDate());
			}
		});
		headerArea.add(btnPrevMonth);

		// タイトル
		lblTitle = new JLabel(UTL.toTitle(date));
		lblTitle.setName("TabGraphicTitle");
		lblTitle.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 18));
		headerArea.add(lblTitle);

		// 翌月ボタン
		JButton btnNextMonth = new JButton(">");
		btnNextMonth.setName("GraphicNextMonthButton");
		btnNextMonth.setFont(font);
		btnNextMonth.setMargin(margin);
		btnNextMonth.setPreferredSize(size);
		btnNextMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setNextMonth();
				repaint(manager.getDate());
			}
		});
		headerArea.add(btnNextMonth);

		// 翌年ボタン
		JButton btnNextYear = new JButton(">>");
		btnNextYear.setName("GraphicNextYearButton");
		btnNextYear.setFont(font);
		btnNextYear.setMargin(margin);
		btnNextYear.setPreferredSize(size);
		btnNextYear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setNextYear();
				repaint(manager.getDate());
			}
		});
		headerArea.add(btnNextYear);

		// グラフエリア
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel();
		panel.setLayout(layout);

		// 凡例・縦軸と横軸のラベル
		JLabel lblLineRed = new JLabel("--");
		lblLineRed.setForeground(Color.RED);
		JLabel lblLineOrange = new JLabel("--");
		lblLineOrange.setForeground(Color.ORANGE);
		JLabel lblLineYellow = new JLabel("--");
		lblLineYellow.setForeground(Color.YELLOW);
		JLabel lblLineGray = new JLabel("--");
		lblLineGray.setForeground(Color.GRAY);
		JLabel lblLineBlue = new JLabel("--");
		lblLineBlue.setForeground(Color.BLUE);
		JLabel lblLineFood = new JLabel("食費");
		JLabel lblLineOtfd = new JLabel("外食費");
		JLabel lblLineEl = new JLabel("電気代");
		JLabel lblLineGs = new JLabel("ガス代");
		JLabel lblLineWt = new JLabel("水道代");
		JLabel lblYen5000 = new JLabel(UTL.toCurrency(5000, getLocale()), JLabel.RIGHT);
		JLabel lblYen10000 = new JLabel(UTL.toCurrency(10000, getLocale()), JLabel.RIGHT);
		lblX1 = new JLabel("X1");
		lblX2 = new JLabel("X2");
		lblX3 = new JLabel("X3");
		lblX4 = new JLabel("X4");
		lblX5 = new JLabel("X5");
		lblX6 = new JLabel("X6");
		lblX1.setName("LabelX1");
		lblX2.setName("LabelX2");
		lblX3.setName("LabelX3");
		lblX4.setName("LabelX4");
		lblX5.setName("LabelX5");
		lblX6.setName("LabelX6");

		// グラフ描画領域
		canvas = new GraphicCanvas();

		// レイアウトの配置設定
		layout.putConstraint(SpringLayout.NORTH, lblLineRed, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblLineRed, 0, SpringLayout.WEST, canvas);
		layout.putConstraint(SpringLayout.NORTH, lblLineFood, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblLineFood, 5, SpringLayout.EAST, lblLineRed);

		layout.putConstraint(SpringLayout.NORTH, lblLineOrange, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblLineOrange, 5, SpringLayout.EAST, lblLineFood);
		layout.putConstraint(SpringLayout.NORTH, lblLineOtfd, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblLineOtfd, 5, SpringLayout.EAST, lblLineOrange);

		layout.putConstraint(SpringLayout.NORTH, lblLineYellow, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblLineYellow, 5, SpringLayout.EAST, lblLineOtfd);
		layout.putConstraint(SpringLayout.NORTH, lblLineEl, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblLineEl, 5, SpringLayout.EAST, lblLineYellow);

		layout.putConstraint(SpringLayout.NORTH, lblLineGray, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblLineGray, 5, SpringLayout.EAST, lblLineEl);
		layout.putConstraint(SpringLayout.NORTH, lblLineGs, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblLineGs, 5, SpringLayout.EAST, lblLineGray);

		layout.putConstraint(SpringLayout.NORTH, lblLineBlue, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblLineBlue, 5, SpringLayout.EAST, lblLineGs);
		layout.putConstraint(SpringLayout.NORTH, lblLineWt, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblLineWt, 5, SpringLayout.EAST, lblLineBlue);

		layout.putConstraint(SpringLayout.NORTH, canvas, 2, SpringLayout.SOUTH, lblLineRed);
		layout.putConstraint(SpringLayout.WEST, canvas, 65, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, canvas, -5, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.SOUTH, canvas, -20, SpringLayout.SOUTH, panel);

		layout.putConstraint(SpringLayout.NORTH, lblYen10000, 63, SpringLayout.NORTH, canvas);
		layout.putConstraint(SpringLayout.WEST, lblYen10000, 5, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, lblYen10000, -3, SpringLayout.WEST, canvas);

		layout.putConstraint(SpringLayout.NORTH, lblYen5000, 56, SpringLayout.SOUTH, lblYen10000);
		layout.putConstraint(SpringLayout.WEST, lblYen5000, 5, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, lblYen5000, -3, SpringLayout.WEST, canvas);

		layout.putConstraint(SpringLayout.NORTH, lblX1, 0, SpringLayout.SOUTH, canvas);
		layout.putConstraint(SpringLayout.WEST, lblX1, 45, SpringLayout.WEST, canvas);
		layout.putConstraint(SpringLayout.NORTH, lblX2, 0, SpringLayout.SOUTH, canvas);
		layout.putConstraint(SpringLayout.WEST, lblX2, 36, SpringLayout.EAST, lblX1);
		layout.putConstraint(SpringLayout.NORTH, lblX3, 0, SpringLayout.SOUTH, canvas);
		layout.putConstraint(SpringLayout.WEST, lblX3, 36, SpringLayout.EAST, lblX2);
		layout.putConstraint(SpringLayout.NORTH, lblX4, 0, SpringLayout.SOUTH, canvas);
		layout.putConstraint(SpringLayout.WEST, lblX4, 36, SpringLayout.EAST, lblX3);
		layout.putConstraint(SpringLayout.NORTH, lblX5, 0, SpringLayout.SOUTH, canvas);
		layout.putConstraint(SpringLayout.WEST, lblX5, 36, SpringLayout.EAST, lblX4);
		layout.putConstraint(SpringLayout.NORTH, lblX6, 0, SpringLayout.SOUTH, canvas);
		layout.putConstraint(SpringLayout.WEST, lblX6, 36, SpringLayout.EAST, lblX5);

		panel.add(lblLineRed);
		panel.add(lblLineFood);
		panel.add(lblLineOrange);
		panel.add(lblLineOtfd);
		panel.add(lblLineYellow);
		panel.add(lblLineEl);
		panel.add(lblLineGray);
		panel.add(lblLineGs);
		panel.add(lblLineBlue);
		panel.add(lblLineWt);
		panel.add(canvas);
		panel.add(lblYen5000);
		panel.add(lblYen10000);
		panel.add(lblX1);
		panel.add(lblX2);
		panel.add(lblX3);
		panel.add(lblX4);
		panel.add(lblX5);
		panel.add(lblX6);

		setLayout(new BorderLayout());
		add(headerArea, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
	}

	/**
	 * 推移タブ初期化
	 * 
	 * @param date      日付
	 * @param summaries 集計値リスト
	 */
	public void initialize(LocalDate date, List<AbSummary> summaries) {
		manager = new AbGraphicManager(date, summaries);
		repaint(date);
	}

	/**
	 * グラフの再描画
	 * 
	 * @param date 日付
	 */
	private void repaint(LocalDate date) {
		lblTitle.setText(manager.getTitle());

		lblX1.setText(UTL.toMonth(date.minusMonths(10)));
		lblX2.setText(UTL.toMonth(date.minusMonths(8)));
		lblX3.setText(UTL.toMonth(date.minusMonths(6)));
		lblX4.setText(UTL.toMonth(date.minusMonths(4)));
		lblX5.setText(UTL.toMonth(date.minusMonths(2)));
		lblX6.setText(UTL.toMonth(date));

		canvas.repaint();
	}

	/**
	 * グラフ描画領域
	 */
	private class GraphicCanvas extends Canvas {

		/**
		 * コンストラクタ
		 */
		public GraphicCanvas() {
			super();
			setBackground(Color.BLACK);
		}

		/**
		 * グラフ描画
		 */
		public void paint(Graphics g) {
			if (manager != null) {
				manager.drawGraph(g);
			}
		}
	}
}
