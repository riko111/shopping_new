package model;

import java.util.List;
import java.util.Map;

import dao.HistoryDAO;
import dao.ItemDAO;

public class ChangeItemLogic {

//		//「quantityを「stock」に変更したい！

	// ■在庫のチェック
	public String checkStock(Map<Integer, List<Object>> cartMap) {
		System.out.println("--------------------ChangeItemLogicTest(checkStock())--------------------");

			ItemDAO dao = new ItemDAO();
			String short_stock = dao.checkStock(cartMap);

			return short_stock; //在庫不足がある場合その商品名

		}

	// ■在庫数の減算
	public boolean reduceStock(Map<Integer, List<Object>> cartMap) {
		System.out.println("--------------------ChangeItemLogicTest(reduceStock())--------------------");

		ItemDAO dao = new ItemDAO();
		return dao.reduceStock(cartMap);

	}

	// ■注文履歴の追加
	public boolean addHistory(int user_id, Map<Integer, List<Object>> cartMap) {
		System.out.println("--------------------ChangeItemLogicTest(addHistory())--------------------");

		HistoryDAO dao = new HistoryDAO();
		return dao.addHistory(user_id, cartMap);

	}

}
