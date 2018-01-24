package DAO;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by bezru on 21.03.2017.
 */
public class DBDAO {
    private static ArrayList codes = new ArrayList();
    private Map codeToValueMap;

    private static Connection connection;
    public static volatile DBDAO instance;

    private static void getCodes() {
        connection = ConnectionClass.getInstance();
        String sql = "select code from Products";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                codes.add(resultSet.getInt("code"));
            }

            System.out.println(Arrays.toString(codes.toArray()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static DBDAO getInstance() {
        if (instance == null) {
            synchronized (Connection.class) {
                if (instance == null) {
                    instance = new DBDAO();
                    getCodes();
                }
            }
        }
        return instance;
    }

    private DBDAO() {
    }

    public ResultSet getListOfPreviousPeriod() {
        connection = ConnectionClass.getInstance();
        ResultSet resultSet = null;
        try {
            String sql = "select p.code, p.prodName, s.dateOfSell, s.countOfProd\n" +
                    "from Products as p \n" +
                    "join sells as s on s.prodCode = p.code ";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getListOfPreviousPeriodByCode(int code) {
        connection = ConnectionClass.getInstance();
        ResultSet resultSet = null;
        try {
            String sql = "select p.code, p.prodName, s.dateOfSell, s.countOfProd\n" +
                    "from Products as p\n" +
                    "join sells as s on s.prodCode = p.code\n" +
                    "where p.code = " + code + " Order by s.dateOfSell DESC";

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getProducts() {
        connection = ConnectionClass.getInstance();
        ResultSet resultSet = null;
        try {
            String sql = "select *  from Products";

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public boolean put(Map map, ArrayList codes) {
        codeToValueMap = map;

        connection = ConnectionClass.getInstance();
        boolean result = false;
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        Date date1 = new Date(date.getYear(), date.getMonth(), date.getDay());

        try {
            String sql = "insert into sells values ";
            for (int i = 0; i < map.size(); i++) {
                if (i > 0) {
                    sql += ", ";
                }
                sql += " (" + codes.get(i) + ", " + "\'" + date1 + "\', " + map.get(codes.get(i)) + ")";

            }

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            result = true;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        public Vector distribute(int code) {
            connection = ConnectionClass.getInstance();
            ResultSet resultSet;
            Vector vector = new Vector(5);

            String sql2 = "select sellCode from  sells where prodCode = " + code + " AND dateOfSell = (select max(dateOfSell) from sells where prodCode = " + code + ") ";
            Statement statement = null;
            int id = 0;
            try {
                statement = connection.createStatement();
                ResultSet resultSet1 = statement.executeQuery(sql2);
                resultSet1.next();
                id = resultSet1.getInt("sellCode");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            int sum = 0;
            int r = 0;
            int r1 = 0;
            int p1 = 0;
            int p2 = 0;
            int p3 = 0;

            String sql = "select * from SellersMed where prCode = " + code + " order by countProd DESC";


            try {

                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                resultSet.next();
                if (resultSet.getInt("countProd") >= (int) (codeToValueMap.get(code))) {
                    vector.add(code);
                    vector.add(getProdName(code));
                    switch (resultSet.getInt("sellCode")) {
                        case 1:
                            vector.add(codeToValueMap.get(code));
                            vector.add(0);
                            vector.add(0);
                            break;
                        case 2:
                            vector.add(0);
                            vector.add(codeToValueMap.get(code));
                            vector.add(0);
                            break;
                        case 3:
                            vector.add(0);
                            vector.add(0);
                            vector.add(codeToValueMap.get(code));
                            break;
                    }
                } else {
                    p1 = resultSet.getInt("countProd");
                    r = (int) codeToValueMap.get(code) - p1;
                    vector.insertElementAt(p1, resultSet.getInt("sellCode") + 2);
                    resultSet.next();
                    if (r <= resultSet.getInt("countProd")) {
                        vector.insertElementAt(r, resultSet.getInt("sellCode") + 2);
                        resultSet.next();
                        vector.insertElementAt(0, resultSet.getInt("sellCode") + 2);
                    } else {
                        p2 = resultSet.getInt("countProd");
                        vector.insertElementAt(p2, resultSet.getInt("sellCode") + 2);
                        r -= p2;
                        resultSet.next();
                        if (r <= resultSet.getInt("countProd")) {
                            vector.insertElementAt(r, resultSet.getInt("sellCode") + 2);
                        } else {
                            p3 = resultSet.getInt("countProd");
                            vector.insertElementAt(p3, resultSet.getInt("sellCode") + 2);
                            sum = p1 + p2 + p3;
                            vector.setElementAt(sum, 2);
                            String sql1 = "update sells set countOfProd = " + sum + "where id = " + id;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return vector;
        }
    */
    public Vector dist(int code) {
        int p1 = 0;
        int need = (int) (codeToValueMap.get(code));
        connection = ConnectionClass.getInstance();
        String sql = "select * from SellersMed where prCode = " + code + " order by countProd DESC";
        ResultSet resultSet = null;
        Vector vector = new Vector(6);
        Map numToVolume = new HashMap();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (need > 0 && resultSet.next()) {
                p1 = resultSet.getInt("countProd");
                if (p1 >= need) {
                    numToVolume.put(resultSet.getInt("sellCode"), need);
                    System.out.println("key" + resultSet.getInt("sellCode"));
                    System.out.println(need);
                    need = 0;
                } else {
                    numToVolume.put(resultSet.getInt("sellCode"), p1);
                    System.out.println("key" + resultSet.getInt("sellCode"));
                    System.out.println(p1);
                    need -= p1;
                }
            }
            vector.add(code);
            vector.add(getProdName(code));
            vector.add((int) (codeToValueMap.get(code)));
            vector.add(numToVolume.get(1));
            System.out.println(numToVolume.get(1));
            vector.add(numToVolume.get(2));
            System.out.println(numToVolume.get(2));
            vector.add(numToVolume.get(3));
            System.out.println(numToVolume.get(3));

            if (need > 0) {
                String sql2 = "select sellCode from  sells where prodCode = " + code + " AND dateOfSell = (select max(dateOfSell) from sells where prodCode = " + code + ") ";
                Statement statement1 = null;
                int id = 0;
                try {
                    statement = connection.createStatement();
                    ResultSet resultSet1 = statement.executeQuery(sql2);
                    resultSet1.next();
                    id = resultSet1.getInt("sellCode");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int count = 0 - need;

                String sql1 = "update sells set countOfProd = " + (0 - need) + "where sellCode = " + id;
            }

} catch (SQLException e) {
        e.printStackTrace();
        }
        return vector;
        }

    public void createdistr(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        for (int i = 0; i < codes.size(); i++) {
            tableModel.addRow(dist((int) codes.get(i)));
        }
    }

    private String getProdName(int code) {
        connection = ConnectionClass.getInstance();
        ResultSet resultSet;
        String sql = "select prodName from Products where code = " + code;
        String result = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            result = resultSet.getString("prodName");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getSellName(int code) {
        connection = ConnectionClass.getInstance();
        ResultSet resultSet;
        String sql = "select sellerName from Seller where codeSeller = " + code;
        String result = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            result = resultSet.getString("sellerName");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
