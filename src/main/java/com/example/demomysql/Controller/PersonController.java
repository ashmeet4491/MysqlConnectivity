package com.example.demomysql.Controller;

import com.example.demomysql.Model.Person;
import com.example.demomysql.Request.CreatePersonRequest;
import com.example.demomysql.Service.PersonService;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController
{


    @Autowired
    PersonService personService;

    private  static Logger logger=LoggerFactory.getLogger(PersonController.class);
    @PostMapping("/person")
    public void  CreatePerson(@RequestBody @Valid CreatePersonRequest personRequest)
    {
        //validations and exception Handling we have outsourced.
        //invoking service classes function
        personService.createPerson(personRequest);



     //   return  new ResponseEntity<>(new Person(), HttpStatus.CREATED);
    }
    //Exception handler



    public static void main(String[] args)
    {



       /* Person p=new Person();
        p.setAge(10);
        p.setId(1);
        p.setFirstname("Ashmeet");
        p.setLastname("Kaur");*/

       /*builder return the person builder object.This builder
        is easier to design*/
     //    Person person=new Person();
        Person.PersonBuilder personBuilder=Person.builder();
        personBuilder.age(10).firstname("ABC").lastname("xyz").id(1);

        Person person=setFn(personBuilder);



    }
   public static  Person setFn(Person.PersonBuilder personBuilder)
   {
       int age=10;
       return personBuilder.age(age).build();
   }








}
