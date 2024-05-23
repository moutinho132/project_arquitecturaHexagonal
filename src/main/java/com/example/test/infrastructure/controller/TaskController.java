package com.example.test.infrastructure.controller;

import com.example.test.application.TaskManagementService;
import com.example.test.domain.models.Task;
import com.example.test.domain.service.TaskService;
import com.example.test.infrastructure.api.TaskApi;
import com.example.test.infrastructure.mapper.TaskApiMapper;
import com.example.test.infrastructure.request.TaskRequest;
import com.example.test.infrastructure.response.PaginatedResponse;
import com.example.test.infrastructure.response.TaskReponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/task")
public class TaskController implements TaskApi {
    private TaskApiMapper mapper;
    private TaskService service;
    private TaskManagementService managementService;

    @Override
    public TaskReponse save(TaskRequest request, String token) {
        return mapper.modelToResponse(managementService.saveTask(mapper.requestToModel(request), token));
    }

    @Override
    public List<TaskReponse> findAll(Integer page,Integer size, String direction, String attribute, String token) {
        final List<Task> response = managementService.findAll(page, size, direction, attribute);
        return mapper.modelToResponseList(response);
    }

    @Override
    public TaskReponse findById(Integer id, String token) {
        return mapper.modelToResponse(managementService.findById(id, token));
    }

    @Override
    public List<TaskReponse> findByCustomer(Integer id, String token) {
        return mapper.modelToResponseList(managementService.findByCustomer(id, token));
    }

    @Override
    public void deleteById(Integer id) {
        managementService.deleteById(id);
    }

    @Override
    public TaskReponse closeById(Integer id,String token) {
       return mapper.modelToResponse(managementService.updateCompletedTask(id,token));
    }
}
