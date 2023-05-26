package cl.ecotouch.msauth.service.impl;

import cl.ecotouch.msauth.dto.TrackingRequestDto;
import cl.ecotouch.msauth.mapper.TrackingMapper;
import cl.ecotouch.msauth.mapper.UserMapper;
import cl.ecotouch.msauth.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrackingServiceImpl implements TrackingService {

    private final TrackingMapper trackingMapper;
    @Override
    public void postTracking(TrackingRequestDto trackingRequestDto) {
        trackingMapper.postTracking(trackingRequestDto);
    }
}
