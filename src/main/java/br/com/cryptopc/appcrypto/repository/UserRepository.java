package br.com.cryptopc.appcrypto.repository;

import br.com.cryptopc.appcrypto.entity.UserI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserI, Long> {
}