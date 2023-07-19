package cl.ecotouch.msauth.service;

import cl.ecotouch.msauth.dto.HQDto;

public interface HQService {
    HQDto getHq(String username);

    void saveOrUpdateHQ(HQDto hqDto);
}
