package test;

import java.util.List;
import java.util.Map;

import dao.ItemDAO;

public class ChangeItemLogicTest {

	public static void main(String[] args) {
		System.out.println("▼▼---------------テストChangeItemLogicTest(main())---------------▼▼");

		Map<Integer, List<Object>> cartMap = null; //★cartMapを用意してテストすること

		String short_stock = null;
		short_stock = checkStock(cartMap);

		if(short_stock == null) {
			System.out.println("全商品の在庫チェックOK");
		} else {
			System.out.println("item_id=" + short_stock + "の在庫チェックNG");
		}

		boolean result;
		result = reduceStock(cartMap);

		if(result) {
			System.out.println("注文処理完了（在庫を注文数分へらした）");
		}

		System.out.println("▲▲-----------------------------------------------------------------▲▲");
	}

	public static String checkStock(Map<Integer, List<Object>> cartMap) {
		System.out.println("--------------------ChangeItemLogicTest(checkStock())--------------------");

		ItemDAO dao = new ItemDAO();
		String short_stock = dao.checkStock(cartMap);

		return short_stock;

	}

	public static boolean reduceStock(Map<Integer, List<Object>> cartMap) {
		System.out.println("--------------------ChangeItemLogicTest(reduceStock())--------------------");

		ItemDAO dao = new ItemDAO();
		return dao.reduceStock(cartMap);

	}
}
