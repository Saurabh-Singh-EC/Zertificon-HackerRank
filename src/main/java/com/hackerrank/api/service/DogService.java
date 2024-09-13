package com.hackerrank.api.service;

import com.hackerrank.api.model.Dog;
import java.util.List;
import java.util.Optional;

public interface DogService {

  List<Dog> getAllDog();

  Dog createNewDog(Dog dog);

  Optional<Dog> getDogById(Long id);
}
