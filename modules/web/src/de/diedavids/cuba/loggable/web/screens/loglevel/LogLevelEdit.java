package de.diedavids.cuba.loggable.web.screens.loglevel;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.loggable.entity.LogLevel;

@UiController("ddcl_LogLevel.edit")
@UiDescriptor("log-level-edit.xml")
@EditedEntityContainer("logLevelDc")
@LoadDataBeforeShow
public class LogLevelEdit extends StandardEditor<LogLevel> {
}