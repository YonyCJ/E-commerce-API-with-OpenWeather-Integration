package com.prapp.pt_crud_ecommerce.excepcion;

public class ModelNotFoundException extends RuntimeException{

    public ModelNotFoundException(String message) {
        super(message);
    }

}
