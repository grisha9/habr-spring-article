package com.example.demo

import com.example.demo.jdbc.JdbcBookRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class JdbcIntegrationTests {

    @Autowired
    lateinit var bookRepository: JdbcBookRepository

    @Test
    fun testTxSequence() {
        bookRepository.clear()
        println("count before: " + bookRepository.count())
        println("tx_id before: " + bookRepository.currentTxId())
        bookRepository.update10TxSequence()
        println("tx_id after: " + bookRepository.currentTxId())
        println("count after: " + bookRepository.count())
    }

    @Test
    fun testTxParallel() {
        bookRepository.clear()
        println("count before: " + bookRepository.count())
        println("tx_id before: " + bookRepository.currentTxId())
        bookRepository.update10TxParallel()
        println("tx_id after: " + bookRepository.currentTxId())
        println("count after: " + bookRepository.count())
    }

    @Test
    fun testTxIdIncrement() {
        println("tx_id 1: " + bookRepository.currentTxId())
        println("tx_id 2: " + bookRepository.currentTxId())
        println("tx_id 3: " + bookRepository.currentTxId())
    }


    companion object {
        @Container
        @ServiceConnection
        val postgreSQLContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:13.3")
    }
}

