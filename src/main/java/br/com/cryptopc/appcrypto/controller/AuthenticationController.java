package br.com.cryptopc.appcrypto.controller;


import br.com.cryptopc.appcrypto.entity.UserI;
import br.com.cryptopc.appcrypto.DTO.UserIAuthenticationDTO;
import br.com.cryptopc.appcrypto.DTO.UserILoginResponseDTO;
import br.com.cryptopc.appcrypto.DTO.UserIRegisterDTO;
import br.com.cryptopc.appcrypto.infra.security.TokenService;
import br.com.cryptopc.appcrypto.repository.UserIRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserIRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserIAuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.pass());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserI) auth.getPrincipal());

        return ResponseEntity.ok(new UserILoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserIRegisterDTO data){
        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.pass());
        UserI newUser = new UserI(data.email(), encryptedPassword);
        newUser.setRole("ROLE_USER");
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }


}

