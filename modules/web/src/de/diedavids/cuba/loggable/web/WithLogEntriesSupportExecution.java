package de.diedavids.cuba.loggable.web;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Frame;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.model.ScreenData;
import com.haulmont.cuba.gui.screen.FrameOwner;
import com.haulmont.cuba.gui.screen.OpenMode;

public interface WithLogEntriesSupportExecution {

    String NAME = "ddcl_WithLogEntriesSupportExecution";

    String ACTION_ID = "logEntry";


    void openLogEntryBrowse(
            FrameOwner frameOwner,
            ListComponent target,
            OpenMode openMode
    );

}
