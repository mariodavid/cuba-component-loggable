package de.diedavids.cuba.loggable.service.sample;

import com.haulmont.cuba.core.entity.Entity;

public interface LogEntryGenerationService {
    String NAME = "ddcl_LogEntryGenerationService";


    public void generate(Entity loggable, int amount);
}