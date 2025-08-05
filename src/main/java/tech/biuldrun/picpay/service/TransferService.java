package tech.biuldrun.picpay.service;



import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.biuldrun.picpay.controller.dto.TransferDto;
import tech.biuldrun.picpay.entity.Transfer;
import tech.biuldrun.picpay.entity.Wallet;
import tech.biuldrun.picpay.exception.InsufficientBalanceException;
import tech.biuldrun.picpay.exception.WalletNotFoundException;
import tech.biuldrun.picpay.exception.transferNotAllowedForWalletTypeExeption;
import tech.biuldrun.picpay.exception.transferNotAuthorizedexeption;
import tech.biuldrun.picpay.repository.TransferRepository;
import tech.biuldrun.picpay.repository.WalletRepository;
import tech.biuldrun.picpay.repository.WalletTypeRepository;

import java.util.concurrent.CompletableFuture;


@Service
public class TransferService {


    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletTypeRepository walletTypeRepository;
    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository,
                           AuthorizationService authorizationService,
                           NotificationService notificationService,
                           WalletTypeRepository walletTypeRepository,
                           WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletTypeRepository = walletTypeRepository;
        this.walletRepository = walletRepository;
    }


    @Transactional
    public Transfer transfer(TransferDto transferDto) {


        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validadeTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender,receiver, transferDto.value());


        walletRepository.save( sender );
         walletRepository.save( receiver );
       var transferResult= transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.setNotification(transferResult));

        return transferResult;
    }

    private void validadeTransfer(TransferDto transferDto, Wallet sender) {

            if (!sender.isTransferAllowedForWalletType()) {
                throw new transferNotAllowedForWalletTypeExeption();
            }
            if (!sender.isBalanceEqualOrGreatherThan(transferDto.value())) {
                throw new InsufficientBalanceException();
            }
            if (!authorizationService.isAuthorized(transferDto)) {
                throw new transferNotAuthorizedexeption();
            }
        }

    }

