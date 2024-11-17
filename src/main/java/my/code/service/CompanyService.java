package my.code.service;

import lombok.RequiredArgsConstructor;
import my.code.entity.Company;
import my.code.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company findCompanyById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }
}
