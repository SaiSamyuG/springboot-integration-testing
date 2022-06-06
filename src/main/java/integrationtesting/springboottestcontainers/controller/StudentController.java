package integrationtesting.springboottestcontainers.controller;

import integrationtesting.springboottestcontainers.entity.Student;
import integrationtesting.springboottestcontainers.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testing")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
}
