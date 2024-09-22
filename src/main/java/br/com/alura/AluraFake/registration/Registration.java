package br.com.alura.AluraFake.registration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import br.com.alura.AluraFake.course.Course;
import br.com.alura.AluraFake.user.User;

@Entity
@Table(name = "Registration")
public class Registration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateRegistration = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "courseID")
    private Course courseID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User userID;
    
    @Deprecated
    public Registration() {}

    public Registration(Course  courseID, User userID) {
        this.courseID =  courseID;
        this.userID = userID;
    }


    public LocalDateTime getDateRegistration() {
        return dateRegistration;
    }

    public Course getCourseID() {
        return courseID;
    }

    public User getUserID() {
        return userID;
    }

    

}
