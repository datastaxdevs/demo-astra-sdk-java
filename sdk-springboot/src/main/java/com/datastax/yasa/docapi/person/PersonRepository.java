package com.datastax.yasa.docapi.person;

import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.datastax.astra.sdk.AstraClient;
import com.datastax.stargate.sdk.doc.ApiDocument;
import com.datastax.stargate.sdk.doc.StargateDocumentRepository;
import com.datastax.stargate.sdk.doc.domain.SearchDocumentQuery;

@Repository
public class PersonRepository extends StargateDocumentRepository<Person> {
    
    /**
     * Constructor from {@link AstraClient}.
     * 
     * @param astraClient
     *      client for Astra
     */
    public PersonRepository(AstraClient astraClient) {
        super(astraClient.getStargateClient(), 
              astraClient.cqlSession().getKeyspace().get().toString());
    }
    
    @PostConstruct
    public void generateBitData() {
        //Address a1 = new Address(20, "Champ Elysees", "PARIS", 75008);
        //create(new Person("Cedrick", "Lunven", "lala@hotmail.com", Arrays.asList(a1)));
        //create(new Person("John", "Connor", "jc@hotmail.com", Arrays.asList(a1)));
        //create(new Person("RAM", "RAM", "jc@hotmail.com", Arrays.asList(a1)));
    }
    
    /**
     * Sample of custom code
     * @param lastName
     * @return
     */
    public Stream<ApiDocument<Person>> findPersonByLastName(String lastName) {
        return search(SearchDocumentQuery.builder()
                .where("lastName").isEqualsTo(lastName)
                .build());
    }
    
    
}
