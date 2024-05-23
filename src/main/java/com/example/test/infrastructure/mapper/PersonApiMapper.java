package com.example.test.infrastructure.mapper;

import com.example.test.domain.models.Person;
import com.example.test.infrastructure.request.PersonRequest;
import com.example.test.infrastructure.response.PersonResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface PersonApiMapper {
    Person requestToModel(PersonRequest request);

    List<Person> requestToModelList(List<PersonRequest> list);

    PersonResponse modelToResponse(Person model);

    List<PersonResponse> modelsToResponseList(List<Person> list);
}
