package com.actividad.uno.exenvios.config;

import com.actividad.uno.exenvios.service.TrackingPortImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint trackingEndpoint(Bus bus, TrackingPortImpl trackingService) {
        EndpointImpl endpoint = new EndpointImpl(bus, trackingService);
        endpoint.publish("/tracking"); // URL donde se publicará el servicio SOAP
        return endpoint;
    }
}