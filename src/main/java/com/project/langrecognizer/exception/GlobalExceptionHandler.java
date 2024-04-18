/**
 * Глобальный обработчик исключений для обработки и представления исключений в REST-контроллерах.
 */
package com.project.langrecognizer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String NOT_FOUND = "Ресурсы не найдены: ";

    /**
     * Обрабатывает исключение ResourceNotFoundException.
     *
     * @param exception Исключение ResourceNotFoundException.
     * @param request   Запрос.
     * @return ResponseEntity с деталями ошибки и статусом NOT_FOUND.
     */
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(final ResourceNotFoundException exception,
                                                                        final WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                NOT_FOUND + exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Обрабатывает исключение BadRequestException.
     *
     * @param exception Исключение BadRequestException.
     * @param request   Запрос.
     * @return ResponseEntity с деталями ошибки и статусом BAD_REQUEST.
     */
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorDetails> handleBadRequestException(final BadRequestException exception,
                                                                  final WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                NOT_FOUND + exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обрабатывает исключение MethodArgumentNotValidException.
     *
     * @param exception Исключение MethodArgumentNotValidException.
     * @param request   Запрос.
     * @return ResponseEntity с деталями ошибки и статусом BAD_REQUEST.
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException exception, final WebRequest request) {
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String errorMessage = String.format("%s - %s", fieldError.getField(), fieldError.getDefaultMessage());
            errors.add(errorMessage);
        });
        String errorMessage = String.join(", ", errors);

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                NOT_FOUND + errorMessage,
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обрабатывает исключение Exception.
     *
     * @param exception Исключение Exception.
     * @param request   Запрос.
     * @return ResponseEntity с деталями ошибки и статусом INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDetails> handleGlobalException(final Exception exception, final WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                NOT_FOUND + exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
