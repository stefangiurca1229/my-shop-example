package com.myshopexample.responses;

public class GenericResponse<GenericObject> {
    private String message;
    private GenericObject body;

    public GenericResponse() {
    }

    public GenericResponse(String message, GenericObject body) {
        this.message = message;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GenericObject getBody() {
        return body;
    }

    public void setBody(GenericObject body) {
        this.body = body;
    }
}
