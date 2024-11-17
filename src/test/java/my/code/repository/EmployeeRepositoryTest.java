package my.code.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import my.code.IntegrationTestBase;
import my.code.dto.EmployeeFilter;
import my.code.entity.Employee;
import my.code.projection.EmployeeNameView;
import my.code.projection.EmployeeNativeView;
import my.code.util.QPredicates;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static my.code.entity.QEmployee.employee;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeRepositoryTest extends IntegrationTestBase {

    private static final Integer IVAN_ID = 1;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testFindByID() {
        Optional<Employee> employee = employeeRepository.findById(IVAN_ID);
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstName() {
        Optional<Employee> employee = employeeRepository.findByFirstNameContaining("va");
        assertTrue(employee.isPresent());
    }

    @Test
    void findAllByFirstNameAndSalary() {
        List<Employee> employees = employeeRepository.findAllByFirstNameAndSalary("Ivan", 1000);
        assertThat(employees, hasSize(1));
    }

    @Test
    void testFindBySalary() {
        List<EmployeeNameView> employees = employeeRepository.findAllBySalaryGreaterThan(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindBySalaryNative() {
        List<EmployeeNativeView> employees = employeeRepository.findAllBySalaryGreaterThanNative(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindCustomQuery() {
        EmployeeFilter filter = EmployeeFilter.builder()
                .firstName("ivaN")
                .build();
        List<Employee> customQuery = employeeRepository.findByFilter(filter);
        assertThat(customQuery, hasSize(1));
    }

    @Test
    void testQuerydslPredicate() {
        BooleanExpression predicate = employee.firstName.containsIgnoreCase("ivaN")
                .and(employee.salary.goe(1000));
        Page<Employee> allValues = employeeRepository.findAll(predicate, Pageable.unpaged());
        assertThat(allValues.getContent(), hasSize(1));
    }

    @Test
    void testQPredicate() {
        EmployeeFilter filter = EmployeeFilter.builder()
                .firstName("ivaN")
                .salary(1000)
                .build();
        Predicate predicate = QPredicates.build()
                .add(filter.getFirstName(), employee.firstName::containsIgnoreCase)
                .add(filter.getLastName(), employee.lastName::containsIgnoreCase)
                .add(filter.getSalary(), employee.salary::goe)
                .buildAnd();
        List<Employee> result = (List<Employee>) employeeRepository.findAll(predicate);
//        assertTrue(result.iterator().hasNext());
        assertThat(result, hasSize(1));
    }
}