package com.actividad.uno.exenvios.services;

import com.actividad.uno.exenvios.model.GetTrackingStatusRequest;
import com.actividad.uno.exenvios.model.GetTrackingStatusResponse;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface TrackingService {

    @WebMethod
    GetTrackingStatusResponse getTrackingStatus(GetTrackingStatusRequest request);
}
