package de.diedavids.cuba.loggable;

import com.haulmont.cuba.core.entity.Entity;
import de.diedavids.cuba.loggable.entity.LogEntryCategory;
import de.diedavids.cuba.loggable.entity.LogLevel;
import de.diedavids.cuba.loggable.service.LogEntryDescription;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

@Component(LogEntriesBean.NAME)
public class LogEntriesBean implements LogEntries {


    @Override
    public MessageLogEntryBuilder message(Entity loggable) {
        return new MessageLogEntryBuilder(loggable);
    }

    @Override
    public ExceptionLogEntryBuilder exception(Entity loggable, Throwable throwable) {
        return new ExceptionLogEntryBuilder(loggable, throwable);
    }


    public static class ExceptionLogEntryBuilder implements LogEntrySourceBuilder {
        private Entity loggable;
        private Throwable throwable;
        private LogLevel level;
        private LogEntryCategory category;
        private String levelCode;
        private String categoryCode;

        private ExceptionLogEntryBuilder(Entity loggable, Throwable throwable) {
            this.loggable = loggable;
            this.throwable = throwable;
        }


        public ExceptionLogEntryBuilder withLevel(LogLevel level) {
            this.level = level;
            return this;
        }

        public ExceptionLogEntryBuilder withCategory(LogEntryCategory category) {
            this.category = category;
            return this;
        }

        public ExceptionLogEntryBuilder withLevelCode(String levelCode) {
            this.levelCode = levelCode;
            return this;
        }

        public ExceptionLogEntryBuilder withCategoryCode(String categoryCode) {
            this.categoryCode = categoryCode;
            return this;
        }

        @Override
        public LogEntrySource build() {
            return new LogEntryDescription(
                loggable,
                throwable.getMessage(),
                ExceptionUtils.getStackTrace(throwable),
                level,
                category,
                levelCode,
                categoryCode
            );
        }
    }
    public static class MessageLogEntryBuilder implements LogEntrySourceBuilder {
        private Entity loggable;
        private String message;
        private String detailedMessage;
        private LogLevel level;
        private LogEntryCategory category;
        private String levelCode;
        private String categoryCode;

        private MessageLogEntryBuilder(Entity loggable) {
            this.loggable = loggable;
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
            return new LogEntryDescription(
                loggable,
                message,
                detailedMessage,
                level,
                category,
                levelCode,
                categoryCode
            );
        }
    }

}