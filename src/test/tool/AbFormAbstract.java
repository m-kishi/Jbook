// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package test.tool;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 抽象クラス
 * フォームとサブフォームで共通する処理をまとめる
 */
public abstract class AbFormAbstract {

	/**
	 * テーブルセルのコンポーネントを取得
	 * 
	 * @param table テーブル
	 * @param row   行
	 * @param col   列
	 * @return コンポーネント
	 */
	protected Component getTableCellRendererComponent(JTable table, int row, int col) {
		DefaultTableCellRenderer cell = (DefaultTableCellRenderer) table.getCellRenderer(row, col);
		return cell.getTableCellRendererComponent(table, table.getValueAt(row, col), false, false, row, col);
	}
}
