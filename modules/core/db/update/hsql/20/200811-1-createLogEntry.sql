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
);