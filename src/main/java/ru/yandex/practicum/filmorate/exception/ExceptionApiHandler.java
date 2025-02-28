package ru.yandex.practicum.filmorate.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse DuplicateException(final DuplicateException e) {
        log.warn("Попытка добавить дубликат фильма: {}", e.getMessage(), e);
        return new ErrorResponse(
                String.format("Фильм с id: %d уже существует", e.getId()),
                "DUPLICATE_RECORD");
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse NotFoundException(final NotFoundException e) {
        log.warn("Объект не найден: {}", e.getMessage(), e);
        return new ErrorResponse("Объект не найден",
                "Проверьте запрос");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse InternalServerException(Exception exception) {

        log.error("Внутренняя ошибка сервера: {}", exception.getMessage(), exception);

        return new ErrorResponse(
                "Внутренняя ошибка сервера",
                "Произошла внутренняя ошибка сервера");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse HandleValidationException(MethodArgumentNotValidException ex) {
        return new ErrorResponse(
                "Validation failed",
                "неправильно введены данные");
    }
}