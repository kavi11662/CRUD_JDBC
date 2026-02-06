package org.example.Service;

import org.example.Model.*;
import org.example.doa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LibraryService {

    @Autowired
    private AuthorDoa authorDao;

    @Autowired
    private BookDoa bookDao;

    public void addBook(Book book) {
        int authorId = authorDao.saveAuthor(book.getAuthor());
        bookDao.saveBook(book, authorId);
    }

    public List<Book> getAllBooks() {
        return bookDao.findAllBooks();
    }

    public List<Book> getBooksByAuthor(String authorName) {
        return bookDao.findBooksByAuthorName(authorName);
    }

    public void deleteBook(int bookId) {
        bookDao.deleteBookById(bookId);
    }
}
