package model;

import java.io.Serializable;

public class HistoryBean implements Serializable {
	private int id;
	private int user_id;
	private int item_id;
	private int item_price;
	private int order_num;
	private String order_date;
	private String item_name;
	private int sum_price;

	public HistoryBean() {}

	// Historyテーブル
	public HistoryBean(int id, int user_id, int item_id, int item_price, int order_num, String order_date) {
		this.id = id;
		this.user_id = user_id;
		this.item_id = item_id;
		this.item_price = item_price;
		this.order_num = order_num;
		this.order_date = order_date;
	}

	// 注文履歴表示①
	public HistoryBean(int id, int user_id, int item_id, int item_price, int order_num, String order_date, String item_name, int sum_price) {
		this.id = id; //不要
		this.user_id = user_id; //不要
		this.item_id = item_id; //不要
		this.item_price = item_price;
		this.order_num = order_num;
		this.order_date = order_date;
		this.item_name = item_name; //追加
		this.sum_price = sum_price; //追加
	}

	// 注文履歴表示②
	public HistoryBean(int item_price, int order_num, String order_date, String item_name, int sum_price) {
		this.item_price = item_price;
		this.order_num = order_num;
		this.order_date = order_date;
		this.item_name = item_name; //追加
		this.sum_price = sum_price; //追加
	}

	public int getId() {
		return id;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public int getItem_price() {
		return item_price;
	}
	public int getOrder_num() {
		return order_num;
	}
	public String getOrder_date() {
		return order_date;
	}
	public String getItem_name() {
		return item_name;
	}
	public int getSum_price() {
		return sum_price;
	}
}
