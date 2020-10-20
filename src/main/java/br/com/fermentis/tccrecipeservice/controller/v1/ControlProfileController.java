package br.com.fermentis.tccrecipeservice.controller.v1;

import br.com.fermentis.tccrecipeservice.model.dto.ControlProfileDTO;
import br.com.fermentis.tccrecipeservice.service.ControlProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/control-profiles")
public class ControlProfileController {

    @Autowired
    private ControlProfileService controlProfileService;

    @GetMapping()
    public ResponseEntity<List<ControlProfileDTO>> getControlProfiles() {
        return ResponseEntity.ok(controlProfileService.getControlProfiles());
    }

    @PostMapping()
    public ResponseEntity<ControlProfileDTO> CrateControlProfiles(@RequestBody ControlProfileDTO controlProfileDTO) {
        return ResponseEntity.ok(controlProfileService.createControlProfile(controlProfileDTO));
    }
}
