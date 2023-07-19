package cl.ecotouch.msauth.mapper;

import cl.ecotouch.msauth.dto.TrackingRequestDto;
import cl.ecotouch.msauth.dto.TrackingResponseDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TrackingMapper {
    @Insert("INSERT INTO tracking (username, method, url, input, output, timestamp) VALUES (#{username}, #{method}, #{route}, #{input}, #{output}, CURRENT_TIMESTAMP)")
    void postTracking(TrackingRequestDto trackingRequestDto);

    @Select("SELECT * FROM TRACKING")
    List<TrackingResponseDto> getTracking();
}
