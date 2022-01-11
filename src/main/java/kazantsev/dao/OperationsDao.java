package kazantsev.dao;

import kazantsev.entity.Book;
import kazantsev.entity.Operation;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OperationsDao extends  Dao<Operation,Integer>{
    String setDateReturn(Date dateReturn,int id) throws SQLException;
    String deleteOperationById(Integer id) throws SQLException;
    String createOperation(Integer id_book,Integer id_reader) throws SQLException;
    List<Operation> getOperationByIdReaderWhereReturnDateNull(int idReader) throws SQLException;
}
