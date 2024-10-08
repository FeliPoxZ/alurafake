package br.com.alura.AluraFake.course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends  JpaRepository<Course, String> {
    Course findCourseByCode (String courseCode);
    String  findCourseNameByCode (String courseCode);
    String findEmailInstrutorByCode (String courseCode);
}
