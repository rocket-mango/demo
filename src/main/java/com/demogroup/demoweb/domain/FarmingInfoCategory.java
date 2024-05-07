package com.demogroup.demoweb.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FarmingInfoCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fcid;

    @Column(name = "category_name")
    private String categoryName;

}
