package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/reports")
@PreAuthorize("hasRole('ADMIN')")
public class AdminReportsController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/overview")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getOverviewReports() {
        Map<String, Object> report = reportService.getAdminOverviewReports();
        return ResponseEntity.ok(ApiResponse.success("Admin reports overview fetched", report));
    }
}
