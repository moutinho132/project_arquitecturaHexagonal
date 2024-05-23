package com.example.test.infrastructure.api;

import com.example.test.infrastructure.request.TaskRequest;
import com.example.test.infrastructure.response.PaginatedResponse;
import com.example.test.infrastructure.response.TaskReponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TaskApi {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    TaskReponse save(@RequestBody @Valid TaskRequest request, @RequestHeader(value = "Authorization") String token);

    /*@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    PaginatedResponse<TaskReponse> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                           @RequestParam(name = "size", defaultValue = "10") Integer size,
                                           @RequestParam(name = "sort-direction", defaultValue = "DESC") String direction,
                                           @RequestParam(name = "sort-attribute", defaultValue = "id") String attribute,
                                           @RequestHeader(value = "Authorization") String token
    );*/

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    List<TaskReponse> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                           @RequestParam(name = "size", defaultValue = "10") Integer size,
                                           @RequestParam(name = "sort-direction", defaultValue = "DESC") String direction,
                                           @RequestParam(name = "sort-attribute", defaultValue = "id") String attribute,
                                           @RequestHeader(value = "Authorization") String token
    );

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    TaskReponse findById(@PathVariable("id") Integer id,@RequestHeader(value="Authorization") String token);

    @GetMapping(path = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    List<TaskReponse> findByCustomer(@PathVariable("id") Integer id, @RequestHeader(value="Authorization") String token);

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    void deleteById(@PathVariable("id") Integer id);

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    TaskReponse closeById(@PathVariable("id") Integer id,@RequestHeader(value="Authorization") String token);

}
