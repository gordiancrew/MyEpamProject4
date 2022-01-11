package kazantsev.entity;

public class Book {
    private int id;
    private String name;
    //todo tittle
    private String author;
    //todo name
    private int year;
    //todo сделать булеан
    private int number;

    public Book(int id,String name, String author, int year,int number){
        this.id=id;
        this.name=name;
        this.author=author;
        this.year=year;
        this.number=number;
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
//        return "Book{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", author='" + author + '\'' +
//                ", year=" + year +
//                ", number=" + number +
//                '}';

        return  "id"+id+"   \""+name+"\"   автор  "+author+"  год издания "+year+" кол-во экз: "+number;


    }
}
