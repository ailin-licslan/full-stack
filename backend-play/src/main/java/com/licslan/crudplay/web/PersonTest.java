package com.licslan.crudplay.web;

import com.licslan.crudplay.common.Result;
import com.licslan.crudplay.entity.DataTestLicslan;
import com.licslan.crudplay.entity.Person;
import com.licslan.crudplay.req.PersonVo;
import com.licslan.crudplay.service.PersonService;
import com.licslan.crudplay.service.WriteFileService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.List;

//允许前端跨域
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class PersonTest {
    private final Environment environment;
    private final PersonService service;
    private final WriteFileService writeFileService;

    public PersonTest(PersonService service,
                      WriteFileService writeFileService,
                      Environment environment) {
        this.service = service;
        this.writeFileService = writeFileService;
        this.environment = environment;
    }

    @GetMapping("/")
    public String test() {
        return "HELLO BOOT!";
    }

    @SneakyThrows
    @GetMapping("/person/getList")
    public Result<List<Person>> getList() {

        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        log.info("[person/getList] 当前运行的端口是: {},ip: {}", port, ip);
        assert port != null;
        return Result.success(service.getPersonList(), Long.valueOf(port), ip);
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable Long id) {
        service.del(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody PersonVo personVo) {
        service.save(personVo);
    }

    @PostMapping("/update")
    public void update(@RequestBody PersonVo personVo) {
        service.update(personVo);
    }

    //千万条数据导入测试
    @GetMapping("/{saveNum}")
    public void saveData(@PathVariable Integer saveNum) {
        writeFileService.saveData2DataBases(saveNum);
    }

    //是否有索引测试查询
    @GetMapping("/index/getList")
    public List<DataTestLicslan> getListByIndexTest(@RequestParam String name) {
        return writeFileService.getList(name);
    }
}
