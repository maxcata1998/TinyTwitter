package org.university.db.project.tinytwitter.service;

import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.Type;

import java.util.List;

@Service
public class TypeService implements IService<Type>{
    @Override
    public boolean add(Type service) {
        return false;
    }

    @Override
    public boolean update(Type service) {
        return false;
    }

    @Override
    public boolean delete(Type service) {
        return false;
    }

    @Override
    public List<Type> find(String pattern) {
        return null;
    }

    public List<Blog> getCollection(Type type) {
        return null;
    }
}
