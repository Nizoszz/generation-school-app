package org.generation.school_app.services;

import org.generation.school_app.domain.Student;
import org.generation.school_app.domain.exception.StudentNotFoundException;
import org.generation.school_app.dto.StudentIdDTO;
import org.generation.school_app.dto.StudentRequestDTO;
import org.generation.school_app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public StudentIdDTO register(StudentRequestDTO studentRequestDto){
        Student studentSave = this.repository.save(Student.create(studentRequestDto));
        return new StudentIdDTO(studentSave.getStudentId());
    }

    public List<Student> getAllstudent(){
        return this.repository.findAll();
    }

    public Student getStudentById(Long id){
        return this.repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    public Student update(Long id, StudentRequestDTO studentRequestDto){
        Student studentExists = this.repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        studentExists.setFullName(studentRequestDto.fullName());
        studentExists.setAge(studentRequestDto.age());
        studentExists.setFirstSemesterNote(studentRequestDto.firstSemesterNote());
        studentExists.setSecondSemesterNote(studentRequestDto.secondSemesterNote());
        studentExists.setTeacherName(studentRequestDto.teacherName());
        studentExists.setClassroomIdentifier(studentRequestDto.classroomIdentifier());
        return this.repository.save(studentExists);
    }

    public void delete(Long id){
        Student studentExists = this.repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        this.repository.deleteById(id);
    }
}
