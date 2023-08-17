package com.panjin.controller;

import com.panjin.service.ILdapService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panjin
 */
@RestController
@RequestMapping("/ldap")
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
}
