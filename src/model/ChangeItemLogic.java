package model;

import dao.HistoryDAO;
import dao.ItemDAO;

public class ChangeItemLogic {
//	public String updateStock(Map<Integer, List<Object>> cartMap) {
//		System.out.println("--------------------ChangeItemLogic(updateStock())--------------------");
//
//		//「quantityを「stock」に変更したい！
//
//		ItemDAO dao = new ItemDAO();
//
//		int item_id = 1; //この値を変更してテストする
//		int quantity = 4; //この値を変更してテストする
//
//		int short_stock = 0;
//		short_stock = checkStock(item_id, quantity); //item_id、quantity
//
//		if(short_stock == 0) {
//			System.out.println("全商品の在庫チェックOK");
//		} else {
//			System.out.println("item_id=" + short_stock + "の在庫チェックNG");
//			return "short_stock";
//		}
//
//		boolean result;
//		result = reduceStock(item_id, quantity); //item_id、quantity
//
//		if(result) {
//			System.out.println("注文処理完了（在庫を注文数分へらした）");
//			return "OK";
//		}
//
//		return "error";
//	}


	public int checkStock(int item_id, int quantity) {
		System.out.println("--------------------ChangeItemLogicTest(checkStock())--------------------");

		ItemDAO dao = new ItemDAO();
		int short_stock = dao.checkStock(item_id, quantity);

		return short_stock;

	}

	public boolean reduceStock(int item_id, int quantity) {
		System.out.println("--------------------ChangeItemLogicTest(reduceStock())--------------------");

		ItemDAO dao = new ItemDAO();
		return dao.reduceStock(item_id, quantity);

	}

	public boolean addHistory(int user_id, int item_id, int item_price, int order_num) {
		System.out.println("--------------------ChangeItemLogicTest(reduceStock())--------------------");

		HistoryDAO dao = new HistoryDAO();
		return dao.addHistory(user_id, item_id, item_price, order_num);

	}


		//商品ごとに在庫チェック
//		int short_stock = 0; //不足商品のitem_id
//		for(Integer key : cartMap.keySet()) {
//			int quantity = (int) cartMap.get(key).get(2);
//			short_stock = dao.checkStock(key, quantity);
//		}
//
//		if(short_stock !=0) {
//			System.out.println();
//		} else {
//			return short_stock;
//		}
//
//		//商品ごとに在庫を減算
//		for(Integer key : cartMap.keySet()) {
//			int quantity = (int) cartMap.get(key).get(2);
//			int isOk = dao.reduceStock(key, quantity);
//
//			int[] result;
//		}

//		return true;

}
