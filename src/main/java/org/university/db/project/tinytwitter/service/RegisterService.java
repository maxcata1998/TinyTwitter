package org.university.db.project.tinytwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.university.db.project.tinytwitter.dao.UserMapper;
import org.university.db.project.tinytwitter.entity.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegisterService implements IService<User> {

    @Resource
    private UserMapper userMapper;

    public boolean exist(String username) {
        return false;
    }

    public User login(String username, String password) {
        return userMapper.findByUNamePwd(username, password);
    }

    @Override
    public boolean add(User user) {
        userMapper.insert(user);
        user.setUserId(userMapper.getIdByUnamePwd(user.getName(), user.getPassword()));
        return true;
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

    @Override
    public List<User> getAll() {
        return null;
    }
}
