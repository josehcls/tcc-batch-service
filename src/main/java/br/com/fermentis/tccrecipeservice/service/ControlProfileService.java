package br.com.fermentis.tccrecipeservice.service;

import br.com.fermentis.tccrecipeservice.model.dto.ControlProfileDTO;
import br.com.fermentis.tccrecipeservice.model.dto.ControlProfileStepDTO;
import br.com.fermentis.tccrecipeservice.model.entity.ControlProfile;
import br.com.fermentis.tccrecipeservice.model.entity.ControlProfileStep;
import br.com.fermentis.tccrecipeservice.model.repository.ControlProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ControlProfileService {

    @Autowired
    private ControlProfileRepository controlProfileRepository;

    @Transactional(readOnly = true)
    public List<ControlProfileDTO> getControlProfiles() {
        List<ControlProfile> controlProfiles = controlProfileRepository.findAll();
        return controlProfiles.stream().map(ControlProfileDTO::new).collect(toList());
    }

    @Transactional(readOnly = true)
    public ControlProfileDTO findControlProfile(Long controlProfileID) {
        Optional<ControlProfile> controlProfile = getControlProfile(controlProfileID);
        return controlProfile.isPresent() ? new ControlProfileDTO(controlProfile.get()) : null;
    }

    protected Optional<ControlProfile> getControlProfile(long controlProfileId) {
        return controlProfileRepository.findById(controlProfileId);
    }

    @Transactional
    public ControlProfileDTO createControlProfile(ControlProfileDTO controlProfileDTO) {
        // TODO: Validate object (fields and duplicate)
        ControlProfile controlProfile = mapFrom(controlProfileDTO);
        controlProfileRepository.save(controlProfile);
        return new ControlProfileDTO(controlProfile);
    }

    private ControlProfile mapFrom(ControlProfileDTO controlProfileDTO) {
        return ControlProfile.builder()
                .name(controlProfileDTO.getName())
                // TODO: get User
                .createdBy(1L)
                .createdAt(new Date())
                .steps(controlProfileDTO.getSteps().stream().map(this::mapFrom).collect(toList()))
                .build();
    }

    private ControlProfileStep mapFrom(ControlProfileStepDTO step) {
        return ControlProfileStep.builder()
                .variable(step.getVariable())
                .value(step.getValue())
                .timeOffset(step.getTimeOffset())
                .createdAt(new Date())
                .build();
    }

}