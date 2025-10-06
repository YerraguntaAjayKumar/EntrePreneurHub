package utils;
public class ValidationUtil {
    public static boolean isValidEmail(String email) {
        return email.contains("@")&&email.contains(".");
    }
    public static boolean isValidPassword(String pwd) {
        return pwd.length()>=4;
    }
}
