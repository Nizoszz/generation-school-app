package org.generation.school_app.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record StudentRequestDTO(
        @NotNull(message = "Name is mandatory")
        String fullName,
        @NotNull(message = "Age is mandatory")
        int age,
        @NotNull(message = "First semester note is mandatory")
        BigDecimal firstSemesterNote,
        @NotNull(message = "Second semester note is mandatory")
        BigDecimal secondSemesterNote,
        @NotNull(message = "Teacher name is mandatory")
        String teacherName,
        @NotNull(message = "Classroom identifier is mandatory")
        String classroomIdentifier
) {
}
