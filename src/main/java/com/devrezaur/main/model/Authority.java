package com.devrezaur.main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "authority_table")
public class Authority {

    @Id
    @GeneratedValue
    @Column(name = "authority_id")
    private int id;

    @Column(name = "authority", unique = true)
    private String authority;
}
