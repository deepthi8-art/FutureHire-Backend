package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.Company;
import com.futurehire.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
@PreAuthorize("hasRole('ADMIN')")
public class AdminCompaniesController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<ApiResponse<Company>> addCompany(@RequestBody Company company) {
        Company created = companyService.saveCompany(company);
        return ResponseEntity.ok(ApiResponse.success("Company created successfully", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Company>>> getCompanies() {
        List<Company> list = companyService.getAllCompanies();
        return ResponseEntity.ok(ApiResponse.success("Companies fetched", list));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> updateCompany(@PathVariable Long id, @RequestBody Company details) {
        Company updated = companyService.updateCompany(id, details);
        return ResponseEntity.ok(ApiResponse.success("Company updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok(ApiResponse.success("Company deleted", "Deleted"));
    }
}
