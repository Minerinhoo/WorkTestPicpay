package tech.biuldrun.picpay.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;


@Entity
@Table(name = "tb_wallet")

public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String fullName;

    @Column(name = "cpj_Cnpj" ,unique = true)
    private String cpfCnpj;

    @Column(name = "email", unique = true)
    private String email;


    private String password;
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType WalletType;


    public Wallet() {
    }

    public Wallet(String fullName, String cpfCnpj, String email, String password, WalletType walletType) {
        this.fullName = fullName;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        WalletType = walletType;
    }


    public void debit(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value) {
        this.balance =  this.balance.add(value);
    }

    public boolean isBalanceEqualOrGreatherThan(@DecimalMin("0.01") @NotNull BigDecimal value) {
        return this.balance.doubleValue() > value.doubleValue();
    }

    public boolean isTransferAllowedForWalletType(){
        return this.WalletType.equals( tech.biuldrun.picpay.entity.WalletType.Enum.USER.get());
    }


    public Wallet(String fullName, String cpfCnpj, Object o) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public WalletType getWalletType() {
        return WalletType;
    }

    public void setWalletType(WalletType walletType) {
        WalletType = walletType;
    }


}

