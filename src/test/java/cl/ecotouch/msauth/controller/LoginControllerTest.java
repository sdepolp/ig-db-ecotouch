package cl.ecotouch.msauth.controller;

import cl.ecotouch.msauth.dto.LoginRequestDto;
import cl.ecotouch.msauth.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginController loginController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void validateUser_OK() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("username", "password");
        when(userService.validateUser(loginRequestDto)).thenReturn(any());
        ResponseEntity<?> responseEntity = loginController.validateUser(loginRequestDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(userService, times(1)).validateUser(loginRequestDto);
    }

    @Test
    void validateUser_HttpStatusCodeException() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("username", "password");
        when(userService.validateUser(loginRequestDto)).thenThrow(new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {
            @Override
            public String getResponseBodyAsString() {
                return "Bad request";
            }
        });
        ResponseEntity<?> responseEntity = loginController.validateUser(loginRequestDto);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Bad request", responseEntity.getBody());
        verify(userService, times(1)).validateUser(loginRequestDto);
    }

}
