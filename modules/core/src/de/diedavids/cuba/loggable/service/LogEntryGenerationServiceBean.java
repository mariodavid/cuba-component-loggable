package de.diedavids.cuba.loggable.service;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.ViewBuilder;
import de.diedavids.cuba.loggable.entity.LogEntryCategory;
import de.diedavids.cuba.loggable.entity.LogLevel;
import org.springframework.stereotype.Service;
import de.diedavids.cuba.loggable.service.sample.LogEntryGenerationService;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Service(LogEntryGenerationService.NAME)
public class LogEntryGenerationServiceBean implements LogEntryGenerationService {

    @Inject
    protected DataManager dataManager;
    @Inject
    protected LogEntryService logEntryService;

    @Override
    public void generate(Entity loggable, int amount) {

        IntStream.range(0, amount)
                .forEach(value -> {
                    final List<LogLevel> allLevels = list(LogLevel.class);
                    final List<LogEntryCategory> allCategories = list(LogEntryCategory.class);

                    logEntryService.createLogEntry(
                            loggable,
                            "hello " + value,
                            "hello world " + value,
                            randomOfList(allLevels),
                            randomOfList(allCategories)
                    );
                });

    }


    private <T> T randomOfList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(random().nextInt(list.size()));
    }

    private Random random() {
        return new Random();
    }

    private <T extends Entity> List<T> list(Class<T> entityClass) {
        return dataManager.load(entityClass).list();
    }

    private <T extends Entity> List<T> list(Class<T> entityClass, Consumer<ViewBuilder> viewBuilderConfigurer) {
        return dataManager.load(entityClass).view(viewBuilderConfigurer).list();
    }
}