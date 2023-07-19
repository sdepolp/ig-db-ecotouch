package cl.ecotouch.msauth.service.impl;

import cl.ecotouch.msauth.dto.HQDto;
import cl.ecotouch.msauth.mapper.HQMapper;
import cl.ecotouch.msauth.service.HQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HQServiceImpl implements HQService {

    private final HQMapper hqMapper;

    @Override
    public HQDto getHq(String username) {
        return hqMapper.findByUsername(username);
    }

    @Override
    public void saveOrUpdateHQ(HQDto hqDto) {
        HQDto hqEntity = hqMapper.findByUsername(hqDto.getUsername());

        if (hqEntity == null) {
            hqMapper.save(hqDto);
        } else {
            hqEntity.setLat(hqDto.getLat());
            hqEntity.setLng(hqDto.getLng());
            hqMapper.update(hqEntity);
        }
    }
}
