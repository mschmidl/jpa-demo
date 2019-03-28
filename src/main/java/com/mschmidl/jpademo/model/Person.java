package com.mschmidl.jpademo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Person")
@Getter @Setter @NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "role")
    String role;
}
