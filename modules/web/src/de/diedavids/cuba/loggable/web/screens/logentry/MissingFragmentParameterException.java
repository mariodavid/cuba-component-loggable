package de.diedavids.cuba.loggable.web.screens.logentry;

import com.haulmont.cuba.core.global.DevelopmentException;

public class MissingFragmentParameterException extends DevelopmentException {

    public MissingFragmentParameterException(String message) {
        super(message);
    }
}
