package br.com.fermentis.tccrecipeservice.controller.v1;

import br.com.fermentis.tccrecipeservice.model.dto.ControlProfileDTO;
import br.com.fermentis.tccrecipeservice.service.ControlProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/control-profiles")
public class ControlProfileController {

    @Autowired
    private ControlProfileService controlProfileService;

    @GetMapping()
    public ResponseEntity<Page<ControlProfileDTO>> getControlProfiles(@RequestParam(value = "query", required = false, defaultValue = "") String query,
                                                      Pageable pageable) {
        return ResponseEntity.ok(controlProfileService.getControlProfiles(query, pageable));
    }

    @PostMapping()
    public ResponseEntity<ControlProfileDTO> CrateControlProfiles(@RequestBody ControlProfileDTO controlProfileDTO) {
        return ResponseEntity.ok(controlProfileService.createControlProfile(controlProfileDTO));
    }
}
