package exercise.controller;

import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Person;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Person> show(@PathVariable long id) {
        return ResponseEntity.of(personRepository.findById(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Person> index() {
        var result = personRepository.findAll();
        System.out.println("!!! list = " + result);
        return result;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person data) {
        Person createdPerson = new Person();
        createdPerson.setFirstName(data.getFirstName());
        createdPerson.setLastName(data.getLastName());
        personRepository.save(createdPerson);
        System.out.println("!!! data = " + data);
        System.out.println("!!! createdPerson = " + createdPerson);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
        return createdPerson;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> destroy(@PathVariable String id) {
        var person = personRepository.findById(Long.parseLong(id));
        return person.map(p -> {
            personRepository.delete(p);
            return ResponseEntity.ok().body(p);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
