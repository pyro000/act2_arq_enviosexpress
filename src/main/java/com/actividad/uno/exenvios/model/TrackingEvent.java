package com.actividad.uno.exenvios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tracking_events")
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(length = 200, nullable = false)
    private String description;

    @Column(length = 100, nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "package_tracking_number", nullable = false)
    private Package pkg;
}
