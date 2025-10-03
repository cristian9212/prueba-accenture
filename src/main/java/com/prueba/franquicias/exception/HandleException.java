package com.prueba.franquicias.exception;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class HandleException  {


    @ExceptionHandler(MyHandleException.class)
    public ResponseEntity<Object> myHandleException(MyHandleException ex) {
        log.error("Handled MyHandleException: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("Unhandled exception: {}", ex.getMessage());
        return ResponseEntity.badRequest().body("system exception" + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(Throwable ex) {
        List<String> errors = ((MethodArgumentNotValidException) ex).getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        log.error("Validation errors: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

}
