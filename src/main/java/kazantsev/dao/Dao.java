package kazantsev.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T, Id> {
    T getById(Id Id);

    List<T> getAll();

    String save(T t);

    void delete(T t);
}
