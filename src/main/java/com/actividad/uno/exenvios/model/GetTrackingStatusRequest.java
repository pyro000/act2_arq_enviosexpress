package com.actividad.uno.exenvios.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetTrackingStatusRequest")
public class GetTrackingStatusRequest {
    private String trackingNumber;

    // Getters y Setters
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
