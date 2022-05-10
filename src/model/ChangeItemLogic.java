package model;

import dao.HistoryDAO;
import dao.ItemDAO;

public class ChangeItemLogic {

//		//「quantityを「stock」に変更したい！


	public String checkStock(int item_id, int quantity) {
		System.out.println("--------------------ChangeItemLogicTest(checkStock())--------------------");

		ItemDAO dao = new ItemDAO();
		String short_stock = dao.checkStock(item_id, quantity);

		return short_stock; //在庫不足がある場合その商品名

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

}
