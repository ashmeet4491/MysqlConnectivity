package com.example.demomysql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DbConfig{

    @Bean
    public Connection getConnection()
    {

        try {
       return  DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbl_33person"
                    ,"root","password");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

