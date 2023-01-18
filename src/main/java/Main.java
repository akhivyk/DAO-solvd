import com.solvd.farm.dao.mysql.CowDAO;
import com.solvd.farm.dao.mysql.FarmDAO;
import com.solvd.farm.dao.mysql.MySQLConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws SQLException {
        MySQLConnectionPool connPool = new MySQLConnectionPool("jdbc:mysql://52.59.193.212:3306/Farm",
                "root", "devintern", 10);
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
                default -> logger.info("Такого варианта нет.");
            }
        }





//        CowDAO DAO_Cow = new CowDAO();
//        logger.info(DAO_Cow.getAllCows());
//        logger.info(DAO_Cow.getCowById(3));
//        DAO_Cow.removeCow(3);
//        logger.info(DAO_Cow.getAllCows());

    }
}
