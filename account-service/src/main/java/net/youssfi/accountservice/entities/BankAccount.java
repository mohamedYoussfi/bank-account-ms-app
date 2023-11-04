package net.youssfi.accountservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BankAccount {
    @Id
    private String id;
    private Double balance;
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private String currency;
    private Long customerId;
    @Transient
    private Customer customer;
}
