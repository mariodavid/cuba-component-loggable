package de.diedavids.cuba.loggable.global;

import com.haulmont.cuba.core.entity.Entity;
import de.diedavids.cuba.loggable.entity.LogEntryCategory;
import de.diedavids.cuba.loggable.entity.LogLevel;
import de.diedavids.cuba.loggable.service.LogEntryDescription;
import de.diedavids.cuba.loggable.service.LogEntrySource;
import org.springframework.stereotype.Component;

@Component(LogEntriesBean.NAME)
public class LogEntriesBean implements LogEntries {


    @Override
    public MessageLogEntryBuilder message(Entity loggable) {
        MessageLogEntryBuilder builder = new MessageLogEntryBuilder();
        builder.withLoggable(loggable);
        return builder;
    }

    @Override
    public LogEntrySourceBuilder exception(Entity loggable, Throwable throwable) {
        return null;
    }


    public static class MessageLogEntryBuilder implements LogEntrySourceBuilder {
        private Entity loggable;
        private String message;
        private String detailedMessage;
        private LogLevel level;
        private LogEntryCategory category;
        private String levelCode;
        private String categoryCode;

        private MessageLogEntryBuilder() {
        }

        public MessageLogEntryBuilder withLoggable(Entity loggable) {
            this.loggable = loggable;
            return this;
        }

        public MessageLogEntryBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public MessageLogEntryBuilder withDetailedMessage(String detailedMessage) {
            this.detailedMessage = detailedMessage;
            return this;
        }

        public MessageLogEntryBuilder withLevel(LogLevel level) {
            this.level = level;
            return this;
        }

        public MessageLogEntryBuilder withCategory(LogEntryCategory category) {
            this.category = category;
            return this;
        }

        public MessageLogEntryBuilder withLevelCode(String levelCode) {
            this.levelCode = levelCode;
            return this;
        }

        public MessageLogEntryBuilder withCategoryCode(String categoryCode) {
            this.categoryCode = categoryCode;
            return this;
        }

        @Override
        public LogEntrySource build() {
            return new LogEntryDescription(loggable, message, detailedMessage, level, category, levelCode, categoryCode);
        }
    }

}