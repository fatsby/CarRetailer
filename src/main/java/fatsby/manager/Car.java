package fatsby.manager;

import java.io.Serializable;

public class Car implements Serializable {
    private String carName;
    private int capacity;
    private int price;
    private String description;
    private String imageURL;

    public Car(String carName, int capacity, int price, String description, String imageURL) {
        this.carName = carName;
        this.capacity = capacity;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
    }

    public Car() {}

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
