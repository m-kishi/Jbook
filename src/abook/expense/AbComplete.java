// ------------------------------------------------------------
// © 2022 https://github.com/m-kishi
// ------------------------------------------------------------
package abook.expense;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import abook.common.AbUtility.UTL;

/**
 * 自動補完クラス
 */
public class AbComplete {

	/** 補完候補(名称 => 種別) */
	private Map<String, String> typeComplement;

	/** 補完候補(名称 => 種別 => 金額) */
	private Map<String, Map<String, Integer>> costComplement;

	/**
	 * コンストラクタ
	 * 
	 * @param expenses 支出情報リスト
	 */
	public AbComplete(List<AbExpense> expenses) {
		typeComplement = new HashMap<String, String>();
		costComplement = new HashMap<String, Map<String, Integer>>();

		// 名称と種別の対応を作る
		// 種別が複数ある場合は登録回数の多い種別を採用
		// それらの種別が同数のときは最新の種別を採用(最新の同日に複数種別は存在しない想定)
		var groupByName = expenses.stream().collect(Collectors.groupingBy(AbExpense::getName));
		for (var nameEntry : groupByName.entrySet()) {
			String name = nameEntry.getKey();
			String type = "";

			int max = 0;
			LocalDate latest = null;
			var groupByType = nameEntry.getValue().stream().collect(Collectors.groupingBy(AbExpense::getType));
			for (var typeEntry : groupByType.entrySet()) {
				int cnt = typeEntry.getValue().size();
				var expense = typeEntry.getValue().stream().sorted(
						Comparator.comparing(AbExpense::getDate).reversed()
				).findFirst().get();
				if (max < cnt || (max == cnt && latest.isBefore(expense.getDate()))) {
					max = cnt;
					type = typeEntry.getKey();
					latest = expense.getDate();
				}
			}
			typeComplement.put(name, type);
		}

		// 名称+種別と金額の対応を作る
		// 金額が複数ある場合は最新の金額を採用(最新の同日に複数金額は存在しない想定)
		var groupByNameAndType = expenses.stream().collect(
				Collectors.groupingBy(AbExpense::getName, Collectors.groupingBy(AbExpense::getType))
		);
		for (String name : groupByNameAndType.keySet()) {
			for (String type : groupByNameAndType.get(name).keySet()) {
				var latest = groupByNameAndType.get(name).get(type).stream().sorted(
						Comparator.comparing(AbExpense::getDate).reversed()
				).findFirst();
				latest.ifPresent(exp -> {
					Map<String, Integer> map = new HashMap<String, Integer>();
					if (costComplement.containsKey(name)) {
						map = costComplement.get(name);
					}
					map.put(type, exp.getCost());
					costComplement.put(name, map);
				});
			}
		}
	}

	/**
	 * 種別取得
	 * 
	 * @param name 名称
	 * @return 種別
	 */
	public String getType(String name) {
		if (UTL.isEmpty(name)) {
			return "";
		}
		return typeComplement.containsKey(name) ? typeComplement.get(name) : "";
	}

	/**
	 * 金額取得
	 * 
	 * @param name 名称
	 * @param type 種別
	 * @return 金額
	 */
	public Integer getCost(String name, String type) {
		if (UTL.isEmpty(name) || UTL.isEmpty(type)) {
			return null;
		}
		if (costComplement.containsKey(name)) {
			if (costComplement.get(name).containsKey(type)) {
				return costComplement.get(name).get(type);
			}
		}
		return null;
	}
}
