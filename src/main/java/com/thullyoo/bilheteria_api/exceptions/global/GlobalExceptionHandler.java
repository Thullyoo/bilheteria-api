package com.thullyoo.bilheteria_api.exceptions.global;

import com.thullyoo.bilheteria_api.exceptions.movie.MovieDeleteException;
import com.thullyoo.bilheteria_api.exceptions.movie.MovieDescriptionException;
import com.thullyoo.bilheteria_api.exceptions.movie.MovieIsPresentException;
import com.thullyoo.bilheteria_api.exceptions.movie.MovieNotFoundException;
import com.thullyoo.bilheteria_api.exceptions.session.SessionIsPresentException;
import com.thullyoo.bilheteria_api.exceptions.session.SessionNotFoundException;
import com.thullyoo.bilheteria_api.exceptions.session.SessionRelatedMovieException;
import com.thullyoo.bilheteria_api.exceptions.ticket.ChairOccupiedException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieIsPresentException.class)
    public ResponseEntity<ExceptionResponse> movieIsPresentException(MovieIsPresentException e){
        List<Details> details = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), details));
    }
    @ExceptionHandler(MovieDeleteException.class)
    public ResponseEntity<ExceptionResponse> movieDeleteException(MovieDeleteException e){
        List<Details> details = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, e.getMessage(), details));
    }

    @ExceptionHandler(MovieDescriptionException.class)
    public ResponseEntity<ExceptionResponse> movieDescriptionException(MovieDescriptionException e){
        List<Details> details = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.LENGTH_REQUIRED)
                .body(new ExceptionResponse(HttpStatus.LENGTH_REQUIRED.value(), HttpStatus.LENGTH_REQUIRED, e.getMessage(), details));
    }
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ExceptionResponse> movieNotFoundException(MovieNotFoundException e){
        List<Details> details = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), details));
    }
    @ExceptionHandler(SessionIsPresentException.class)
    public ResponseEntity<ExceptionResponse> sessionIsPresentException(SessionIsPresentException e){
        List<Details> details = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), details));
    }
    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ExceptionResponse> sessionNotFoundException(SessionNotFoundException e){
        List<Details> details = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), details));
    }
    @ExceptionHandler(SessionRelatedMovieException.class)
    public ResponseEntity<ExceptionResponse> sessionRelatedMovieException(SessionRelatedMovieException e){
        List<Details> details = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ExceptionResponse(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, e.getMessage(), details));
    }
    @ExceptionHandler(ChairOccupiedException.class)
    public ResponseEntity<ExceptionResponse> chairOccupiedException(ChairOccupiedException e){
        List<Details> details = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ExceptionResponse(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, e.getMessage(), details));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> validationException(MethodArgumentNotValidException e){
        List<Details> details = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach((exception) -> {
            details.add(new Details(exception.getField(), exception.getDefaultMessage()));
        });
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY, e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), details));
    }
}
