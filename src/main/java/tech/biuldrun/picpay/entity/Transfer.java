package tech.biuldrun.picpay.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_Transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;



    @ManyToOne
    @JoinColumn(name = "wallet_sender_id")
    @JsonIgnore
    private Wallet sender;

    @ManyToOne
    @JoinColumn(name = "wallet_receiver_id")
    @JsonIgnore
    private Wallet receiver;

    @Column(name = "value")
    private BigDecimal value;

    public Transfer() {
    }

    public UUID getId() {
        return id;
    }

    public Transfer(Wallet receiver, Wallet sender, BigDecimal value) {
        this.receiver = receiver;
        this.sender = sender;
        this.value = value;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Wallet getSender() {
        return sender;
    }

    public void setSender(Wallet sender) {
        this.sender = sender;
    }

    public Wallet getReceiver() {
        return receiver;
    }

    public void setReceiver(Wallet receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
