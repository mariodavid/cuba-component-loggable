package de.diedavids.cuba.loggable.web.screens.logentrycategory;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.loggable.entity.LogEntryCategory;

@UiController("ddcl_LogEntryCategory.browse")
@UiDescriptor("log-entry-category-browse.xml")
@LookupComponent("logEntryCategoriesTable")
@LoadDataBeforeShow
public class LogEntryCategoryBrowse extends StandardLookup<LogEntryCategory> {
}