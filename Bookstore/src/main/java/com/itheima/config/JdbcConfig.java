package com.itheima.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/*
等同于
<context:property-placeholder location="classpath*:jdbc.properties"/>
 */
@PropertySource("classpath:jdbc.properties")
public class JdbcConfig {
    /*
    使用注入的形式，读取properties文件中的属性值，
    等同于<property name="*******" value="${jdbc.driver}"/>
     */
    @Value("${jdbc.driverClassName}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String userName;
    @Value("${jdbc.password}")
    private String password;

    /*定义dataSource的bean，
    等同于<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
     */
    @Bean("dataSource")
    public DataSource getDataSource(){
        //创建对象
        DruidDataSource ds = new DruidDataSource();
        /*
        等同于set属性注入<property name="driverClassName" value="driver"/>
         */
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        return ds;
    }
}

