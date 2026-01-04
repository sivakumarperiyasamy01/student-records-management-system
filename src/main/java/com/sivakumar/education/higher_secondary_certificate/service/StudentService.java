package com.sivakumar.education.higher_secondary_certificate.service;

import com.sivakumar.education.higher_secondary_certificate.dto.StudentRecord;
import com.sivakumar.education.higher_secondary_certificate.dto.StudentResponse;
import com.sivakumar.education.higher_secondary_certificate.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;


    public StudentService(StudentRepository repository)
    {
        this.repository = repository;
    }


    public void saveStudent(StudentRecord record){

        int schoolId = repository.saveSchool(record.getSchoolName());

        repository.saveStudent(record, schoolId );

        record.getMarks().forEach((subject,marks)->{
                    int subjectId=repository.getSubjectId(subject);
                    repository.saveMarks(record.getRegisterNumber(),subjectId,marks);
        });
    }


    public Optional<StudentResponse> getRecord(String Registernumber){
            return repository.fetchStudentRecord(Registernumber);
    };




}
