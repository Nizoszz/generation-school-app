package org.generation.school_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.generation.school_app.controller.exception.InternalServerErrorException;
import org.generation.school_app.domain.Student;
import org.generation.school_app.domain.exception.StudentNotFoundException;
import org.generation.school_app.dto.StudentIdDTO;
import org.generation.school_app.dto.StudentRequestDTO;
import org.generation.school_app.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @Operation(summary = "Register a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Register successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters"),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server", content = @Content(schema = @Schema(implementation = InternalServerErrorException.class)))
    })
    @PostMapping
    public ResponseEntity<StudentIdDTO> register(@Valid @RequestBody StudentRequestDTO studentRequestDto, UriComponentsBuilder uriComponentsBuilder){
        StudentIdDTO studentIdDto = this.service.register(studentRequestDto);
        var uri = uriComponentsBuilder.path("/api/students/{id}").buildAndExpand(studentIdDto.studentId()).toUri();
        return ResponseEntity.created(uri).body(studentIdDto);
    }
    @Operation(summary = "Get all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recovery students successfully"),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server", content = @Content(schema = @Schema(implementation = InternalServerErrorException.class)))
    })
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> allStudents = this.service.getAllstudent();
        return ResponseEntity.ok().body(allStudents);
    }
    @Operation(summary = "Get a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recovery student successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content(schema = @Schema(implementation = StudentNotFoundException.class))),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server", content = @Content(schema = @Schema(implementation = InternalServerErrorException.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id){
        Student student = this.service.getStudentById(id);
        return ResponseEntity.ok().body(student);
    }

    @Operation(summary = "Update a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Update successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters"),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content(schema = @Schema(implementation = StudentNotFoundException.class))),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server", content = @Content(schema = @Schema(implementation = InternalServerErrorException.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable("id") Long id, @Valid @RequestBody StudentRequestDTO studentRequestDto){
        Student student = this.service.update(id, studentRequestDto);
        return ResponseEntity.ok().body(student);
    }

    @Operation(summary = "Delete a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content(schema = @Schema(implementation = StudentNotFoundException.class))),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server", content = @Content(schema = @Schema(implementation = InternalServerErrorException.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
