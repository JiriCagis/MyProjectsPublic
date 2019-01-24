package structural.composite;

import java.util.ArrayList;

/**
 * Created by osboxes on 07/01/18.
 */
public class CompositeExample {

    interface Animal {
        void makeSound();
    }

    class Dog implements Animal{

        @Override
        public void makeSound() {
            System.out.println("HAF HAF !!");
        }
    }

    class Cow implements Animal{

        @Override
        public void makeSound() {
            System.out.println("Buuuuu !!");
        }
    }

    class AnimalGang implements Animal{

        private ArrayList<Animal> childAnimals = new ArrayList<>();

        @Override
        public void makeSound() {
            for(Animal animal:childAnimals){
                animal.makeSound();
            }
        }

        public void add(Animal animal){
            childAnimals.add(animal);
        }

        public void remove(Animal animal){
            childAnimals.remove(animal);
        }
    }

    public CompositeExample(){
        Dog dog = new Dog();
        Cow cow = new Cow();

        AnimalGang dogGang = new AnimalGang();
        dogGang.add(dog);
        dogGang.add(dog);
        dogGang.add(dog);

        AnimalGang cowGang = new AnimalGang();
        cowGang.add(cow);
        cowGang.add(cow);
        cowGang.add(cow);

        AnimalGang allAnimalsGang = new AnimalGang();
        allAnimalsGang.add(dogGang);
        allAnimalsGang.add(cowGang);

        allAnimalsGang.makeSound();
    }

    public static void main(String args[]){
        new CompositeExample();
    }
}
