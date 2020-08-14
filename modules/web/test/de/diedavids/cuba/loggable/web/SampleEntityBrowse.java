package de.diedavids.cuba.loggable.web;

import com.haulmont.cuba.gui.components.ButtonsPanel;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.loggable.entity.sample.SampleEntity;

import javax.inject.Inject;

@UiController("ddcl_SampleEntity.browse")
@UiDescriptor("sample-entity-browse.xml")
@LookupComponent("sampleEntitiesTable")
@LoadDataBeforeShow
public class SampleEntityBrowse extends StandardLookup<SampleEntity> implements
    WithLogEntriesSupport {

    @Inject
    protected GroupTable<SampleEntity> sampleEntitiesTable;

    @Inject
    protected ButtonsPanel buttonsPanel;

    @Override
    public ListComponent getListComponentForLogEntries() {
        return sampleEntitiesTable;
    }

    @Override
    public ButtonsPanel getButtonsPanelForLogEntries() {
        return buttonsPanel;
    }
}