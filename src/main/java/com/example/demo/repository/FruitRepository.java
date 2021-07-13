package com.example.demo.repository;

import com.example.demo.models.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// JPARepository extends PagingAndSorting extends Crud interface
// An interface is a collection of abstract methods; whoever implements does the logic
// Need to specify entity type, id type
public interface FruitRepository extends JpaRepository<Fruit, Long> {
    List<Fruit> findAll();

    Optional<Fruit> findById(Long id);

    List<Fruit> findByName(String name);

    List<Fruit> findByInSeason(Boolean inSeason);
}
