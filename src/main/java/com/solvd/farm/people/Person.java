package com.solvd.farm.people;

public abstract class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("Имя не может быть пустым.");
        }
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i))) {
                throw new Exception("Не правильный формат ввода имени.");
            }
        }
        this.name = name;
    }

    public void setAge(int age) throws Exception {
        if (age < 18) {
            throw new Exception("Возраст введен некорректно.");
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
