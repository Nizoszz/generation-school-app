package org.generation.school_app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.school_app.dto.StudentRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "tab_students")
@Table(name = "tab_students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, length = 2)
    private int age;
    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal firstSemesterNote;
    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal secondSemesterNote;
    @Column(nullable = false, length = 50)
    private String teacherName;
    @Column(nullable = false, length = 4)
    private String classroomIdentifier;
    private LocalDateTime createdAt;

    public Student(String fullName, int age, BigDecimal firstSemesterNote, BigDecimal secondSemesterNote, String teacherName, String classroomIdentifier) {
        this.fullName = fullName;
        this.age = age;
        this.firstSemesterNote = firstSemesterNote;
        this.secondSemesterNote = secondSemesterNote;
        this.teacherName = teacherName;
        this.classroomIdentifier = classroomIdentifier;
        this.createdAt = LocalDateTime.now();

    }

    public static Student create(StudentRequestDTO studentRequestDto){
        return new Student(studentRequestDto.fullName(), studentRequestDto.age(), studentRequestDto.firstSemesterNote(), studentRequestDto.secondSemesterNote(), studentRequestDto.teacherName(), studentRequestDto.classroomIdentifier());
    }
}
