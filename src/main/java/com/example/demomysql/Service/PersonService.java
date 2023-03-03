package com.example.demomysql.Service;

import com.example.demomysql.Model.Person;
import com.example.demomysql.Repository.PersonRepository;
import com.example.demomysql.Request.CreatePersonRequest;
import com.example.demomysql.utils.BadPersonRequestExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service //defines it as a bean
public class PersonService {

@Autowired
PersonRepository personRepository;

    public void createPerson(CreatePersonRequest personRequest){

        Person person=personRequest.to();//this will return the person object.
        if (person.getAge() == null){

          Integer age=calculateAgeFromDob(person.getDob());
            person.setAge(age);
        }

        personRepository.createPerson(person);

    }

    private Integer calculateAgeFromDob(String dob){
        if(dob==null)
        {
            return null;
        }

        LocalDate dobDate=LocalDate.parse(dob);

            LocalDate currentDate= LocalDate.now();

        return Period.between(dobDate,currentDate).getYears();



    }

    public List<Person> deletePerson(int id) throws BadPersonRequestExceptions {
        List<Person> list=personRepository.getPerson(id);
         boolean val=personRepository.deletePerson(id);
         if(val)
         {
             return list;
         }

         throw new BadPersonRequestExceptions("Wrong id");





    }

    public List<Person> getAllPerson()
    {
         return personRepository.getAllPerson();

    }

    public List<Person> getPersonById(int id)
    {
        return personRepository.getPerson(id);
    }


}
