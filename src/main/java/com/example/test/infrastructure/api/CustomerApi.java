package com.example.test.infrastructure.api;

import com.example.test.infrastructure.request.CustomerRequest;
import com.example.test.infrastructure.response.CustomerResponse;
import com.example.test.infrastructure.response.PaginatedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CustomerApi {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    CustomerResponse save(@RequestBody CustomerRequest request, @RequestHeader(value="Authorization") String token);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    List<CustomerResponse> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                @RequestParam(name = "sort-direction", defaultValue = "ASC") String direction,
                                                @RequestParam(name = "sort-attribute", defaultValue = "id") String attribute
    );
}
