package tech.biuldrun.picpay.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import tech.biuldrun.picpay.entity.WalletType;
import tech.biuldrun.picpay.repository.WalletTypeRepository;

import java.util.Arrays;


@Configuration
public class DataLoader implements CommandLineRunner {

    private WalletTypeRepository walletTypeRepository;
    private void forEach(Object o) {
    }

    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.Enum.values())
                .forEach(walletType-> walletTypeRepository.save(walletType.get()));

    }


}

