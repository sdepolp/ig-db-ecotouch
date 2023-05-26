package cl.ecotouch.msauth.controller;

import cl.ecotouch.msauth.dto.LoginRequestDto;
import cl.ecotouch.msauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/login")
public class LoginController {

    private final UserService userService;
    @CrossOrigin("*")
    @PostMapping("")
    public ResponseEntity<?> validateUser(@RequestBody LoginRequestDto loginRequestDto){
        try{
            return ResponseEntity.ok(userService.validateUser(loginRequestDto));
        }catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
        }
    }
}
