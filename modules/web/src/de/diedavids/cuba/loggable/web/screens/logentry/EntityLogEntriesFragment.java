package de.diedavids.cuba.loggable.web.screens.logentry;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.loggable.entity.LogEntry;

import javax.inject.Inject;


/**
 * Fragment for displaying all LogEntries for a given Entity.
 *
 * It renders a CRUD table of LogEntries with the ability to
 *
 * - create an LogEntry
 * - edit an LogEntry
 * - remove an LogEntry
 *
 * -----------------------------------------------------------
 *
 * Parameters
 *
 * - loggableDc (required): InstanceDataContainer that holds the entity
 *
 *
 * Example Usage
 *
 * <fragment screen="ddcl_EntityLogEntriesFragment">
 *     <properties>
 *         <property name="loggableDc" ref="customerDc"/>
 *     </properties>
 * </fragment>
 */
@UiController("ddcl_EntityLogEntriesFragment")
@UiDescriptor("entity-logEntries-fragment.xml")
public class EntityLogEntriesFragment extends ScreenFragment {

    @Inject
    protected CollectionLoader<LogEntry> logEntriesDl;
    @Inject
    protected Table<LogEntry> logEntriesTable;

    private InstanceContainer<? extends Entity> loggableDc;


    /**
     * sets the required Attribute 'loggableDc'
     * @param loggableDc
     */
    public void setLoggableDc(InstanceContainer<? extends Entity> loggableDc) {
        this.loggableDc = loggableDc;
    }


    @Subscribe(target = Target.PARENT_CONTROLLER)
    private void onAfterShowHost(Screen.AfterShowEvent event) {

        if (loggableDc == null) {
            throw new MissingFragmentParameterException(
              "Fragment Parameter 'loggableDc' not provided"
            );
        }

        if (loggableDc.getItem() == null) {
            throw new IllegalStateException(
              "Fragment Parameter 'loggableDc' has no item attached"
            );
        }

        logEntriesDl.setParameter("loggable", loggableDc.getItem());
        logEntriesDl.load();
    }

}