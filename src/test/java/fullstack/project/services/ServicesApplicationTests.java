package fullstack.project.services;

import fullstack.project.services.strings.routes.Routes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ServicesApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "default.default@gmail.com")
    @Test
    void shouldReturnAListOf30StudentFromTheDefaultAuthenticatedTutor() throws Exception {
        mockMvc.perform(get(Routes.STUDENT_BASE_URL).accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(jsonPath("$.length()").value(30));
    }
}
