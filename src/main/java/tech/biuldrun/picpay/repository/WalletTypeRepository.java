package tech.biuldrun.picpay.repository;


import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.biuldrun.picpay.entity.WalletType;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
    Object findAllById(@NotNull Long payer);
}
