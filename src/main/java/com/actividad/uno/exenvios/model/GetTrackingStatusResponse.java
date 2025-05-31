package com.actividad.uno.exenvios.model;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;

@XmlRootElement(name = "GetTrackingStatusResponse")
public class GetTrackingStatusResponse {
    private String status;
    private String currentLocation;
    private Date estimatedDeliveryDate;
    private List<TrackingEvent> history;

    // Getters y Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }

    public Date getEstimatedDeliveryDate() { return estimatedDeliveryDate; }
    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) { this.estimatedDeliveryDate = estimatedDeliveryDate; }

    public List<TrackingEvent> getHistory() { return history; }
    public void setHistory(List<TrackingEvent> history) { this.history = history; }
}
