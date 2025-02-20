package com.example.arquiteturaSpring;
import com.example.arquiteturaSpring.todos.*;
import jakarta.persistence.EntityManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.sql.Connection;

public class ExemploInjecaoDependencia {
    public static void main(String[] args) throws Exception {

        //Exemplo sem Injeção de depêndencia.

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("url");
        dataSource.setUsername("Username");
        dataSource.setPassword("password");

        Connection connection = dataSource.getConnection();

        EntityManager entityManager = null;

        TodoRepository repository = null;
        TodoValidator todoValidator=  new TodoValidator(repository);
        MailSender  mailSender = new MailSender();

        TodoService service = new TodoService(repository,todoValidator, mailSender);
    }
}