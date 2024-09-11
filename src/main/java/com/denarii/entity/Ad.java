package com.denarii.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Optional;

@Data
@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserWebApp user;  // Какой пользователь разместил объявление

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_country")
    private Country country;  // Страна

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_city")
    private City city;  // Город

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_currency_sell")
    private Currency currencySell;  // Какую валюту продает человек

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_currency_buy")
    private Currency currencyBuy;  // Какую валюту покупает человек

    @Column(name = "exchange_rate", nullable = false)
    private Double exchangeRate;  // Курс

    @Column(name = "min_amount", nullable = false)
    private int minAmount;  // Минимальная сумма

    @Column(name = "max_amount", nullable = false)
    private int maxAmount;  // Максимальная сумма

    @Column(name = "bank_transfer_available", nullable = false)
    private boolean bankTransferAvailable;  // Возможен ли банковский перевод

    @Column(name = "description", length = 500)
    private String description;  // Описание
}
