package com.datastax.yasa;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.datastax.astra.sdk.AstraClient;
import com.datastax.stargate.sdk.doc.CollectionClient;
import com.datastax.yasa.docapi.person.Address;
import com.datastax.yasa.docapi.person.Person;
import com.datastax.yasa.docapi.person.PersonRepository;

@SpringBootTest
public class T03_DocumentApiTest {
    
    @Autowired
    private AstraClient astraClient;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Test
    public void should_ns1_exist() {
        Assertions.assertTrue(astraClient
                   .apiStargateDocument()
                   .namespace("ns1")
                   .exist());
    }
    
    @Test
    public void should_delete_collection() {
        if (astraClient.apiStargateDocument()
                .namespace("ns1")
                .collection("person").exist()) {
            astraClient.apiStargateDocument()
                   .namespace("ns1")
                   .collection("person")
                   .delete();
        }
        
        System.out.println("Available Collections:\n-----");
        astraClient.apiStargateDocument()
                   .namespace("ns1")
                   .collectionNames()
                   .forEach(System.out::println);
    }
    
    @Test
    public void should_create_document() {
        Address a1 = new Address(20, "Champ Elysees", "PARIS", 75008);
        Address a2 = new Address(10, "YYIYI", "SANTA CLARA", 75008);
        Person p1  = new Person("RAM", "RAM", "ram@hotmail.com", Arrays.asList(a1, a2));
        String docId = personRepository.create(p1);
        
        personRepository.findAllPageable()
                        .getResults()
                        .stream().forEach(doc -> {
           System.out.println(doc.getDocumentId() + ":" + doc.getDocument().getEmail());
        });
    }
/*
@Test
public void findAll() {
    personRepo.findAll().forEach(apiDoc -> {
        System.out.println(apiDoc.getDocumentId() + ":" + apiDoc.getDocument());
    });
}

@Test
public void findByLastName() {
    personRepo.findPersonByLastName("RAM").forEach(apiDoc -> {
        System.out.println(apiDoc.getDocumentId() + ":" + apiDoc.getDocument());
    });
}
*/
    
}
