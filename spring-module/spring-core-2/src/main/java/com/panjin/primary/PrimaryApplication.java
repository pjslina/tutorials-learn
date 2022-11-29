package com.panjin.primary;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author panjin
 */
public class PrimaryApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(Config.class);

        Employee employee = context.getBean(Employee.class);
        System.out.println(employee);

        ManagerService service = context.getBean(ManagerService.class);
        Manager manager = service.getManager();
        System.out.println(manager.getManagerName());
    }
    
}
