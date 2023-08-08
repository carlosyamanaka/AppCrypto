package br.com.cryptopc.appcrypto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//Getters e setters
//Construtor comum
//construtor com todos os atributos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserI implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 70)
    private String email;
    @Column(length = 70)
    private String pass;
    @Column(length = 70)
    private String role;

    //@JoinColumn é um column pra chave estrangeira
    //Muitas moedas se ligam a um usuario    
    @ManyToMany
    @JoinTable(name="user_has_coins", joinColumns=
    {@JoinColumn(name="user_id")}, inverseJoinColumns=
      {@JoinColumn(name="coin_id")})
    private List<Coin> coins;

    public UserI(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
