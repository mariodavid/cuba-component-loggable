package de.diedavids.cuba.loggable.service;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import de.diedavids.cuba.loggable.LogEntrySource;
import de.diedavids.cuba.loggable.entity.LogEntry;
import de.diedavids.cuba.loggable.entity.LogEntryCategory;
import de.diedavids.cuba.loggable.entity.LogLevel;

import java.util.Optional;

public class LogEntryDescription implements LogEntrySource {

    private final Entity loggable;
    private final String message;
    private final String detailedMessage;
    private final LogLevel level;
    private final LogEntryCategory category;
    private final String levelCode;
    private final String categoryCode;

    public LogEntryDescription(
            Entity loggable,
            String message,
            String detailedMessage,
            LogLevel level,
            LogEntryCategory category,
            String levelCode,
            String categoryCode
    ) {
        this.loggable = loggable;
        this.message = message;
        this.detailedMessage = detailedMessage;
        this.level = level;
        this.category = category;
        this.levelCode = levelCode;
        this.categoryCode = categoryCode;
    }

    @Override
    public LogEntry toLogEntry(DataManager dataManager) {
        LogEntry logEntry = dataManager.create(LogEntry.class);

        logEntry.setLoggable(loggable);
        logEntry.setMessage(message);
        logEntry.setDetailedMessage(detailedMessage);

        LogLevel logLevel = Optional.ofNullable(level)
                .orElseGet(() -> loadByCode(dataManager, LogLevel.class, levelCode));
        logEntry.setLevel(logLevel);

        LogEntryCategory logEntryCategory = Optional.ofNullable(category)
                .orElseGet(() -> loadByCode(dataManager, LogEntryCategory.class, categoryCode));
        logEntry.setCategory(logEntryCategory);

        return logEntry;
    }


    private <T extends Entity> T loadByCode(DataManager dataManager, Class<T> entityClass, String code) {
        return (T) dataManager.load(entityClass)
                .query("e.code = ?1", code)
                .optional()
                .orElse(null);
    }

}
