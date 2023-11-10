package com.licslan.crudplay.service;

import com.licslan.crudplay.dao.PersonDao;
import com.licslan.crudplay.entity.Person;
import com.licslan.crudplay.req.PersonVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    private final PersonDao dao;

    public PersonService(PersonDao dao) {
        this.dao = dao;
    }

    public List<Person> getPersonList() {
        return dao.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void del(Long id) {
        dao.deleteById(id);
    }


    @Transactional(rollbackFor = Exception.class)
    public void save(PersonVo personVo) {
        Person person = Person.builder().
                name(personVo.getName()).
                age(personVo.getAge()).
                sex(personVo.isSex()).build();
        dao.save(person);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(PersonVo personVo) {
        Person person = Person.builder().id(personVo.getId()).
                name(personVo.getName()).
                age(personVo.getAge()).
                sex(personVo.isSex()).build();
        dao.save(person);
    }
}
