package tech.biuldrun.picpay.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.biuldrun.picpay.entity.Wallet;
import tech.biuldrun.picpay.entity.WalletType;

public record CreateWalletDto(
        @NotBlank String fullName,
       @NotBlank String cpfCnpj,
       @NotBlank String email,
       @NotBlank String password,
       @NotNull WalletType.Enum walletType) {

    public Wallet toWallet(){
        Wallet wallet = new Wallet ();
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()

        );
    }
}
