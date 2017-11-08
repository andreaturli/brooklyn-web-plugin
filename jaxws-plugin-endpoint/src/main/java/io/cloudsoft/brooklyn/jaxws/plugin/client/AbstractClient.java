package io.cloudsoft.brooklyn.jaxws.plugin.client;

import net.atos.esb.publicschemas.servicerequest.*;
import net.atos.esb.publicservices.servicerequest.PortType;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.GregorianCalendar;

public abstract class AbstractClient {

    private final PortType portType;
    private final ObjectFactory factory;

    public AbstractClient(String username, String password, String address) {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(PortType.class);
        jaxWsProxyFactoryBean.setUsername(username);
        jaxWsProxyFactoryBean.setPassword(password);
        jaxWsProxyFactoryBean.setAddress(address);

        jaxWsProxyFactoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
        // add an interceptor to log the incoming response messages
        jaxWsProxyFactoryBean.getInInterceptors().add(new LoggingInInterceptor());
        // add an interceptor to log the incoming fault messages
        jaxWsProxyFactoryBean.getInFaultInterceptors().add(new LoggingInInterceptor());

        portType = (PortType) jaxWsProxyFactoryBean.create();
        factory = new ObjectFactory();
    }

    public PortType getPortType() {
        return portType;
    }

    public abstract Dack open(String orderName);

    public abstract Dack update(Object pack);

    public abstract Dack processACK(Object pack);

    protected OrderDetails.Field createField(String name, String value) {
        OrderDetails.Field field = new OrderDetails.Field();
        field.setName(name);
        field.setValue(value);
        return field;
    }

    public abstract Pack buildPack();

}
