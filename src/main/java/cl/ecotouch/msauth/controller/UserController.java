package cl.ecotouch.msauth.controller;

import cl.ecotouch.msauth.dto.UsersDto;
import cl.ecotouch.msauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    @CrossOrigin("*")
    @GetMapping("/get-users")
    public ResponseEntity<?> getUsers(){
        try{
            return ResponseEntity.ok(userService.getUsers());
        }catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
        }
    }
    @CrossOrigin("*")
    @GetMapping("/get-user-info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token){
        try{
            return ResponseEntity.ok(userService.getUserById(token));
        }catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
        }
    }

    @CrossOrigin("*")
    @PostMapping
    public ResponseEntity<Void> insertUsuario(@RequestBody UsersDto usuario) {
        userService.insertUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin("*")
    @PutMapping("/{username}")
    public ResponseEntity<Void> updateUsuario(@PathVariable String username, @RequestBody UsersDto usuario) {
        usuario.setUsername(username);
        userService.updateUsuario(usuario);
        return ResponseEntity.noContent().build();
    }
    @CrossOrigin("*")
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String username) {
        userService.deleteUsuario(username);
        return ResponseEntity.noContent().build();
    }
}
