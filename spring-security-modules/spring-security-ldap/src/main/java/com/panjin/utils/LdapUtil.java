package com.panjin.utils;

import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;

import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 这个类还没有测试过，不知道是否可用
 * @author panjin
 */
public class LdapUtil {

    private LdapUtil() {
    }

    public static <T> T getUserDetails(LdapTemplate ldapTemplate, String username, Class<T> clazz) {
        // 构造查询条件
        String filter = "(&(objectClass=user)(sAMAccountName=" + username + "))";
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        // 执行查询
        List<T> results = ldapTemplate.search("", filter, searchControls, new UserDetailsMapper<>(clazz));
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    private static class UserDetailsMapper<T> implements ContextMapper<T> {
        private final Class<T> clazz;

        public UserDetailsMapper(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public T mapFromContext(Object ctx) throws NamingException {
            T userDetails = null;
            if (clazz != null) {
                try {
                    // 动态创建对象
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    userDetails = (T) constructor.newInstance();
                    // 获取所有字段
                    Field[] fields = clazz.getDeclaredFields();
                    // 遍历字段，根据LDAP查询结果填充字段值
                    for (Field field : fields) {
                        String attributeName = field.getName();
                        Object attributeValue = null;
                        if (ctx instanceof DirContextAdapter) {
                            attributeValue = ((DirContextAdapter) ctx).getObjectAttribute(attributeName);
                        } else if (ctx instanceof SearchResult) {
                            attributeValue = ((SearchResult) ctx).getAttributes().get(attributeName);
                        }
                        if (attributeValue != null) {
                            // 设置字段值
                            field.setAccessible(true);
                            field.set(userDetails, attributeValue);
                        }
                    }
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
            return userDetails;
        }
    }
}
