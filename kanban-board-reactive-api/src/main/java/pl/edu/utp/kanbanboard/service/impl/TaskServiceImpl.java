package pl.edu.utp.kanbanboard.service.impl;

import org.springframework.stereotype.Repository;
import pl.edu.utp.kanbanboard.model.Task;
import pl.edu.utp.kanbanboard.repository.TaskRepository;
import pl.edu.utp.kanbanboard.service.TaskService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository projectRepository) { this.taskRepository = projectRepository; }

    @Override
    public Flux<Task> all() { return this.taskRepository.findAll(); }

    @Override
    public Mono<Task> get(String id) { return this.taskRepository.findById(id); }

    @Override
    public Mono<Task> create(Task newTask) {
        return Mono.just(newTask)
                .map(task -> {
                    task.setTaskId(UUID.randomUUID().toString());
                    return task;
                })
                .flatMap(taskRepository::save);
    }

    @Override
    public Mono<Task> update(String id, Task updateTask) {
        return this.taskRepository
                .findById(id)
                .doOnNext(task -> {
                    task.setName(updateTask.getName());
                    task.setDescription(updateTask.getDescription());
                    task.setOrder(updateTask.getOrder());
                    task.setDeliveryDateTime(updateTask.getDeliveryDateTime());
                })
                .flatMap(this.taskRepository::save);
    }

    @Override
    public Mono<Task> delete(String id) {
        return this.taskRepository
                .findById(id)
                .flatMap(s -> this.taskRepository.deleteById(s.getTaskId()).thenReturn(s));
    }
}