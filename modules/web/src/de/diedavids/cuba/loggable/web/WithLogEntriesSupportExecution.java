package de.diedavids.cuba.loggable.web;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Frame;
import com.haulmont.cuba.gui.components.Window;

public interface WithLogEntriesSupportExecution {

    String NAME = "ddcl_WithLogEntriesSupportExecution";

    String ACTION_ID = "logEntry";


    Window openLogEntryBrowse(Frame frame, Action action, Entity entity, WindowManager.OpenType openType);

    void updateCaption(Action action, Entity entity);

    void setIcon(Action action);


}
