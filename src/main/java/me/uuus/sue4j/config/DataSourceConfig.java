package me.uuus.sue4j.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Mr.Su[swb0917@gmail.com]
 */


@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfig {

    private static final String DRUID_PREFIX = "spring.datasource.druid";

    @Bean
    @ConfigurationProperties(DataSourceConfig.DRUID_PREFIX)
    public DataSource dataSource(
            DataSourceProperties properties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.determineDriverClassName());
        dataSource.setUrl(properties.determineUrl());
        dataSource.setUsername(properties.determineUsername());
        dataSource.setPassword(properties.determinePassword());
        DatabaseDriver databaseDriver = DatabaseDriver
                .fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }
        return dataSource;
    }
}