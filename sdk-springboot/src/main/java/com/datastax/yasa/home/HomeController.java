package com.datastax.yasa.home;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.datastax.stargate.sdk.doc.ApiDocument;
import com.datastax.yasa.docapi.person.Person;
import com.datastax.yasa.docapi.person.PersonRepository;

/**
 * Home Controller, we want to show the gate.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
@Controller
public class HomeController {

    /** View name. */
    private static final String HOME_VIEW = "home";
    
    /** Data Access. */
    private final PersonRepository homeRepository;
    
    /**
     * Injection with Constructor.
     */
    public HomeController(PersonRepository homeRepository) {
		this.homeRepository = homeRepository;
	}
    
    @PostConstruct
    public void populateChevonsAndCatalog() {
    	// Load Objects coming from the DB
    }

	@GetMapping("/")
    public String show(Model model) 
    throws Exception {
	    List<ApiDocument<Person>> persons =  homeRepository
                .findAll().collect(Collectors.toList());
        model.addAttribute("persons",persons);
        return HOME_VIEW;
    }
}
