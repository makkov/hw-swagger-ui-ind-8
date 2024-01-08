package com.example.hwswaggeruiind8.dto;

public class AggregateResponseDto {

    private FacultyDto faculty;

    private StudentDto student;

    public FacultyDto getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDto faculty) {
        this.faculty = faculty;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AggregateResponseDto)) return false;

        AggregateResponseDto that = (AggregateResponseDto) o;

        if (getFaculty() != null ? !getFaculty().equals(that.getFaculty()) : that.getFaculty() != null) return false;
        return getStudent() != null ? getStudent().equals(that.getStudent()) : that.getStudent() == null;
    }

    @Override
    public int hashCode() {
        int result = getFaculty() != null ? getFaculty().hashCode() : 0;
        result = 31 * result + (getStudent() != null ? getStudent().hashCode() : 0);
        return result;
    }
}
