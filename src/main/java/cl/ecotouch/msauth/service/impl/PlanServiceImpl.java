package cl.ecotouch.msauth.service.impl;

import cl.ecotouch.msauth.dto.PlanRequestDto;
import cl.ecotouch.msauth.mapper.PlanMapper;
import cl.ecotouch.msauth.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanMapper planMapper;

    @Override
    public void savePlan(PlanRequestDto planRequestDto) {
        Date submitDate = new Date();
        planRequestDto.setSubmitDate(submitDate);
        int idPlan = planRequestDto.getIdPlan();
        Date finishDate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(submitDate);
        switch (idPlan) {
            case 1:
                calendar.add(Calendar.MONTH, 1);
                break;
            case 2:
                calendar.add(Calendar.MONTH, 3);
                break;
            case 3:
                calendar.add(Calendar.YEAR, 1);
                break;
            default:
                break;
        }
        finishDate = calendar.getTime();
        planRequestDto.setFinishDate(finishDate);
        planRequestDto.setActive(1);
        planRequestDto.setRemainingWeight(50.0);
        planMapper.savePlan(planRequestDto);
    }

    @Override
    public Optional<PlanRequestDto> getPlan(String username) {
        Optional<PlanRequestDto> response = planMapper.getPlan(username).stream().findFirst();
        return response;
    }

    @Override
    public void updateRemainingWeight(String username, Double remainingWeight) {
        planMapper.updateRemainingWeight(username,remainingWeight);
    }
}
