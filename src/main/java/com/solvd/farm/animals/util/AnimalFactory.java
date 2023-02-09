package com.solvd.farm.animals.util;

import com.solvd.farm.animals.*;

public class AnimalFactory {
    public Animal createEntityAnimal (AnimalType animalType) {
        Animal animal = null;

        switch (animalType) {
            case COW -> animal = new Cow();
            case HEN -> animal = new Hen();
            case GOAT -> animal = new Goat();
            case HORSE -> animal = new Horse();
            case SHEEP -> animal = new Sheep();
        }

        return animal;
    }
}
