package com.project.user.service;

import com.project.user.domains.UserRequestDTO;
import com.project.user.exception.UserAlreadyExistsException;
import com.project.user.model.UserModel;
import com.project.user.repository.UserRepository;
import com.project.user.service.impl.UserServiceImpl;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Assertions;
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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;


@TestPropertySource(properties = "validation.password.pattern=^([A-Z]{1})+(.*[a-z])+([0-9]{2})$")
public class UserServiceInterfaceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserServiceImpl userService;

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

        Optional<UserModel> op
                = Optional.empty();

        var userModel = UserModel.builder().name("djdjjdd").password("jfndsjkfds").active(true).build();

        when(userRepository.findByEmail(userRequestDTO.getEmail())).thenReturn(op);
        when(jwtService.generateToken(userModel)).thenReturn("kjdflskajfklsajd");
        when(userRepository.save(userModel)).thenReturn(userModel);

        Assertions.assertNull(this.userService.save(userRequestDTO));
    }

    @Test
    public void ValidaCorreoGuardarUsuarioTest(){
        var userRequestDTO = UserRequestDTO.builder().name("jekjkee")
                .password("dsdsdsds")
                .email("nalvaradov85@gmail.com")
                .phones(new ArrayList<>()).build();



        var userModel = UserModel.builder().name("djdjjdd").password("jfndsjkfds").active(true).build();

        Optional<UserModel> op
                = Optional.of(userModel);

        when(userRepository.findByEmail(userRequestDTO.getEmail())).thenReturn(op);
        Assertions.assertThrows(UserAlreadyExistsException.class, ()-> this.userService.save(userRequestDTO));
    }


}
