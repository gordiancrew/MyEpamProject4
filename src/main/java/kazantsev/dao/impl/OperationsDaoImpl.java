package kazantsev.dao.impl;

import kazantsev.controller.LibraryServlet;
import kazantsev.dao.OperationsDao;
import kazantsev.database.ConnectionSourse;
import kazantsev.entity.Book;
import kazantsev.entity.Operation;
import kazantsev.entity.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OperationsDaoImpl implements OperationsDao {
    private static final Logger log = Logger.getLogger(OperationsDaoImpl.class);
   Connection conn=null;
   public OperationsDaoImpl(Connection conn){
       this.conn=conn;
   }


    @Override
    public Operation getById(Integer Id) {
        String sql = "select * from operations where id=?";
        PreparedStatement statement = null;
        Operation result = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, Id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                result = operationMapping(rs);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Operation> getAll() {
        String sql = "select * from operations";
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        List<Operation> listOperations = new ArrayList<>();
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
            listOperations.add(operationMapping(rs));
        }
        Collections.sort(listOperations, Comparator.comparing(Operation::getDateGet));
        return listOperations;
    }

    @Override
    public String save(Operation operation) {
        String sql = "insert operations (id_book,id_reader,date_get) values (?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, operation.getBook().getId());
            statement.setInt(2, operation.getReader().getId());
            statement.setDate(3, Date.valueOf(operation.getDateGet()));
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return "ok";
    }

    @Override
    public void delete(Operation operation) {
        deleteOperationById(operation.getId());
    }

    @Override
    public String setDateReturn(Date dateReturn, int id) {
        String sql = "update  operations set date_return=? where id=?";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setDate(1, dateReturn);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return "ok";
    }

    @Override
    public String deleteOperationById(Integer id) {
        Operation operationDel = null;
        operationDel = getById(id);
        if (operationDel != null) {
            String userString = "id" + operationDel.getId();
            String sql = "delete from operations where id=?";
            PreparedStatement statement = null;
            try {
                statement = conn.prepareStatement(sql);

                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
            return "ok";
        } else {
            return "no";
        }
    }

    @Override
    public String createOperation(Integer id_book, Integer id_reader) {
        String sql = "insert operations (id_book,id_reader,date_get) values (?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id_book);
            statement.setInt(2, id_reader);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return "ok";
    }

    @Override
    public List<Operation> getOperationByIdReaderWhereReturnDateNull(int idReader) {
        String sql = "select*from operations where id_reader =? and date_return is NULL";
        PreparedStatement statement = null;
        List<Operation> operationsList = new ArrayList<>();
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idReader);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                operationsList.add(operationMapping(rs));
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return operationsList;
    }

    public Operation operationMapping(ResultSet rs) {
        BooksDaoImpl booksDao = new BooksDaoImpl(conn);
        UsersDaoImpl usersDao = new UsersDaoImpl(conn);
        Operation operation = null;
        int id = 0;
        try {
            id = rs.getInt("id");
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
            operation = new Operation(book, reader, dateGet, dateReturn);
            operation.setId(id);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return operation;
    }
}
