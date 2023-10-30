package com.licslan.crudplay.service;

import com.licslan.crudplay.dao.DataTestLicslanDao;
import com.licslan.crudplay.entity.DataTestLicslan;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 * MySQL 千万数据测试  看看索引的效果如何
 *
 * 1. create table data_test_licslan(id int primary key, username varchar(20));
 *
 * 2. mysql --local-infile -u root -p     set @@global.local_infile=1;
 *
 * 3. load data local infile "C:\\data-test\\YOUR_FILE_NAME_HERE.txt"
 *    into table data_test_licslan fields terminated by ',' lines terminated by '\n';
 *
 * 4. select * from table where username = "";
 *
 * 5. explain SELECT * FROM `licslan`.`data_test_licslan` WHERE `username` = '用户名_9999996\r';
 *
 * 6. create index of the filed you want and compare with no index see the time query cost
 *
 */

@Service
public class WriteFileService {

    //100 W
    public final static int SAVE_100W_DATA = 100 * 10000;

    //1000 W
    public final static int SAVE_1000W_DATA = 1000 * 10000;

    public final static String PATH_100W_SAVE = "C:\\data-test\\100W.txt";

    public final static String PATH_1000W_SAVE = "C:\\data-test\\1000W.txt";




    public WriteFileService(DataTestLicslanDao dao) {
        this.dao = dao;
    }

    public void saveData2DataBases(int unitNum) {

        long stime2 = System.currentTimeMillis();
        try {
            BufferedWriter writer;
            if (unitNum == 100) {
                //100 w 数据写入哈
                writer = new BufferedWriter(new FileWriter(PATH_100W_SAVE));
                writeData2Txt(writer, SAVE_100W_DATA);
            } else {
                //1000 w 数据写入哈
                writer = new BufferedWriter(new FileWriter(PATH_1000W_SAVE));
                writeData2Txt(writer, SAVE_1000W_DATA);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long etime2 = System.currentTimeMillis();

        System.out.println("BUFFERED_WRITER 写入用时:" + (etime2 - stime2));
    }

    private static void writeData2Txt(BufferedWriter writer, int num) throws IOException {
        for (int i = 1; i <= num; i++) {
            String data = String.format("%d,test_data_%s", i, i);
            writer.write(data);
            writer.newLine(); // 换行
        }
    }

    private final DataTestLicslanDao dao;


    /**
     * 因为从txt文件导入的。
     * 这种表的原文件里每一行都有一个换行\n
     * 导入之后输入下面语句查找相同用户名
     * SELECT * FROM `licslan`.`data_test_licslan` WHERE `username`='用户名_9999996';
     * 结果一个也没有 为啥呢？
     * 因为你导入的时候，每一行的 \n 也被计入最后一个字段的内容了，
     * 本文的环境里最后一个字段是username，所以你的每个username里都包含了一个换行符的。
     * 并且，这个在你MySQL里面是不可见的。但是你输入内容的时候要把它算进来。
     * 并且，根据MySQL的习惯这个 \n 要写成 \r才行。
     * 正确的输入应该是：
     * SELECT * FROM `licslan`.`data_test_licslan` WHERE `username` = '用户名_9999996\r' LIMIT 0,1000
     */
    public List<DataTestLicslan> getList(String name) {
        name = name + "\r";
        return dao.findByUsername(name);
    }
}
