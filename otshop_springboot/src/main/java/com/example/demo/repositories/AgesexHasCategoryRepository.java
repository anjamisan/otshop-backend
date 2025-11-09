package com.example.demo.repositories;

import model.Agesex;
import model.Category;
import model.AgesexHasCategory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgesexHasCategoryRepository extends JpaRepository<AgesexHasCategory, Integer> {

    boolean existsByAgesexAndCategory(Agesex agesex, Category category);
    
    //nadji sve kategorije za odredjeni pol
    List<AgesexHasCategory> findByAgesex(Agesex agesex);
    
    //proveri jel postoji poveznica
    Optional<AgesexHasCategory> findByAgesexAndCategory(Agesex agesex, Category category);
}
