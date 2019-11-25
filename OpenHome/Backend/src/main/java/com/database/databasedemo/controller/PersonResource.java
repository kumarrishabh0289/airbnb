package com.database.databasedemo.controller;

import com.database.databasedemo.repository.PersonNotFound;
import com.database.databasedemo.repository.PersonSpringDataRepo;
import com.database.databasedemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PersonResource {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String secretKey = "openhome";

    @Autowired
    private PersonSpringDataRepo repo;

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
    public ResponseEntity<Object> createStudent(@RequestBody Person person) {
        Person savedPerson = repo.save(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPerson.getId()).toUri();

        return ResponseEntity.created(location).build();

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


        String name = payload.get(payload.keySet().toArray()[0]);

        System.out.println(name);

        String password = payload.get(payload.keySet().toArray()[1]);

        System.out.println(password);

        Optional<Person> person = Optional.ofNullable(repo.findByName(name));
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


                encodedNameAsToken = encoder.encode(name.concat(secretKey));




                System.out.println(encoder.matches(name.concat(secretKey),encodedNameAsToken));
                HashMap<String, String> map = new HashMap<>();
                map.put("token", encodedNameAsToken);
                System.out.println(encodedNameAsToken);
                map.put("name", name);
                //map.put("password", password);
                return map;
            }
            else{

                System.out.println("Password inValid");
                throw new PersonNotFound("Password Wrong");
            }


        }

    }
}
