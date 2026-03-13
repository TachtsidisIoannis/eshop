package com.project.eshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stores")
@Getter
@Setter
public class Store {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer id;

    private String city;
    private String address;
}
