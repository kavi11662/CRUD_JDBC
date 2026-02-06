package org.example.doa;
import org.example.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.*;
@Repository
public class BookDoa {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void saveBook(Book book, int authorId) {
        String sql = "INSERT INTO books (title, isbn, author_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getIsbn(), authorId);
        System.out.println("Book saved: " + book.getTitle());
    }

    // 🔹 NEW: Fetch all books with authors
    public List<Book> findAllBooks() {
        String sql = "SELECT b.id, b.title, b.isbn, a.id AS author_id, a.name AS author_name " +
                "FROM books b JOIN authors a ON b.author_id = a.id";

        return jdbcTemplate.query(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Author author = new Author(rs.getInt("author_id"), rs.getString("author_name"));
                return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("isbn"), author);
            }
        });
    }

    public List<Book> findBooksByAuthorName(String authorName) {
        String sql = "SELECT b.id, b.title, b.isbn, a.id AS author_id, a.name AS author_name " +
                "FROM books b JOIN authors a ON b.author_id = a.id " +
                "WHERE a.name = ?";

        return jdbcTemplate.query(sql, new Object[]{authorName}, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Author author = new Author(rs.getInt("author_id"), rs.getString("author_name"));
                return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("isbn"), author);
            }
        });
    }

    public void deleteBookById(int bookId) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, bookId);
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author(rs.getInt("author_id"), rs.getString("author_name"));
            return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("isbn"), author);
        }
    }
}
