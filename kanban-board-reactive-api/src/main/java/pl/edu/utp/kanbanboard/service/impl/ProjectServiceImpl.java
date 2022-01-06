package pl.edu.utp.kanbanboard.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.utp.kanbanboard.model.Project;
import pl.edu.utp.kanbanboard.repository.ProjectRepository;
import pl.edu.utp.kanbanboard.service.ProjectService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) { this.projectRepository = projectRepository; }

    @Override
    public Flux<Project> all() { return this.projectRepository.findAll(); }

    @Override
    public Mono<Project> get(String id) { return this.projectRepository.findById(id); }

    @Override
    public Mono<Project> create(Project newProject) {
        return Mono.just(newProject)
                .map(project -> {
                    project.setProjectId(UUID.randomUUID().toString());
                    project.setCreateDateTime(LocalDateTime.now());
                    project.setUpdateDateTime(LocalDateTime.now());
                    return project;
                })
                .flatMap(projectRepository::save);
    }

    @Override
    public Mono<Project> update(String id, Project updateProject) {
        return this.projectRepository
                .findById(id)
                .doOnNext(project -> {
                    project.setName(updateProject.getName());
                    project.setDescription(updateProject.getDescription());
                    project.setSubmitDateTime(updateProject.getSubmitDateTime());
                    project.setUpdateDateTime(LocalDateTime.now());
                })
                .flatMap(this.projectRepository::save);
    }

    @Override
    public Mono<Project> delete(String id) {
        return this.projectRepository
                .findById(id)
                .flatMap(s -> this.projectRepository.deleteById(s.getProjectId()).thenReturn(s));
    }
}
