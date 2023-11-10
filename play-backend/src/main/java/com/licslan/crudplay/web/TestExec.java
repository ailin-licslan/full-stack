package com.licslan.crudplay.web;

import com.licslan.crudplay.task.ConsumeTask;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestExec {
    private final ConsumeTask task;

    public TestExec(ConsumeTask task) {
        this.task = task;
    }

    @RequestMapping("/add/{path}")
    public String addData(@PathVariable("path") Integer path) {
        Person person = new Person();
        person.setAge(15);
        person.setName("jack……&￥#werw~`4~");
        long num = 1000L;
        if (path == 1) {
            //1655380824000    1655391624000   1655388538000
            task.produce(person.getName(), 1698126807000L);
        } else {
            task.produce(Long.toString(num), 1655384424000L);
        }
        return "success";
    }
}
@Data
class Person {
    private String name;
    private int age;
}
