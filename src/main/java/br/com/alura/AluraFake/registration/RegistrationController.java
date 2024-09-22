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
                .body(new ErrorItemDTO("email", "Email não cadastrado no  sistema"));
        }
        User student =  registrationService.getUserByEmail(newRegistration.getStudentEmail());
        if (registrationService.getCourseByCode(newRegistration.getCourseCode()) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorItemDTO("code", "Curso não encontrado"));
        }
        Course ChosenCourse = registrationService.getCourseByCode(newRegistration.getCourseCode());
        if (registrationService.validationCourse(ChosenCourse)){
            Registration newEnrollment = new Registration(ChosenCourse, student);
            registrationRepository.save(newEnrollment);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorItemDTO("code", "Curso invalido"));
    }

    @GetMapping("/registration/report")
    public ResponseEntity<List<RegistrationReportItem>> report() {
        List<RegistrationReportItem> items = new ArrayList<>();

        //Questão 4 aqui

        //Dados fakes que devem ser rescrevidos
        items.add(new RegistrationReportItem(
                "Java para iniciantes",
                "java",
                "Caio Bugorin",
                "caio.bugorin@alura.com.br",
                10L
                )
        );

        items.add(new RegistrationReportItem(
                        "Spring para iniciantes",
                        "spring",
                        "Caio Bugorin",
                        "caio.bugorin@alura.com.br",
                        9L
                )
        );

        items.add(new RegistrationReportItem(
                        "Maven para avançados",
                        "mavem",
                        "Caio Bugorin",
                        "caio.bugorin@alura.com.br",
                        9L
                )
        );

        return ResponseEntity.ok(items);
    }


}
