package hvalfangst.cars.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DatabaseConfiguration(@Value("\${db.url}") private val url: String) {

    init {
       connect()
    }

    /**
     * Establishes a database connection using HikariCP connection pool
     */
    private final fun connect() {
        val pool = createConnectionPool(url)
        Database.connect(pool)
    }

    /**
     * Creates and configures a HikariCP connection pool based on application configuration.
     *
     * @return HikariCP data source.
     */
    private fun createConnectionPool(url: String): HikariDataSource {
        val config = HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            this.jdbcUrl = url
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        return HikariDataSource(config)
    }
}