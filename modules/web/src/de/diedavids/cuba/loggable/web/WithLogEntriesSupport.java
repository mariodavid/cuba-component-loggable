package de.diedavids.cuba.loggable.web;

import com.haulmont.cuba.core.global.BeanLocator;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.ButtonsPanel;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.components.actions.ItemTrackingAction;
import com.haulmont.cuba.gui.components.actions.ListAction;
import com.haulmont.cuba.gui.screen.*;

import java.util.Collections;

public interface WithLogEntriesSupport {


    String BUTTON_MSG_KEY = "actions.logEntries";
    String ICON_KEY = "font-icon:FILE_TEXT_O";


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
    default OpenMode logEntryListOpenType() {
        return OpenMode.DIALOG;
    }

    @Subscribe
    default void initLogEntriesButton(Screen.InitEvent event) {

        Screen screen = event.getSource();
        Button button = createOrGetButton(screen);

        initButtonWithTagsFunctionality(screen, button);
    }

    default void initButtonWithTagsFunctionality(Screen screen, Button button) {

        WithLogEntriesSupportExecution withLogEntriesSupportExecution = getBeanLocator(screen).get(WithLogEntriesSupportExecution.class);
        Messages messages = getBeanLocator(screen).get(Messages.class);


        ListAction action = new ItemTrackingAction(getListComponentForLogEntries(), "logEntriesAction")
                .withPrimary(true)
                .withIcon(ICON_KEY)
                .withCaption(messages.getMainMessage(BUTTON_MSG_KEY))
                .withHandler(e -> withLogEntriesSupportExecution.openLogEntryBrowse(
                        screen,
                        getListComponentForLogEntries(),
                        logEntryListOpenType()
                ));

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