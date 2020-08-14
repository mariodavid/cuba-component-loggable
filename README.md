[ ![Download](https://api.bintray.com/packages/mariodavid/cuba-components/cuba-component-loggable/images/download.svg) ](https://bintray.com/mariodavid/cuba-components/cuba-component-loggable/_latestVersion)
[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Build Status](https://travis-ci.org/mariodavid/cuba-component-loggable.svg?branch=master)](https://travis-ci.org/mariodavid/cuba-component-loggable)

# CUBA Platform Component - Loggable

This application component lets you easily add log entries to any entity in your application.


## Installation

1. `loggable` is available in the [CUBA marketplace](https://www.cuba-platform.com/marketplace)
2. Select a version of the add-on which is compatible with the platform version used in your project:

| Platform Version | Add-on Version |
| ---------------- | -------------- |
| 7.2.x            | 0.1.x          |


The latest version is: [ ![Download](https://api.bintray.com/packages/mariodavid/cuba-components/cuba-component-loggable/images/download.svg) ](https://bintray.com/mariodavid/cuba-components/cuba-component-loggable/_latestVersion)

Add custom application component to your project:

* Artifact group: `de.diedavids.cuba.loggable`
* Artifact name: `loggable-global`
* Version: *add-on version*


## Supported DBMS

The following databases are supported by this application component:

* HSQLDB
* PostgreSQL

All other DBMS systems are supported in the way, that CUBA studio automatically creates DDL scripts.
Therefore, it is totally possible to use the application component even without dedicated SQL scripts directly from this application component.

## Using the application component

### Creating Log Entries

In source code you can create Log Entries by using the `LogEntryService`. The service contains the following API:

```java
    /**
     * loads all Log Entries for a given loggable entity
     * @param loggable the entity to check for Log Entries
     * @return the Log Entries
     */
    Collection<LogEntry> getLogEntries(Entity loggable);


    /**
     * Creates and stores one Log Entry based on the given LogEntrySource
     * @param logEntryDescription the LogEntrySource which will be stored
     * @return the resulting saved LogEntry
     */
    LogEntry createLogEntry(
            LogEntrySource logEntryDescription
    );

    /**
     * Creates and stores multiple Log Entries based on the given LogEntrySource
     * @param logEntryDescriptions the LogEntrySources which will be stored
     * @return the resulting saved Log Entries as an EntitySet
     */
    EntitySet createLogEntries(
            Collection<LogEntrySource> logEntryDescriptions
    );
``` 

The way to create the `LogEntrySource` objects is done via the `LogEntries` API. This API has methods to create different builders, for creating specific Log Entries. Here is an example of its usage:

```java
logEntryService.createLogEntry(
        logEntries.message(loggable)
                .withLevel(infoLevel)
                .withCategory(validationCategory)
                .withMessage("The Validation was successfull. No errors found.")
                .withDetailedMessage("Details of the Validation: Customer Name = 'Homer Simpson'")
                .build()
);
```



### Browse Screens

Let your browse screens implement the `WithLogEntriesSupport` interface.

```java

public class CustomerBrowse extends StandardLookup<Customer> implements WithLogEntriesSupport {

    @Inject
    protected GroupTable<Customer> customersTable;

    @Inject
    protected ButtonsPanel buttonsPanel;

    @Override
    public ListComponent getListComponentForLogEntries() {
        return customersTable;
    }

    @Override
    public ButtonsPanel getButtonsPanelForLogEntries() {
        return buttonsPanel;
    }

    @Override
    public WindowManager.OpenType logEntryListOpenType() {
        return WindowManager.OpenType.NEW_TAB;
    }
}
```

In the Interface variant the following attributes have to be defined by implementing methods instead of annotation attributes:

* `getListComponentForLogEntries` defines the list component to target for the loggable functionality
* `getButtonsPanelForLogEntries` defines the button panel on which a button will be placed
* `logEntryListOpenType` optionally defines the open type for the logEntry list



### Edit Screens

Besides using LogEntries in the Browse Screen of the Entity, it is also possible to show LogEntries as part of an Edit Screen.

In order to do display logEntries of a particular Entity there is a `fragment` which can be used in the Edit Screen:

```xml
<fragment screen="ddcl_EntityLogEntriesFragment">
    <properties>
        <property name="loggableDc" ref="customerDc"/>
    </properties>
</fragment>
```

An example can be found in the [customer-edit.xml](https://github.com/mariodavid/cuba-example-using-loggable/blob/master/modules/web/src/com/company/ceua/web/customer/customer-edit.xml#L53).


### Log Entry Level & Categorization

It is possible to assign a level as well as a category to the log entry. Both of them are ways to categorize the log message.

* Log Level: normally is used for expressing the severity of the message. Example values: `Info`, `Warning`, `Error`
* Log Entry Category: this is a way to describe the sub part of the business where the message belongs to. Examples might be `Validation`, `Import`, `Customer Interaction` etc.

In the Administration Menu `Administration > Log Entries` there are management UIs for creating those categorizations.

The `code` attribute is used for referencing them in source code, which simplifies the creation of a log entry without having to do manual lookups via `dataManager` all over the place. An example of the codes from source code: 

````java
logEntries.message(loggable)
        .withLevelCode("WARNING")
        .withCategoryCode("VALIDATION")
        .withMessage("The Validation was successfull. No errors found.")
        .withDetailedMessage("Details of the Validation: Customer Name = 'Homer Simpson'")
        .build()
````

### Example usage
To see this application component in action, check out this example: [cuba-example-using-loggable](https://github.com/mariodavid/cuba-example-using-loggable).