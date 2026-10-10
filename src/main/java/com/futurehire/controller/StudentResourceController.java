package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.StudyResource;
import com.futurehire.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/resources")
public class StudentResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudyResource>>> getAllResources() {
        List<StudyResource> resources = resourceService.getAllResources();
        return ResponseEntity.ok(ApiResponse.success("Study resources fetched", resources));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudyResource>> getResourceById(@PathVariable Long id) {
        StudyResource res = resourceService.getResourceById(id);
        return ResponseEntity.ok(ApiResponse.success("Resource details fetched", res));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StudyResource>>> searchResources(@RequestParam(required = false) String keyword) {
        List<StudyResource> resources = resourceService.searchResources(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search results fetched", resources));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<StudyResource>>> filterByCategory(@PathVariable String category) {
        List<StudyResource> resources = resourceService.filterByCategory(category);
        return ResponseEntity.ok(ApiResponse.success("Category resources fetched", resources));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ApiResponse<StudyResource>> downloadResource(@PathVariable Long id) {
        StudyResource res = resourceService.incrementDownload(id);
        return ResponseEntity.ok(ApiResponse.success("Download count incremented", res));
    }
}
