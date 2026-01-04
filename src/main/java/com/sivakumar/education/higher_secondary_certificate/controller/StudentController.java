package com.sivakumar.education.higher_secondary_certificate.controller;

import com.sivakumar.education.higher_secondary_certificate.dto.StudentRecord;
import com.sivakumar.education.higher_secondary_certificate.dto.StudentResponse;
import com.sivakumar.education.higher_secondary_certificate.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/students")
public class StudentController
{
    private final StudentService service;

    public StudentController(StudentService service)
    {
         this.service=service;
    }

    @PostMapping
    public String saveStudentRecord(@RequestBody StudentRecord Record)
    {
        service.saveStudent(Record);
        return "Student Record Saved SuccessFully";
    }

    @GetMapping("/{registrationNumber}")
    public Optional<StudentResponse> getStudentRecord(@PathVariable String registrationNumber){
       return  service.getRecord(registrationNumber);
    }


}
