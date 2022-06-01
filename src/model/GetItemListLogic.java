package model;

import java.util.List;

import dao.ItemDAO;

public class GetItemListLogic {

	// リンクから商品リストに遷移するとき
	public List<ItemBean> execute() {
		System.out.println("--------------------GetItemListLogic(execute())--------------------");

		ItemDAO dao = new ItemDAO();
		List<ItemBean> itemList = dao.getItem(0); //管理者(ユーザーID=1）以外のユーザー用
		return itemList;
	}

	// ログイン時に商品リストに遷移するとき
	public List<ItemBean> execute(int userId) {
		System.out.println("--------------------GetItemListLogic(execute(userId))--------------------");

		ItemDAO dao = new ItemDAO();
		List<ItemBean> itemList = dao.getItem(userId);
		return itemList;
	}
}
