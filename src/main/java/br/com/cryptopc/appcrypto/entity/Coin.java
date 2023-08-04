package br.com.cryptopc.appcrypto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

//Getters e setters
//Construtor comum
//construtor com todos os atributos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rank;
    @Column(length = 10)
    private String symbol;
    @Column(length = 70)
    private String name;
    @Column(precision = 70, scale = 30)
    private BigDecimal supply;
    @Column(precision = 70, scale = 30)
    private BigDecimal maxSupply;
    @Column(precision = 70, scale = 30)
    private BigDecimal marketCapUsd;
    @Column(precision = 70, scale = 30)
    private BigDecimal volumeUsd24Hr;
    @Column(precision = 70, scale = 30)
    private BigDecimal priceUsd;
    @Column(precision = 70, scale = 30)
    private BigDecimal changePercent24Hr;
    @Column(precision = 70, scale = 30)
    private BigDecimal vwap24Hr;
    @Column(length = 70)
    private String explorer;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
