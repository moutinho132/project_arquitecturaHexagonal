package com.example.test.infrastructure.mapper;

import com.example.test.domain.models.Task;
import com.example.test.infrastructure.request.TaskRequest;
import com.example.test.infrastructure.response.TaskReponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                PersonApiMapper.class,
                UserApiMapper.class,
        }

)
public interface TaskApiMapper {
        TaskReponse modelToResponse(final Task model);

        Task requestToModel(final TaskRequest request);

        List<TaskReponse> modelToResponseList(final List<Task> tasks);
}
