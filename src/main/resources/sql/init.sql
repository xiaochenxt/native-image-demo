CREATE TABLE "t_user" (
    "id" int8 NOT NULL,
    "created_by" text NOT NULL DEFAULT '',
    "created_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    "deleted" bool,
    "modified_by" text NOT NULL DEFAULT '',
    "modified_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    "version" int8,
    "name" text,
    "age" int4,
    PRIMARY KEY ("id")
);