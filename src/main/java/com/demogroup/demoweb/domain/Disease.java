package com.demogroup.demoweb.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Disease{
    @Id
    private Long did;
    private String name;

    @Column(name = "name_en")
    private String ename;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @Column(columnDefinition = "TEXT")
    private String symptom;

    @Column(columnDefinition = "TEXT")
    private String handle;
}
