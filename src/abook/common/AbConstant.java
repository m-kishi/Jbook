// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.common;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 定数クラス
 */
public class AbConstant {

	/**
	 * コンストラクタ
	 */
	private AbConstant() {
	}

	/**
	 * フォーマット
	 */
	public static class FMT {
		/** 日付(yyyy-MM-dd) */
		public static final String DATE = "yyyy-MM-dd";
		/** 備考のツールチップ */
		public static final String NOTE = "<html>%s</html>";
	}

	/**
	 * 列インデックス
	 */
	public static class COL {
		/** 支出情報 */
		public static class EXPENSE {
			/** 日付 */
			public static final int DATE = 0;
			/** 名称 */
			public static final int NAME = 1;
			/** 種別 */
			public static final int TYPE = 2;
			/** 金額 */
			public static final int COST = 3;
			/** 備考 */
			public static final int NOTE = 4;
		}
	}

	/**
	 * 色
	 */
	public static class COLOR {
		/** 備考があるときの背景色 */
		public static final Color NOTE_BACKGROUND = new Color(222, 252, 231);
	}

	/**
	 * 種別
	 */
	public static class TYPE {
		/** 食費 */
		public static final String FOOD = "食費";
		/** 外食費 */
		public static final String OTFD = "外食費";
		/** 雑貨 */
		public static final String GOOD = "雑貨";
		/** 交際費 */
		public static final String FRND = "交際費";
		/** 交通費 */
		public static final String TRFC = "交通費";
		/** 遊行費 */
		public static final String PLAY = "遊行費";
		/** 家賃 */
		public static final String HOUS = "家賃";
		/** 光熱費 */
		public static final String ENGY = "光熱費";
		/** 通信費 */
		public static final String CNCT = "通信費";
		/** 医療費 */
		public static final String MEDI = "医療費";
		/** 保険料 */
		public static final String INSU = "保険料";
		/** その他 */
		public static final String OTHR = "その他";
		/** 収入 */
		public static final String EARN = "収入";
		/** 合計 */
		public static final String TTAL = "合計";
		/** 収支 */
		public static final String BLNC = "収支";
		/** 特入 */
		public static final String BNUS = "特入";
		/** 特出 */
		public static final String SPCL = "特出";
		/** 秘密入 */
		public static final String PRVI = "秘密入";
		/** 秘密出 */
		public static final String PRVO = "秘密出";

		/** 支出情報として指定可能な種別 */
		public static final List<String> EXPENSES = new ArrayList<String>(
				Arrays.asList(
						FOOD, OTFD, GOOD, FRND, TRFC, PLAY,
						HOUS, ENGY, CNCT, MEDI, INSU, OTHR,
						EARN, BNUS, SPCL, PRVI, PRVO
				)
		);
	}
}