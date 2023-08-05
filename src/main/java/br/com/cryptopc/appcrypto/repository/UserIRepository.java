package br.com.cryptopc.appcrypto.repository;

import br.com.cryptopc.appcrypto.entity.UserI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIRepository extends JpaRepository<UserI, Long> {
    UserDetails findByEmail(String email);
}
