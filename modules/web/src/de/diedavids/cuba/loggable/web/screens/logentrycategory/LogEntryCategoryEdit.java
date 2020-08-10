package de.diedavids.cuba.loggable.web.screens.logentrycategory;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.loggable.entity.LogEntryCategory;

@UiController("ddcl_LogEntryCategory.edit")
@UiDescriptor("log-entry-category-edit.xml")
@EditedEntityContainer("logEntryCategoryDc")
@LoadDataBeforeShow
public class LogEntryCategoryEdit extends StandardEditor<LogEntryCategory> {
}