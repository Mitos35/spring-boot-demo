package my.code.service;

import lombok.RequiredArgsConstructor;
import my.code.entity.Employee;
import my.code.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee findByFirstName(String firstName) {
        return employeeRepository.findByFirstNameContaining(firstName)
                .orElse(null);
    }
}
