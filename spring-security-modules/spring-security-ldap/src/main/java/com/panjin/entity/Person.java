package com.panjin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

/**
 * @author panjin
 */
@lombok.Getter
//@Entry(objectClasses = { "inetOrgPerson", "top"})
@Entry(objectClasses = { "inetOrgPerson", "top"})
public final class Person {

    @Id
    @JsonIgnore
    private Name id;

    /**
     * 微软AD域的用户名为：sAMAccountName
     * openldap的用户名为：uid
     */
    @Attribute(name = "uid")
    private String userName;

    @Attribute(name = "password")
    private String password;

    @Attribute(name = "mail")
    private String email;

    public Person() {
    }

    public Person(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setId(Name id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return userName;
    }
}
