package com.itheima.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
/*
将MyBatisConfig类和JdbcConfig类交给Spring管理
 */
@Import({MyBatisConfig.class,JdbcConfig.class})
/**
 *等同于<context:component-scan base-package="com.itheima.service">
 */
@ComponentScan( "com.itheima.service")
/*开启事务管理
等同于<tx:annotation-driven transaction-manager="transactionManager"/>
 */
@EnableTransactionManagement
public class SpringConfig {
    /*
    等同于<bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
     */
    @Bean("transactionManager")
    public DataSourceTransactionManager getDataSourceTxManager(@Autowired DataSource dataSource){
        DataSourceTransactionManager dtm = new DataSourceTransactionManager();
        //等同于<property name="dataSource" ref="dataSource"/>
        dtm.setDataSource(dataSource);
        return dtm;
    }
}






