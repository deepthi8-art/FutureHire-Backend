package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.Company;
import com.futurehire.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/companies")
public class StudentCompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Company>>> getCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(ApiResponse.success("Companies fetched successfully", companies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        return ResponseEntity.ok(ApiResponse.success("Company details fetched", company));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Company>>> searchCompanies(@RequestParam(required = false) String name) {
        List<Company> companies = companyService.searchCompanies(name);
        return ResponseEntity.ok(ApiResponse.success("Search results fetched", companies));
    }
}
