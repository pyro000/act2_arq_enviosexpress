package com.actividad.uno.exenvios.repository;

import com.actividad.uno.exenvios.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package, String> {
    Optional<Package> findByTrackingNumber(String trackingNumber);
}
