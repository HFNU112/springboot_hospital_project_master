package com.lb.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;


/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName DataSourceConfig.java
 * @Description 数据库配置
 * @createTime 2020年03月25日 13:35:00
 */
@Configuration
public class DataSourceConfig {
    /**
     * BeetlSQL 官方推荐配置
     * 使用的是 Hikari 连接池  springboot2.0 默认支持
     */
    @Bean(name="datasource")
    public DataSource datasource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return ds;
    }
}
