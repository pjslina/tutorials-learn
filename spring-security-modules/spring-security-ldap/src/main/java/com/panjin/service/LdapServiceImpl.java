package com.panjin.service;

import com.panjin.entity.Person;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Service;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * @author panjin
 */
@Service
public class LdapServiceImpl implements ILdapService {

    private final LdapTemplate ldapTemplate;

    public LdapServiceImpl(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public boolean authenticate(String userName, String password) {
        EqualsFilter filter = new EqualsFilter("uid", userName);
        return ldapTemplate.authenticate("", filter.toString(), password);
    }

    @Override
    public List<Person> searchLdapUser(String keyword) {
        keyword = "*" + keyword + "*";
        LdapQuery query = query().where("uid").like(keyword).or("mail").like(keyword);
        return ldapTemplate.find(query, Person.class);
    }

    @Override
    public void resetPwd(String userName, String newPassword) {
        // 1. 查找AD用户
        LdapQuery query = query().where("uid").is(userName);
        Person person = ldapTemplate.findOne(query, Person.class);

        // 2. 创建密码
        String newQuotedPassword = "\"" + newPassword + "\"";
        byte[] newUnicodePassword = new byte[0];
        try {
            newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        // 3. 修改密码
        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("unicodePwd", newUnicodePassword));
        ldapTemplate.modifyAttributes(person.getId(), new ModificationItem[]{item});
    }

    @Override
    public List<Person> findAll() {
        return ldapTemplate.findAll(Person.class);
    }
}
