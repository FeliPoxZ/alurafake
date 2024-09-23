package br.com.alura.AluraFake.registration;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    @Query("SELECT new br.com.alura.AluraFake.registration.RegistrationReportItem(c.name, c.code, u.name, c.emailInstrutor, COUNT(r)) " +
           "FROM Registration r " +
           "JOIN r.courseID c " +
           "JOIN r.userID u " +
           "GROUP BY c.name, c.code, u.name, c.emailInstrutor " +
           "ORDER BY COUNT(r) DESC")
    List<RegistrationReportItem> findCoursesWithTotalRegistrations();

}
