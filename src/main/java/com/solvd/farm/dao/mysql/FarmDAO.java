package com.solvd.farm.dao.mysql;

import com.solvd.farm.Farm;
import com.solvd.farm.animals.Animal;
import com.solvd.farm.animals.Cow;
import com.solvd.farm.animals.Hen;
import com.solvd.farm.animals.Horse;
import com.solvd.farm.animals.util.AnimalFactory;
import com.solvd.farm.animals.util.AnimalType;
import com.solvd.farm.dao.IFarmDAO;
import com.solvd.farm.dao.util.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class FarmDAO extends DAO implements IFarmDAO {

    private static final Logger logger = LogManager.getLogger();
    private static DAOFactory daoFactory;

    static {
        daoFactory.createAllDAO();
    }

    @Override
    public Farm getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Farm entity) {

    }

    @Override
    public Farm createEntity(Farm entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<Farm> getAllFarms() {
        return null;
    }

    @Override
    public Farm getFarmById() {
        return null;
    }

    public void getAllInformation(int idFarm) {
        daoFactory.getCowDAO().getAllFarmCows(idFarm).forEach(logger::info);
        daoFactory.getGoatDAO().getAllFarmGoats(idFarm).forEach(logger::info);
        daoFactory.getHenDAO().getAllFarmHens(idFarm).forEach(logger::info);
        daoFactory.getHorseDAO().getAllFarmHorses(idFarm).forEach(logger::info);
        daoFactory.getSheepDAO().getAllFarmSheep(idFarm).forEach(logger::info);
    }

    public void createAnimal(Scanner in) {
        logger.info("Выберите животное, которое хотите добавить на ферму:\n1 - Корова\n2 - Курица\n3 - Лошадь\n4 - Вернуться в меню");
        int chooseAnimal = Integer.parseInt(in.nextLine());
        AnimalFactory animalFactory = new AnimalFactory();
        Animal animal = null;
        switch (chooseAnimal) {
            case 1 -> animal = animalFactory.createEntityAnimal(AnimalType.COW);
            case 2 -> animal = animalFactory.createEntityAnimal(AnimalType.HEN);
            case 3 -> animal = animalFactory.createEntityAnimal(AnimalType.HORSE);
        }
        switch (chooseAnimal) {
            case 1 -> {
                logger.info("Введите возраст животного: ");
                int age = Integer.parseInt(in.nextLine());
                logger.info("Введите кличку животного: ");
                String name = in.nextLine();
                logger.info("Введите вес животного: ");
                double weight = Double.parseDouble(in.nextLine());
                animal.setAge(age);
                animal.setName(name);
                animal.setWeight(weight);
                logger.info("Корова добавлена на ферму, id - " + daoFactory.getCowDAO().createCow((Cow) animal));
            }
            case 2 -> {
                logger.info("Введите возраст животного: ");
                int age = Integer.parseInt(in.nextLine());
                logger.info("Введите кличку животного: ");
                String name = in.nextLine();
                logger.info("Введите вес животного: ");
                double weight = Double.parseDouble(in.nextLine());
                animal.setAge(age);
                animal.setName(name);
                animal.setWeight(weight);
                logger.info("Курица добавлена на ферму, id - " + daoFactory.getHenDAO().createHen((Hen) animal));
            }
            case 3 -> {
                logger.info("Введите возраст животного: ");
                int age = Integer.parseInt(in.nextLine());
                logger.info("Введите кличку животного: ");
                String name = in.nextLine();
                logger.info("Введите вес животного: ");
                double weight = Double.parseDouble(in.nextLine());
                animal.setAge(age);
                animal.setName(name);
                animal.setWeight(weight);
                logger.info("Лошадь добавлена на ферму, id - " + daoFactory.getHorseDAO().createHorse((Horse) animal));
            }
            case 4 -> logger.info("Вы вернулись в главное меню.");
        }
    }

    public void cloneAnimal(Scanner in) {
        logger.info("Выберите животное, которое хотите клонировать:\n1 - Корова\n2 - Курица\n3 - Лошадь\n4 - Вернуться в меню");
        int chooseAnimal = Integer.parseInt(in.nextLine());
        switch (chooseAnimal) {
            case 1 -> {
                logger.info("Введите кличку коровы, которую хотите клонировать:");
                String oldName = in.nextLine();
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Корова клонирована, новая кличка - " + newName + ", id - " +
                        "" + daoFactory.getCowDAO().cloneCow(oldName, newName));
            }
            case 2 -> {
                logger.info("Введите кличку курицы, которую хотите клонировать:");
                String oldName = in.nextLine();
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Лошадь клонирована, новая кличка - " + newName + ", id - " +
                        "" + daoFactory.getHenDAO().cloneHen(oldName, newName));
            }
            case 3 -> {
                logger.info("Введите кличку лошади, которую хотите клонировать:");
                String oldName = in.nextLine();
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Лошадь клонирована, новая кличка - " + newName + ", id - " +
                        "" + daoFactory.getHorseDAO().cloneHorse(oldName, newName));
            }
            case 4 -> logger.info("Вы вернулись в главное меню.");
        }
    }

    public void updateAnimal(Scanner in) {
        logger.info("Выберите животное, которое хотите изменить:\n1 - Корова\n2 - Курица\n3 - Лошадь\n4 - Вернуться в меню");
        int chooseAnimal = Integer.parseInt(in.nextLine());
        switch (chooseAnimal) {
            case 1 -> {
                logger.info("Введите id коровы, которую хотите изменить:");
                int id = Integer.parseInt(in.nextLine());
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Введите новый вес животного:");
                double weight = Double.parseDouble(in.nextLine());
                logger.info("Введите новый возраст животного:");
                int age = Integer.parseInt(in.nextLine());
                boolean b = daoFactory.getCowDAO().updateCow(newName, weight, age, id);
                if (b) {
                    logger.info("Информация успешно обновлена.");
                }
            }
            case 2 -> {
                logger.info("Введите id курицы, которую хотите изменить:");
                int id = Integer.parseInt(in.nextLine());
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Введите новый вес животного:");
                double weight = Double.parseDouble(in.nextLine());
                logger.info("Введите новый возраст животного:");
                int age = Integer.parseInt(in.nextLine());
                boolean b = daoFactory.getHenDAO().updateHen(newName, weight, age, id);
                if (b) {
                    logger.info("Информация успешно обновлена.");
                }
            }
            case 3 -> {
                logger.info("Введите id лошади, которую хотите изменить:");
                int id = Integer.parseInt(in.nextLine());
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Введите новый вес животного:");
                double weight = Double.parseDouble(in.nextLine());
                logger.info("Введите новый возраст животного:");
                int age = Integer.parseInt(in.nextLine());
                boolean b = daoFactory.getHorseDAO().updateHorse(newName, weight, age, id);
                if (b) {
                    logger.info("Информация успешно обновлена.");
                }
            }
            case 4 -> logger.info("Вы вернулись в главное меню.");
        }
    }

    public void removeAnimal(Scanner in) {
        logger.info("Выберите животное, которое хотите изменить:\n1 - Корова\n2 - Курица\n3 - Лошадь\n4 - Вернуться в меню");
        int chooseAnimal = Integer.parseInt(in.nextLine());
        switch (chooseAnimal) {
            case 1 -> {
                logger.info("Введите кличку коровы, которую хотите удалить:");
                String name = in.nextLine();
                logger.info("Вы точно хотите удалить животное?\ny - да\nn - нет");
                if (in.nextLine().equals("y")) {
                    boolean b = daoFactory.getCowDAO().removeCowName(name);
                    if (b) {
                        logger.info("Корова удалена.");
                    }
                } else {
                    logger.info("Действие отменено.");
                }
            }
            case 2 -> {
                logger.info("Введите кличку курицы, которую хотите удалить:");
                String name = in.nextLine();
                logger.info("Вы точно хотите удалить животное?\ny - да\nn - нет");
                if (in.nextLine().equals("y")) {
                    boolean b = daoFactory.getHenDAO().removeHenName(name);
                    if (b) {
                        logger.info("Курица удалена.");
                    }
                } else {
                    logger.info("Действие отменено.");
                }
            }
            case 3 -> {
                logger.info("Введите кличку лошади, которую хотите удалить:");
                String name = in.nextLine();
                logger.info("Вы точно хотите удалить животное?\ny - да\nn - нет");
                if (in.nextLine().equals("y")) {
                    boolean b = daoFactory.getHorseDAO().removeHorseName(name);
                    if (b) {
                        logger.info("Лошадь удалена.");
                    }
                } else {
                    logger.info("Действие отменено.");
                }
            }
            case 4 -> logger.info("Вы вернулись в главное меню.");
        }
    }
}
