package kazantsev;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Test {
    public static void main(String[] args) {

        String passwordEter = "222";

        String password= BCrypt.hashpw(passwordEter, BCrypt.gensalt(12));
        System.out.println(password);

    }
}
