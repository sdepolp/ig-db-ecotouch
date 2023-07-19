package cl.ecotouch.msauth.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PlanRequestDto {
    String username;
    Integer idPlan;
    Date submitDate;
    Date finishDate;
    Integer active;
    Double remainingWeight;
}
