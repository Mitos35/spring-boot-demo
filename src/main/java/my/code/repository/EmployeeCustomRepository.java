package my.code.repository;

import my.code.dto.EmployeeFilter;
import my.code.entity.Employee;

import java.util.List;

public interface EmployeeCustomRepository {
    List<Employee> findByFilter(EmployeeFilter filter);
}
