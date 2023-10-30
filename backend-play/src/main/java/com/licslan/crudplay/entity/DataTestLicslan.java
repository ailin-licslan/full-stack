package com.licslan.crudplay.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "data_test_licslan")
@Data
@Entity
public class DataTestLicslan {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
}
