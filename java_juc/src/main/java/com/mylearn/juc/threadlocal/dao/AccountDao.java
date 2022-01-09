package com.mylearn.juc.threadlocal.dao;

import com.mylearn.juc.threadlocal.ThreadLocalDemo;
import com.mylearn.juc.threadlocal.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDao {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public void out(Connection connection,String outUser,int money) throws SQLException {
        String sql = "update account set money = money - ? where name = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, money);
        pstm.setString(2, outUser);
        pstm.executeUpdate();

        // 此处不能释放connection,否则事务不能一致
        // JdbcUtils.release(pstm, connection);
        // 需改为:
        JdbcUtils.release(pstm);
    }

    public void out(String outUser, int money) throws SQLException {
        String sql = "update account set money = money - ? where name = ?";

        Connection connection = JdbcUtils.getConnection();

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, money);
        pstm.setString(2, outUser);
        pstm.executeUpdate();

        JdbcUtils.release(pstm, connection);
    }

    public void in(Connection connection,String inUser,int money)throws SQLException{
        String sql = "update account set money = money + ? where name = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, money);
        pstm.setString(2, inUser);
        pstm.executeUpdate();
        JdbcUtils.release(pstm);
    }

    public void in(String inUser, int money) throws SQLException {
        String sql = "update account set money = money + ? where name = ?";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, money);
        pstm.setString(2, inUser);
        pstm.executeUpdate();
        // 此处不能释放connection,否则事务不能一致
        // JdbcUtils.release(pstm, connection);
        // 需改为:
        JdbcUtils.release(pstm);
    }

}
