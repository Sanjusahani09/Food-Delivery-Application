package com.food.model;

public class Restaurant {
	private int restaurantId;
	private String name;
	private String cuisineTime;
	private int deliveryTime;
	private String address;
	private float ratings;
	private boolean isActive;
	private String ImagePath;
	public Restaurant() {
		super();
	}
	public Restaurant(int restaurantId, String name, String cuisineTime, int deliveryTime, String address,
			float ratings, boolean isActive, String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.cuisineTime = cuisineTime;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.ratings = ratings;
		this.isActive = isActive;
		ImagePath = imagePath;
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
	public String getCuisineTime() {
		return cuisineTime;
	}
	public void setCuisineTime(String cuisineTime) {
		this.cuisineTime = cuisineTime;
	}
	public int getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getRatings() {
		return ratings;
	}
	public void setRatings(float ratings) {
		this.ratings = ratings;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getImagePath() {
		return ImagePath;
	}
	public void setImagePath(String ImagePath) {
		this.ImagePath = ImagePath;
	}
	@Override
	public String toString() {
		return restaurantId + "     " + name + "    " + cuisineTime
				+ "      " + deliveryTime + "     " + address + "     " + ratings + "     "
				+ isActive + "     " + ImagePath;
	}
	
}
