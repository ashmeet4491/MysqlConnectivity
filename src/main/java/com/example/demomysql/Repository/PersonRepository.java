package com.example.demomysql.Repository;

import com.example.demomysql.Model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository{


    private Connection connection;
    private PreparedStatement preparedStatement;


    PersonRepository(Connection connection)
    {
        this.connection=connection;
        createTable();

        try {
            this.preparedStatement=connection.prepareStatement(
                    "Insert into person(first_name,last_name,age,dob) VALUES(?,?,?,?)");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

 private static Logger logger= LoggerFactory.getLogger(PersonRepository.class);

    public  void createPerson(Person p)  {
//here it will do some db operations

    //"create table person(id int primary_key,first_name varchar(40),last_name varchar(40),age int ,dob varchar(12) );

    try {




        preparedStatement.setString(1,p.getFirstname());
        preparedStatement.setString(2,p.getLastname());
        preparedStatement.setInt(3,p.getAge());
        preparedStatement.setString(4,p.getDob());

       int result2= preparedStatement.executeUpdate();
       
       logger.info("result {}",result2);


    } catch (SQLException e) {
        throw new RuntimeException(e);
    }



}

    public  void createPersonStatic(Person person)  {
//here it will do some db operations

        //"create table person(id int primary_key,first_name varchar(40),last_name varchar(40),age int ,dob varchar(12) );

        try {

            Statement statement =connection.createStatement();
            int result=statement.executeUpdate("Insert into person(id,first_name,last_name,age,dob) " +
                    "VALUES(1,'Ashmeet','Kaur',10,'1998-12-12')");
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
        statement.execute("Create table if not exists person(id int primary key auto_increment,first_name varchar(40),last_name varchar(30)," +
                "age int,dob varchar(12))");

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}

    public List<Person> getPerson(int id)
    {
        List<Person>ls=new ArrayList<>();

        PreparedStatement statement= null;
        try {
            statement = connection.prepareStatement("Select * from person where id=?");
            statement.setInt(1,id);
            ResultSet resultSet =statement.executeQuery();

            while(resultSet.next())
            {
                Person person=executeResultSet(resultSet);
                ls.add(person);
            }

            /*We will be getting result in the resultset but we need it in form of List .
             How can we do that?Result set is nothing but it is the table simply Row and coloum*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ls;



    }
    public List<Person> getAllPerson()
    {
        List<Person>ls=new ArrayList<>();

        Statement statement= null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery("Select * from Person");
            while(resultSet.next())
            {
                Person person=executeResultSet(resultSet);
                ls.add(person);
            }


            /*We will be getting result in the resultset but we need it in form of List .
             How can we do that?Result set is nothing but it is the table simply Row and coloum*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      return ls;
    }

    public Person executeResultSet(ResultSet resultSet) throws SQLException {

            int a=resultSet.getInt(1);
            String b=resultSet.getString("first_name");
            String c=resultSet.getString("last_name");
            int d=resultSet.getInt("age");
            String e=resultSet.getString("dob");

            Person person= Person.builder().id(a).firstname(b).lastname(c).age(d).dob(e).build();
            return  person;



    }

}
