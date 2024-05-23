package com.example.test.domain.mapper;

import com.example.test.domain.entities.CustomerEntity;
import com.example.test.domain.models.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                PersonMapper.class,
        }
)
public interface CustomerMapper {

    Customer entityToModel(CustomerEntity entity);

    List<Customer> entitiesToModelList(List<CustomerEntity> list);

    CustomerEntity modelToEntity(Customer model);

    List<CustomerEntity> modelsToEntityList(List<Customer> list);


}
