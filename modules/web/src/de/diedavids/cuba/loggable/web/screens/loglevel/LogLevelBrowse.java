package de.diedavids.cuba.loggable.web.screens.loglevel;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.loggable.entity.LogLevel;

@UiController("ddcl_LogLevel.browse")
@UiDescriptor("log-level-browse.xml")
@LookupComponent("logLevelsTable")
@LoadDataBeforeShow
public class LogLevelBrowse extends StandardLookup<LogLevel> {
}