package fatsby.manager;

import java.io.*;

public class Serializer {
    public static boolean fileExists(String directoryPath, String fileName) {
        File file = new File(directoryPath, fileName);
        return file.exists() && !file.isDirectory();
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
}
