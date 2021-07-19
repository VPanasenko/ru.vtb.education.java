package springsecurity.dao;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void securityCheckAdminRoles() throws Exception {
        mockMvc.
                perform(
                        get("/admin")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "manager2", authorities = "READ_ALL_MANAGER")
    public void securityCheckManagerAuthority() throws Exception {
        mockMvc.
                perform(
                        get("/user_info_manager")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = "READ_ALL_USER")
    public void securityCheckUserFailAuthority() throws Exception {
        mockMvc.
                perform(
                        get("/user_info_manager")
                )
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
