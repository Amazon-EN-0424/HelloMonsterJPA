package com.ironhack.hellomonsterjpa.repository;


import com.ironhack.hellomonsterjpa.model.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {

    // JPA Query Methods
    List<Monster> findByName(String name);
    List<Monster> findByType(String type);
    List<Monster> findByLevelGreaterThanEqual(Integer level);
    List<Monster> findByNameStartingWith(String prefix);
    List<Monster> findByNameContainingIgnoreCaseAndAndIsLegendary(String name, Boolean isLegendary);
    Optional<Monster> findFirstByName(String name);
    Optional<Monster> findByIdAndIsLegendary(Long id, Boolean isLegendary);


    // JPQL
    @Query("SELECT m FROM Monster m WHERE m.level BETWEEN :minLevel AND :maxLevel")
    List<Monster> findByLevelRange(@Param("minLevel") Integer minLevel,
                                   @Param("maxLevel") Integer maxLevel);

    // Native Query
    @Query(value = "SELECT * FROM monsters WHERE name = :name AND monster_level > :level", nativeQuery = true)
    List<Monster> findByNameAndLevelGreaterThan(@Param("name") String name, @Param("level") Integer level);

}
