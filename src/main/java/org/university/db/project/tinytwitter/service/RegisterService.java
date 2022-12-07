package org.university.db.project.tinytwitter.service;

import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.dao.UserMapper;
import org.university.db.project.tinytwitter.entity.User;

import java.util.List;

@Service
public class RegisterService implements IService<User> {
    private UserMapper userMapper;
    public RegisterService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public boolean exist(String username) {
        return false;
    }

    public User login(String username, String password) {
        if ("1".equals(username) && "2".equals(password)) {
            return new User();
        }
        return null;
    }

    @Override
    public boolean add(User user) {
        return userMapper.insert(user) == 1;
    }

    @Override
    public boolean update(User user) {
        return userMapper.updateByPrimaryKey(user)==1;
    }

    @Override
    public boolean delete(User user) {
        return userMapper.deleteByPrimaryKey(user.getUserId())==1;
    }

    @Override
    public List<User> find(String user) {
        return userMapper.find(user);
    }
}
