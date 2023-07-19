package cl.ecotouch.msauth.controller;

import cl.ecotouch.msauth.dto.TrackingRequestDto;
import cl.ecotouch.msauth.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/v1/tracking")
public class TrackingController {

    private final TrackingService trackingService;

    @CrossOrigin("*")
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
    @CrossOrigin("*")
    @GetMapping(value="/get-all")
    public ResponseEntity<?> getTracking(){
        try{
            return ResponseEntity.ok(trackingService.getAllTracking());
        }catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
        }
    }
}
