package com.softserveinc.ita.javaclub.volleyblog.exception;

import com.softserveinc.ita.javaclub.volleyblog.security.jwt.JwtAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(ex.getClass().getName() + " handled");

        String error = ex.getParameterName() + " parameter is missing";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(ex.getClass().getName() + " handled");

        String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(ex.getClass().getName() + " handled");

        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(ex.getClass().getName() + " handled");

        String error = ex.getRequestPartName() + " part is missing";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(ex.getClass().getName() + " handled");

        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    // Status 404
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(ex.getClass().getName() + " handled");
        //
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // Status 405
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        log.info(ex.getClass().getName());

        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // Status 415
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        log.info(ex.getClass().getName());

        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // Other exceptions
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        log.info(ex.getClass().getName() + " handled");

        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex,
            WebRequest request) {

        log.info(ex.getClass().getName() + " handled");

        String error = ex.getMostSpecificCause().toString();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage(), error);

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {

        log.info("IN RestExceptionHandler.handleAccessDeniedException " + ex.getClass().getName() + " handled");

        ApiError apiError = new ApiError(
                HttpStatus.FORBIDDEN,
                ex.getLocalizedMessage(),
                "Access denied!");

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    protected ResponseEntity<Object> handleJwtAuthenticationException(
            DataIntegrityViolationException ex,
            WebRequest request) {

        log.info(ex.getClass().getName() + " handled");

        String error = ex.getMostSpecificCause().toString();
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED,
                ex.getLocalizedMessage(), error);

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    protected ResponseEntity<Object> handleRecordNotFoundException(
            RecordNotFoundException ex,
            WebRequest request) {

        log.info(ex.getClass().getName() + " handled");

        String error = ex.getCause().toString();
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED,
                ex.getLocalizedMessage(), error);

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }

}
