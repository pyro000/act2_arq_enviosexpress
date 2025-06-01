package com.actividad.uno.exenvios.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "packages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String trackingNumber;
    // --- FIN CAMBIOS ---

    private String senderName;
    private String receiverName;
    private String origin;
    private String destination;
    private double weight;
    private String dimensions; // Formato "largo x ancho x alto"
    private String status;
    private String currentLocation;
    private LocalDate estimatedDeliveryDate;

    @OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<TrackingEvent> history = new ArrayList<>();

    public void addTrackingEvent(TrackingEvent event) {
        history.add(event);
        event.setPackageEntity(this);
    }

    public void removeTrackingEvent(TrackingEvent event) {
        history.remove(event);
        event.setPackageEntity(null);
    }
}