package org.tarik.casestudy.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany (mappedBy = "role")
    @JsonIgnore
    private List<User> users;


    @Override
    public String getAuthority() {
        return  name;
    }
}
