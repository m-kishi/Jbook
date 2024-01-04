// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.form;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import abook.common.AbConstant.TYPE;
import abook.common.AbUtility.UTL;
import abook.expense.AbSummary;
import abook.expense.manager.AbSummaryManager;
import abook.form.subform.AbSubformType;

/**
 * 月次タブ
 */
public class AbTabSummary extends JPanel {

	/** 親のフレーム */
	private AbFormMain frame;

	/** 月次情報管理クラス */
	private AbSummaryManager manager;

	/** タイトル */
	private JLabel lblTitle;

	/** 食費 */
	private CostLabel lblCostFood;
	/** 外食費 */
	private CostLabel lblCostOtfd;
	/** 雑貨 */
	private CostLabel lblCostGood;
	/** 交際費 */
	private CostLabel lblCostFrnd;
	/** 交通費 */
	private CostLabel lblCostTrfc;
	/** 遊行費 */
	private CostLabel lblCostPaly;
	/** 家賃 */
	private CostLabel lblCostHous;
	/** 光熱費 */
	private CostLabel lblCostEngy;
	/** 通信費 */
	private CostLabel lblCostCnct;
	/** 医療費 */
	private CostLabel lblCostMedi;
	/** 保険料 */
	private CostLabel lblCostInsu;
	/** その他 */
	private CostLabel lblCostOthr;
	/** 合計 */
	private CostLabel lblCostTtal;
	/** 収支 */
	private CostLabel lblCostBlnc;

