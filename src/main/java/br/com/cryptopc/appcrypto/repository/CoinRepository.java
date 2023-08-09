package br.com.cryptopc.appcrypto.repository;

import br.com.cryptopc.appcrypto.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin, String> {
}
