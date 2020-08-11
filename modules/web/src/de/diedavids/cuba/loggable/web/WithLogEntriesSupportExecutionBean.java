package de.diedavids.cuba.loggable.web;

import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.screen.FrameOwner;
import com.haulmont.cuba.gui.screen.OpenMode;
import de.diedavids.cuba.loggable.service.LogEntryService;
import de.diedavids.cuba.loggable.web.screens.logentry.LogEntryBrowse;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component(WithLogEntriesSupportExecution.NAME)

public class WithLogEntriesSupportExecutionBean implements WithLogEntriesSupportExecution {

    @Inject
    private ScreenBuilders screenBuilders;

    @Override
    public void openLogEntryBrowse(FrameOwner frameOwner, ListComponent target, OpenMode openMode) {
        LogEntryBrowse logEntryBrowse =
                screenBuilders.screen(frameOwner)
                        .withScreenClass(LogEntryBrowse.class)
                        .withOpenMode(openMode)
                        .build();

        logEntryBrowse.setLoggable(target.getSingleSelected());

        logEntryBrowse.show();
    }

}
