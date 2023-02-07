package com.rms.rest.controller;


import com.rms.rest.dto.EmployeeDto;
import com.rms.rest.handler.EmployeeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@Tag(name = "Employees", description = "Rest Api for Employees")
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
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeHandler.addEmployee(employeeDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update employee")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Integer id) {
        return employeeHandler.updateEmployee(employeeDto, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete employee By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return employeeHandler.deleteById(id);
    }

}
