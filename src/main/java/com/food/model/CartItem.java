package com.food.model;

public class CartItem {
	private int ItemId;
	private int restaurantId;
	private String name;
	private int price;
	private int quantity;
	private String imagePath;
	private String description;
	public CartItem() {
		super();
	}
	public CartItem(int itemId, int restaurantId, String name, int price, int quantity,String imagePath,String description) {
		super();
		this.ItemId = itemId;
		this.restaurantId = restaurantId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.imagePath=imagePath;
		this.description=description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getItemId() {
		return ItemId;
	}
	public void setItemId(int itemId) {
		ItemId = itemId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
