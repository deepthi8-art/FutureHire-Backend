package com.futurehire.service;

import com.futurehire.entity.StudyResource;
import com.futurehire.exception.ResourceNotFoundException;
import com.futurehire.repository.StudyResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private StudyResourceRepository resourceRepository;

    public List<StudyResource> getAllResources() {
        return resourceRepository.findAll();
    }

    public StudyResource getResourceById(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Study resource not found with id: " + id));
    }

    public List<StudyResource> filterByCategory(String category) {
        if (category == null || category.trim().isEmpty() || category.equalsIgnoreCase("All")) {
            return getAllResources();
        }
        return resourceRepository.findByCategoryContainingIgnoreCase(category);
    }

    public List<StudyResource> searchResources(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllResources();
        }
        return resourceRepository.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(keyword, keyword);
    }

    public StudyResource incrementDownload(Long id) {
        StudyResource resource = getResourceById(id);
        resource.setDownloads(resource.getDownloads() + 1);
        return resourceRepository.save(resource);
    }

    public StudyResource createResource(StudyResource resource) {
        return resourceRepository.save(resource);
    }

    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}
