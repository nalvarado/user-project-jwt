package com.project.user.service;

import com.project.user.domains.UserRequestDTO;
import com.project.user.repository.UserRepository;
import com.project.user.service.impl.UserServiceImpl;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.when;


@TestPropertySource(properties = "validation.password.pattern=^([A-Z]{1})+(.*[a-z])+([0-9]{2})$")
public class UserServiceInterfaceTest {


    @Mock
    private UserRepository userRepository;

    // -------------------------------------------------------------------
    // -- Setup ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Setup.
     */
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdTest (){
        var uuid = new UUID(0,16);
        when(this.userRepository.findById(uuid)).thenReturn(null);
    }

    @Test
    public void GuardarUsuarioTest(){
        var userRequestDTO = UserRequestDTO.builder().name("jekjkee")
                .password("dsdsdsds")
                .email("nalvaradov85@gmail.com")
                .phones(new ArrayList<>()).build();
        when(userRepository.findByEmail(userRequestDTO.getEmail())).thenReturn(null);

    }


}
