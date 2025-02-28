package ru.yandex.practicum.filmorate.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class InMemoryFilmService implements FilmService {

    private final InMemoryFilmStorage storage;
    private final UserService userService;

    @Override
    public List<Film> getAllFilms() {
        return storage.getAllFilms();
    }

    @Override
    public void addFilm(Film film) {
        storage.addFilm(film);
    }

    @Override
    public void updateFilm(Film film) {
        storage.updateFilm(film);
    }

    @Override
    public void addLikeFilm(Long filmId, Long userId) {
        if (userService.getUserOnId(userId) != null) {
            storage.getFilmToId(filmId).getLikeId().add(userId);
        }
    }

    @Override
    public void removeLikeFilm(Long filmId, Long userId) {
        if (userService.getUserOnId(userId) != null) {
            storage.getFilmToId(filmId).getLikeId().remove(userId);
        }
    }

    @Override
    public List<Film> getPopularFilmOnLike(Integer countFilms) {
        return storage.getAllFilms().stream()
                .sorted(Comparator.comparingInt(Film::getAmountLikes).reversed())
                .limit(countFilms)
                .collect(Collectors.toList());
    }

    @Override
    public Film getFilmOnId(Long filmId) {
        return storage.getFilmToId(filmId);
    }
}