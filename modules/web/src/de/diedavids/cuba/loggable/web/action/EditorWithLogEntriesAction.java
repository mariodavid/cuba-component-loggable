package de.diedavids.cuba.loggable.web.action;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractAction;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Window;
import de.diedavids.cuba.loggable.web.WithLogEntriesSupportExecution;

public class EditorWithLogEntriesAction extends AbstractAction implements Action.HasOpenType {


    private WithLogEntriesSupportExecution hasAttachmentsBean = AppBeans.get(WithLogEntriesSupportExecution.class);
    private WindowManager.OpenType openType;
    protected Window.Editor editor;

    public EditorWithLogEntriesAction(Window.Editor editor) {
        this(WithLogEntriesSupportExecution.ACTION_ID, editor);
    }

    public EditorWithLogEntriesAction(String id, Window.Editor editor) {
        super(id);
        this.editor = editor;

        updateCaption();
        hasAttachmentsBean.setIcon(this);
    }

    public void updateCaption() {
        hasAttachmentsBean.updateCaption(this, editor.getItem());
    }

    @Override
    public void refreshState() {
        super.refreshState();
        updateCaption();
    }

    @Override
    public void actionPerform(Component component) {
        hasAttachmentsBean.openLogEntryBrowse(editor.getFrame(), this, editor.getItem(), openType);
    }

    public WindowManager.OpenType getOpenType() {
        return openType;
    }

    public void setOpenType(WindowManager.OpenType openType) {
        this.openType = openType;
    }

}
