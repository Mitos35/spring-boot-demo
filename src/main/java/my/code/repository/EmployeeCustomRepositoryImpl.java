package my.code.repository;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import my.code.dto.EmployeeFilter;
import my.code.entity.Employee;

import java.util.List;

import static my.code.entity.QEmployee.employee;

@RequiredArgsConstructor
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<Employee> findByFilter(EmployeeFilter filter) {
        return new JPAQuery<Employee>(entityManager)
                .select(employee)
                .from(employee)
                .where(employee.firstName.containsIgnoreCase(filter.getFirstName()))
                .fetch();
    }
}
