package com.in28minutes.springboot.microservice.example.forex

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import java.math.BigDecimal

@Entity
data class ExchangeValue (

    @Id
    val id: Long = 0,

    @Column(name = "currency_from")
    val from: String? = null,

    @Column(name = "currency_to")
    val to: String? = null,

    var conversionMultiple: BigDecimal? = null,
    var port: Int = 0
)
