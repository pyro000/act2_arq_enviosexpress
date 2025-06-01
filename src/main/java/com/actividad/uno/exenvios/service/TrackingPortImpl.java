package com.actividad.uno.exenvios.service;

import com.actividad.uno.exenvios.repository.PackageRepository;
import com.actividad.uno.exenvios.model.Package;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Importa las clases generadas por WSDL.
// Asegúrate de que las importaciones de estas clases sean de 'com.envios_express.tracking.*'
import com.envios_express.tracking.GetTrackingStatusRequest;
import com.envios_express.tracking.GetTrackingStatusResponse;
import com.envios_express.tracking.TrackingPort;
import com.envios_express.tracking.TrackingError;
import com.envios_express.tracking.TrackingErrorFault;
import com.envios_express.tracking.TrackingErrorFaultMessage; // <<-- ¡Importa esta clase!

import java.time.ZoneId;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@WebService(serviceName = "TrackingService",
        portName = "TrackingPortSoap11",
        targetNamespace = "http://www.envios-express.com/tracking",
        endpointInterface = "com.envios_express.tracking.TrackingPort")
public class TrackingPortImpl implements TrackingPort {

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public GetTrackingStatusResponse getTrackingStatus(GetTrackingStatusRequest parameters) throws TrackingErrorFaultMessage {
        String trackingNumber = parameters.getTrackingNumber();

        if (trackingNumber == null || trackingNumber.trim().isEmpty()) {
            TrackingError error = new TrackingError();
            error.setErrorCode(400);
            error.setErrorMessage("Tracking number cannot be empty.");
            error.setInvalidField("trackingNumber");
            TrackingErrorFault faultInfo = new TrackingErrorFault(); // Este es el objeto que contiene el error
            faultInfo.setError(error);
            // ¡Lanza la excepción correcta!
            throw new TrackingErrorFaultMessage("Invalid Request: Tracking number is empty", faultInfo);
        }

        Optional<Package> packageOptional = packageRepository.findByTrackingNumber(trackingNumber);

        if (packageOptional.isEmpty()) {
            TrackingError error = new TrackingError();
            error.setErrorCode(404);
            error.setErrorMessage("Package with tracking number " + trackingNumber + " not found.");
            error.setInvalidField("trackingNumber");
            TrackingErrorFault faultInfo = new TrackingErrorFault(); // Este es el objeto que contiene el error
            faultInfo.setError(error);
            // ¡Lanza la excepción correcta!
            throw new TrackingErrorFaultMessage("Package not found", faultInfo);
        }

        Package foundPackage = packageOptional.get();
        GetTrackingStatusResponse response = new GetTrackingStatusResponse();
        response.setStatus(foundPackage.getStatus());
        response.setCurrentLocation(foundPackage.getCurrentLocation());

        if (foundPackage.getEstimatedDeliveryDate() != null) {
            try {
                GregorianCalendar gcal = GregorianCalendar.from(foundPackage.getEstimatedDeliveryDate().atStartOfDay(ZoneId.systemDefault()));
                XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
                response.setEstimatedDeliveryDate(xmlGregorianCalendar);
            } catch (Exception e) {
                System.err.println("Error converting date: " + e.getMessage());
                // En un entorno de producción, aquí podrías loguear el error de forma más robusta
                // y decidir si esto es un error fatal o si simplemente la fecha no se incluye.
            }
        }

        List<com.actividad.uno.exenvios.model.TrackingEvent> jpaHistoryEvents = foundPackage.getHistory();

        List<com.envios_express.tracking.TrackingEvent> soapHistoryEvents = jpaHistoryEvents.stream()
                .map(event -> {
                    // Este TrackingEvent es el generado por el WSDL
                    com.envios_express.tracking.TrackingEvent soapEvent = new com.envios_express.tracking.TrackingEvent();
                    try {
                        GregorianCalendar gcal = GregorianCalendar.from(event.getDate().atZone(ZoneId.systemDefault()));
                        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
                        soapEvent.setDate(xmlGregorianCalendar);
                    } catch (Exception e) {
                        System.err.println("Error converting event date: " + e.getMessage());
                        // Similar al caso anterior, manejar el error o ignorar el evento específico.
                    }
                    soapEvent.setDescription(event.getDescription());
                    soapEvent.setLocation(event.getLocation());
                    return soapEvent;
                })
                .collect(Collectors.toList());
        response.getHistory().addAll(soapHistoryEvents);

        return response;
    }
}