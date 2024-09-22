package br.com.alura.AluraFake.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.AluraFake.course.Course;
import br.com.alura.AluraFake.course.CourseRepository;
import br.com.alura.AluraFake.course.Status;
import br.com.alura.AluraFake.user.User;
import br.com.alura.AluraFake.user.UserRepository;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public User getUserByEmail(String studentEmail) {
        User student = userRepository.findByEmail(studentEmail);
        return student;
    }

    public Course getCourseByCode(String code){
        Course course = courseRepository.findCourseByCode(code);
        return course;
    }

    public boolean validationCourse(Course chosenCourse){
        if(chosenCourse.getStatus() == Status.ACTIVE){
            return true;
        }else{
            return false;
        }
    }
}
