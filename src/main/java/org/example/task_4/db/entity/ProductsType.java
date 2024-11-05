package org.example.task_4.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "prducts_type")
public class ProductsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable=false, updatable=false)
    private Long id;

    @Column(name = "name")
    private String name;
}
