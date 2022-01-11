package kazantsev.dao.impl;

import kazantsev.dao.OperationsDao;
import kazantsev.database.ConnectionSourse;
import kazantsev.entity.Book;
import kazantsev.entity.Operation;
import kazantsev.entity.User;

import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OperationsDaoImpl implements OperationsDao {
    ConnectionSourse sourse = ConnectionSourse.instance();
    Connection conn;

    {
        try {
            conn = sourse.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Operation getById(Integer Id) throws SQLException {
        String sql = "select * from operations where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, Id);
        ResultSet rs = statement.executeQuery();
        Operation result = null;
        if (rs.next())
            result = operationMapping(rs);
        return result;
    }

    @Override
    public List<Operation> getAll() throws SQLException {
        String sql = "select * from operations";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Operation> listOperations = new ArrayList<>();
        while (rs.next()) {
            listOperations.add(operationMapping(rs));
        }
        Collections.sort(listOperations, Comparator.comparing(Operation::getDateGet));
        return listOperations;
    }

    @Override
    public String save(Operation operation) throws SQLException {
        String sql = "insert operations (id_book,id_reader,date_get) values (?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, operation.getBook().getId());
        statement.setInt(2, operation.getReader().getId());
        statement.setDate(3, Date.valueOf(operation.getDateGet()));


        statement.executeUpdate();
        return "Книга " + operation.getBook().getAuthor() + " " + operation.getBook().getName() + "  взята" +
                " " + operation.getReader().getSureName();
    }

    @Override
    public void delete(Operation operation) throws SQLException {
        deleteOperationById(operation.getId());
    }

    @Override
    public String setDateReturn(Date dateReturn, int id) throws SQLException {
        String sql = "update  operations set date_return=? where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDate(1, dateReturn);
        statement.setInt(2, id);
        statement.executeUpdate();
        return "ok";
    }

    @Override
    public String deleteOperationById(Integer id) throws SQLException {
        Operation operationDel = getById(id);
        if (operationDel != null) {
            String userString = "id" + operationDel.getId();
            String sql = "delete from operations where id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            return "операция " + operationDel + " удалена";
        } else {
            return "Нет такой операции";
        }
    }

    @Override
    public String createOperation(Integer id_book, Integer id_reader) throws SQLException {
        String sql = "insert operations (id_book,id_reader,date_get) values (?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id_book);
        statement.setInt(2, id_reader);
        statement.setDate(3, Date.valueOf(LocalDate.now()));
        statement.executeUpdate();
        return "ok";
    }

    @Override
    public List<Operation> getOperationByIdReaderWhereReturnDateNull(int idReader) throws SQLException {
        String sql = "select*from operations where id_reader =? and date_return is NULL";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, idReader);
        List<Operation> operationsList = new ArrayList<>();
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            operationsList.add(operationMapping(rs));
        }
        return operationsList;
    }


    public Operation operationMapping(ResultSet rs) throws SQLException {
        BooksDaoImpl booksDao = new BooksDaoImpl();
        UsersDaoImpl usersDao = new UsersDaoImpl();
        int id = rs.getInt("id");
        int idBook = rs.getInt("id_book");
        Book book = booksDao.getById(idBook);
        int idReader = rs.getInt("id_reader");
        User reader = usersDao.getById(idReader);
        LocalDate dateGet = null;
        if (rs.getDate("date_get") != null) {
            dateGet = rs.getDate("date_get").toLocalDate();
        }
        LocalDate dateReturn = null;
        if (rs.getDate("date_return") != null) {
            dateReturn = rs.getDate("date_return").toLocalDate();
        }
        Operation operation = new Operation(book, reader, dateGet, dateReturn);
        operation.setId(id);

        return operation;
    }


}
