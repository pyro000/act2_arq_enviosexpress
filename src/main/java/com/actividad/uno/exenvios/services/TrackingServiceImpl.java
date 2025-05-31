package com.actividad.uno.exenvios.services;

import com.actividad.uno.exenvios.model.*;
import com.actividad.uno.exenvios.model.Package;
import com.actividad.uno.exenvios.repository.PackageRepository;

import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@WebService(endpointInterface = "com.actividad.uno.exenvios.services.TrackingService")
public class TrackingServiceImpl implements TrackingService {

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public GetTrackingStatusResponse getTrackingStatus(GetTrackingStatusRequest request) {
        Optional<Package> opt = packageRepository.findByTrackingNumber(request.getTrackingNumber());
        if (opt.isEmpty()) {
            throw new RuntimeException("Tracking number not found: " + request.getTrackingNumber());
        }

        Package pkg = opt.get();

        GetTrackingStatusResponse response = new GetTrackingStatusResponse();
        response.setStatus(pkg.getStatus());
        response.setCurrentLocation(pkg.getCurrentLocation());
        response.setEstimatedDeliveryDate(pkg.getEstimatedDeliveryDate());
        response.setHistory(pkg.getHistory());

        return response;
    }
}
