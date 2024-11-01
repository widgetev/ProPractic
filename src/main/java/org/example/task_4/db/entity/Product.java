package org.example.task_4.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;

@Entity
@Setter @Getter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, insertable=false, updatable=false)
    private Long id;
    @Column(name = "accnum")
    private String accNum;
    @Column(name = "sum")
    private BigDecimal sum;

    @JoinColumn(name = "type", referencedColumnName = "id")
    @Cascade(CascadeType.MERGE)
    @ManyToOne private ProductsType type;
    @JoinColumn(name = "userid",  referencedColumnName = "id")
    @Cascade(CascadeType.MERGE)
    @ManyToOne private Users user;

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", accNum='" + accNum + '\'' +
                ", sum=" + sum +
                ", type=" + type +
                ", user=" + user +
                '}';
    }
}
