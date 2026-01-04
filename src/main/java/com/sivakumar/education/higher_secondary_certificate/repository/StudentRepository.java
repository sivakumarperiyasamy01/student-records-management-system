package com.sivakumar.education.higher_secondary_certificate.repository;


import com.sivakumar.education.higher_secondary_certificate.dto.StudentRecord;
import com.sivakumar.education.higher_secondary_certificate.dto.StudentResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }

    public int saveSchool(String schoolName)
    {
        Integer schoolId = null;
        try
        {
            jdbcTemplate.update("INSERT INTO schools (school_name) VALUES (?)", schoolName);
            schoolId = jdbcTemplate.queryForObject("SELECT school_id FROM schools WHERE school_name = ?", Integer.class, schoolName);
        }
        catch(Exception e)
        {

        }
        return schoolId;
    }


    public void saveStudent(StudentRecord student, int schoolId){

        jdbcTemplate.update(
                " insert into students (register_number,student_Name,date_of_birth,year_of_passing,school_id) values(?,?,?,?,?)",
                student.getRegisterNumber(),
                student.getStudentName(),
                student.getDateOfBirth(),
                student.getYearOfPassing(),
                schoolId
        );
    }


    public int getSubjectId(String subjectName){
        Integer id = jdbcTemplate.queryForObject(
                "select subject_id from subject where subject_Name=?",
                Integer.class, subjectName
        );
        return id;
    }

    public void saveMarks(String registerNumber, int subjectid, int marks){

        jdbcTemplate.update(
                "insert into students_marks (register_Number, subject_id, marks) values(?,?,?)"
        , registerNumber, subjectid, marks);
    }




    public Optional<StudentResponse> fetchStudentRecord(String RegisterNumber) {

        String sql =
                "select register_Number, student_Name, date_of_birth,year_of_passing,school_name from students as s join " +"schools  as sc on s.school_id=sc.school_id where s.register_Number=?";

        List<StudentResponse> student = jdbcTemplate.query(sql, (rs, rownum) -> {

            StudentResponse s1 = new StudentResponse();

            s1.setRegisterNumber(rs.getString("register_Number"));
            s1.setStudentName(rs.getString("student_Name"));
            s1.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
            s1.setYearOfPassing(rs.getInt("year_of_passing"));
            s1.setSchoolName(rs.getString("school_name"));
            return s1;

        }, RegisterNumber);


        if(student.isEmpty()){
            return Optional.empty();
        }


        String StudentMarks="select sc.subject_Name, s.marks from subject as sc join " +
                "students_marks as s on sc.subject_id=s.subject_id where register_Number=?";


        StudentResponse response= student.get(0);

       Map<String, Integer>marks=jdbcTemplate.query(
               StudentMarks,
               rs->{
                   Map<String,Integer>map= new HashMap<>();
                   while(rs.next()){
                       map.put(rs.getString("subject_Name"),rs.getInt("marks"));
                   }
                   return map;
               }
               ,RegisterNumber

       );


            response.setMarks(marks);
            return Optional.of(response);
    }
};


