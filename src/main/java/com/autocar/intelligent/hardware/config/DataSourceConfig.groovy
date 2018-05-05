package com.autocar.intelligent.hardware.config

import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by guobingwei on 18/4/29.
 */
@Configuration
class DataSourceConfig {

    @Value('${jdbcUrl}')
    private String jdbcUrl

    @Value('${userName}')
    private String userName

    @Value('${password}')
    private String password

    @Value('${jdbcDriver}')
    private String jdbcDriver

    @Bean('dateSource')
    public Sql getDataSource() {
        return Sql.newInstance(jdbcUrl, userName, password, jdbcDriver)
    }
}
