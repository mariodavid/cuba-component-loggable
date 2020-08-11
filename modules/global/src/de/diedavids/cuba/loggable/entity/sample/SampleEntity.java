package de.diedavids.cuba.loggable.entity.sample;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "DDCL_SAMPLE_ENTITY")
@Entity(name = "ddcl_SampleEntity")
@NamePattern("%s|customerName")
public class SampleEntity extends StandardEntity {
    private static final long serialVersionUID = 7035760013444998116L;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}