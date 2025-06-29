public class Book {
    private static int counter = 1000;
    private final int id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int year;
    private int totalCopies;
    private int borrowed;

    public Book(String title, String author, String isbn, String genre, int year, int totalCopies) {
        this.id = counter++;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
        this.totalCopies = totalCopies;
        this.borrowed = 0;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
    public int getAvailableCopies() { return totalCopies - borrowed; }

    public boolean isAvailable() {
        return getAvailableCopies() > 0;
    }

    public boolean borrowBook() {
        if (isAvailable()) {
            borrowed++;
            return true;
        }
        return false;
    }

    public void returnBook() {
        if (borrowed > 0) borrowed--;
    }

    @Override
    public String toString() {
        return "[" + id + "] \"" + title + "\" by " + author + " (" + year + ") | ISBN: " + isbn +
                " | Genre: " + genre + " | Available: " + getAvailableCopies() + "/" + totalCopies;
    }
}
