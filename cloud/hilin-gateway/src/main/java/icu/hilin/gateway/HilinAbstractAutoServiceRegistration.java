package icu.hilin.gateway;

import org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.context.ApplicationEvent;

public class HilinAbstractAutoServiceRegistration extends AbstractAutoServiceRegistration {
    protected HilinAbstractAutoServiceRegistration(ServiceRegistry serviceRegistry, AutoServiceRegistrationProperties properties) {
        super(serviceRegistry, properties);
    }

    @Override
    protected Object getConfiguration() {
        return null;
    }

    @Override
    protected boolean isEnabled() {
        return false;
    }

    @Override
    protected Registration getRegistration() {
        return null;
    }

    @Override
    protected Registration getManagementRegistration() {
        return null;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

    }
}
