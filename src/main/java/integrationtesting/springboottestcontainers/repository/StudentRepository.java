package integrationtesting.springboottestcontainers.repository;

import integrationtesting.springboottestcontainers.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
