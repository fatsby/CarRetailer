package fatsby.manager;

public class Room {
    private String roomNumber;
    private int capacity;
    private int price;
    private String description;
    private String imageURL;

    public Room(String roomNumber, int capacity, int price, String description, String imageURL) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
    }

    public Room() {}

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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
