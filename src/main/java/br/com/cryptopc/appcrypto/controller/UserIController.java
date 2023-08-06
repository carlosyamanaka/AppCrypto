package br.com.cryptopc.appcrypto.controller;

import br.com.cryptopc.appcrypto.entity.UserI;
import br.com.cryptopc.appcrypto.repository.UserIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
