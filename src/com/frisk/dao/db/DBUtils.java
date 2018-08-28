package com.frisk.dao.db;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/20
 * Time: 14:16
 */
public class DBUtils {
    static Connection conn = null;
    static PreparedStatement pst;


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/moviesystem?&useSSL=" +
                            "false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&tinyInt1isBit=false" +
                            "&useOldAliasMetadataBehavior=true",
                    "root", "root");
            return conn;
        } else {
            return conn;
        }
    }

    public static int execute(String sql, Object... args) throws SQLException {
        try {
            int row;
            conn = getConn();
            pst = conn.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    pst.setObject(i + 1, args[i]);
                }
            }
            System.out.println(pst.toString());
            row = pst.executeUpdate();
            return row;
        } finally {
            closeConn();
            closePst(pst);
        }
    }

    private static void closePst(PreparedStatement pst) throws SQLException {
        if (pst != null) {
            pst.close();
        }
    }

    private static void closeConn() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    private static void clostResutlSet(ResultSet r) throws SQLException {
        if (r != null) {
            r.close();
        }
    }


    public static <T> List<T> query(Class<T> clazz, String sql, Object... args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        ResultSet rs = null;
        try {
            conn = getConn();
            pst = conn.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    pst.setObject(i + 1, args[i]);
                }
            }
            System.out.println(pst.toString());
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            List<T> result = new ArrayList<>();
            while (rs.next()) {
//                这一句一定不能写在for循环里,写在里面会无法成功对对象赋值
                T obj = clazz.newInstance();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {

//                    System.out.print(rsmd.getColumnName(i + 1)+"\t");
//                对obj对象赋值
                    Field field = clazz.getDeclaredField(rsmd.getColumnName(i + 1));
                    field.setAccessible(true);
                    field.set(obj, rs.getObject(i+1));

                }
                result.add(obj);
            }
            return result;

        } finally {
            closeConn();
            closePst(pst);
            clostResutlSet(rs);
        }
    }

}
