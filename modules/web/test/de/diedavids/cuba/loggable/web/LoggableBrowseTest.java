package de.diedavids.cuba.loggable.web;

import static de.diedavids.sneferu.ComponentDescriptors.button;
import static de.diedavids.sneferu.ComponentDescriptors.table;
import static de.diedavids.sneferu.Interactions.click;
import static de.diedavids.sneferu.Interactions.selectInList;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.haulmont.cuba.core.entity.Entity;
import de.diedavids.cuba.loggable.entity.LogEntry;
import de.diedavids.cuba.loggable.entity.sample.SampleEntity;
import de.diedavids.cuba.loggable.web.screens.logentry.LogEntryBrowse;
import de.diedavids.sneferu.environment.StartScreen;
import de.diedavids.sneferu.interactions.SelectInListInteraction;
import de.diedavids.sneferu.screen.StandardLookupTestAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoggableBrowseTest extends BrowseTest {

    private ChangesData data;
    private SampleEntity sampleEntity;

    @BeforeEach
    void setUp() {

        data = new ChangesData(environment.getContainer());

        sampleEntity = data.sample("Customer Name 123");

        when(dataIsLoadedForEntity(SampleEntity.class))
            .thenReturn(
                asList(
                    sampleEntity
                )
            );
    }

    @Test
    void given_twoLogEntriesExistForEntity_when_showingLogEntries_then_logEntriesScreenIsShown(
        @StartScreen StandardLookupTestAPI<SampleEntity, SampleEntityBrowse> browse
    ) {

        // given:
        final LogEntry logEntry1 = data.logEntry(sampleEntity, "Log Entry 1");
        final LogEntry logEntry2 = data.logEntry(sampleEntity, "Log Entry 2");

        when(dataIsLoadedForEntity(LogEntry.class))
            .thenReturn(
                asList(logEntry1, logEntry2)
            );

        // when:
        browse
            .interact(selectInTable("sampleEntitiesTable", sampleEntity))
            .andThen(click(button("logEntriesBtn")));

        final StandardLookupTestAPI<LogEntry, LogEntryBrowse> logEntriesScreen = environment
            .getUiTestAPI().getOpenedLookupScreen(LogEntryBrowse.class);

        // then:
        assertThat(environment.getUiTestAPI().isActive(logEntriesScreen))
            .isTrue();

        // and:
        logEntriesScreen
            .interact(logEntryIsAvailable(logEntry1))
            .andThen(logEntryIsAvailable(logEntry2));
    }

    private SelectInListInteraction logEntryIsAvailable(LogEntry logEntry1) {
        return isAvailableInTable("logEntriesTable", logEntry1);
    }

    private SelectInListInteraction selectInTable(String tableId, Entity entity) {
        return selectInList(table(tableId), entity);
    }

    private SelectInListInteraction isAvailableInTable(String tableId, Entity entity) {
        return selectInTable(tableId, entity);
    }

}