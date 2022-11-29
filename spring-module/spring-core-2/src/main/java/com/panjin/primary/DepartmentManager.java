package com.panjin.primary;

import org.springframework.stereotype.Component;

/**
 * @author panjin
 */
@Component
public class DepartmentManager implements Manager {
    @Override
    public String getManagerName() {
        return "Department manager";
    }
}
