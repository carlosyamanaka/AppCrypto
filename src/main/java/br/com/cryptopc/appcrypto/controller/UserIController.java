package br.com.cryptopc.appcrypto.controller;

import br.com.cryptopc.appcrypto.DTO.UserIProfileDTO;
import br.com.cryptopc.appcrypto.entity.Coin;
import br.com.cryptopc.appcrypto.entity.UserI;
import br.com.cryptopc.appcrypto.repository.UserIRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserIController {

    private final UserIRepository userIRepository;

    public UserIController(@Autowired UserIRepository userIRepository){
        this.userIRepository = userIRepository;
    }

    @GetMapping("{id_user}")
    public ResponseEntity getById(@PathVariable Long id_user){
        return new ResponseEntity<>(userIRepository.findById(id_user), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody UserI userI){
        try{
            return new ResponseEntity<>(userIRepository.save(userI), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id_user}")
    public ResponseEntity delete(@PathVariable Long id_user){
        try{
            userIRepository.deleteById(id_user);
            return new ResponseEntity<>("UserI deleted!", HttpStatus.OK);
        } catch(Exception e){
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

    @PatchMapping("/{id_user}")
    public ResponseEntity<String> addCoin(@PathVariable String id_user, @RequestBody String coinId) {
        try {
            UserI userI = userIRepository.findUserIByEmail(id_user);
            Coin coin = new Coin();
            coin.setId(coinId);
            userI.getCoins().add(coin);
            userIRepository.save(userI);
            return ResponseEntity.ok("Coin Added");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }    
    


    @GetMapping("/{id_user}/coins")
    public ResponseEntity<Coin> getCoinsUser(@PathVariable Long id_user){
        try{
        UserI userI = userIRepository.findById(id_user).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        List <Coin> coins = userI.getCoins();
        return  ResponseEntity.ok((Coin) coins);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
