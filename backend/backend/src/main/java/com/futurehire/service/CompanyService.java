package com.futurehire.service;

import com.futurehire.entity.Company;
import com.futurehire.exception.ResourceNotFoundException;
import com.futurehire.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
    }

    public List<Company> searchCompanies(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAllCompanies();
        }
        return companyRepository.findByNameContainingIgnoreCase(name);
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company details) {
        Company comp = getCompanyById(id);
        comp.setName(details.getName());
        comp.setTier(details.getTier());
        comp.setCtcRange(details.getCtcRange());
        comp.setLocation(details.getLocation());
        comp.setDescription(details.getDescription());
        comp.setMinCgpa(details.getMinCgpa());
        comp.setRequiredSkills(details.getRequiredSkills());
        if (details.getLogoUrl() != null) comp.setLogoUrl(details.getLogoUrl());
        return companyRepository.save(comp);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
