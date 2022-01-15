package kazantsev;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Locale;
import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) {

        ResourceBundle resourceBundle=ResourceBundle.getBundle("messages", new Locale("en"));
        ResourceBundle resourceBundleRU=ResourceBundle.getBundle("messages", new Locale("ru"));
        System.out.println(resourceBundle.getString("wolf"));
        System.out.println(resourceBundleRU.getString("wolf"));

    }
}
