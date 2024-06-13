package fatsby.manager;

import java.util.ArrayList;

public class Store {
    private static Store instance;
    private ArrayList<Car> cars;

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }
    public Store() {
        cars = new ArrayList<Car>();
        cars = (ArrayList<Car>) Serializer.deserializeRooms("C:\\FatsbyCarRetailer\\database\\cars");
        System.out.println(cars);
    }
    public ArrayList<Car> getCars() {
        return cars;
    }
    public void addCar(Car car) {
        cars.add(car);
    }
    public void removeCar(Car car) {
        cars.remove(car);
    }
}
