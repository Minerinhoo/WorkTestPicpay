package tech.biuldrun.picpay.entity;


import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_wallet_type")
public class WalletType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @OneToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType walletType;
    private BigDecimal balance;

    public WalletType getWalletType() {
        return walletType;
    }

    public void setWalletType(WalletType WalletType) {
        this.walletType = WalletType;
    }

    public WalletType() {
    }

    public WalletType(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTransferAllowedForWalletType() {
        return walletType != null && walletType.equals(Enum.USER.get());
    }



    public enum Enum {

        USER(1L, "user"),
        MERCHANT(2L, "merchant");

        @Id
        private Long id;
        @Enumerated(EnumType.STRING)
        private String description;

        Enum(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public WalletType get(){
            return new WalletType(id, description);
        }

    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WalletType that = (WalletType) o;
        return Objects.equals( id, that.id ) && Objects.equals( description, that.description ) && Objects.equals( walletType, that.walletType );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, description, walletType );
    }
}
