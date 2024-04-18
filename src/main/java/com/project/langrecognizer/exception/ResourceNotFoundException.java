/**
 * Исключение, указывающее на отсутствие запрашиваемых ресурсов (HTTP статус 404 Not Found).
 */
package com.project.langrecognizer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Создает новое исключение с указанным сообщением.
     *
     * @param message Сообщение об ошибке.
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
