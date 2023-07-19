package cl.ecotouch.msauth.mapper;

import cl.ecotouch.msauth.dto.PlanRequestDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PlanMapper {

    @Insert("INSERT INTO subscription (username, idplan, submitdate, finishdate, active, remainingWeight) VALUES (#{username}, #{idPlan}, #{submitDate}, #{finishDate}, #{active}, #{remainingWeight})")
    void savePlan(PlanRequestDto planRequestDto);

    @Select("SELECT * FROM subscription WHERE username = #{username}")
    List<PlanRequestDto> getPlan(String username);

    @Update("UPDATE SUBSCRIPTION set remainingWeight = #{remainingWeight} where username = #{username}")
    void updateRemainingWeight(String username, Double remainingWeight);
}
