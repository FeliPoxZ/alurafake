package br.com.alura.AluraFake.registration;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.AluraFake.user.User;
import br.com.alura.AluraFake.util.ErrorItemDTO;
import br.com.alura.AluraFake.course.Course;


import java.util.ArrayList;
import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private RegistrationService registrationService;



    @PostMapping("/registration/new")
    public ResponseEntity<Object> createCourse(@Valid @RequestBody NewRegistrationDTO newRegistration) {
        if (registrationService.getUserByEmail(newRegistration.getStudentEmail()) ==  null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorItemDTO("studentEmail", "Email não cadastrado no  sistema"));
        }
        User student =  registrationService.getUserByEmail(newRegistration.getStudentEmail());
        if (registrationService.getCourseByCode(newRegistration.getCourseCode()) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorItemDTO("courseCode", "Curso não encontrado"));
        }
        Course ChosenCourse = registrationService.getCourseByCode(newRegistration.getCourseCode());
        if (registrationService.validationCourse(ChosenCourse)){
            Registration newEnrollment = new Registration(ChosenCourse, student);
            try {
                System.err.println("entrou no try catch");
                registrationRepository.save(newEnrollment);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                String errorMessage = e.getMessage();
                if (errorMessage.contains("UC_UserCourse")) {                    System.err.println("entro no if do catchhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorItemDTO("email", "O usuário já esta matriculado"));
                }
            }
            
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorItemDTO("code", "Curso desativado"));
    }

    @GetMapping("/registration/report")
    public ResponseEntity<List<RegistrationReportItem>> report() {
        List<RegistrationReportItem> items = new ArrayList<>();
        items = registrationRepository.findCoursesWithTotalRegistrations();
        return ResponseEntity.ok(items);
    }


}
