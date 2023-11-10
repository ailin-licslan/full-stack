package com.licslan.crudplay.service;

import com.licslan.crudplay.DateUtil;
import com.licslan.crudplay.dao.PeopleDao;
import com.licslan.crudplay.dao.PersonDao;
import com.licslan.crudplay.entity.People;
import com.licslan.crudplay.entity.Person;
import com.licslan.crudplay.req.PeopleVo;
import com.licslan.crudplay.req.PersonVo;
import com.licslan.crudplay.res.PeopleDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.licslan.crudplay.DateUtil.*;

@Service
public class PeopleService {

    private final PeopleDao dao;

    public PeopleService(PeopleDao dao) {
        this.dao = dao;
    }

    public List<PeopleDto> getPeopleList() {
        List<People> all = dao.findAll();
        List<PeopleDto> peopleDtoList = new ArrayList<>();

        for (People people : all) {

            String[] split;

            if (people.getTags() != null) {
                split = people.getTags().split(",");
            } else {
                split = new String[]{};
            }

            PeopleDto build = PeopleDto.builder().
                    id(people.getId()).
                    name(people.getName()).
                    address(people.getAddress()).
                    age(people.getAge()).
                    tags(Arrays.asList(split)).
                    createTime(DateUtil.format(people.getCreateTime(), "yyyy-MM-dd HH:mm:ss")).
                    build();
            peopleDtoList.add(build);
        }
        return peopleDtoList;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean del(Long id) {
        return dao.delLogic(id) > 0;
    }


    @Transactional(rollbackFor = Exception.class)
    public void save(PeopleVo personVo) {
        People people = People.builder().
                name(personVo.getName()).
                age(personVo.getAge()).
                address(personVo.getAddress()).build();
        dao.save(people);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(PeopleVo personVo) {
        People people = People.builder().id(personVo.getId()).
                name(personVo.getName()).
                age(personVo.getAge()).
                tags(personVo.getTags()).
                createTime(localDateTime2Instant(parse2LocalDate(personVo.getCreateTime(),"yyyy-MM-dd HH:mm:ss").atStartOfDay())).
                address(personVo.getAddress()).build();
        dao.save(people);
    }

    public PeopleDto get(Long id) {
        People people = dao.findById(id).get();
        PeopleDto build = PeopleDto.builder().
                id(people.getId()).
                name(people.getName()).
                address(people.getAddress()).
                age(people.getAge()).
                tags(Arrays.asList(people.getTags())).
                createTime(DateUtil.format(people.getCreateTime(), "yyyy-MM-dd HH:mm:ss")).
                build();
        return build;
    }

    public List<PeopleDto> findByName(String name) {
        List<People> all = dao.findPeopleByName(name);
        List<PeopleDto> peopleDtoList = new ArrayList<>();
        for (People people : all) {
            String[] split = people.getTags().split(",");
            PeopleDto build = PeopleDto.builder().
                    id(people.getId()).
                    name(people.getName()).
                    address(people.getAddress()).
                    age(people.getAge()).
                    tags(Arrays.asList(split)).
                    createTime(DateUtil.format(people.getCreateTime(), "yyyy-MM-dd HH:mm:ss")).
                    build();
            peopleDtoList.add(build);
        }
        return peopleDtoList;
    }
}
