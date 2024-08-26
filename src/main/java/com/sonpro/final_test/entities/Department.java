package com.sonpro.final_test.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;

    @Column(name = "status", columnDefinition = "tinyint")
    private Short status;
    @Column(name = "created_date")
    private Long createdDate;
    @Column(name = "last_modified_date")
    private Long lastModifiedDate;



}