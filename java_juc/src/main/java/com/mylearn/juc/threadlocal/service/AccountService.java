package com.mylearn.juc.threadlocal.service;

import com.mylearn.juc.threadlocal.dao.AccountDao;
import com.mylearn.juc.threadlocal.utils.JdbcUtils;

import java.sql.Connection;

public class AccountService {

    // 线程并发情况下，为了保证每个线程使用各自的connection，所以加锁synchronized
    public boolean transfer(String outUser, String inUser, int money) {
        AccountDao accountDao = new AccountDao();
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            // 禁止事务自动提交
            connection.setAutoCommit(false);
            // 转出
            accountDao.out(outUser, money);
            // 模拟异常
            int res = 1 / 0;
            // 转入
            accountDao.in(inUser, money);
            // 事务提交
            JdbcUtils.commitAndClose(connection);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            // 事务回滚
            JdbcUtils.rollbackAndClose(connection);
            return false;
        }
        return true;
    }

}
