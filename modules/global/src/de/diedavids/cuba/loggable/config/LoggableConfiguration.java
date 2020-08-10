package de.diedavids.cuba.loggable.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;


@Source(type = SourceType.DATABASE)
public interface LoggableConfiguration extends Config {

    @Property("loggable.updateLogEntryCounterOnSelect")
    @Default("true")
    Boolean getUpdateLogEntryCounterOnSelect();

}
