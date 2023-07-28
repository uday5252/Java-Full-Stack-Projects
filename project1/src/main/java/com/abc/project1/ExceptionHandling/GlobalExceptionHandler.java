package com.abc.project1.ExceptionHandling;

import com.abc.project1.ExceptionHandling.CustomException.InvalidIdException;
import com.abc.project1.ExceptionHandling.CustomException.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static String getCauseAsString(Throwable ex){
        return ex.getCause() == null ? null:ex.getCause().toString();
    }

    /*TODO
        cvi.getPropertyPath();  // get the property causing NotNull exception
        cvi.getMessage();       // message describing exception
        cvi.getRootBeanClass(); // class having that prop
    */
    public static String getConstraintsViolationsAsString(Set<ConstraintViolation<?>> constraintViolationSet){
        Iterator<?> it = constraintViolationSet.iterator();
        Set<String> violationMessages = new HashSet<>();

        while(it.hasNext()){
            ConstraintViolationImpl<?> cvi = (ConstraintViolationImpl<?>) it.next();

            String violationMessage = " Property `" + cvi.getPropertyPath() +
                    "` -> " + cvi.getMessage() +
                    ". In RootBeanClass " + cvi.getRootBeanClass();

            violationMessages.add(violationMessage);
        }
        return violationMessages.toString();
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponse> customGlobalExceptionHandler(Exception ex, HttpServletRequest request){
        String cause = GlobalExceptionHandler.getCauseAsString(ex);
        String requestedUri = request.getRequestURI();
        if(ex instanceof HttpMessageNotReadableException) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            new ExceptionResponse("HttpMessageNotReadableException occurred...", requestedUri, cause)
                    );
        }else if(ex instanceof ValidationException){
            if(ex instanceof ConstraintViolationException){
                Set<ConstraintViolation<?>> constraintViolationSet = ((ConstraintViolationException) ex).getConstraintViolations();
                String constraintsViolationsMessage = GlobalExceptionHandler.getConstraintsViolationsAsString(constraintViolationSet);
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(
                                new ExceptionResponse("ConstraintViolationException occurred: "+constraintsViolationsMessage, requestedUri, cause)
                        );
            }else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(
                                new ExceptionResponse("ValidationException occurred...", requestedUri, cause)
                        );
            }
        }
        else if(ex instanceof DataIntegrityViolationException){
            //TODO:- cause of this exception--> ex.getMostSpecificException()
            cause = ((DataIntegrityViolationException) ex).getMostSpecificCause().toString();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            new ExceptionResponse("DataIntegrityViolationException occurred.", requestedUri, cause)
                    );
        }
        else if(ex instanceof InvalidIdException){
            System.out.println("sssss");
            int id = ((InvalidIdException) ex).getId();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            new ExceptionResponse("InvalidIdException occurred: entered id "+id+" is invalid. please note that id can only be positive.", requestedUri, cause)
                    );
        }else if(ex instanceof ResourceNotFoundException){
            int id = ((ResourceNotFoundException) ex).getId();
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ExceptionResponse("ResourceNotFoundException occurred: requested resource doesn't exist with id "+id, requestedUri, cause)
                    );
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            new ExceptionResponse("unknown exception occurred: "+ex.getMessage(), requestedUri, cause)
                    );
        }
    }
}
