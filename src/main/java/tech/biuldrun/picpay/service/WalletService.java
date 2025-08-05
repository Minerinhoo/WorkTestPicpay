package tech.biuldrun.picpay.service;

import org.springframework.stereotype.Service;
import tech.biuldrun.picpay.controller.dto.CreateWalletDto;
import tech.biuldrun.picpay.entity.Wallet;
import tech.biuldrun.picpay.exception.WalletDataAlreadyExistsExecption;
import tech.biuldrun.picpay.repository.WalletRepository;



@Service
public class WalletService {

    private final WalletRepository walletRepository;


    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;

    }

    public Wallet createWallet(CreateWalletDto dto) {

       var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (walletDb.isPresent()){
            throw new WalletDataAlreadyExistsExecption( "CpfCnpj or Email Exists");

        }

        return walletRepository.save(dto.toWallet());
    }




}