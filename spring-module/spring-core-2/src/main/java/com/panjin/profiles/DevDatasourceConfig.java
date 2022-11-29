package com.panjin.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author panjin
 */
@Component
@Profile("dev")
public class DevDatasourceConfig implements DatasourceConfig {

    @Override
    public void setup() {
        System.out.println("Setting up datasource for DEV environment. ");
    }

}
