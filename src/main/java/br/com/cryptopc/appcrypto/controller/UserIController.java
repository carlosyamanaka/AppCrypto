package br.com.cryptopc.appcrypto.controller;

import br.com.cryptopc.appcrypto.DTO.UserCoinDTO;
import br.com.cryptopc.appcrypto.DTO.UserIDataDTO;
import br.com.cryptopc.appcrypto.DTO.UserIProfileDTO;
import br.com.cryptopc.appcrypto.entity.Coin;
import br.com.cryptopc.appcrypto.entity.UserI;
import br.com.cryptopc.appcrypto.repository.CoinRepository;
import br.com.cryptopc.appcrypto.repository.UserIRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserIController {

    @Autowired
    private CoinController coinController;

    private final UserIRepository userIRepository;

    public UserIController(@Autowired UserIRepository userIRepository) {
        this.userIRepository = userIRepository;
    }

    @GetMapping("{id_user}")
    public ResponseEntity getById(@PathVariable Long id_user) {
        return new ResponseEntity<>(userIRepository.findById(id_user), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody UserI userI) {
        try {
            return new ResponseEntity<>(userIRepository.save(userI), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id_user}")
    public ResponseEntity delete(@PathVariable Long id_user) {
        try {
            userIRepository.deleteById(id_user);
            return new ResponseEntity<>("UserI deleted!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return ResponseEntity.ok(username);
        } else {
            return ResponseEntity.ofNullable("User is not authenticated.");
        }
    }

    @PatchMapping("/addMoeda")
    public ResponseEntity<String> addCoin(@RequestBody UserCoinDTO userCoinDTO) {
        try {
            UserI userI = userIRepository.findUserIByEmail(userCoinDTO.email());
            Coin coin = new Coin();
            coin.setId(userCoinDTO.coin());
            coinController.addCoin(coin);
            if (!userI.getCoins().contains(coin)) {
                userI.getCoins().add(coin);
                userIRepository.save(userI);
                return ResponseEntity.ok("Coin Added");
            }else{
                return ResponseEntity.status(400).body("Coin already added");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
    @PatchMapping("/removeMoeda")
    public ResponseEntity<String> removeCoin(@RequestBody UserCoinDTO userCoinDTO) {
        try {
            UserI userI = userIRepository.findUserIByEmail(userCoinDTO.email());
            Coin coin = new Coin();
            coin.setId(userCoinDTO.coin());
            userI.getCoins().remove(coin);
            userIRepository.save(userI);
            return ResponseEntity.ok("Coin Removed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
    @GetMapping("/usercoins")
    public ResponseEntity<List<Coin>> getCoinsUser(@RequestParam String email) {
        try {
            UserI userI = userIRepository.findUserIByEmail(email);
            List<Coin> coins = userI.getCoins();
            return ResponseEntity.ok(coins);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PatchMapping("/password")
    public ResponseEntity<String>updatePassword(@RequestBody UserIDataDTO userDataDTO) {
        try {
            UserI userI = userIRepository.findUserIByEmail(userDataDTO.email());
                userI.setPass(userDataDTO.pass());
                userIRepository.save(userI);
                return ResponseEntity.ok("Coin Added");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
}
