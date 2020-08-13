-- begin DDCL_LOG_LEVEL
create table ddcl_LOG_LEVEL (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    CODE varchar(255),
    --
    primary key (ID)
)^
-- end DDCL_LOG_LEVEL
-- begin DDCL_LOG_ENTRY_CATEGORY
create table DDCL_LOG_ENTRY_CATEGORY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    CODE varchar(255),
    --
    primary key (ID)
)^
-- end DDCL_LOG_ENTRY_CATEGORY
-- begin DDCL_LOG_ENTRY
create table DDCL_LOG_ENTRY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    MESSAGE varchar(4000) not null,
    DETAILED_MESSAGE longvarchar,
    LOGGABLE varchar(255) not null,
    LEVEL_ID varchar(36),
    CATEGORY_ID varchar(36),
    --
    primary key (ID)
)^
-- end DDCL_LOG_ENTRY
