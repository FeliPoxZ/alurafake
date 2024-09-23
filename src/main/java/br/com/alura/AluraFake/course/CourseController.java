package br.com.alura.AluraFake.course;

import jakarta.validation.Valid;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.AluraFake.user.Role;
import br.com.alura.AluraFake.user.User;
import br.com.alura.AluraFake.user.UserRepository;
import br.com.alura.AluraFake.util.ErrorItemDTO;






@RestController
public class CourseController {

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final UserRepository userRepository;

    public  CourseController(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }
   
    @PostMapping("/course/new")
    public ResponseEntity<Object> createCourse(@Valid @RequestBody NewCourseDTO newCourse) {
        String userEmail = newCourse.getEmailInstrutor();
        User instructor = userRepository.findByEmail(userEmail);

        if (instructor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorItemDTO("emailInstrutor", "Usuario não encontrado"));
        }else{
            if (instructor.getRole() == Role.INSTRUCTOR){
                Course course = newCourse.toModel();
                courseRepository.save(course);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorItemDTO("emailInstrutor", "Usuario nao tem permissão  para criar curso"));
            }
        }

    }

    @PostMapping("/course/{code}/inactive")
    public ResponseEntity<Object> createCourse(@PathVariable("code") String courseCode) {
        // Questão 2 aqui
        Course choseCourse = courseRepository.findCourseByCode(courseCode);
        if (choseCourse == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorItemDTO("code", "curso não encotrado"));
        }else{
            choseCourse.setDateInactivation(LocalDateTime.now());
            choseCourse.setStatus(Status.INACTIVE);
            courseRepository.save(choseCourse);
            return ResponseEntity.ok().build();
        }
    }

}
