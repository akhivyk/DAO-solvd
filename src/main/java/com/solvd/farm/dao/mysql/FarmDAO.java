package com.solvd.farm.dao.mysql;

import com.solvd.farm.Farm;
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

    public void getAllInformation(int idFarm, MySQLConnectionPool connPool) throws SQLException {
        cowDAO.getAllFarmCows(idFarm, connPool).forEach(logger::info);
        goatDAO.getAllFarmGoats(idFarm, connPool).forEach(logger::info);
        henDAO.getAllFarmHen(idFarm, connPool).forEach(logger::info);
        horseDAO.getAllFarmHorse(idFarm, connPool).forEach(logger::info);
        sheepDAO.getAllFarmSheep(idFarm, connPool).forEach(logger::info);
    }

    public void createAnimal(Scanner in, MySQLConnectionPool connPool) {
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
                logger.info("Корова добавлена на ферму, id - " + cowDAO.createCow(age, name, weight, connPool).getIdCow());
            }
            case 2 -> {
                logger.info("Введите возраст животного: ");
                int age = Integer.parseInt(in.nextLine());
                logger.info("Введите кличку животного: ");
                String name = in.nextLine();
                logger.info("Введите вес животного: ");
                double weight = Double.parseDouble(in.nextLine());
                logger.info("Курица добавлена на ферму, id - " + henDAO.createHen(age, name, weight, connPool).getIdHen());
            }
            case 3 -> {
                logger.info("Введите возраст животного: ");
                int age = Integer.parseInt(in.nextLine());
                logger.info("Введите кличку животного: ");
                String name = in.nextLine();
                logger.info("Введите вес животного: ");
                double weight = Double.parseDouble(in.nextLine());
                logger.info("Лошадь добавлена на ферму, id - " + horseDAO.createHorse(age, name, weight, connPool).getIdHorse());
            }
            case 4 -> logger.info("Вы вернулись в главное меню.");
        }
    }

    public void cloneAnimal(Scanner in, MySQLConnectionPool connPool) {
        logger.info("Выберите животное, которое хотите клонировать:\n1 - Корова\n2 - Курица\n3 - Лошадь\n4 - Вернуться в меню");
        int chooseAnimal = Integer.parseInt(in.nextLine());
        switch (chooseAnimal) {
            case 1 -> {
                logger.info("Введите кличку коровы, которую хотите клонировать:");
                String oldName = in.nextLine();
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Корова клонирована, новая кличка - " + newName + ", id - " +
                        "" + cowDAO.cloneCow(oldName, newName, connPool).getIdCow());
            }
            case 2 -> {
                logger.info("Введите кличку курицы, которую хотите клонировать:");
                String oldName = in.nextLine();
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Лошадь клонирована, новая кличка - " + newName + ", id - " +
                        "" + henDAO.cloneHen(oldName, newName, connPool).getIdHen());
            }
            case 3 -> {
                logger.info("Введите кличку лошади, которую хотите клонировать:");
                String oldName = in.nextLine();
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Лошадь клонирована, новая кличка - " + newName + ", id - " +
                        "" + horseDAO.cloneHorse(oldName, newName, connPool).getIdHorse());
            }
            case 4 -> logger.info("Вы вернулись в главное меню.");
        }
    }

    public void updateAnimal(Scanner in, MySQLConnectionPool connPool) {
        logger.info("Выберите животное, которое хотите изменить:\n1 - Корова\n2 - Курица\n3 - Лошадь\n4 - Вернуться в меню");
        int chooseAnimal = Integer.parseInt(in.nextLine());
        switch (chooseAnimal) {
            case 1 -> {
                logger.info("Введите кличку коровы, которую хотите изменить:");
                String oldName = in.nextLine();
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Введите новый вес животного:");
                double weight = Double.parseDouble(in.nextLine());
                logger.info("Введите новый возраст животного:");
                int age = Integer.parseInt(in.nextLine());
                boolean b = cowDAO.updateCow(connPool, oldName, newName, weight, age);
                if (b) {
                    logger.info("Информация успешно обновлена.");
                }
            }
            case 2 -> {
                logger.info("Введите кличку курицы, которую хотите изменить:");
                String oldName = in.nextLine();
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Введите новый вес животного:");
                double weight = Double.parseDouble(in.nextLine());
                logger.info("Введите новый возраст животного:");
                int age = Integer.parseInt(in.nextLine());
                boolean b = henDAO.updateHen(connPool, oldName, newName, weight, age);
                if (b) {
                    logger.info("Информация успешно обновлена.");
                }
            }
            case 3 -> {
                logger.info("Введите кличку лошади, которую хотите изменить:");
                String oldName = in.nextLine();
                logger.info("Введите новую кличку:");
                String newName = in.nextLine();
                logger.info("Введите новый вес животного:");
                double weight = Double.parseDouble(in.nextLine());
                logger.info("Введите новый возраст животного:");
                int age = Integer.parseInt(in.nextLine());
                boolean b = horseDAO.updateHorse(connPool, oldName, newName, weight, age);
                if (b) {
                    logger.info("Информация успешно обновлена.");
                }
            }
            case 4 -> logger.info("Вы вернулись в главное меню.");
        }
    }

    public void removeAnimal(Scanner in, MySQLConnectionPool connPool) {
        logger.info("Выберите животное, которое хотите изменить:\n1 - Корова\n2 - Курица\n3 - Лошадь\n4 - Вернуться в меню");
        int chooseAnimal = Integer.parseInt(in.nextLine());
        switch (chooseAnimal) {
            case 1 -> {
                logger.info("Введите кличку коровы, которую хотите удалить:");
                String name = in.nextLine();
                logger.info("Вы точно хотите удалить животное?\ny - да\nn - нет");
                if (in.nextLine().equals("y")) {
                    boolean b = cowDAO.removeCow(name);
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
                    boolean b = henDAO.removeHen(name);
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
                    boolean b = horseDAO.removeHorse(name);
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
