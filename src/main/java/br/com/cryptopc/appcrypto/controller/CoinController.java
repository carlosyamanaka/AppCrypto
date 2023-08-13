package br.com.cryptopc.appcrypto.controller;

import br.com.cryptopc.appcrypto.entity.Coin;
import br.com.cryptopc.appcrypto.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coins")
public class CoinController {

    private final CoinRepository coinRepository;

    @Autowired
    public CoinController(CoinRepository coinRepository){
        this.coinRepository = coinRepository;
    }

    @PostMapping("/added")
    public ResponseEntity<String> addCoin(@RequestBody Coin coin) {
        try {
            coinRepository.save(coin);
            return ResponseEntity.ok("Coin Added!!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
}
