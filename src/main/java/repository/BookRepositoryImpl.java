package repository;

import model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public Book findById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, Book.class);
    }

    @Override
    public void save(Book book) {
        String sql = "INSERT INTO books(title, author, published_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPublishedDate());
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, published_date = ? WHERE id = ?";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPublishedDate(), book.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setPublishedDate(resultSet.getDate("published_date").toLocalDate());
            return book;
        }
    }
}
