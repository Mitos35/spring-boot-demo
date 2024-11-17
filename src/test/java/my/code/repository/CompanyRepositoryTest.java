package my.code.repository;

import my.code.IntegrationTestBase;
import my.code.entity.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CompanyRepositoryTest extends IntegrationTestBase {

    private static final Integer APPLE_ID = 1;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        Optional<Company> companyOptional = companyRepository.findById(APPLE_ID);
        assertTrue(companyOptional.isPresent());
        companyOptional.ifPresent(entity ->
                assertEquals("Apple", entity.getName()));
    }

    @Test
    void save() {
        Company company = Company.builder()
                .name("Fitbit").build();

        Company companySave = companyRepository.save(company);

        assertNotNull(companySave.getId());
    }

}