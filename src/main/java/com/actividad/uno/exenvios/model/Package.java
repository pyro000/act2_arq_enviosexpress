package com.actividad.uno.exenvios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "packages")
public class Package {

    @Id
    @Column(name = "trackingNumber", nullable = false, unique = true, length = 20)
    private String trackingNumber;

    @Column(length = 50, nullable = false)
    private String senderName;

    @Column(length = 50, nullable = false)
    private String receiverName;

    @Column(length = 50, nullable = false)
    private String origin;

    @Column(length = 50, nullable = false)
    private String destination;

    private double weight;

    @Column(length = 50, nullable = false)
    private String dimensions;

    @Column(length = 50, nullable = false)
    private String status;

    @Column(length = 50, nullable = false)
    private String currentLocation;

    @Temporal(TemporalType.DATE)
    private Date estimatedDeliveryDate;

    @OneToMany(mappedBy = "pkg", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrackingEvent> history;
}
