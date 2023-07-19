package cl.ecotouch.msauth.service;

import cl.ecotouch.msauth.dto.PlanRequestDto;

import java.util.Optional;

public interface PlanService {
    void savePlan(PlanRequestDto planRequestDto);
    Optional<PlanRequestDto> getPlan(String username);
    void updateRemainingWeight(String username, Double remainingWeight);
}
