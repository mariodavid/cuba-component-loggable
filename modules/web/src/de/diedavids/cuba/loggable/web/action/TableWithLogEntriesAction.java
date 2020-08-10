package de.diedavids.cuba.loggable.web.action;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.components.actions.ItemTrackingAction;
import de.diedavids.cuba.loggable.web.WithLogEntriesSupportExecution;

public class TableWithLogEntriesAction extends ItemTrackingAction implements Action.HasOpenType {


    private WithLogEntriesSupportExecution withAttachmentsSupportExecution = AppBeans.get(WithLogEntriesSupportExecution.class);
    private WindowManager.OpenType openType;

    public TableWithLogEntriesAction(ListComponent target, WindowManager.OpenType openType) {
        this(WithLogEntriesSupportExecution.ACTION_ID, target);
    }

    public TableWithLogEntriesAction(String id, ListComponent target) {
        super(target, id);

        updateCaption();
        withAttachmentsSupportExecution.setIcon(this);
    }

    public void updateCaption() {
        withAttachmentsSupportExecution.updateCaption(this, getTarget().getSingleSelected());
    }

    @Override
    public void refreshState() {
        super.refreshState();
        updateCaption();
    }

    @Override
    public void actionPerform(Component component) {
        withAttachmentsSupportExecution.openLogEntryBrowse(getTarget().getFrame(), this, getTarget().getSingleSelected(), openType);
    }


    public WindowManager.OpenType getOpenType() {
        return openType;
    }

    public void setOpenType(WindowManager.OpenType openType) {
        this.openType = openType;
    }

}
