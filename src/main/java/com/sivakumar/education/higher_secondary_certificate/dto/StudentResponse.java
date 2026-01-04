package com.sivakumar.education.higher_secondary_certificate.dto;

import java.time.LocalDate;
import java.util.Map;

public class StudentResponse {
    private String registerNumber;
    private String studentName;
    private LocalDate dateOfBirth;
    private int yearOfPassing;
    private String schoolName;

    private Map<String, Integer> marks;

    public String getRegisterNumber() {
        return registerNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getYearOfPassing() {
        return yearOfPassing;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setYearOfPassing(int yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public void setMarks(Map<String, Integer> marks) {
        this.marks = marks;
    }
}
