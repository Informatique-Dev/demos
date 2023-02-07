package com.rms.rest.controller;


import com.rms.rest.dto.EmployeeDto;
import com.rms.rest.handler.EmployeeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
@Tag(name = "Employee", description = "Rest Api for Employee")
public class EmployeeController {

    private EmployeeHandler employeeHandler;

//    @GetMapping
//    @Operation(summary = "Get All" , description = "this api for get all employees")
//    public ResponseEntity<?> getAll (@PathVariable int page ,@PathVariable int size )
//    {
//        Pageable pageable= (Pageable) PageRequest.of(page , size);
//        List<EmployeeDto> list =employeeHandler.getAll(pageable).getBody();
//        return  ResponseEntity.ok(list);
//    }

    @GetMapping
    @Operation(summary = "Get All" , description = "this api for get all employees")
    public ResponseEntity<?> getAll (@RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                     @RequestParam (value = "size" , defaultValue = "10") Integer size)
    {
        return employeeHandler.getAll(page,size);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get employee by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return employeeHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add new employee")
    public ResponseEntity<?> save(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeeHandler.save(employeeDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update employee")
    public ResponseEntity<?> update(@RequestBody EmployeeDto employeeDto, @PathVariable Integer id) {
        return employeeHandler.update(employeeDto, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete employee By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return employeeHandler.delete(id);
    }

}
