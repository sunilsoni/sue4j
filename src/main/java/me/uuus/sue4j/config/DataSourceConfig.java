package me.uuus.sue4j.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 配置数据库连接池
 *
 * @author Mr.Su[swb0917@gmail.com]
 */


@Configuration
public class DataSourceConfig {

    private static final String DRUID_PREFIX = "spring.datasource.druid";

    @Bean(destroyMethod = "close")
    @ConfigurationProperties(DataSourceConfig.DRUID_PREFIX)
    public DataSource dataSource() {
        return new DruidDataSource();
    }
}