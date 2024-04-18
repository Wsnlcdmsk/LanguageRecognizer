/**
 * Класс, представляющий детали об ошибке.
 */
package com.project.langrecognizer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetails {
    /**
     * Временная метка ошибки.
     */
    private LocalDateTime timestamp;

    /**
     * HTTP статус ошибки.
     */
    private int status;

    /**
     * Тип ошибки.
     */
    private String error;

    /**
     * Описание ошибки.
     */
    private String description;
}
