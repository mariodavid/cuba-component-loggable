package de.diedavids.cuba.loggable.web

import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.core.global.Messages
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.Action
import com.haulmont.cuba.gui.components.Frame
import com.haulmont.cuba.gui.components.Window
import de.diedavids.cuba.loggable.config.LoggableConfiguration
import de.diedavids.cuba.loggable.service.LogEntryService
import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

import javax.inject.Inject

@Component(WithLogEntriesSupportExecution.NAME)
@CompileStatic
class WithLogEntriesSupportExecutionBean implements WithLogEntriesSupportExecution {

    private static final String LOG_ENTRYS_ICON = 'font-icon:FILE_TEXT_O'
    private static final String ACTION_MSG_KEY = 'actions.LogEntries'
    private static final String ACTION_COUNT_MSG_KEY = 'actions.LogEntries.count'

    @Inject
    LogEntryService logEntryService

    @Inject
    LoggableConfiguration loggableConfiguration

    @Inject
    Messages messages


    @Override
    void setIcon(Action action) {
        action.icon = LOG_ENTRYS_ICON
    }

    @Override
    void updateCaption(Action action, Entity entity) {
        if (entity && loggableConfiguration.updateLogEntryCounterOnSelect) {
            int logEntryCount = logEntryService.countLogEntries(entity)
            action.caption = messages.formatMainMessage(ACTION_COUNT_MSG_KEY, logEntryCount)
        } else {
            action.caption = messages.getMainMessage(ACTION_MSG_KEY)
        }
    }

    @Override
    Window openLogEntryBrowse(Frame frame, Action action, Entity entity, WindowManager.OpenType openType) {
        Window browse = frame.openWindow(
                'ddcl_LogEntry.browse',
                openType ?: WindowManager.OpenType.DIALOG,
                [entity: entity] as Map<String, Object>)

        browse.addCloseListener(new Window.CloseListener() {
            @Override
            void windowClosed(String actionId) {
                updateCaption(action, entity)
            }
        })

        browse
    }
}
