package cl.ecotouch.msauth.mapper;

import cl.ecotouch.msauth.dto.HQDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HQMapper {

    @Select("SELECT * FROM driverhq WHERE username = #{username}")
    HQDto findByUsername(String username);

    @Insert("INSERT INTO driverhq(username, lat, lng) VALUES(#{username}, #{lat}, #{lng})")
    void save(HQDto HQDto);

    @Update("UPDATE driverhq SET lat = #{lat}, lng = #{lng} WHERE username = #{username}")
    void update(HQDto HQDto);
}
