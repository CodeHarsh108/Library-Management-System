import java.util.*;

public class User {
    private String name;
    private String username;
    private String password;
    private List<Book> borrowedBooks = new ArrayList<>();

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public boolean login(String inputPass) {
        return password.equals(inputPass);
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getName() {
        return name;
    }
}
