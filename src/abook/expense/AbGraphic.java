// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.expense;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import abook.common.AbConstant.GRAPH;

/**
 * 推移情報クラス
 */
public class AbGraphic {

	/** 座標 */
	private List<Point> points;

	/** 色 */
	private Color color;

	/** 描画属性 */
	private BasicStroke stroke;

	/**
	 * コンストラクタ
	 * 
	 * @param color 色
	 */
	public AbGraphic(Color color) {
		points = new ArrayList<Point>();

		this.color = color;

		// 線幅，両端の形，線の接合部の形
		stroke = new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
	}

	/**
	 * 座標追加
	 * 
	 * @param cost 金額
	 */
	public void addPoint(int cost) {
		int x = (int) (GRAPH.HORIZONTAL * points.size());
		int y = (int) (GRAPH.COEFFICIENT * cost + GRAPH.HEIGHT);
		points.add(new Point(x, y));
	}

	/**
	 * グラフ描画
	 * 
	 * @param g 描画領域
	 */
	public void drawData(Graphics g) {
		g.setColor(color);

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(stroke);

		Point prev = null;
		for (Point p : points) {
			g.fillRect(
					(int) (p.getX() - GRAPH.RECTANGLE_SIZE / 2),
					(int) (p.getY() - GRAPH.RECTANGLE_SIZE / 2),
					GRAPH.RECTANGLE_SIZE,
					GRAPH.RECTANGLE_SIZE
			);

			if (prev != null) {
				g.drawLine((int) prev.getX(), (int) prev.getY(), (int) p.getX(), (int) p.getY());
			}

			prev = p;
		}
	}
}
