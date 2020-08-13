package de.diedavids.cuba.loggable.service;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.ViewBuilder;
import de.diedavids.cuba.loggable.LogEntrySource;
import de.diedavids.cuba.loggable.entity.LogEntryCategory;
import de.diedavids.cuba.loggable.entity.LogLevel;
import de.diedavids.cuba.loggable.LogEntriesBean;
import org.springframework.stereotype.Service;
import de.diedavids.cuba.loggable.service.sample.LogEntryGenerationService;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service(LogEntryGenerationService.NAME)
public class LogEntryGenerationServiceBean implements LogEntryGenerationService {

    @Inject
    protected DataManager dataManager;
    @Inject
    protected LogEntryService logEntryService;
    @Inject
    protected LogEntriesBean logEntries;

    @Override
    public void generate(Entity loggable, int amount) {

        List<LogEntrySource> allEntries = IntStream.range(0, amount)
                .mapToObj(value -> {
                    final List<LogLevel> allLevels = list(LogLevel.class);
                    final List<LogEntryCategory> allCategories = list(LogEntryCategory.class);

                    return logEntries.message(loggable)
                            .withLevel(randomOfList(allLevels))
                            .withCategory(randomOfList(allCategories))
                            .withMessage("hello " + value)
                            .withDetailedMessage("hello world " + value)
                            .build();
                })
                .collect(Collectors.toList());

        logEntryService.createLogEntries(allEntries);

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