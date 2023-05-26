package cl.ecotouch.msauth.controller;

import cl.ecotouch.msauth.dto.LoginRequestDto;
import cl.ecotouch.msauth.dto.TrackingRequestDto;
import cl.ecotouch.msauth.service.TrackingService;
import cl.ecotouch.msauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/v1/tracking")
public class TrackingController {

    private final TrackingService trackingService;
    @PostMapping(value = "/")
    public ResponseEntity<?> trackActivity(@RequestBody TrackingRequestDto trackingRequestDto){
        try{
            trackingService.postTracking(trackingRequestDto);
            return ResponseEntity.ok().build();
        }catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
        }
    }
}
