package de.diedavids.cuba.loggable.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "DDCL_LOG_ENTRY_CATEGORY", indexes = {
        @Index(name = "IDX_DDCL_LOG_ENTRY_CATEGORY_CODE", columnList = "CODE")
})
@Entity(name = "ddcl_LogEntryCategory")
public class LogEntryCategory extends StandardEntity {
    private static final long serialVersionUID = -8195110435216737964L;

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