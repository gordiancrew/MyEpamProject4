package kazantsev.entity;

import java.sql.Date;
import java.time.LocalDate;

public class Operation {
    private int id;
    private Book book;
    private User reader;
    private LocalDate dateGet;
    private LocalDate dateReturn;

    public void setDateReturn(LocalDate dateReturn) {
        this.dateReturn = dateReturn;
    }

    public Operation(Book book, User reader, LocalDate dateGet, LocalDate dateReturn) {

        this.book = book;
        this.reader = reader;
        this.dateGet = dateGet;
        this.dateReturn = dateReturn;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public User getReader() {
        return reader;
    }

    public LocalDate getDateGet() {
        return dateGet;
    }

    public LocalDate getDateReturn() {
        return dateReturn;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", book=" + book +
                ", reader=" + reader +
                ", dateGet=" + dateGet +
                ", dateReturn=" + dateReturn +
                '}';
    }
}
