package cl.ecotouch.msauth.controller;

import cl.ecotouch.msauth.dto.HQDto;
import cl.ecotouch.msauth.service.HQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/hq")
@RequiredArgsConstructor
public class HQController {

    private final HQService hqService;
    @CrossOrigin("*")
    @PostMapping("/save-or-update")
    public ResponseEntity<?> saveOrUpdateHQ(@RequestBody HQDto hqDto) {
        hqService.saveOrUpdateHQ(hqDto);
        return ResponseEntity.ok().build();
    }
    @CrossOrigin("*")
    @GetMapping("/{username}")
    public ResponseEntity<?> getHeadQuarters(@PathVariable String username) {
        return ResponseEntity.ok(hqService.getHq(username));
    }
}
