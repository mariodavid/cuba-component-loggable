package de.diedavids.cuba.loggable.web;

import com.haulmont.cuba.core.app.DataService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.web.app.main.MainScreen;
import com.haulmont.cuba.web.testsupport.proxy.DataServiceProxy;
import com.haulmont.cuba.web.testsupport.proxy.TestServiceProxy;
import de.diedavids.cuba.loggable.DdclWebTestContainer.Common;
import de.diedavids.sneferu.environment.SneferuTestUiEnvironment;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mockito;

public abstract class BrowseTest {

    @RegisterExtension
    protected SneferuTestUiEnvironment environment = new SneferuTestUiEnvironment(Common.INSTANCE)
        .withScreenPackages(
            "de.diedavids.cuba.loggable.web"
        )
        .withUserLogin("admin")
        .withMainScreen(MainScreen.class);

    protected DataService dataService;

    @BeforeEach
    void mockDataService() {
        dataService = Mockito.spy(
            new DataServiceProxy(environment.getContainer())
        );
        TestServiceProxy.mock(DataService.class, dataService);
    }

    protected <EntityClass extends Entity> List<EntityClass> dataIsLoadedForEntity(Class<EntityClass> clazz) {
        final String entityName = environment.getContainer().getBean(Metadata.class).getSession()
            .getClass(clazz).getName();

        LoadContext<EntityClass> loadedList = Mockito.argThat(loadContext ->
            loadContext != null && loadContext.getEntityMetaClass().equals(entityName)
        );

        return dataService.loadList(loadedList);
    }

}