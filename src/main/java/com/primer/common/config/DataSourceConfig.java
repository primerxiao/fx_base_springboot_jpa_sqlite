package com.primer.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据源配置
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite::resource:sqlite/data.db");
        //dataSource.setUsername("");
        //dataSource.setPassword("");
        //#    initialSize: 1
        dataSource.setInitialSize(1);
        //#    minIdle: 3
        dataSource.setMinIdle(3);
        //#    maxActive: 5
        dataSource.setMaxActive(5);
        //#    maxWait: 60000
        dataSource.setMaxWait(60000);
        //#    timeBetweenEvictionRunsMillis: 60000
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        //#    minEvictableIdleTimeMillis: 30000
        dataSource.setMinEvictableIdleTimeMillis(30000);
        //#    validationQuery: select 'x'
        dataSource.setValidationQuery("select 'x'");
        //#    testWhileIdle: true
        dataSource.setTestWhileIdle(true);
        //#    testOnBorrow: false
        dataSource.setTestOnBorrow(false);
        //#    testOnReturn: false
        dataSource.setTestOnReturn(false);
        //#    poolPreparedStatements: true
        dataSource.setPoolPreparedStatements(false);
        //#    maxPoolPreparedStatementPerConnectionSize:  20
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(-1);
        //#    filters=sta: slf4j
        // dataSource.setFilters("sta: slf4j");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
}