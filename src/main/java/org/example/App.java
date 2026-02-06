package org.example;

import org.example.Service.LibraryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.example.Config.*;
import org.example.Model.*;
import java.util.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        LibraryService libraryService = context.getBean(LibraryService.class);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== 📚 Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Books by Author");
            System.out.println("4. Delete Book by ID");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 :
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String authorName = scanner.nextLine();

                    Author author = new Author(authorName);
                    Book book = new Book(title, isbn, author);
                    libraryService.addBook(book);
                    System.out.println("✅ Book added successfully!");
                    break;
                case 2 :
                    System.out.println("\n📚 All Books:");
                    List<Book> books = libraryService.getAllBooks();
                    books.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter author name: ");
                    String searchAuthor = scanner.nextLine();
                    List<Book> books1= libraryService.getBooksByAuthor(searchAuthor);
                    if (books1.isEmpty()) {
                        System.out.println("⚠️ No books found for this author.");
                    } else {
                        System.out.println("\n📖 Books by " + searchAuthor + ":");
                        books1.forEach(System.out::println);
                    }
                    break;
                case 4 :
                    System.out.print("Enter book ID to delete: ");
                    int bookId = scanner.nextInt();
                    libraryService.deleteBook(bookId);
                    System.out.println("🗑️ Book deleted successfully!");
                     break;
                case 5 :
                    running = false;
                    System.out.println("👋 Exiting Library System...");
                    break;
                default :System.out.println("⚠️ Invalid choice, try again.");
            }
        }

        scanner.close();
    }
}
