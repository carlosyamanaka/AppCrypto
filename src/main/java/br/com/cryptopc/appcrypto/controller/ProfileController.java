package br.com.cryptopc.appcrypto.controller;

import br.com.cryptopc.appcrypto.repository.UserIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    public ProfileController(@Autowired UserIRepository userIRepository){
    }




}
