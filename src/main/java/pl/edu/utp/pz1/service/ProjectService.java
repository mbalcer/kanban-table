package pl.edu.utp.pz1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.edu.utp.pz1.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<Project> findById(Integer projectId);

    List<Project> findAll();

    Page<Project> getProjects(Pageable pageable);

    Project create(Project project);

    Project update(Integer id, Project updatedProject);

    void delete(Integer id);
}
