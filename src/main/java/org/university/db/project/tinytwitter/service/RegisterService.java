package org.university.db.project.tinytwitter.service;

import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.entity.User;

import java.util.List;

@Service
public class RegisterService implements IService<User> {
    public boolean exist(String username) {
        return false;
    }

    public User login(String username, String password) {
        return null;
    }

    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> find(String user) {
        return null;
    }
}
