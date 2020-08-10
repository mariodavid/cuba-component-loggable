package de.diedavids.cuba.loggable.web.screens.logentry;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.loggable.entity.LogEntry;


@UiController("ddcl_LogEntry.edit")
@UiDescriptor("logEntry-edit.xml")
@EditedEntityContainer("logEntryDc")
@LoadDataBeforeShow
public class LogEntryEdit extends StandardEditor<LogEntry> {

}