package integrationtesting.springboottestcontainers;

import integrationtesting.springboottestcontainers.entity.Student;
import integrationtesting.springboottestcontainers.repository.StudentRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class SpringbootTestcontainersApplicationTests {

    @Container
    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void shouldReturnListOfStudents() throws Exception {
        System.out.println(postgreSQLContainer.getDatabaseName());
        System.out.println(postgreSQLContainer.getUsername());
        List<Student> students =
                List.of(Student.builder().firstName("Sai Samyuktha").lastName("Gourishetti").email("sai@gmail.com").build(),
                        Student.builder().firstName("lumos").lastName("practice").email("project@gmail.com").build());
        studentRepository.saveAll(students);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/testing/students"));

        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(students.size())));
    }
}
