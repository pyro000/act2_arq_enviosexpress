package com.actividad.uno.exenvios.repository;

import com.actividad.uno.exenvios.model.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingEventRepository extends JpaRepository<TrackingEvent, Long> {
}
