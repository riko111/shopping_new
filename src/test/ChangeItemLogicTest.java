package test;

import dao.ItemDAO;

public class ChangeItemLogicTest {

	public static void main(String[] args) {
		System.out.println("▼▼---------------テストChangeItemLogicTest(main())---------------▼▼");
		int item_id = 1; //この値を変更してテストする
		int quantity = 4; //この値を変更してテストする

		int short_stock = 0;
		short_stock = checkStock(item_id, quantity); //item_id、quantity

		if(short_stock == 0) {
			System.out.println("全商品の在庫チェックOK");
		} else {
			System.out.println("item_id=" + short_stock + "の在庫チェックNG");
		}

		boolean result;
		result = reduceStock(item_id, quantity); //item_id、quantity

		if(result) {
			System.out.println("注文処理完了（在庫を注文数分へらした）");
		}

		System.out.println("▲▲-----------------------------------------------------------------▲▲");
	}

	public static int checkStock(int item_id, int quantity) {
		System.out.println("--------------------ChangeItemLogicTest(checkStock())--------------------");

		ItemDAO dao = new ItemDAO();
		int short_stock = dao.checkStock(item_id, quantity);

		return short_stock;

	}

	public static boolean reduceStock(int item_id, int quantity) {
		System.out.println("--------------------ChangeItemLogicTest(reduceStock())--------------------");

		ItemDAO dao = new ItemDAO();
		return dao.reduceStock(item_id, quantity);

	}
}
