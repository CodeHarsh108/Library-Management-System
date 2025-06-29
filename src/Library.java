import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public Library() {
        seedBooks();
    }

    private void seedBooks() {
        books.add(new Book("1984", "George Orwell", "9780451524935", "Dystopian", 1949, 5));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", "Classic", 1960, 4));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Classic", 1925, 3));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "9780547928227", "Fantasy", 1937, 6));
        books.add(new Book("Harry Potter", "J.K. Rowling", "9780439554930", "Fantasy", 1997, 7));
        books.add(new Book("Sapiens", "Yuval Noah Harari", "9780062316097", "History", 2011, 5));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "9780316769488", "Classic", 1951, 4));
        books.add(new Book("The Alchemist", "Paulo Coelho", "9780061122415", "Fiction", 1988, 4));
        books.add(new Book("Atomic Habits", "James Clear", "9780735211292", "Self-help", 2018, 6));
        books.add(new Book("Clean Code", "Robert C. Martin", "9780132350884", "Programming", 2008, 2));
        books.add(new Book("Rich Dad Poor Dad", "Robert Kiyosaki", "9781612681139", "Finance", 1997, 3));
        books.add(new Book("Thinking, Fast and Slow", "Daniel Kahneman", "9780374533557", "Psychology", 2011, 4));
        books.add(new Book("Inferno", "Dan Brown", "9781400079155", "Thriller", 2013, 5));
        books.add(new Book("Steve Jobs", "Walter Isaacson", "9781451648546", "Biography", 2011, 3));
        books.add(new Book("A Brief History of Time", "Stephen Hawking", "9780553380163", "Science", 1988, 2));
    }

    public void registerUser(String name, String username, String password) {
        users.add(new User(name, username, password));
        System.out.println("✅ User registered successfully.");
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.login(password)) {
                System.out.println("✅ Login successful. Welcome, " + user.getName() + "!");
                return user;
            }
        }
        System.out.println("❌ Invalid credentials.");
        return null;
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("✅ Book added: " + book);
    }

    public void deleteBook(int id) {
        books.removeIf(book -> book.getId() == id);
        System.out.println("✅ Book deleted.");
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("⚠️ No books found.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void searchByTitle(String title) {
        books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .forEach(System.out::println);
    }

    public void searchByAuthor(String author) {
        books.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .forEach(System.out::println);
    }

    public void filterByGenre(String genre) {
        books.stream()
                .filter(b -> b.getGenre().equalsIgnoreCase(genre))
                .forEach(System.out::println);
    }

    public void borrowBook(int bookId, User user) {
        for (Book book : books) {
            if (book.getId() == bookId && book.borrowBook()) {
                user.borrowBook(book);
                System.out.println("✅ Book borrowed successfully.");
                return;
            }
        }
        System.out.println("❌ Book unavailable.");
    }

    public void returnBook(int bookId, User user) {
        for (Book book : user.getBorrowedBooks()) {
            if (book.getId() == bookId) {
                book.returnBook();
                user.returnBook(book);
                System.out.println("✅ Book returned.");
                return;
            }
        }
        System.out.println("❌ You haven't borrowed this book.");
    }

    public void userBorrowedBooks(User user) {
        List<Book> borrowed = user.getBorrowedBooks();
        if (borrowed.isEmpty()) {
            System.out.println("📭 You haven't borrowed any books.");
        } else {
            System.out.println("📚 Your borrowed books:");
            borrowed.forEach(System.out::println);
        }
    }

    public void libraryStats() {
        long available = books.stream().filter(Book::isAvailable).count();
        System.out.println("📈 Total books: " + books.size());
        System.out.println("✅ Available: " + available);
        System.out.println("🚫 Borrowed: " + (books.size() - available));
    }
}
