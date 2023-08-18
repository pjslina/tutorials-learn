package com.panjin.controller;

import com.panjin.service.ILdapService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author panjin
 */
@RestController
@RequestMapping("/ldap")
@Slf4j
public class LdapController {

    private final ILdapService ldapService;

    public LdapController(ILdapService ldapService) {
        this.ldapService = ldapService;
    }

     @GetMapping("/authenticate")
     public boolean authenticate(@RequestParam String userName, @RequestParam String password) {
         return ldapService.authenticate(userName, password);
     }

    @GetMapping("/search")
    public Object search(@RequestParam(required = false) String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return ldapService.findAll();
        }
        return ldapService.searchLdapUser(keyword);
    }

    @GetMapping("/resetPwd")
    public void resetPwd(@RequestParam String userName, @RequestParam String newPassword) {
        ldapService.resetPwd(userName, newPassword);
    }

    /**
     * 查询用户信息，如果有的字段没有，则可以通过LDAP查询语言来检索
     *
     * @return 返回如下：
     * {
     * 	"dn": "cn=test01",                   // 表示该条记录在LDAP中的唯一标识符，即区分条目的名称。
     * 	"password": null,                    // 表示用户的密码，该字段为空值。
     * 	"username": "test01",                // 表示用户的唯一标识符，即登录名
     * 	"authorities": [],                   // 表示用户所拥有的权限.
     * 	"accountNonExpired": true,           // 表示该用户的账户是否过期，true表示账户未过期
     * 	"accountNonLocked": true,            // 表示该用户的账户是否被锁定，true表示账户未被锁定
     * 	"credentialsNonExpired": true,       // 表示该用户的凭证是否过期，true表示凭证未过期
     * 	"enabled": true,                     // 表示该用户的账户是否启用，true表示账户已启用
     * 	"timeBeforeExpiration": 2147483647,  // 表示距离密码过期的剩余时间（单位为秒），该用户的密码未过期（2147483647）
     * 	"graceLoginsRemaining": 2147483647,  // 表示用户的宽限登录次数，即即将密码过期时，用户可以使用的额外登录次数（2147483647）
     * 	"givenName": "test01",               // 表示用户的名字
     * 	"sn": "test01",                      // 表示用户的姓氏
     * 	"description": null,                 // 表示用户的描述信息 该字段为空值
     * 	"telephoneNumber": null,             // 表示用户的电话号码 该字段为空值
     * 	"cn": ["test01"]                     // 表示用户的全名 该字段为数组
     * }
     *
     */
    @GetMapping("/getUserInfo")
    public Object getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            log.info("权限：grantedAuthority.getAuthority() = {}", grantedAuthority.getAuthority());
        }
        Object credentials = authentication.getCredentials();
        log.info("认证：credentials = {}", credentials);
        Object details = authentication.getDetails();
        log.info("详细信息：details = {}", details);
        Object principal = authentication.getPrincipal();
        log.info("主要信息：principal = {}", principal);
        return principal;
    }
}
