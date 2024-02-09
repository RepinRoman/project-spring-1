package com.repin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task", schema = "todo")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "INT")
    private Status status;
}