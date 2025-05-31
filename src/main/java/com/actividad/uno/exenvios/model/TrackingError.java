package com.actividad.uno.exenvios.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TrackingError")
public class TrackingError {
    private int errorCode;
    private String errorMessage;
    private String invalidField;

    // Getters y Setters
    public int getErrorCode() { return errorCode; }
    public void setErrorCode(int errorCode) { this.errorCode = errorCode; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public String getInvalidField() { return invalidField; }
    public void setInvalidField(String invalidField) { this.invalidField = invalidField; }
}
