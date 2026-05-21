package utils;
import java.io.*;
public class FileUtil {
    public static void saveObject(String filename, Object obj) {
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object loadObject(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}
