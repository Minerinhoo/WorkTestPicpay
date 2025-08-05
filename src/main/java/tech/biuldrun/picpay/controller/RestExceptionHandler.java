package tech.biuldrun.picpay.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.biuldrun.picpay.exception.PicPayException;

import java.util.Objects;

import static java.util.Arrays.stream;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PicPayException.class)
    public ProblemDetail hendlerPicPayException(PicPayException e){
        return e.toProblemDetail();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ProblemDetail hadleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        var fieldErros = e.getFieldErrors()
                .stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        var pb = ProblemDetail.forStatus( HttpStatus.BAD_REQUEST );
        pb.setTitle("your request parameters  didn't validade.");
        pb.setProperty("Invalid Params", fieldErros);

        return null;
    }

        private record InvalidParam(String name, String reason){}
}
