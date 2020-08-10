package de.diedavids.cuba.loggable.web.screens.logentry;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.entitysoftreference.web.SoftReferenceInstanceNameTableColumnGenerator;
import de.diedavids.cuba.loggable.entity.LogEntry;

import javax.inject.Inject;

@UiController("ddcl_LogEntry.browse")
@UiDescriptor("logEntry-browse.xml")
@LookupComponent("logEntriesTable")
public class LogEntryBrowse extends StandardLookup<LogEntry> {

    @Inject
    protected GroupTable<LogEntry> logEntriesTable;

    @Inject
    protected CollectionContainer<LogEntry> logEntriesDc;

    @Inject
    protected CollectionLoader<LogEntry> logEntriesDl;
    @Inject
    protected MetadataTools metadataTools;
    @Inject
    protected MessageBundle messageBundle;

    private Entity loggable;

    public void setLoggable(Entity loggable) {
        this.loggable = loggable;
    }


    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {

        if (loggable == null) {
            throw new IllegalStateException(
                    "Fragment Parameter 'loggable' has no item attached"
            );
        }

        logEntriesDl.setParameter("loggable", loggable);
        logEntriesDl.load();

        getWindow().setCaption(
                messageBundle.formatMessage("browseCaption", metadataTools.getInstanceName(loggable))
        );
    }


}