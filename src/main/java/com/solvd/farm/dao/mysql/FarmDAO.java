package com.solvd.farm.dao.mysql;

import com.solvd.farm.Farm;
import com.solvd.farm.animals.Cow;
import com.solvd.farm.animals.Hen;
import com.solvd.farm.animals.Horse;
import com.solvd.farm.dao.IFarmDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FarmDAO extends MySqlDAO implements IFarmDAO {

    private static final Logger logger = LogManager.getLogger();
    private final CowDAO cowDAO = new CowDAO();
    private final GoatDAO goatDAO = new GoatDAO();
    private final HenDAO henDAO = new HenDAO();
    private final HorseDAO horseDAO = new HorseDAO();
    private final SheepDAO sheepDAO = new SheepDAO();

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
        cowDAO.getAllFarmCows(idFarm).forEach(logger::info);
        goatDAO.getAllFarmGoats(idFarm).forEach(logger::info);
        henDAO.getAllFarmHens(idFarm).forEach(logger::info);
        horseDAO.getAllFarmHorses(idFarm).forEach(logger::info);
        sheepDAO.getAllFarmSheep(idFarm).forEach(logger::info);
    }

    public void createAnimal(Scanner in) {
        logger.info("Выберите животное, которое хотите добавить на ферму:\n1 - Корова\n2 - Курица\n3 - Лошадь\n4 - Вернуться в меню");
        int chooseAnimal = Integer.parseInt(in.nextLine());
        switch (chooseAnimal) {
            case 1 -> {
                logger.info("Введите возраст животного: ");
                int age = Integer.parseInt(in.nextLine());
                logger.info("Введите кличку животного: ");
                String name = in.nextLine();
                logger.info("Введите вес животного: ");
                double weight = Double.parseDouble(in.nextLine());
                Cow c = new Cow(age, name, weight);
                logger.info("Корова добавлена на ферму, id - " + cowDAO.createCow(c));
            }
            case 2 -> {
                logger.info("Введите возраст животного: ");
                int age = Integer.parseInt(in.nextLine());
                logger.info("Введите кличку животного: ");
                String name = in.nextLine();
                logger.info("Введите вес животного: ");
                double weight = Double.parseDouble(in.nextLine());
                Hen h = new Hen(age, name, weight);
                logger.info("Курица добавлена на ферму, id - " + henDAO.createHen(h));
            }
            case 3 -> {
                logger.info("Введите возраст животного: ");
                int age = Integer.parseInt(in.nextLine());
                logger.info("Введите кличку животного: ");
                String name = in.nextLine();
                logger.info("Введите вес животного: ");
                double weight = Double.parseDouble(in.nextLine());
                Horse h = new Horse(age, name, weight);
                logger.info("Лошадь добавлена на ферму, id - " + horseDAO.createHorse(h));
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
                        "" + cowDAO.cloneCow(oldName, newName));
            }
            case 2 -> {
                logger.info("Введите кличку курицы, которую хотите клонировать:");
                String oldName = in.nextLine();
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Лошадь клонирована, новая кличка - " + newName + ", id - " +
                        "" + henDAO.cloneHen(oldName, newName));
            }
            case 3 -> {
                logger.info("Введите кличку лошади, которую хотите клонировать:");
                String oldName = in.nextLine();
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Лошадь клонирована, новая кличка - " + newName + ", id - " +
                        "" + horseDAO.cloneHorse(oldName, newName));
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
                boolean b = cowDAO.updateCow(newName, weight, age, id);
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
                boolean b = henDAO.updateHen(newName, weight, age, id);
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
                boolean b = horseDAO.updateHorse(newName, weight, age, id);
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
                    boolean b = cowDAO.removeCowName(name);
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
                    boolean b = henDAO.removeHenName(name);
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
                    boolean b = horseDAO.removeHorseName(name);
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

    public CowDAO getCowDAO() {
        return cowDAO;
    }

    public GoatDAO getGoatDAO() {
        return goatDAO;
    }

    public HenDAO getHenDAO() {
        return henDAO;
    }

    public HorseDAO getHorseDAO() {
        return horseDAO;
    }

    public SheepDAO getSheepDAO() {
        return sheepDAO;
    }
}
