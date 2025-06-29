import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);
        User currentUser = null;

        while (true) {
            System.out.println("\n📘 Library Menu:");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Register");
            System.out.println("0. Exit");
            System.out.print("👉 Enter choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Username: ");
                    String u = sc.nextLine();
                    System.out.print("Password: ");
                    String p = sc.nextLine();
                    if (Admin.login(u, p)) {
                        System.out.println("🛠️ Welcome Admin!");
                        adminMenu(lib, sc);
                    } else {
                        System.out.println("❌ Invalid admin credentials.");
                    }
                }
                case 2 -> {
                    System.out.print("Username: ");
                    String u = sc.nextLine();
                    System.out.print("Password: ");
                    String p = sc.nextLine();
                    currentUser = lib.loginUser(u, p);
                    if (currentUser != null) {
                        userMenu(lib, sc, currentUser);
                    }
                }
                case 3 -> {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Username: ");
                    String username = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();
                    lib.registerUser(name, username, pass);
                }
                case 0 -> {
                    System.out.println("👋 Goodbye!");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void adminMenu(Library lib, Scanner sc) {
        while (true) {
            System.out.println("\n🛠️ Admin Options:");
            System.out.println("1. View all books");
            System.out.println("2. Add book");
            System.out.println("3. Delete book");
            System.out.println("4. View stats");
            System.out.println("0. Logout");
            System.out.print("👉 Enter choice: ");
            int c = sc.nextInt(); sc.nextLine();
            switch (c) {
                case 1 -> lib.viewAllBooks();
                case 2 -> {
                    System.out.print("Title: ");
                    String t = sc.nextLine();
                    System.out.print("Author: ");
                    String a = sc.nextLine();
                    System.out.print("ISBN: ");
                    String i = sc.nextLine();
                    System.out.print("Genre: ");
                    String g = sc.nextLine();
                    System.out.print("Year: ");
                    int y = sc.nextInt();
                    System.out.print("Copies: ");
                    int copy = sc.nextInt(); sc.nextLine();
                    lib.addBook(new Book(t, a, i, g, y, copy));
                }
                case 3 -> {
                    System.out.print("Enter Book ID to delete: ");
                    int id = sc.nextInt();
                    lib.deleteBook(id);
                }
                case 4 -> lib.libraryStats();
                case 0 -> { return; }
                default -> System.out.println("❌ Invalid.");
            }
        }
    }

    private static void userMenu(Library lib, Scanner sc, User user) {
        while (true) {
            System.out.println("\n📚 User Options:");
            System.out.println("1. View all books");
            System.out.println("2. Borrow book");
            System.out.println("3. Return book");
            System.out.println("4. My borrowed books");
            System.out.println("5. Search by title");
            System.out.println("6. Search by author");
            System.out.println("7. Filter by genre");
            System.out.println("0. Logout");
            System.out.print("👉 Enter choice: ");
            int c = sc.nextInt(); sc.nextLine();
            switch (c) {
                case 1 -> lib.viewAllBooks();
                case 2 -> {
                    System.out.print("Enter Book ID to borrow: ");
                    int id = sc.nextInt();
                    lib.borrowBook(id, user);
                }
                case 3 -> {
                    System.out.print("Enter Book ID to return: ");
                    int id = sc.nextInt();
                    lib.returnBook(id, user);
                }
                case 4 -> lib.userBorrowedBooks(user);
                case 5 -> {
                    System.out.print("Title keyword: ");
                    String t = sc.nextLine();
                    lib.searchByTitle(t);
                }
                case 6 -> {
                    System.out.print("Author name: ");
                    String a = sc.nextLine();
                    lib.searchByAuthor(a);
                }
                case 7 -> {
                    System.out.print("Genre: ");
                    String g = sc.nextLine();
                    lib.filterByGenre(g);
                }
                case 0 -> { return; }
                default -> System.out.println("❌ Invalid.");
            }
        }
    }
}
