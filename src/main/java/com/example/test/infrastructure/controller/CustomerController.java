package com.example.test.infrastructure.controller;

import com.example.test.application.CustomerManagementService;
import com.example.test.domain.models.Customer;
import com.example.test.domain.models.Person;
import com.example.test.domain.service.CustomerService;
import com.example.test.infrastructure.api.CustomerApi;
import com.example.test.infrastructure.mapper.CustomerApiMapper;
import com.example.test.infrastructure.request.CustomerRequest;
import com.example.test.infrastructure.response.CustomerResponse;
import com.example.test.infrastructure.response.PaginatedResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController implements CustomerApi {
    private CustomerApiMapper mapper;
    private CustomerService service;
    private CustomerManagementService managementService;

    @Override
    public CustomerResponse save(final CustomerRequest request, final String token) {
        return mapper.modelToResponse(managementService.saveCustomer(mapper.requestToModel(request),token));
    }

   /* @Override
    public PaginatedResponse<CustomerResponse> findAll(final Integer page, final Integer size, final String direction, final String attribute) {
        final List<Customer> response = managementService.findAll(page, size, direction, attribute);
        return PaginatedResponse.<CustomerResponse>builder()
                .total(response.size())
                .page(page)
                .size(response.size())
                .items(mapper.modelsToResponseList(response))
                .build();
    }*/


    @Override
    public List<CustomerResponse> findAll(final Integer page, final Integer size, final String direction, final String attribute) {
        final List<Customer> response = managementService.findAll(page, size, direction, attribute);
        return mapper.modelsToResponseList(response);
    }
}
