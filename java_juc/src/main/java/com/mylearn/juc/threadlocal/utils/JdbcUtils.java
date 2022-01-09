package com.mylearn.juc.threadlocal.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {

    private static final ComboPooledDataSource ds = new ComboPooledDataSource();

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static void commitAndClose(Connection connection) {
        if (connection != null) {
            try {
                connection.commit();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void rollbackAndClose(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        // 从当前线程中取出connection
        Connection connection = threadLocal.get();
        try {
            // 如果为空，则从连接池中取出
            if(connection == null){
                connection = ds.getConnection();
                // 将connection放入到连接池中
                threadLocal.set(connection);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void release(AutoCloseable... ios) {
        for (AutoCloseable io : ios) {
            if (io != null) {
                try {
                    io.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
