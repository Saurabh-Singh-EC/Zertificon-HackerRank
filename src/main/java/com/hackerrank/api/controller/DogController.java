package com.hackerrank.api.controller;

import com.hackerrank.api.model.Dog;
import com.hackerrank.api.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dog")
public class DogController {
    private final DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Dog> getAllDog() {
        return dogService.getAllDog();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        Optional<Long> optionalDog = Optional.ofNullable(dog.getId());
        if (optionalDog.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return new ResponseEntity<>(dogService.createNewDog(dog), HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dog> getDogById(@PathVariable String id) {
        try {
            long dogId = Long.parseLong(id);
            Optional<Dog> foundDog = dogService.getDogById(dogId);
            return foundDog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (NumberFormatException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
