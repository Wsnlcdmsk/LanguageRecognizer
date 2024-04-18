/**
 * This package contains aspects for logging in the language recognizer project.
 */
package com.project.langrecognizer.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark methods for logging.
 * Methods annotated with {@code LoggingAnnotation} will be logged.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggingAnnotation {
}
