package com.ironhack.hellomonsterjpa.demo;


import com.ironhack.hellomonsterjpa.model.Monster;
import com.ironhack.hellomonsterjpa.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MonsterDataLoader  implements CommandLineRunner {

    @Autowired
    private MonsterRepository monsterRepository;

    @Override
    public void run(String... args) throws Exception {

        Monster dragon = new Monster("Dragon", 10, "Fire", true);
        Monster goblin = new Monster("Goblin", 2, "Mischief", false);
        Monster golem = new Monster("Golem", 7, "Earth", false);
        Monster kaiju = new Monster("Kaiju", 8, "Humanoid", true); // Kaiju N.8 //TODO watch it
        Monster kobold = new Monster("Kobold", 1, "Humanoid", false);
        Monster koboldKing = new Monster("Kobold King", 5, "Humanoid", true);
        var listOfMonster = List.of(dragon, goblin, golem, kaiju, kobold, koboldKing);
        monsterRepository.saveAll(listOfMonster);


        System.out.println("Printing all monsters\n");
        var monsters = monsterRepository.findAll();
        for (Monster monster : monsters){
            System.out.println(monster);
        }
        System.out.println("Finding by name\n");
        var kaijuX = monsterRepository.findByName("Dragon");
        System.out.println(kaijuX);

        System.out.println("Finding by type\n");
        var humanoids = monsterRepository.findByType("Humanoid");
        for (Monster humanoid : humanoids){
            System.out.println(humanoid);
        }
        System.out.println("Finding by level\n");
        var level5 = monsterRepository.findByLevelGreaterThanEqual(5);
        for (Monster monster : level5){
            System.out.println(monster);
        }


        System.out.println("Finding by name starting with\n");
        var nameStartsWithK = monsterRepository.findByNameStartingWith("K");
        for (Monster monster : nameStartsWithK){
            System.out.println(monster);
        }


        System.out.println("Finding by name containing and is legendary\n");
        var nameContainsOAndLegendary = monsterRepository
                .findByNameContainingIgnoreCaseAndAndIsLegendary("ol", true);
        for (Monster monster : nameContainsOAndLegendary){
            System.out.println(monster);
        }

        System.out.println("Finding first by name\n");
        var firstKobold = monsterRepository.findFirstByName("Kobold");
        if(firstKobold.isPresent()){
            System.out.println(firstKobold.get());
        }

        System.out.println("Finding by id and is legendary\n");
        var koboldKingLegendary = monsterRepository.findByIdAndIsLegendary(6L, true);
        if(koboldKingLegendary.isPresent()){
            System.out.println(koboldKingLegendary.get());
        }

        System.out.println("Not finding by id and is legendary\n");
        var koboldFighter = monsterRepository.findByIdAndIsLegendary(5L, true);
        if (koboldFighter.isEmpty()){
            System.out.println("Kobold Fighter not found");
        }

        System.out.println("Finding by level range\n");
        var levelRange = monsterRepository.findByLevelRange(5, 8);
        for (Monster monster : levelRange){
            System.out.println(monster);
        }

        System.out.println("Finding by name and level greater than\n");
        var nameAndLevel = monsterRepository.findByNameAndLevelGreaterThan("Kobold King", 4);
        for (Monster monster : nameAndLevel){
            System.out.println(monster);
        }
    }
}
