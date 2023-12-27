package cl.ecotouch.msauth.controller;

import cl.ecotouch.msauth.dto.PlanRequestDto;
import cl.ecotouch.msauth.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping("/planes")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @CrossOrigin("*")
    @PostMapping(value = "/save-plan")
    public ResponseEntity<?> savePlan(@RequestBody PlanRequestDto planRequestDto){
        try{
            planService.savePlan(planRequestDto);
            return ResponseEntity.ok().build();
        }catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
        }
    }

    @CrossOrigin("*")
    @GetMapping(value = "/get-plan")
    public ResponseEntity<?> getPlan(@RequestParam String username){
        try{
            return ResponseEntity.ok(planService.getPlan(username));
        }catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
        }
    }

    @CrossOrigin("*")
    @GetMapping(value = "/get-all-plan")
    public ResponseEntity<?> getAllPlan(){
        try{
            return ResponseEntity.ok(planService.getAllPlan());
        }catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
        }
    }

    @CrossOrigin("*")
    @PutMapping(value = "/update-weight")
    public ResponseEntity<?> updateWeight(@RequestParam String username,@RequestParam Double remainingWeight){
        try{
            planService.updateRemainingWeight(username,remainingWeight);
            return ResponseEntity.ok().build();
        }catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
        }
    }
}
