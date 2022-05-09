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
