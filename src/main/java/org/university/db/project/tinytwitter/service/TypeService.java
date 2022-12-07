package org.university.db.project.tinytwitter.service;

import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.dao.TypeMapper;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.Type;

import java.util.List;

@Service
public class TypeService implements IService<Type>{

    private TypeMapper typeMapper;

    public TypeService(TypeMapper typeMapper){
        this.typeMapper = typeMapper;
    }

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

    public List<Blog> getCollection(Type type) {
        return null;
    }
}
