package br.com.alura.AluraFake.course;

import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Course")
public class Course {

    //ciração do codigo com id
    @Id
    @Pattern(regexp = "^[a-zA-Z-]+$", message = "O código do curso deve ser textual, sem espaços, nem caracteres numéricos e nem caracteres especiais, mas pode ser separado por hífen (-)")
    private String code;

    //criação do nome
    private String name;
    private String emailInstrutor;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Nullable
    private LocalDateTime dateInactivation = null;

    @Deprecated
    public Course() {}

    public Course (String code, String name,  String emailInstrutor, String description, Status status) {
        this.code = code;
        this.name = name;
        this.emailInstrutor = emailInstrutor;
        this.description = description;
        this.status = status;
        this.dateInactivation = null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getEmailInstrutor() {
        return emailInstrutor;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDateInactivation() {
        return dateInactivation;
    }

    


}
