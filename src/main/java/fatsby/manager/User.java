package fatsby.manager;

import java.util.ArrayList;

public class User implements java.io.Serializable {
    private static User instance;
    private String username;
    private String password;
    private String gender;
    private boolean isStaff;
    private ArrayList<Car> ownedCars;

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public static void setInstance(User newInstance) {
        if (newInstance != null) {
            instance = newInstance;
        }
    }

    public User() {
        ownedCars = new ArrayList<>();
    }

    public User(String username, String password, String gender) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        ownedCars = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    public ArrayList<Car> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(ArrayList<Car> ownedCars) {
        this.ownedCars = ownedCars;
    }

    public void addOwnedCar(Car car) {
        ownedCars.add(car);
    }
    public void removeOwnedCar(Car car) {
        ownedCars.remove(car);
    }
}
