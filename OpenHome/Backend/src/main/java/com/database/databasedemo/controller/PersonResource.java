package com.database.databasedemo.controller;

import com.database.databasedemo.repository.PersonNotFound;
import com.database.databasedemo.repository.PersonSpringDataRepo;
import com.database.databasedemo.entity.Person;
import com.database.databasedemo.repository.PersonSpringDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.database.databasedemo.mail.SendMail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.mail.PasswordAuthentication;
import java.util.*;
import javax.mail.Authenticator;

import javax.mail.*;
import javax.mail.internet.*;


import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins="*")
@RestController
public class PersonResource {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String secretKey = "openhome";

    @Autowired
    private PersonSpringDataRepo repo;

    @RequestMapping(value = "/sendemail")
    public String sendEmail(@RequestBody Map<String, String> payload) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
        String subject = payload.get(payload.keySet().toArray()[0]);
        String recevier = payload.get(payload.keySet().toArray()[1]);
        String body = payload.get(payload.keySet().toArray()[2]);
        SendMail y = new SendMail();
        y.sendEmail(subject,recevier,body);
        return "Email sent successfully";
    }

    @GetMapping("/verifyUser/{id}")
    public String validatePerson(@PathVariable int id) {
        Optional<Person> person = repo.findById(id);

        if (!person.isPresent())
            throw new PersonNotFound("id-" + id);

        Person p = retrievePerson(id);
        p.setVerification("yes");
        repo.save(p);
        return "Your Email ID is verified And your Account is now active";
    }

    @GetMapping("/verifyemail/{email}")
    public Map<String, String> validatePersonWithEmail(@PathVariable String email) {
        Optional<Person> person = Optional.ofNullable(repo.findByEmail(email));

        if (!person.isPresent())
            throw new PersonNotFound("Not Present");

        String encodedNameAsToken = "";
        System.out.println("Password Validated");


        encodedNameAsToken = encoder.encode(person.get().getName().concat(secretKey));




        System.out.println(encoder.matches(person.get().getName().concat(secretKey),encodedNameAsToken));
        HashMap<String, String> map = new HashMap<>();
        map.put("token", encodedNameAsToken);
        System.out.println(encodedNameAsToken);
        map.put("name", person.get().getName());
        map.put("role", person.get().getRole());
        map.put("email", person.get().getEmail());
        map.put("id", String.valueOf(person.get().getId()));
        map.put("verified", String.valueOf(person.get().getVerification()));

        return map;
    }

    @GetMapping("/persons")
    public List<Person> retriveAllPersons(@RequestHeader HttpHeaders headers)
    {

//        System.out.println(headers.get("name").get(0));
//        System.out.println(headers.get("token").get(0));


        if(encoder.matches(headers.get("name").get(0).concat(secretKey),headers.get("token").get(0))){
            System.out.println("Token Validated");
            return repo.findAll();
        }
        else{
            throw new PersonNotFound("Invalid Token");
        }


    }

    @PostMapping("/persons")
    public ResponseEntity<Object> createStudent(@RequestBody Person person) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
        System.out.println("Thisis" + repo.findByEmail(person.getEmail()));
        Optional<Person>  p = Optional.ofNullable(repo.findByEmail(person.getEmail()));
        if (!p.isPresent()) {


            Person savedPerson;
            savedPerson = repo.save(person);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedPerson.getId()).toUri();
            System.out.println(savedPerson.getEmail());
            String subject = "Please Verify your Email ID with Open Home";
            String recevier = savedPerson.getEmail();
            String body = "Hi " + savedPerson.getName() + ",\n\nPlease verify your email with us by clicking on below link:\n http://localhost:8181/verifyUser/" + savedPerson.getId();
            SendMail y = new SendMail();
            y.sendEmail(subject, recevier, body);


            return ResponseEntity.created(location).build();

        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/persons/{id}")
    public Person retrievePerson(@PathVariable int id) {
        Optional<Person> person = repo.findById(id);

        if (!person.isPresent())
            throw new PersonNotFound("id-" + id);

        return person.get();
    }



    @PostMapping("/authenticate")
    public Map<String, String> auth(@RequestBody Map<String, String> payload) {


        String email = payload.get(payload.keySet().toArray()[0]);

        System.out.println("email"+email);

        String password = payload.get(payload.keySet().toArray()[1]);

        System.out.println("password"+password);

        Optional<Person> person = Optional.ofNullable(repo.findByEmail(email));
        if (!person.isPresent()){

            System.out.println("Person is not present in DB");
            throw new PersonNotFound("Not Found" +person);
        }
        else{
            //Person is Present in DB
            System.out.println("Person From DB");
            System.out.println(person);

            if(password.equals(person.get().getPassword())){
                String encodedNameAsToken = "";
                System.out.println("Password Validated");


                encodedNameAsToken = encoder.encode(person.get().getName().concat(secretKey));




                System.out.println(encoder.matches(person.get().getName().concat(secretKey),encodedNameAsToken));
                HashMap<String, String> map = new HashMap<>();
                map.put("token", encodedNameAsToken);
                System.out.println(encodedNameAsToken);
                map.put("name", person.get().getName());
                map.put("role", person.get().getRole());
                map.put("email", person.get().getEmail());
                map.put("id", String.valueOf(person.get().getId()));
                map.put("verified", String.valueOf(person.get().getVerification()));

                return map;
            }
            else{

                System.out.println("Password inValid");
                throw new PersonNotFound("Password Wrong");
            }
        }

    }
}
