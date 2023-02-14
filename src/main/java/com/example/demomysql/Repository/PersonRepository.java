package com.example.demomysql.Repository;

import com.example.demomysql.Model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class PersonRepository{


    private Connection connection;



    PersonRepository(Connection connection)
    {
        this.connection=connection;
        createTable();

    }

 private static Logger logger= LoggerFactory.getLogger(PersonRepository.class);

    public  void createPerson(Person person)  {
//here it will do some db operations

    //"create table person(id int primary_key,first_name varchar(40),last_name varchar(40),age int ,dob varchar(12) );

    try {

        Statement statement =connection.createStatement();
        boolean result=statement.execute("Insert into person(id,first_name,last_name,age,dob) " +
                "VALUES(1,'Ashmeet','Kaur',10,'1998-12-12)");
        logger.info("result {}",result);

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }



}

public  void createTable()
{
    try {
        Statement statement=connection.createStatement();
        //if not exist even if it exist it will not be called multiple times.
        statement.execute("Create table if not exists person(id int primary key,first_name varchar(40),last_name varchar(30)," +
                "age int,dob varchar(12))");

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}

}
