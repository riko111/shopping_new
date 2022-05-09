package model;

import java.util.List;

import dao.ItemDAO;

public class GetItemListLogic {
	public List<ItemBean> execute() {
		System.out.println("--------------------GetItemListLogic(execute())--------------------");

		ItemDAO dao = new ItemDAO();
		List<ItemBean> itemList = dao.getItem();
		return itemList;
	}
}
