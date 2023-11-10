package com.licslan.crudplay.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "people")
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "del_flag=0")
public class People {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String address;
    private String tags;
    @Column(name = "create_time")
    private Instant createTime;
    @Column(name = "del_flag")
    private boolean delFlag;
}
