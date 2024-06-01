package com.ironhack.hellomonsterjpa.repository;

import com.ironhack.hellomonsterjpa.model.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MonsterRepositoryTest {

    @Autowired
    private MonsterRepository monsterRepository;

    @BeforeEach
    void setup() {
        monsterRepository.deleteAll();

        Monster m1= new Monster("Dragon", 10, "Fire", true);
        Monster m2= new Monster("Goblin", 2, "Mischief", false);
        Monster m3= new Monster("Golem", 7, "Earth", false);

        monsterRepository.saveAll(List.of(m1, m2, m3));
    }


    @Test
    void findByName() {
        List<Monster> monsters = monsterRepository.findByName("Dragon");
        assertEquals(1, monsters.size());
        assertEquals("Dragon", monsters.get(0).getName());
    }
    @Test
    void findByLevelRange_ShouldReturnMonsters_WhenLevelInRange() {
        List<Monster> monsters = monsterRepository.findByLevelRange(4, 11);

        assertThat(monsters).hasSize(2);
        assertThat(monsters).extracting("name")
                .containsExactlyInAnyOrder("Golem", "Dragon");
    }


}