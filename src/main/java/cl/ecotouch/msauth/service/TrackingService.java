package cl.ecotouch.msauth.service;

import cl.ecotouch.msauth.dto.TrackingRequestDto;
import cl.ecotouch.msauth.dto.TrackingResponseDto;

import java.util.List;


public interface TrackingService {
    void postTracking(TrackingRequestDto trackingRequestDto);

    List<TrackingResponseDto> getAllTracking();
}
