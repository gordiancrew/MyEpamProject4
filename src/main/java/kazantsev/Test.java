package kazantsev;

import kazantsev.dao.UsersDao;
import kazantsev.dao.impl.UsersDaoImpl;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) throws SQLException {

        UsersDao usersDao=new UsersDaoImpl();
        System.out.println(usersDao.getUsersByConfirm(Boolean.TRUE));
    }
}
