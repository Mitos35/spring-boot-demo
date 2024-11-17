package my.code.controller;

import lombok.RequiredArgsConstructor;
import my.code.entity.Employee;
import my.code.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("name")
    public Employee getEmployeeFirstName(@RequestParam String firstName) {
        return employeeService.findByFirstName(firstName);
    }
}
