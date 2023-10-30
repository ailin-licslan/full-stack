package com.licslan.crudplay.web;

import com.licslan.crudplay.common.Result;
import com.licslan.crudplay.req.PeopleVo;
import com.licslan.crudplay.res.PeopleDto;
import com.licslan.crudplay.service.PeopleService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class PeopleTest {
    private final PeopleService service;
    private final Environment environment;

    public PeopleTest(PeopleService service, Environment environment) {
        this.service = service;
        this.environment = environment;
    }


    //getList
    @SneakyThrows
    @GetMapping("/people/getList")
    public Result<List<PeopleDto>> getList() {

        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");

        log.info("[people/getList] 当前运行的端口是: {},ip: {}", port, ip);

        assert port != null;
        return Result.success(service.getPeopleList(), Long.valueOf(port), ip);
    }


    //getInfo
    @SneakyThrows
    @GetMapping("/people/{id}")
    public Result<PeopleDto> get(@PathVariable Long id) {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        assert port != null;
        return Result.success(service.get(id),Long.valueOf(port), ip);
    }


    //deleteInfo
    @SneakyThrows
    @DeleteMapping("/people/{id}")
    public Result<Boolean> del(@PathVariable Long id) {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        assert port != null;
        return Result.success(service.del(id),Long.valueOf(port), ip);
    }


    @SneakyThrows
    @GetMapping("/people")
    public Result<List<PeopleDto>> findByName(@RequestParam String name) {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        assert port != null;
        return Result.success(service.findByName(name),Long.valueOf(port), ip);
    }



    //addInfo && updateInfo
    @PostMapping("/save-people")
    public void save(@RequestBody PeopleVo peopleVo) {
        service.save(peopleVo);
    }



    //addInfo && updateInfo
    @PostMapping("/update-people")
    public void update(@RequestBody PeopleVo peopleVo) {
        service.update(peopleVo);
    }














}
