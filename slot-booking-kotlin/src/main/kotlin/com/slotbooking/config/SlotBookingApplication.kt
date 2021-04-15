package com.slotbooking.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

const val LG_PACKAGE = "com.slotbooking"

@EnableJpaRepositories(basePackages = arrayOf(LG_PACKAGE))
@EntityScan(basePackages = arrayOf(LG_PACKAGE))
@SpringBootApplication(scanBasePackages = arrayOf(LG_PACKAGE))
class SlotBookingApplication

fun main(args: Array<String>) {
	runApplication<SlotBookingApplication>(*args)
}
