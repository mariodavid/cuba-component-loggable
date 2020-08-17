package de.diedavids.cuba.loggable.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.haulmont.cuba.core.app.DataManagerBean;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EntityStates;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.testsupport.TestContainer;
import de.diedavids.cuba.loggable.DdclTestContainer;
import de.diedavids.cuba.loggable.LogEntries;
import de.diedavids.cuba.loggable.entity.LogEntry;
import java.util.Collection;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;



@Disabled
class LogEntryServiceBeanTest {


    @RegisterExtension
    static TestContainer container = DdclTestContainer.Common.INSTANCE;
    static DataManager dataManager;
    static LogEntryService sut;
    static LogEntries logEntries;
    static EntityStates entityStates;
    private User user;

    @BeforeAll
    static void beforeAll() {
        dataManager = AppBeans.get(DataManager.class);
        sut = AppBeans.get(LogEntryService.class);
        logEntries = AppBeans.get(LogEntries.class);
        entityStates = AppBeans.get(EntityStates.class);

    }


    @Test
    void given_oneMessageEntrySource_when_createLogEntries_then_OneLogEntryWasCreated() {

        user = dataManager.create(User.class);
        final Collection<LogEntry> allEntries = sut.createLogEntries(
            Collections.singletonList(
                logEntries.message(user)
                    .withMessage("hello world")
                    .build()
            )
        );


        // then:
        assertThat(allEntries)
            .hasSize(1);

        // and: the message is correct
        assertThat(allEntries)
            .extracting(LogEntry::getMessage)
            .containsExactly("hello world");

    }

    @Test
    void when_createLogEntries_then_logEntriesAreInNewState() {

        user = dataManager.create(User.class);
        final Collection<LogEntry> allEntries = sut.createLogEntries(
            Collections.singletonList(
                logEntries.message(user)
                    .withMessage("hello world")
                    .build()
            )
        );


        // then:
        assertThat(allEntries)
            .hasSize(1);

        // and: the log entries are in new state
        assertThat(allEntries)
            .extracting(logEntry -> entityStates.isNew(logEntry))
            .containsExactly(true);

    }
}