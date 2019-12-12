package com.devs.travels.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.devs.travels.databuilder.builder.dto.TokenInfoDTOBuilder;
import com.devs.travels.domain.dto.client.TokenInfoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devs.travels.databuilder.builder.UserBuilder;
import com.devs.travels.domain.User;
import com.devs.travels.exception.ConflictException;
import com.devs.travels.repository.UserRepository;
import com.devs.travels.service.client.TokenGeneratorClient;
import static com.devs.travels.util.Constants.DEFAULT_ID;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private User user;

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder encoder;
    
    @Mock
    private TokenGeneratorClient tokenGenerator;

    @BeforeEach
    void setUp() {
        user = new UserBuilder().build();
    }

    @Test
    void shouldCreateUserWhenUserValid() {
        when(repository.existsByEmailOrCpf(user.getEmail(), user.getCpf())).thenReturn(Boolean.FALSE);

        User userCreated = new UserBuilder().buildMock();
        when(repository.save(user)).thenReturn(userCreated);

        User employee = service.createEmployee(user);

        assertNotNull(employee);
        assertTrue(employee.isActive());
        assertEquals(DEFAULT_ID, employee.getId());
        assertEquals(userCreated, user);
    }


    @Test
    void shouldExceptionWhenUserInvalid() {
    	 when(repository.existsByEmailOrCpf(user.getEmail(), user.getCpf())).thenReturn(Boolean.TRUE);
         
    	 assertThrows(ConflictException.class, () -> service.createEmployee(user), 
    			 UserService.THIS_EMAIL_OR_CPF_IS_ALREADY_USED );
    }


    @Test
    void shouldGetInfoWhenActiveUser() {
        String token = "KFJGD";
        TokenInfoDTO infoDTO = configGetInfoWhenActiveUser(token);

        TokenInfoDTO DTO = service.activeUser(token);

        assertGetInfoWhenActiveUser(infoDTO, DTO);
    }

    private TokenInfoDTO configGetInfoWhenActiveUser(String token) {
        TokenInfoDTO infoDTO = new TokenInfoDTOBuilder().build();
        when(tokenGenerator.getInformation(token)).thenReturn(infoDTO);
        return infoDTO;
    }

    private void assertGetInfoWhenActiveUser(TokenInfoDTO infoDTO, TokenInfoDTO DTO) {
        assertNotNull(DTO);
        assertEquals(infoDTO.getId(), DTO.getId());
        assertEquals(infoDTO.getMessage(), DTO.getMessage());
        assertEquals(infoDTO.getValue(), DTO.getValue());
    }

}
