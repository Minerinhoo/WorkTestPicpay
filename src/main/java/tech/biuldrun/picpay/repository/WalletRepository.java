package tech.biuldrun.picpay.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.biuldrun.picpay.entity.Wallet;

import java.util.Optional;




public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCnpj, String email);



}
