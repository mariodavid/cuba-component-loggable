package de.diedavids.cuba.loggable.web;

import com.haulmont.cuba.core.global.BeanLocator;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.ButtonsPanel;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.components.actions.ListAction;
import com.haulmont.cuba.gui.screen.Extensions;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import de.diedavids.cuba.loggable.web.action.TableWithLogEntriesAction;

import java.util.Collections;

public interface WithLogEntriesSupport {

    /**
     * defines the list component that will be used as a basis for the logEntry functionality
     *
     * @return the list component
     */
    ListComponent getListComponentForLogEntries();

    /**
     * the button id of the destination button
     * <p>
     * It will either picked up from existing XML definitions or created with this identifier
     *
     * @return the button identifier
     */
    default String getButtonIdForLogEntries() {
        return "logEntriesBtn";
    }


    /**
     * defines the button panel that will be used for inserting the logEntries button
     *
     * @return the destination buttonPanel
     */
    ButtonsPanel getButtonsPanelForLogEntries();


    /**
     * option to configure the option type of the tag link
     */
    default WindowManager.OpenType logEntryListOpenType() {
        return WindowManager.OpenType.DIALOG;
    }

    @Subscribe
    default void initLogEntriesButton(Screen.InitEvent event) {

        Screen screen = event.getSource();
        Button button = createOrGetButton(screen);

        ListAction action = new TableWithLogEntriesAction(
                getListComponentForLogEntries(),
                logEntryListOpenType()
        );
        getListComponentForLogEntries().addAction(action);
        button.setAction(action);

    }

    default Button createOrGetButton(Screen screen) {
        BeanLocator beanLocator = getBeanLocator(screen);
        ButtonsPanelHelper buttonsPanelHelper = beanLocator.get(ButtonsPanelHelper.NAME);
        return buttonsPanelHelper.createButton(getButtonIdForLogEntries(), getButtonsPanelForLogEntries(), Collections.emptyList());
    }

    default BeanLocator getBeanLocator(Screen screen) {
        return Extensions.getBeanLocator(screen);
    }

}