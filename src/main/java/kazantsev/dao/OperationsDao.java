package kazantsev.dao;

import kazantsev.entity.Book;
import kazantsev.entity.Operation;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OperationsDao extends Dao<Operation, Integer> {
    String setDateReturn(Date dateReturn, int id);

    String deleteOperationById(Integer id);

    String createOperation(Integer id_book, Integer id_reader);

    List<Operation> getOperationByIdReaderWhereReturnDateNull(int idReader);
}
