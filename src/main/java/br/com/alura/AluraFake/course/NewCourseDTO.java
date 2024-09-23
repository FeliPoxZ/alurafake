package br.com.alura.AluraFake.course;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static br.com.alura.AluraFake.course.Status.ACTIVE;



public class NewCourseDTO {


    //code
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z-]+$")
    private String code;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Email
    private String emailInstrutor;

    @NotBlank
    private String description;

    public NewCourseDTO() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailInstrutor() {
        return emailInstrutor;
    }

    public void setEmailInstrutor(String emailInstrutor) {
        this.emailInstrutor = emailInstrutor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course toModel(){
        return new Course(code, name, emailInstrutor, description, ACTIVE);
    }



}
