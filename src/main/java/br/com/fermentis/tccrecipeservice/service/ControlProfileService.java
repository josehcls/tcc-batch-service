package br.com.fermentis.tccrecipeservice.service;

import br.com.fermentis.tccrecipeservice.model.dto.ControlProfileDTO;
import br.com.fermentis.tccrecipeservice.model.dto.ControlProfileStepDTO;
import br.com.fermentis.tccrecipeservice.model.entity.ControlProfile;
import br.com.fermentis.tccrecipeservice.model.entity.ControlProfileStep;
import br.com.fermentis.tccrecipeservice.model.repository.ControlProfileRepository;
import br.com.fermentis.tccrecipeservice.model.repository.ControlProfileStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private ControlProfileStepRepository controlProfileStepRepository;

    @Transactional(readOnly = true)
    public Page<ControlProfileDTO> getControlProfiles(String query, Pageable pageable) {
        query = "%" + query + "%";
        Page<ControlProfile> controlProfiles = controlProfileRepository.getControlProfiles(query, pageable);
        return new PageImpl<>(controlProfiles.getContent().stream().map(ControlProfileDTO::new).collect(toList()), pageable, controlProfiles.getTotalElements());
    }

    protected Optional<ControlProfile> findControlProfile(Long controlProfileID) {
        return controlProfileRepository.findById(controlProfileID);
    }

    @Transactional(readOnly = true)
    public ControlProfileDTO getControlProfile(long controlProfileId) throws Exception {
        ControlProfile controlProfile = findControlProfile(controlProfileId).orElseThrow(() -> new Exception("Control Profile not found"));
        return new ControlProfileDTO(controlProfile);
    }

    @Transactional
    public ControlProfileDTO createControlProfile(ControlProfileDTO controlProfileDTO) {
        // TODO: Validate object (fields and duplicate)
        ControlProfile controlProfile = mapFrom(controlProfileDTO);
        controlProfileRepository.save(controlProfile);
        return new ControlProfileDTO(controlProfile);
    }

    protected ControlProfile mapFrom(ControlProfileDTO controlProfileDTO) {
        return ControlProfile.builder()
                .id(controlProfileDTO.getId())
                .name(controlProfileDTO.getName())
                // TODO: get User
                .createdBy(1L)
                .createdAt(new Date())
                .updatedAt(new Date())
                .steps(controlProfileDTO.getSteps().stream().map(this::mapFrom).collect(toList()))
                .build();
    }

    private ControlProfileStep mapFrom(ControlProfileStepDTO step) {
        return ControlProfileStep.builder()
                .step(step.getStep())
                .variable(step.getVariable())
                .value(step.getValue())
                .timeOffset(step.getTimeOffset())
                .createdAt(new Date())
                .build();
    }

    @Transactional
    public ControlProfileDTO updateControlProfile(Long controlProfileId, ControlProfileDTO controlProfileDTO) throws Exception {
        ControlProfile controlProfile = findControlProfile(controlProfileId).orElseThrow(() -> new Exception("Control Profile not found"));
        // TODO: Validate object (fields and duplicate)
        controlProfile.setName(controlProfileDTO.getName());
        cleanSteps(controlProfile);
        controlProfile.setSteps(controlProfileDTO.getSteps().stream().map(this::mapFrom).collect(toList()));
        controlProfile.setUpdatedAt(new Date());
        controlProfileRepository.save(controlProfile);
        return new ControlProfileDTO(controlProfile);
    }

    @Transactional
    public void deleteControlProfile(Long controlProfileId) throws Exception {
        ControlProfile controlProfile = findControlProfile(controlProfileId).orElseThrow(() -> new Exception("Control Profile not found"));
        controlProfile.setDeletedAt(new Date());
        controlProfile.setUpdatedAt(new Date());
        controlProfileRepository.save(controlProfile);
    }

    private void cleanSteps(ControlProfile controlProfile) {
        List<ControlProfileStep> steps = controlProfile.getSteps();
        steps.forEach(step -> {
            step.setDeletedAt(new Date());
            step.setUpdatedAt(new Date());
        });
        controlProfileStepRepository.saveAll(steps);
    }

}