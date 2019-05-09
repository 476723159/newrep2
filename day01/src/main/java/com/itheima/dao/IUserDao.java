package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    List<User> findAll();
    /**
     * 保存
     */
    void saveUser(User user);
    /**
     * 更新
     */
    void updateUser(User user);
    /**
     * 删除
     */
    void deleteUser(Integer id);
    //根据id查询
    User findById(Integer id);
    //模糊查询
    List<User> findByName(String username);
    //查询总条数
    int findTotal();
    //根据queryVo查询
    List<User> findUserByVo(QueryVo vo);
}
