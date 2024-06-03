package org.tarik.casestudy.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username ;

    @Column(name = "password")
    private String password ;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
