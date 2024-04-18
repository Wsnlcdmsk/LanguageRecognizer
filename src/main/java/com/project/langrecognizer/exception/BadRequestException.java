/**
 * Исключение, указывающее на некорректный запрос (HTTP статус 400 Bad Request).
 */
package com.project.langrecognizer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
     * Создает новое исключение с указанным сообщением.
     *
     * @param message Сообщение об ошибке.
     */
    public BadRequestException(final String message) {
        super(message);
    }

}
