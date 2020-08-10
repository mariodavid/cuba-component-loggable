package de.diedavids.cuba.loggable.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.SystemLevel;
import de.diedavids.cuba.entitysoftreference.EntitySoftReferenceConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamePattern("%s|message")
@Table(name = "DDCL_LOG_ENTRY", indexes = {
        @Index(name = "IDX_DDCL_LOG_ENTRY", columnList = "LOGGABLE")
})
@Entity(name = "ddcl_LogEntry")
public class LogEntry extends StandardEntity {
    private static final long serialVersionUID = -5689061909136000090L;


    @NotNull
    @Column(name = "MESSAGE", nullable = false, length = 4000)
    protected String message;

    @Lob
    @Column(name = "DETAILED_MESSAGE")
    private String detailedMessage;

    @NotNull
    @SystemLevel
    @Convert(converter = EntitySoftReferenceConverter.class)
    @MetaProperty(datatype = "EntitySoftReference", mandatory = true)
    @Column(name = "LOGGABLE", nullable = false)
    protected com.haulmont.cuba.core.entity.Entity loggable;


    @Lookup(type = LookupType.DROPDOWN, actions = {})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEVEL_ID")
    private LogLevel level;


    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    protected LogEntryCategory category;

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }

    public void setCategory(LogEntryCategory category) {
        this.category = category;
    }

    public LogEntryCategory getCategory() {
        return category;
    }


    public com.haulmont.cuba.core.entity.Entity getLoggable() {
        return loggable;
    }

    public void setLoggable(com.haulmont.cuba.core.entity.Entity loggable) {
        this.loggable = loggable;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}