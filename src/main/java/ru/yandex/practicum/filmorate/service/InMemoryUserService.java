package ru.yandex.practicum.filmorate.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class InMemoryUserService implements UserService {
    private final InMemoryUserStorage storage;

    @Override
    public List<User> getAllUsers() {
        return storage.getAllUsers();
    }

    @Override
    public void createUser(User user) {
        storage.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        storage.updateUser(user);
    }

    @Override
    public void addFriends(Long userId, Long friendsId) {
        storage.getUserToId(userId).getFriendsId().add(friendsId);
        storage.getUserToId(friendsId).getFriendsId().add(userId);
    }

    @Override
    public void deleteFriend(Long userId, Long friendsId) {
        storage.getUserToId(userId).getFriendsId().remove(friendsId);
        storage.getUserToId(friendsId).getFriendsId().remove(userId);
    }

    @Override
    public List<User> getAllFriends(Long userId) {
        return storage.getUserToId(userId)
                .getFriendsId()
                .stream()
                .map(storage::getUserToId)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserOnId(Long userId) {
        return storage.getUserToId(userId);
    }

    @Override
    public List<User> getCommonFriends(Long userId, Long commonUserId) {
        return storage.getUserToId(userId)
                .getFriendsId()
                .stream()
                .filter(friendId -> storage.getUserToId(commonUserId).getFriendsId().contains(friendId))
                .map(storage::getUserToId)
                .collect(Collectors.toList());
    }
}