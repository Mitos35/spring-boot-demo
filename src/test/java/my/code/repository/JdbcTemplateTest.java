package my.code.repository;

import my.code.IntegrationTestBase;
import my.code.entity.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcTemplateTest extends IntegrationTestBase {
    private static final String INSERT_SQL = """
            INSERT INTO company (name)
            VALUES (?);
            """;
    private static final String DELETE_RETURNING_SQL = """
            DELETE FROM company WHERE name = ? RETURNING *;
            """;

    @Autowired
    private JdbcOperations jdbcOperations;

    @Test
    void testInsert() {
        int result = jdbcOperations.update(INSERT_SQL, "Microsoft");
        assertEquals(1, result);
    }

    @Test
    void testDelete() {
        String companyName = "Microsoft";
        jdbcOperations.update(INSERT_SQL, companyName);

        List<Company> result = jdbcOperations.query(DELETE_RETURNING_SQL, (rs, rowNum) -> Company.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build(), companyName);

        assertThat(result, hasSize(1));
    }
}
