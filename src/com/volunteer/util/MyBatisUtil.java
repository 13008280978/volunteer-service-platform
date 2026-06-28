package com.volunteer.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

public class MyBatisUtil {
    private static SqlSessionFactory factory;
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("MyBatis初始化失败：" + e.getMessage());
        }
    }
    public static SqlSession getSession() {
        return factory.openSession(true);
    }
    public static SqlSession getSession(boolean autoCommit) {
        return factory.openSession(autoCommit);
    }
}
