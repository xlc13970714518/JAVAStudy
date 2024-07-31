package com.xlc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

public class DataSourceConfig {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /*
    *
    * */

    @Value("${jdbc2.driver}")
    private String driver2;

    @Value("${jdbc2.url}")
    private String url2;

    @Value("${jdbc2.username}")
    private String username2;

    @Value("${jdbc2.password}")
    private String password2;

    @Primary
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

/*    @Bean
    public DataSource dataSource2(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver2);
        dataSource.setUrl(url2);
        dataSource.setUsername(username2);
        dataSource.setPassword(password2);
        return dataSource;
    }*/
}
