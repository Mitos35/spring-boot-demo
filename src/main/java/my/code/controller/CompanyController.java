package my.code.controller;

import lombok.RequiredArgsConstructor;
import my.code.entity.Company;
import my.code.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/{id}")
    public Company getCompanyByUid(@PathVariable Integer id) {
        return companyService.findCompanyById(id);
    }
}
