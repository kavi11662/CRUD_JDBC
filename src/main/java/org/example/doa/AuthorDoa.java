package org.example.doa;
import org.example.Model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class AuthorDoa {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveAuthor(Author author) {
        String sql = "INSERT INTO authors (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, author.getName());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue(); // return generated id
        }
}
