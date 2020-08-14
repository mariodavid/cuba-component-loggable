-- begin DDCL_LOG_LEVEL
create table ddcl_LOG_LEVEL (
    ID uuid,
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
    ID uuid,
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
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    MESSAGE varchar(4000) not null,
    DETAILED_MESSAGE text,
    LOGGABLE varchar(255) not null,
    LEVEL_ID uuid,
    CATEGORY_ID uuid,
    --
    primary key (ID)
)^
-- end DDCL_LOG_ENTRY
