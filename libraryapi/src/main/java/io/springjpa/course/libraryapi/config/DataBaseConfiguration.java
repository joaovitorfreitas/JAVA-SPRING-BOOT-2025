package io.springjpa.course.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.password}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //@Bean
    DataSource datasource(){
        //não recomendada em produção
        //DriverManagerDataSource ds = new DriverManagerDataSource();
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);

        return ds;
    }

    
    //Pool de conexões
    //@Bean
    public HikariDataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10); // maximo conexões liberadas
        config.setMinimumIdle(1); // minimo liberado de inciio
        config.setPoolName("libraryapi-db-pool");
        config.setMaxLifetime(6000000); // 600 mil ms, conexão vai durar no máximo 10 minutos.
        config.setConnectionTimeout(10000); // tempo tentando conexão até dar erro
        config.setConnectionTestQuery("SELECT 1"); // testando se o banco está funcionando

        return new HikariDataSource(config);
    }
}
