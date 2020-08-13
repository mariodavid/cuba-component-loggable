package de.diedavids.cuba.loggable.web;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.loggable.entity.sample.SampleEntity;

@UiController("ddcl_SampleEntity.edit")
@UiDescriptor("sample-entity-edit.xml")
@EditedEntityContainer("sampleEntityDc")
@LoadDataBeforeShow
public class SampleEntityEdit extends StandardEditor<SampleEntity> {
}