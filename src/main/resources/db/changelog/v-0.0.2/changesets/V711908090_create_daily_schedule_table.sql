-- liquibase formatted sql
-- changeset gnori:V711908090_create_daily_schedule_table

CREATE TABLE daily_schedules
(
    id           UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    week_day     VARCHAR,
    date         DATE,
    lessons_data VARCHAR,
    group_name   VARCHAR,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP WITH TIME ZONE,
    CONSTRAINT date_group_name_unique UNIQUE (date, group_name)
)