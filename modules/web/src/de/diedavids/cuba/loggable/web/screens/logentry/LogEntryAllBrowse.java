package de.diedavids.cuba.loggable.web.screens.logentry;

import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.loggable.entity.LogEntry;
import de.diedavids.cuba.entitysoftreference.web.SoftReferenceInstanceNameTableColumnGenerator;

import javax.inject.Inject;

@UiController("ddcl_LogEntryAll.browse")
@UiDescriptor("logEntry-all-browse.xml")
@LookupComponent("logEntriesTable")
@LoadDataBeforeShow
public class LogEntryAllBrowse extends StandardLookup<LogEntry> {

    @Inject
    protected GroupTable<LogEntry> logEntriesTable;
    @Inject
    protected UiComponents uiComponents;
    @Inject
    protected MetadataTools metadataTools;
    @Inject
    protected ScreenBuilders screenBuilders;

    @Subscribe
    protected void onInit(InitEvent event) {
        logEntriesTable.addGeneratedColumn("loggable",
                new SoftReferenceInstanceNameTableColumnGenerator(
                        "loggable",
                        uiComponents,
                        metadataTools,
                        screenBuilders,
                        this
                )
        );
    }
    
    
}