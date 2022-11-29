package com.panjin.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author panjin
 */
@Component
@Profile("production")
public class ProductionDatasourceConfig implements DatasourceConfig {

    @Override
    public void setup() {
        System.out.println("Setting up datasource for PRODUCTION environment. ");
    }

}
