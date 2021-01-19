package dariomodule;

import info.magnolia.module.ModuleLifecycle;
import info.magnolia.module.ModuleLifecycleContext;
import org.springframework.boot.SpringApplication;

public class TagesModule implements ModuleLifecycle {

    @Override
    public void start(ModuleLifecycleContext moduleLifecycleContext) {
        SpringApplication.run(TagesSpringBootApplication.class);
    }

    @Override
    public void stop(ModuleLifecycleContext moduleLifecycleContext) {
        SpringApplication.exit(TagesSpringBootApplication.applicationContext, () -> 0);
    }

}
