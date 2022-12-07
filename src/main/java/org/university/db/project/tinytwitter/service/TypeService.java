package org.university.db.project.tinytwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.dao.TypeMapper;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.Type;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeService implements IService<Type>{

    @Resource
    private TypeMapper typeMapper;

    @Override
    public boolean add(Type service) {
        return typeMapper.insert(service) ==1;
    }

    @Override
    public boolean update(Type service) {
        return typeMapper.updateByPrimaryKey(service)==1;
    }

    @Override
    public boolean delete(Type service) {
        return typeMapper.deleteByPrimaryKey(service.getTypeId())==1;
    }

    @Override
    public List<Type> find(String pattern) {
        return typeMapper.find(pattern);
    }

    @Override
    public List<Type> getAll() {
        return null;
    }

    public List<Blog> getCollection(Type type) {
        return null;
    }
}
