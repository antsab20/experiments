package com.account.account.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
@Builder
public class Account {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "amount")
    private int amount;

    @Getter
    @Setter
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Users user;

}
