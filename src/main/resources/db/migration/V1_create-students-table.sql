CREATE TABLE IF NOT EXISTS 'tab_students'(
    student_id SERIAL AUTO_INCREMENT PRIMARY KEY NOT NULL,
    fullName VARCHAR(50) NOT NULL,
    age int NOT NULL,
    first_semester_note NUMERIC(4, 2) NOT NULL,
    second_semester_note NUMERIC(4, 2) NOT NULL,
    teacher_name VARCHAR(50) NOT NULL,
    classroom_identifier VARCHAR(4) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);