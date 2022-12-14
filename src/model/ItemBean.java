package model;

import java.io.Serializable;

public class ItemBean implements Serializable {
	private int id;
	private String name;
	private String type;
	private int price;
	private int quantity;
	private String image;
	private int state;
	private String created_at;

	public ItemBean() {}

	// itemテーブル
	public ItemBean(int id, String name, String type, int price, int quantity, String image, int state, String created_at) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.state = state;
		this.created_at = created_at;
	}
	// 商品一覧取得時のデバッグ表示用
	public String toString() {
		return "id:" + this.id + ",name:" + this.name + ",type:" + this.type + ",price:" + this.price
				+ ",quantity:" + this.quantity + ",image:" + this.image + ",state:" + this.state + ",created_at:" + this.created_at;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public int getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getImage() {
		return image;
	}
	public int getState() {
		return state;
	}
	public String getCreated_at() {
		return created_at;
	}
}
