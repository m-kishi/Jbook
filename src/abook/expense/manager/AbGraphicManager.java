// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.expense.manager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import abook.common.AbConstant.GRAPH;
import abook.common.AbConstant.NAME;
import abook.common.AbConstant.TYPE;
import abook.common.AbUtility.UTL;
import abook.expense.AbExpense;
import abook.expense.AbGraphic;
import abook.expense.AbSummary;

/**
 * 推移情報管理クラス
 */
public class AbGraphicManager {

	/** 表示年月 */
	private LocalDate date;

	/** 月次情報リスト */
	private List<AbSummary> summaries;

	/** 基準線リスト */
	private List<BaseLine> lines;

	/** 推移情報リスト */
	private List<AbGraphic> graphics;

	/**
	 * コンストラクタ
	 * 
	 * @param date      日付
	 * @param summaries 月次情報リスト
	 */
	public AbGraphicManager(LocalDate date, List<AbSummary> summaries) {
		this.date = date;
		this.summaries = summaries;
		lines = createBaseLines();
		graphics = createGraphics();
	}

	/**
	 * 基準線リスト生成
	 * 
	 * @return 基準線リスト
	 */
	private List<BaseLine> createBaseLines() {
		var result = new ArrayList<BaseLine>();
		for (int value : GRAPH.LINE_VALUES) {
			result.add(new BaseLine(value));
		}
		return result;
	}

	/**
	 * 推移情報リスト生成
	 * 
	 * @return 推移情報リスト
	 */
	private List<AbGraphic> createGraphics() {
		var result = new ArrayList<AbGraphic>();

		AbGraphic gdF = new AbGraphic(Color.RED);
		AbGraphic gdO = new AbGraphic(Color.ORANGE);
		AbGraphic gdE = new AbGraphic(Color.YELLOW);
		AbGraphic gdG = new AbGraphic(Color.GRAY);
		AbGraphic gdW = new AbGraphic(Color.BLUE);

		for (LocalDate dt = date.minusYears(1); dt.isBefore(date) || dt.isEqual(date); dt = dt.plusMonths(1)) {
			AbSummary emptySummary = new AbSummary(dt, new ArrayList<AbExpense>());

			// lambda は final なローカル変数しかアクセスできないため，実質 final となるような変数に代入
			LocalDate current = dt;
			var selectedSummary = summaries.stream().filter(sum ->
					sum.getYear() == current.getYear() && sum.getMonth() == current.getMonthValue()
			).findFirst();

			AbSummary summary = selectedSummary.isPresent() ? selectedSummary.get() : emptySummary;

			int valF = summary.getCostByType(TYPE.FOOD);
			int valO = summary.getCostByType(TYPE.OTFD);
			int valE = summary.getCostByName(NAME.EL);
			int valG = summary.getCostByName(NAME.GS);
			int valW = summary.getCostByName(NAME.WT);

			gdF.addPoint(valF);
			gdO.addPoint(valO);
			gdE.addPoint(valE);
			gdG.addPoint(valG);
			gdW.addPoint(valW);
		}

		result.add(gdF);
		result.add(gdO);
		result.add(gdE);
		result.add(gdG);
		result.add(gdW);

		return result;
	}

	/**
	 * 現在の推移情報の日付
	 * 
	 * @return 現在の推移情報の日付
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * タイトル
	 * 
	 * @return yyyy年MM月
	 */
	public String getTitle() {
		return UTL.toTitle(date);
	}

	/**
	 * 前年へシフト
	 */
	public void setPrevYear() {
		date = date.minusYears(1);
		graphics = createGraphics();
	}

	/**
	 * 前月へシフト
	 */
	public void setPrevMonth() {
		date = date.minusMonths(1);
		graphics = createGraphics();
	}

	/**
	 * 翌月へシフト
	 */
	public void setNextMonth() {
		date = date.plusMonths(1);
		graphics = createGraphics();
	}

	/**
	 * 翌年へシフト
	 */
	public void setNextYear() {
		date = date.plusYears(1);
		graphics = createGraphics();
	}

	/**
	 * グラフ描画
	 * 
	 * @param g 描画領域
	 */
	public void drawGraph(Graphics g) {
		for (BaseLine line : lines) {
			line.drawLine(g);
		}
		for (AbGraphic ghc : graphics) {
			ghc.drawData(g);
		}
	}

	/**
	 * 基準線クラス
	 */
	private static class BaseLine {

		/** 開始座標 */
		private Point strPoint;

		/** 終了座標 */
		private Point endPoint;

		/** 描画属性 */
		private BasicStroke stroke;

		/**
		 * コンストラクタ
		 * 
		 * @param value 基準値
		 */
		public BaseLine(int value) {
			int h = (int) (GRAPH.COEFFICIENT * value + GRAPH.HEIGHT);
			strPoint = new Point(0, h);
			endPoint = new Point(GRAPH.WIDTH, h);

			// 線幅，両端の形，線の接合部の形，接合トリミングの制限値，破線パターン配列，破線パターン開始位置
			stroke = new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, new float[] { 2.0f }, 0);
		}

		/**
		 * 基準線描画
		 * 
		 * @param g 描画領域
		 */
		public void drawLine(Graphics g) {
			g.setColor(Color.GRAY);

			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(stroke);

			g.drawLine((int) strPoint.getX(), (int) strPoint.getY(), (int) endPoint.getX(), (int) endPoint.getY());
		}
	}
}
