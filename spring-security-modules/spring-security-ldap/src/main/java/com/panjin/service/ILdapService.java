package com.panjin.service;

import com.panjin.entity.Person;

import java.util.List;

public interface ILdapService {

    /**
     * LDAP用户认证
     * @param userName 用户名
     * @param password 密码
     * @return 是否认证成功
     */
    boolean authenticate(String userName, String password);

    /**
     * 检索用户域
     * @param keyword 关键字
     * @return 用户列表
     */
    List<Person> searchLdapUser(String keyword);

    /**
     * 修改密码
     * @param userName 用户名
     * @param newPassword 新密码
     */
    void resetPwd(String userName, String newPassword);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<Person> findAll();
}
