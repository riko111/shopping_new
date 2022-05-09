package model;

import java.io.Serializable;

public class CartBean implements Serializable {
	private int id;
	private String name;
	private int price;
	private int quantity;
	private int sum_price; //追加
	private int user_id; //追加

//	public CartBean(int id, String name, String type, int price, int quantity, String image, int state, String created_at, int sum_price, int user_id) {
	public CartBean(int id, String name, int price, int quantity,int sum_price, int user_id) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getSum_price() {
		return sum_price;
	}
	public int getUser_id() {
		return user_id;
	}
}
