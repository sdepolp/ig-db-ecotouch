package cl.ecotouch.msauth.service;

import cl.ecotouch.msauth.dto.TrackingRequestDto;


public interface TrackingService {
    void postTracking(TrackingRequestDto trackingRequestDto);
}
