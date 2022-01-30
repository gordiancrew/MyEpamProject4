package kazantsev.entity;

public class Book {
    private int id;
    private String name;
    private String author;
    private int year;
    private int number;
    private String description;

    public Book(String name, String author, int year,int number, String description){

        this.name=name;
        this.author=author;
        this.year=year;
        this.number=number;
        this.description=description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return  "id"+id+"   \""+name+"\"   автор  "+author+"  год издания "+year+" кол-во экз: "+number;

    }
}
