package cl.ecotouch.msauth.mapper;

import cl.ecotouch.msauth.dto.LoginRequestDto;
import cl.ecotouch.msauth.dto.TrackingRequestDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrackingMapper {
    @Insert("INSERT INTO tracking (username, method, url, input, output, timestamp) VALUES (#{username}, #{method}, #{route}, #{input}, #{output}, CURRENT_TIMESTAMP)")
    void postTracking(TrackingRequestDto trackingRequestDto);
}
