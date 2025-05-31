package com.actividad.uno.exenvios.config;

import com.actividad.uno.exenvios.services.TrackingServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Bean
    public Endpoint endpoint(Bus bus, TrackingServiceImpl trackingService) {
        EndpointImpl endpoint = new EndpointImpl(bus, trackingService);
        endpoint.publish("/tracking");
        return endpoint;
    }
}
