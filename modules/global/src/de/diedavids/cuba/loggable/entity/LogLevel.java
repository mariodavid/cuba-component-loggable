package de.diedavids.cuba.loggable.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "ddcl_LOG_LEVEL", indexes = {
        @Index(name = "IDX_DDCL_LOG_LEVEL_CODE", columnList = "CODE")
})
@Entity(name = "ddcl_LogLevel")
@NamePattern("%s|name")
public class LogLevel extends StandardEntity {
    private static final long serialVersionUID = -3640295999996523927L;


    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "CODE")
    protected String code;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}