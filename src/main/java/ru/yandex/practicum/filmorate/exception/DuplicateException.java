package ru.yandex.practicum.filmorate.exception;

import lombok.Getter;

@Getter
public class DuplicateException extends RuntimeException {
    private final long id;

        public DuplicateException(String message, long id) {
        super(message);
        this.id = id;
    }

}