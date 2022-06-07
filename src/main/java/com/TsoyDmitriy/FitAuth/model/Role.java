package com.TsoyDmitriy.FitAuth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name = "auth_role")
public class Role implements GrantedAuthority {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    private String name;

    public Role(Long id, String roleName) {
        this.id = id;
        this.name = roleName;
    }

    public Role(String roleName) {
        this.name = roleName;
    }

    @Override
    public String getAuthority() {
        return String.format("ROLE_%s", name);
    }
}
