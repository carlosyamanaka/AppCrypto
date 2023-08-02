package br.com.cryptopc.appcrypto.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserI {

    @Id
    private Long id;
    private String email;
    private String pass;

    @ElementCollection
    private List <String> followedCrypto;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
