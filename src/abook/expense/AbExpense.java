// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.expense;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import abook.common.AbConstant.FMT;
import abook.common.AbConstant.TYPE;
import abook.common.AbException;
import abook.common.AbManager;
import abook.common.AbManager.MESSAGE;
import abook.common.AbUtility.UTL;

/**
 * 支出情報クラス
 */
public class AbExpense {

	/** 日付 */
	private LocalDate date;

	/** 名称 */
	private String name;

	/** 種別 */
	private String type;

	/** 金額 */
	private int cost;

	/** 備考 */
	private String note;

	/**
	 * コンストラクタ
	 * 
	 * @param date 日付
	 * @param name 名称
	 * @param type 種別
	 * @param cost 金額
	 * @param note 備考
	 * @throws AbException
	 */
	public AbExpense(String date, String name, String type, String cost, String note) throws AbException {
		this.date = parseDate(date);
		this.name = parseName(name);
		this.type = parseType(type);
		this.cost = parseCost(cost);
		this.note = parseNote(note);
	}

	/**
	 * 日付設定
	 * 
	 * @param date 日付
	 * @return 日付
	 * @throws AbException
	 */
	private LocalDate parseDate(String date) throws AbException {

		if (UTL.isEmpty(date)) {
			AbManager.throwException(MESSAGE.DATE_NULL);
		}

		LocalDate dt = LocalDate.now();
		try {
			dt = LocalDate.parse(date, DateTimeFormatter.ofPattern(FMT.DATE));
		} catch (DateTimeParseException ex) {
			AbManager.throwException(MESSAGE.DATE_FORMAT);
		}

		return dt;
	}

	/**
	 * 名称設定
	 * 
	 * @param name 名称
	 * @return 名称
	 * @throws AbException
	 */
	private String parseName(String name) throws AbException {
		if (UTL.isEmpty(name)) {
			AbManager.throwException(MESSAGE.NAME_NULL);
		}
		return name;
	}

	/**
	 * 種別設定
	 * 
	 * @param type 種別
	 * @return 種別
	 * @throws AbException
	 */
	private String parseType(String type) throws AbException {

		if (UTL.isEmpty(type)) {
			AbManager.throwException(MESSAGE.TYPE_NULL);
		}

		if (!TYPE.EXPENSES.contains(type)) {
			AbManager.throwException(MESSAGE.TYPE_WRONG);
		}

		return type;
	}

	/**
	 * 金額設定
	 * 
	 * @param cost 金額
	 * @return 金額
	 * @throws AbException
	 */
	private int parseCost(String cost) throws AbException {

		if (UTL.isEmpty(cost)) {
			AbManager.throwException(MESSAGE.COST_NULL);
		}

		int value = 0;
		try {
			value = Integer.parseInt(cost);
			if (value < 0) {
				AbManager.throwException(MESSAGE.COST_MINUS);
			}
		} catch (NumberFormatException ex) {
			AbManager.throwException(MESSAGE.COST_FORMAT);
		}

		return value;
	}

	/**
	 * 備考設定
	 * 
	 * @param note 備考
	 * @return 備考
	 */
	private String parseNote(String note) {
		if (UTL.isEmpty(note)) {
			return "";
		}
		return note;
	}

	/**
	 * DBファイル出力形式(CSV)
	 * 
	 * @return DBファイル出力形式(CSV)
	 */
	public String toDBFileFormat() {
		String format = "\"%s\",\"%s\",\"%s\",\"%d\",\"%s\"";
		return String.format(format, UTL.toString(date), name, type, cost, note);
	}

	/**
	 * getter(日付)
	 * 
	 * @return 日付
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * getter(名称)
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter(種別)
	 * 
	 * @return 種別
	 */
	public String getType() {
		return type;
	}

	/**
	 * getter(金額)
	 * 
	 * @return 金額
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * getter(備考)
	 * 
	 * @return 備考
	 */
	public String getNote() {
		return note;
	}
}
