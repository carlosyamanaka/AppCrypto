package br.com.cryptopc.appcrypto.entity;

import jakarta.persistence.*;
import lombok.*;

//Getters e setters
//Construtor comum
//construtor com todos os atributos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coin {

    @Id
    private String id;
}