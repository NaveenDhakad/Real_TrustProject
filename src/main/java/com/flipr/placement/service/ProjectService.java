package com.flipr.placement.service;

import com.flipr.placement.model.Project;
import com.flipr.placement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            Project existingProject = project.get();
            existingProject.setName(projectDetails.getName());
            existingProject.setDescription(projectDetails.getDescription());
            if (projectDetails.getImageUrl() != null) {
                existingProject.setImageUrl(projectDetails.getImageUrl());
            }
            if (projectDetails.getImageData() != null) {
                existingProject.setImageData(projectDetails.getImageData());
            }
            return projectRepository.save(existingProject);
        }
        return null;
    }
}
