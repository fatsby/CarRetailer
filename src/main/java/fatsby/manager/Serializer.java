package fatsby.manager;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
    public static boolean fileExists(String directoryPath, String fileName) {
        File file = new File(directoryPath, fileName);
        return file.exists() && !file.isDirectory();
    }
    public static void createDirectory() {
        File mainDirectory = new File("C:\\FatsbyCarRetailer");
        File resourcesDir = new File("C:\\FatsbyCarRetailer\\resources\\images\\cars");
        File carDB = new File("C:\\FatsbyCarRetailer\\database\\cars");
        File staffDB = new File("C:\\FatsbyCarRetailer\\database\\staffs");
        File userDB = new File("C:\\FatsbyCarRetailer\\database\\users");
        if (!mainDirectory.exists()) {
            resourcesDir.mkdirs();
            carDB.mkdirs();
            staffDB.mkdirs();
            userDB.mkdirs();
        }
    }

    public static void serializeObject(Object obj, String directoryPath, String fileName){
        try{
            FileOutputStream fileOut = new FileOutputStream(directoryPath + "/" + fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object deserializeObject(String directoryPath, String fileName){
        Object obj = null;
        try{
            FileInputStream fileIn = new FileInputStream(directoryPath + "/" + fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            obj = objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return obj;
    }
    public static void FileCopy(String src, String carName){
        String dest = "C:\\FatsbyCarRetailer\\resources\\images\\cars\\"+carName+".jpg";
        try{
            Files.copy(Paths.get(src), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully!");
        } catch (IOException e) {
            System.out.println("Failed to copy the file.");
            e.printStackTrace();
        }
    }

    public static List<Car> deserializeRooms(String directoryPath) {
        List<Car> cars = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath))) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    Car car = (Car) deserializeObject(file.getParent().toString(), file.getFileName().toString());
                    if (car != null) {
                        cars.add(car);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }
}


