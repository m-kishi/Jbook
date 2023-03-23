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
		/** 年月(yyyy-MM) */
		public static final String MONTHLY_GROUP = "yyyy-MM";
		/** 月(MM) */
		public static final String MONTH = "MM";
		/** タイトル(yyyy年MM月) */
		public static final String TITLE = "yyyy年MM月";
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

		/** 収支情報 */
		public static class BALANCE {
			/** 年度 */
			public static final int YEAR = 0;
			/** 収入 */
			public static final int EARN = 1;
			/** 支出 */
			public static final int EXPENSE = 2;
			/** 収支 */
			public static final int BALANCE = 3;
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
	 * 名称
	 */
	public static class NAME {
		/** 電気代 */
		public static final String EL = "電気代";
		/** ガス代 */
		public static final String GS = "ガス代";
		/** 水道代 */
		public static final String WT = "水道代";

		/** 光熱費に属する名称 */
		public static final List<String> ENERGY = new ArrayList<String>(
				Arrays.asList(EL, GS, WT)
		);
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

		/** 月次情報の対象種別 */
		public static final List<String> SUMMARIES = new ArrayList<String>(
				Arrays.asList(
						FOOD, OTFD, GOOD, FRND, TRFC, PLAY,
						HOUS, ENGY, CNCT, MEDI, INSU, OTHR
				)
		);

		/** 収支情報 */
		public static class BALANCE {

			/** 収支情報の収入対象 */
			public static final List<String> EARN = new ArrayList<String>(
				Arrays.asList(TYPE.EARN, TYPE.BNUS)
			);

			/** 収支情報の支出対象 */
			public static final List<String> EXPENSE = new ArrayList<String>(
				Arrays.asList(
					FOOD, OTFD, GOOD, FRND, TRFC, PLAY,
					HOUS, ENGY, CNCT, MEDI, INSU, OTHR, SPCL
				)
			);
		}

		/** 秘密収支の対象種別 */
		public static final List<String> PRIVATES = new ArrayList<String>(
				Arrays.asList(PRVI, PRVO)
		);
	}

	/**
	 * グラフ情報
	 */
	public static class GRAPH {
		/** 描画領域幅 */
		public static final int WIDTH = 345;
		/** 描画領域高さ */
		public static final int HEIGHT = 214;
		/** 表示月数 */
		public static final int DISTANCE = 13;
		/** 支出最大値 */
		public static final float MAX_VALUE = 15000;
		/** 月間隔 */
		public static final int HORIZONTAL = WIDTH / DISTANCE;
		/** 係数 */
		public static final float COEFFICIENT = -HEIGHT / MAX_VALUE;
		/** 描画点サイズ */
		public static final int RECTANGLE_SIZE = 6;
		/** 基準線描画値 */
		public static final List<Integer> LINE_VALUES = new ArrayList<Integer>(
				Arrays.asList(2500, 5000, 7500, 10000, 12500)
		);
	}
}
