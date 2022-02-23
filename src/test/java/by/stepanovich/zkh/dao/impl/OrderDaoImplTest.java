package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.ConnectionPool;
import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.OrderStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.*;

import static by.stepanovich.zkh.connection.ConnectionPool.getInstance;
import static org.junit.Assert.*;

public class OrderDaoImplTest {
    private static final String FIND_ALL_ORDERS = """
            SELECT *
            FROM orders""";
    Set<Order> allOrders = new HashSet<>();

    private OrderDao orderDao = new OrderDaoImpl();

    @Before
    public void setUp() throws Exception {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try (Connection connection = getInstance().retrieveConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_ORDERS);
            while (resultSet.next()) {
                Order order = new Order(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8),
                        resultSet.getTimestamp(9),
                        OrderStatus.of(resultSet.getString(10)),
                        resultSet.getString(11));
                allOrders.add(order);
            }
        }
    }


    @Test
    public void registerOrder() {
        try {
            Order orderTest = new Order(52, 6, "Неманская", "56", "5", "Помогите все сломалось!", Timestamp.valueOf("2022-01-30 23:22:00.0"), Timestamp.valueOf("2022-02-20 13:47:14.0"), null, OrderStatus.IN_PROCESSING, "");
            Order registeredOrder = orderDao.registerOrder(6, "Неманская", "56", "5", "Помогите все сломалось!", "2022-01-30 23:22:00", "").get();
            Assert.assertEquals(orderTest.getUserId(), registeredOrder.getUserId());
            Assert.assertEquals(orderTest.getScopeOfWork(), registeredOrder.getScopeOfWork());
            Assert.assertEquals(orderTest.getStreet(), registeredOrder.getStreet());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllUsersOrderById() {
        try {
            List<Order> testOrders = new ArrayList<>();
            testOrders.add(new Order(2, 5, "Мира", "46", "45", "Прорвало трубу в подвале",
                    Timestamp.valueOf("2022-01-28 16:29:00.0"), Timestamp.valueOf("2022-01-19 16:29:56.0"),
                    Timestamp.valueOf("2022-02-07 21:57:01.0"), OrderStatus.COMPLETED, "null"));
            testOrders.add(new Order(3, 5, "Козлова", "43/5", "4A", "Сорвало крышу, всем, совсем", Timestamp.valueOf("2022-01-30 17:00:00.0"),
                    Timestamp.valueOf("2022-01-19 16:58:51.0"), Timestamp.valueOf("2022-02-06 13:28:02.0"), OrderStatus.COMPLETED, "ada97068afc89224de7c255d8073871d.jpg"));
            testOrders.add(new Order(4, 5, "Коласа", "55", "53", "Дорога совсем разбита! Нет больше сил! Почините!", Timestamp.valueOf("2022-01-21 02:27:00.0"),
                    Timestamp.valueOf("2022-01-20 02:28:01.0"), Timestamp.valueOf("2022-02-08 13:29:25.0"), OrderStatus.COMPLETED, "monstor-pothole.jpg"));
            testOrders.add(new Order(5, 5, "Красноармейская", "4", "", "Отключили воду", Timestamp.valueOf("2022-01-30 23:22:00.0"), Timestamp.valueOf("2022-01-21 01:39:58.0"), Timestamp.valueOf("2022-02-10 12:07:27.0"),
                    OrderStatus.COMPLETED, "null"));
            testOrders.add(new Order(6, 5, "Бабушкина", "4", "25", "Слишком много мусора! Срочно уберите", Timestamp.valueOf("2022-01-21 20:57:34.0"),
                    Timestamp.valueOf("2022-01-21 20:57:34.0"), null, OrderStatus.AT_WORK, "asd65kjh.jpg"));
            testOrders.add(new Order(7, 5, "Лавочкина", "5", "55", "Шумные соседи тусуются всю ночь!", Timestamp.valueOf("2022-01-21 21:15:37.0"),
                    Timestamp.valueOf("2022-01-21 21:15:37.0"), null, OrderStatus.AT_WORK, "null"));
            testOrders.add(new Order(8, 5, "Дедушкина", "42", "", "Сломана лавка во дворе.", Timestamp.valueOf("2022-01-21 21:23:57.0"), Timestamp.valueOf("2022-01-21 21:23:57.0"),
                    Timestamp.valueOf("2022-02-09 22:47:23.0"), OrderStatus.COMPLETED, "lavka.jpg"));
            testOrders.add(new Order(9, 5, "", "", "", "Почему не убирают снег! Невозможно же ходить", Timestamp.valueOf("2022-01-23 15:55:03.0"), Timestamp.valueOf("2022-01-23 15:55:03.0"),
                    Timestamp.valueOf("2022-02-07 23:16:57.0"), OrderStatus.COMPLETED, "photoZKHsneg-mashiny-noch.jpg"));
            testOrders.add(new Order(11, 5, "Заслонова", "56", "", "Сломаны качели на детской площадке", Timestamp.valueOf("2022-01-23 18:13:56.0"),
                    Timestamp.valueOf("2022-01-23 18:13:56.0 "), Timestamp.valueOf("2022-02-04 15:40:35.0"), OrderStatus.COMPLETED, "photoZKHscale_1200.jpg"));
            testOrders.add(new Order(13, 5, "Кнорина", "17", "", "Выбито окно в подъезде",
                    Timestamp.valueOf("2022-01-24 15:49:47.0"), Timestamp.valueOf("2022-01-24 15:49:46.0"), null, OrderStatus.AT_WORK, "photoZKHfmg5d9afd74e72e72.jpg"));

            Assert.assertArrayEquals(testOrders.toArray(), orderDao.findAllUsersOrderById(5).toArray());

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateOrderStatus() {
        try {
            Assert.assertTrue(orderDao.updateOrderStatus(2, OrderStatus.AT_WORK));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractPhotos() {
        try {
            Map<String, String> testMap = new HashMap<>();
            testMap.put("472022-02-102jjlljvecnj2.jpg", "Уберите мусор!!! СРОЧНО");
            testMap.put("202022-02-09noch.jpg", "Уберите снег! ");
            testMap.put("572022-02-08i.jpg", "Открытый люк на ул. Беды");
            testMap.put("502022-02-08fmg5d9afd74e72e72.jpg", "Выбито окно в подъезде");
            testMap.put("342022-02-09scale_1200.jpg", "Сломаны качели! СРОЧНО ПОЧИНИТЕ!!!");
            testMap.put("252022-02-08scale_1200.jpg", "Сломаны качели на площадке");
            Assert.assertEquals(testMap.entrySet(), orderDao.extractPhotos().entrySet());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllOrder() {
        try {
            Assert.assertArrayEquals(allOrders.toArray(), orderDao.findAllOrder().toArray());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findOrdersByStatus() {
        try {
            List<Order> completedOrders = new ArrayList<>();
            for (Order order : allOrders) {
                if (order.getOrderStatus().equals(OrderStatus.COMPLETED)) {
                    completedOrders.add(order);
                }
            }
            Collections.sort(completedOrders);
            Assert.assertArrayEquals(completedOrders.toArray(), orderDao.findOrdersByStatus(OrderStatus.COMPLETED).toArray());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        try {
            Order testOrder = new Order(2, 5, "Мира", "46", "45", "Прорвало трубу в подвале", Timestamp.valueOf("2022-01-28 16:29:00.0"), Timestamp.valueOf("2022-01-19 16:29:56.0"),
                    Timestamp.valueOf("2022-02-07 21:57:01.0"), OrderStatus.COMPLETED, "null");
            Assert.assertEquals(orderDao.findById(2).get(), testOrder);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}