	/**
	 * コンストラクタ
	 * 
	 * @param frame     親のフレーム
	 * @param date      日付
	 * @param summaries 月次情報リスト
	 */
	public AbTabSummary(AbFormMain frame, LocalDate date, List<AbSummary> summaries) {
		super();
		this.frame = frame;
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
		btnPrevYear.setName("SummaryPrevYearButton");
		btnPrevYear.setFont(font);
		btnPrevYear.setMargin(margin);
		btnPrevYear.setPreferredSize(size);
		btnPrevYear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setPrevYear();
				setSummary();
			}
		});
		headerArea.add(btnPrevYear);

		// 前月ボタン
		JButton btnPrevMonth = new JButton("<");
		btnPrevMonth.setName("SummaryPrevMonthButton");
		btnPrevMonth.setFont(font);
		btnPrevMonth.setMargin(margin);
		btnPrevMonth.setPreferredSize(size);
		btnPrevMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setPrevMonth();
				setSummary();
			}
		});
		headerArea.add(btnPrevMonth);

		// タイトル
		lblTitle = new JLabel(UTL.toTitle(date));
		lblTitle.setName("TabSummaryTitle");
		lblTitle.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 18));
		headerArea.add(lblTitle);

		// 翌月ボタン
		JButton btnNextMonth = new JButton(">");
		btnNextMonth.setName("SummaryNextMonthButton");
		btnNextMonth.setFont(font);
		btnNextMonth.setMargin(margin);
		btnNextMonth.setPreferredSize(size);
		btnNextMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setNextMonth();
				setSummary();
			}
		});
		headerArea.add(btnNextMonth);

		// 翌年ボタン
		JButton btnNextYear = new JButton(">>");
		btnNextYear.setName("SummaryNextYearButton");
		btnNextYear.setFont(font);
		btnNextYear.setMargin(margin);
		btnNextYear.setPreferredSize(size);
		btnNextYear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.setNextYear();
				setSummary();
			}
		});
		headerArea.add(btnNextYear);

		// 月次エリア
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel();
		panel.setLayout(layout);

		// 種別のラベルと金額
		TypeLabel lblTypeFood = new TypeLabel(this, "LblTypeFood", TYPE.FOOD);
		TypeLabel lblTypeOtfd = new TypeLabel(this, "LblTypeOtfd", TYPE.OTFD);
		TypeLabel lblTypeGood = new TypeLabel(this, "LblTypeGood", TYPE.GOOD);
		TypeLabel lblTypeFrnd = new TypeLabel(this, "LblTypeFrnd", TYPE.FRND);
		TypeLabel lblTypeTrfc = new TypeLabel(this, "LblTypeTrfc", TYPE.TRFC);
		TypeLabel lblTypePaly = new TypeLabel(this, "LblTypePaly", TYPE.PLAY);
		TypeLabel lblTypeHous = new TypeLabel(this, "LblTypeHous", TYPE.HOUS);
		TypeLabel lblTypeEngy = new TypeLabel(this, "LblTypeEngy", TYPE.ENGY);
		TypeLabel lblTypeCnct = new TypeLabel(this, "LblTypeCnct", TYPE.CNCT);
		TypeLabel lblTypeMedi = new TypeLabel(this, "LblTypeMedi", TYPE.MEDI);
		TypeLabel lblTypeInsu = new TypeLabel(this, "LblTypeInsu", TYPE.INSU);
		TypeLabel lblTypeOthr = new TypeLabel(this, "LblTypeOthr", TYPE.OTHR);
		TypeLabel lblTypeTtal = new TypeLabel(this, "LblTypeTtal", TYPE.TTAL);
		TypeLabel lblTypeBlnc = new TypeLabel(this, "LblTypeBlnc", TYPE.BLNC);
		LineLabel lblLine1 = new LineLabel("----------------------------------------");
		LineLabel lblLine2 = new LineLabel("------------------");
		lblCostFood = new CostLabel(this, "LblCostFood", TYPE.FOOD, 0);
		lblCostOtfd = new CostLabel(this, "LblCostOtfd", TYPE.OTFD, 0);
		lblCostGood = new CostLabel(this, "LblCostGood", TYPE.GOOD, 0);
		lblCostFrnd = new CostLabel(this, "LblCostFrnd", TYPE.FRND, 0);
		lblCostTrfc = new CostLabel(this, "LblCostTrfc", TYPE.TRFC, 0);
		lblCostPaly = new CostLabel(this, "LblCostPaly", TYPE.PLAY, 0);
		lblCostHous = new CostLabel(this, "LblCostHous", TYPE.HOUS, 0);
		lblCostEngy = new CostLabel(this, "LblCostEngy", TYPE.ENGY, 0);
		lblCostCnct = new CostLabel(this, "LblCostCnct", TYPE.CNCT, 0);
		lblCostMedi = new CostLabel(this, "LblCostMedi", TYPE.MEDI, 0);
		lblCostInsu = new CostLabel(this, "LblCostInsu", TYPE.INSU, 0);
		lblCostOthr = new CostLabel(this, "LblCostOthr", TYPE.OTHR, 0);
		lblCostTtal = new CostLabel(this, "LblCostTtal", TYPE.TTAL, 0);
		lblCostBlnc = new CostLabel(this, "LblCostBlnc", TYPE.BLNC, 0);

		// レイアウトの配置設定
		layout.putConstraint(SpringLayout.NORTH, lblTypeFood, 11, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblTypeFood, 35, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, lblTypeFood, 85, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblTypeOtfd, 8, SpringLayout.SOUTH, lblTypeFood);
		layout.putConstraint(SpringLayout.WEST, lblTypeOtfd, 35, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, lblTypeOtfd, 85, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblLine1, 5, SpringLayout.SOUTH, lblTypeOtfd);
		layout.putConstraint(SpringLayout.WEST, lblLine1, 25, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, lblLine1, -25, SpringLayout.EAST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblTypeGood, 5, SpringLayout.SOUTH, lblLine1);
		layout.putConstraint(SpringLayout.WEST, lblTypeGood, 35, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, lblTypeGood, 85, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblTypeFrnd, 8, SpringLayout.SOUTH, lblTypeGood);
		layout.putConstraint(SpringLayout.WEST, lblTypeFrnd, 35, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, lblTypeFrnd, 85, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblTypeTrfc, 8, SpringLayout.SOUTH, lblTypeFrnd);
		layout.putConstraint(SpringLayout.WEST, lblTypeTrfc, 35, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, lblTypeTrfc, 85, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblTypePaly, 8, SpringLayout.SOUTH, lblTypeTrfc);
		layout.putConstraint(SpringLayout.WEST, lblTypePaly, 35, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, lblTypePaly, 85, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblTypeHous, 11, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblTypeHous, -70, SpringLayout.WEST, lblCostHous);
		layout.putConstraint(SpringLayout.EAST, lblTypeHous, -20, SpringLayout.WEST, lblCostHous);

		layout.putConstraint(SpringLayout.NORTH, lblTypeEngy, 8, SpringLayout.SOUTH, lblTypeHous);
		layout.putConstraint(SpringLayout.WEST, lblTypeEngy, -70, SpringLayout.WEST, lblCostEngy);
		layout.putConstraint(SpringLayout.EAST, lblTypeEngy, -20, SpringLayout.WEST, lblCostEngy);

		layout.putConstraint(SpringLayout.NORTH, lblTypeCnct, 5, SpringLayout.SOUTH, lblLine1);
		layout.putConstraint(SpringLayout.WEST, lblTypeCnct, -70, SpringLayout.WEST, lblCostCnct);
		layout.putConstraint(SpringLayout.EAST, lblTypeCnct, -20, SpringLayout.WEST, lblCostCnct);

		layout.putConstraint(SpringLayout.NORTH, lblTypeMedi, 8, SpringLayout.SOUTH, lblTypeCnct);
		layout.putConstraint(SpringLayout.WEST, lblTypeMedi, -70, SpringLayout.WEST, lblCostMedi);
		layout.putConstraint(SpringLayout.EAST, lblTypeMedi, -20, SpringLayout.WEST, lblCostMedi);

		layout.putConstraint(SpringLayout.NORTH, lblTypeInsu, 8, SpringLayout.SOUTH, lblTypeMedi);
		layout.putConstraint(SpringLayout.WEST, lblTypeInsu, -70, SpringLayout.WEST, lblCostInsu);
		layout.putConstraint(SpringLayout.EAST, lblTypeInsu, -20, SpringLayout.WEST, lblCostInsu);

		layout.putConstraint(SpringLayout.NORTH, lblTypeOthr, 8, SpringLayout.SOUTH, lblTypeInsu);
		layout.putConstraint(SpringLayout.WEST, lblTypeOthr, -70, SpringLayout.WEST, lblCostOthr);
		layout.putConstraint(SpringLayout.EAST, lblTypeOthr, -20, SpringLayout.WEST, lblCostOthr);

		layout.putConstraint(SpringLayout.NORTH, lblLine2, 5, SpringLayout.SOUTH, lblTypeOthr);
		layout.putConstraint(SpringLayout.WEST, lblLine2, -195, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.EAST, lblLine2, -25, SpringLayout.EAST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblTypeTtal, 5, SpringLayout.SOUTH, lblLine2);
		layout.putConstraint(SpringLayout.WEST, lblTypeTtal, -70, SpringLayout.WEST, lblCostTtal);
		layout.putConstraint(SpringLayout.EAST, lblTypeTtal, -20, SpringLayout.WEST, lblCostTtal);

		layout.putConstraint(SpringLayout.NORTH, lblTypeBlnc, 8, SpringLayout.SOUTH, lblTypeTtal);
		layout.putConstraint(SpringLayout.WEST, lblTypeBlnc, -70, SpringLayout.WEST, lblCostBlnc);
		layout.putConstraint(SpringLayout.EAST, lblTypeBlnc, -20, SpringLayout.WEST, lblCostBlnc);

		layout.putConstraint(SpringLayout.NORTH, lblCostFood, 11, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblCostFood, 20, SpringLayout.EAST, lblTypeFood);
		layout.putConstraint(SpringLayout.EAST, lblCostFood, 100, SpringLayout.EAST, lblTypeFood);

		layout.putConstraint(SpringLayout.NORTH, lblCostOtfd, 8, SpringLayout.SOUTH, lblCostFood);
		layout.putConstraint(SpringLayout.WEST, lblCostOtfd, 20, SpringLayout.EAST, lblTypeOtfd);
		layout.putConstraint(SpringLayout.EAST, lblCostOtfd, 100, SpringLayout.EAST, lblTypeOtfd);

		layout.putConstraint(SpringLayout.NORTH, lblCostGood, 8, SpringLayout.SOUTH, lblLine1);
		layout.putConstraint(SpringLayout.WEST, lblCostGood, 20, SpringLayout.EAST, lblTypeGood);
		layout.putConstraint(SpringLayout.EAST, lblCostGood, 100, SpringLayout.EAST, lblTypeGood);

		layout.putConstraint(SpringLayout.NORTH, lblCostFrnd, 8, SpringLayout.SOUTH, lblCostGood);
		layout.putConstraint(SpringLayout.WEST, lblCostFrnd, 20, SpringLayout.EAST, lblTypeFrnd);
		layout.putConstraint(SpringLayout.EAST, lblCostFrnd, 100, SpringLayout.EAST, lblTypeFrnd);

		layout.putConstraint(SpringLayout.NORTH, lblCostTrfc, 8, SpringLayout.SOUTH, lblCostFrnd);
		layout.putConstraint(SpringLayout.WEST, lblCostTrfc, 20, SpringLayout.EAST, lblTypeTrfc);
		layout.putConstraint(SpringLayout.EAST, lblCostTrfc, 100, SpringLayout.EAST, lblTypeTrfc);

		layout.putConstraint(SpringLayout.NORTH, lblCostPaly, 8, SpringLayout.SOUTH, lblCostTrfc);
		layout.putConstraint(SpringLayout.WEST, lblCostPaly, 20, SpringLayout.EAST, lblTypePaly);
		layout.putConstraint(SpringLayout.EAST, lblCostPaly, 100, SpringLayout.EAST, lblTypePaly);

		layout.putConstraint(SpringLayout.NORTH, lblCostHous, 11, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lblCostHous, -115, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.EAST, lblCostHous, -35, SpringLayout.EAST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblCostEngy, 8, SpringLayout.SOUTH, lblCostHous);
		layout.putConstraint(SpringLayout.WEST, lblCostEngy, -115, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.EAST, lblCostEngy, -35, SpringLayout.EAST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblCostCnct, 8, SpringLayout.SOUTH, lblLine1);
		layout.putConstraint(SpringLayout.WEST, lblCostCnct, -115, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.EAST, lblCostCnct, -35, SpringLayout.EAST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblCostMedi, 8, SpringLayout.SOUTH, lblCostCnct);
		layout.putConstraint(SpringLayout.WEST, lblCostMedi, -115, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.EAST, lblCostMedi, -35, SpringLayout.EAST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblCostInsu, 8, SpringLayout.SOUTH, lblCostMedi);
		layout.putConstraint(SpringLayout.WEST, lblCostInsu, -115, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.EAST, lblCostInsu, -35, SpringLayout.EAST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblCostOthr, 8, SpringLayout.SOUTH, lblCostInsu);
		layout.putConstraint(SpringLayout.WEST, lblCostOthr, -115, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.EAST, lblCostOthr, -35, SpringLayout.EAST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblCostTtal, 8, SpringLayout.SOUTH, lblLine2);
		layout.putConstraint(SpringLayout.WEST, lblCostTtal, -115, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.EAST, lblCostTtal, -35, SpringLayout.EAST, panel);

		layout.putConstraint(SpringLayout.NORTH, lblCostBlnc, 8, SpringLayout.SOUTH, lblCostTtal);
		layout.putConstraint(SpringLayout.WEST, lblCostBlnc, -115, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.EAST, lblCostBlnc, -35, SpringLayout.EAST, panel);

		panel.add(lblTypeFood);
		panel.add(lblTypeOtfd);
		panel.add(lblTypeGood);
		panel.add(lblTypeFrnd);
		panel.add(lblTypeTrfc);
		panel.add(lblTypePaly);
		panel.add(lblTypeHous);
		panel.add(lblTypeEngy);
		panel.add(lblTypeCnct);
		panel.add(lblTypeMedi);
		panel.add(lblTypeInsu);
		panel.add(lblTypeOthr);
		panel.add(lblTypeTtal);
		panel.add(lblTypeBlnc);
		panel.add(lblLine1);
		panel.add(lblLine2);
		panel.add(lblCostFood);
		panel.add(lblCostOtfd);
		panel.add(lblCostGood);
		panel.add(lblCostFrnd);
		panel.add(lblCostTrfc);
		panel.add(lblCostPaly);
		panel.add(lblCostHous);
		panel.add(lblCostEngy);
		panel.add(lblCostCnct);
		panel.add(lblCostMedi);
		panel.add(lblCostInsu);
		panel.add(lblCostOthr);
		panel.add(lblCostTtal);
		panel.add(lblCostBlnc);

		setLayout(new BorderLayout());
		add(headerArea, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
	}

	/**
	 * 初期化
	 * 
	 * @param date      日付
	 * @param summaries 月次情報リスト
	 */
	public void initialize(LocalDate date, List<AbSummary> summaries) {
		manager = new AbSummaryManager(date, summaries);
		setSummary();
	}

	/**
	 * 月次情報の表示設定
	 */
	public void setSummary() {
		lblTitle.setText(UTL.toTitle(manager.getDate()));
		lblCostFood.setCost(manager.getCost(TYPE.FOOD));
		lblCostOtfd.setCost(manager.getCost(TYPE.OTFD));
		lblCostGood.setCost(manager.getCost(TYPE.GOOD));
		lblCostFrnd.setCost(manager.getCost(TYPE.FRND));
		lblCostTrfc.setCost(manager.getCost(TYPE.TRFC));
		lblCostPaly.setCost(manager.getCost(TYPE.PLAY));
		lblCostHous.setCost(manager.getCost(TYPE.HOUS));
		lblCostEngy.setCost(manager.getCost(TYPE.ENGY));
		lblCostCnct.setCost(manager.getCost(TYPE.CNCT));
		lblCostMedi.setCost(manager.getCost(TYPE.MEDI));
		lblCostInsu.setCost(manager.getCost(TYPE.INSU));
		lblCostOthr.setCost(manager.getCost(TYPE.OTHR));
		lblCostTtal.setCost(manager.getCost(TYPE.TTAL));
		lblCostBlnc.setCost(manager.getCost(TYPE.BLNC));
	}

	/**
	 * 種別ラベル
	 */
	public static class TypeLabel extends JLabel {

		/**
		 * コンストラクタ
		 * 
		 * @param tab  月次タブ
		 * @param name ID
		 * @param type 種別
		 */
		public TypeLabel(AbTabSummary tab, String name, String type) {
			super(type);

			setName(name);
			setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
			if (TYPE.SUMMARIES.contains(type)) {
				addMouseListener(new MouseAction(tab, this, type));
			}
		}
	}

	/**
	 * 金額ラベル
	 */
	public static class CostLabel extends JLabel {

		/**
		 * コンストラクタ
		 * 
		 * @param tab  月次タブ
		 * @param name ID
		 * @param type 種別
		 * @param cost 金額
		 */
		public CostLabel(AbTabSummary tab, String name, String type, int cost) {
			super();

			setCost(cost);
			setName(name);
			setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
			setHorizontalAlignment(JLabel.RIGHT);
			if (TYPE.SUMMARIES.contains(type)) {
				addMouseListener(new MouseAction(tab, this, type));
			}
		}

		/**
		 * 金額設定
		 * 
		 * @param cost 金額
		 */
		public void setCost(int cost) {
			NumberFormat nf = NumberFormat.getCurrencyInstance(getLocale());
			setText(nf.format(cost));
		}
	}

	/**
	 * 罫線ラベル
	 */
	private static class LineLabel extends JLabel {

		/**
		 * コンストラクタ
		 * 
		 * @param line 罫線
		 */
		public LineLabel(String line) {
			super(line);
			setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
			setHorizontalAlignment(JLabel.CENTER);
		}
	}

	/**
	 * マウスのアクション
	 * 
	 * 種別と金額のラベルへカーソルを合わせたとき，リンクのように振る舞う
	 * そのリンクをクリックして種別サブフォームを表示する
	 */
	private static class MouseAction implements MouseListener {

		/** 月次タブ */
		private AbTabSummary tab;

		/** ラベル */
		private JLabel label;

		/** 種別 */
		private String type;

		/** 元のラベル表示内容を保持する */
		private String text;

		/**
		 * コンストラクタ
		 * 
		 * @param tab   月次タブ
		 * @param label ラベル
		 * @param type  種別
		 */
		public MouseAction(AbTabSummary tab, JLabel label, String type) {
			this.tab = tab;
			this.label = label;
			this.type = type;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			AbSubformType form = new AbSubformType(tab.manager.getDate(), type, tab.frame.getExpenses());
			form.setVisible(true);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			text = label.getText();
			label.setText(String.format("<html><u>%s</u></html>", text));
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			label.setText(text);
			label.setCursor(Cursor.getDefaultCursor());
		}
	}
}
