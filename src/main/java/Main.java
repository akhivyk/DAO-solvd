import com.solvd.farm.dao.mysql.FarmDAO;
import com.solvd.farm.dao.mysql.MySQLConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws SQLException {
        MySQLConnectionPool connPool = new MySQLConnectionPool(10);
        Scanner in = new Scanner(System.in);
        int chooseAction = 0;
        FarmDAO farmDAO = new FarmDAO();
        while (chooseAction != 7) {
            logger.info("""
                    Выберите действие:
                    1 - Вывод всей информации о ферме
                    2 - Добавить новое животное
                    3 - Клонировать животное
                    4 - Изменить животное
                    5 - Удалить животное (RIP)
                    6 - Выход""");
            chooseAction = Integer.parseInt(in.nextLine());
            switch (chooseAction) {
                case 1 -> farmDAO.getAllInformation(1, connPool);
                case 2 -> farmDAO.createAnimal(in, connPool);
                case 3 -> farmDAO.cloneAnimal(in, connPool);
                case 4 -> farmDAO.updateAnimal(in, connPool);
                case 5 -> farmDAO.removeAnimal(in, connPool);
                case 6 -> {
                    logger.info("Bye - bye..");
                    chooseAction = 7;
                }
                default -> logger.info("Такого варианта нет.");
            }
        }
    }
}
