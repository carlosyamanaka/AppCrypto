package br.com.cryptopc.appcrypto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Collection;

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