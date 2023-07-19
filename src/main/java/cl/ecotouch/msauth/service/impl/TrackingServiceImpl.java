package cl.ecotouch.msauth.service.impl;

import cl.ecotouch.msauth.dto.TrackingRequestDto;
import cl.ecotouch.msauth.dto.TrackingResponseDto;
import cl.ecotouch.msauth.mapper.TrackingMapper;
import cl.ecotouch.msauth.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrackingServiceImpl implements TrackingService {

    private final TrackingMapper trackingMapper;
    @Override
    public void postTracking(TrackingRequestDto trackingRequestDto) {
        trackingMapper.postTracking(trackingRequestDto);
    }

    @Override
    public List<TrackingResponseDto> getAllTracking() {
        return trackingMapper.getTracking();
    }
}
