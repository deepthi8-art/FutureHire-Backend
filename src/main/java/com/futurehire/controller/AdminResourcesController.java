package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.StudyResource;
import com.futurehire.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/resources")
@PreAuthorize("hasRole('ADMIN')")
public class AdminResourcesController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping
    public ResponseEntity<ApiResponse<StudyResource>> addResource(@RequestBody StudyResource resource) {
        StudyResource created = resourceService.createResource(resource);
        return ResponseEntity.ok(ApiResponse.success("Study resource created", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudyResource>>> getResources() {
        List<StudyResource> list = resourceService.getAllResources();
        return ResponseEntity.ok(ApiResponse.success("Resources fetched", list));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
        return ResponseEntity.ok(ApiResponse.success("Study resource deleted", "Deleted"));
    }
}
