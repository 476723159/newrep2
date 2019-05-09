package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest {
    private InputStream in;
    private SqlSession session;
    private  IUserDao userDao;
    @Before
    public void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }
    @After
    public void destory()throws Exception{
        session.commit();
        //6.释放资源
        session.close();
        in.close();
    }
    @Test
    public void testFindAll()throws Exception{
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("张三");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        user.setUserAddress("北京顺义");
        userDao.saveUser(user);
    }
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId(50);
        user.setUserName("张五");
        user.setUserSex("女");
        user.setUserBirthday(new Date());
        user.setUserAddress("河南郑州");
        userDao.updateUser(user);
    }
    @Test
    public void testDelete(){
        userDao.deleteUser(50);
    }
    @Test
    public void testFindById(){
        User user = userDao.findById(48);
        System.out.println(user);
    }
    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
        for (User user : users){
            System.out.println(user);
        }
    }
    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.println(total);
    }

    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User user1 : users){
            System.out.println(user1);
        }
    }
}
