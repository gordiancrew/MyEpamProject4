package kazantsev.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T,Id> {
    T getById(Id Id) throws SQLException;

    List<T> getAll() throws SQLException;

    String save(T t) throws SQLException;

    void delete(T t) throws SQLException;
}
