-- liquibase formatted sql
-- changeset gnori:V4_create_medias_table

CREATE TABLE medias
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    name       VARCHAR(200)             NOT NULL,
    url        VARCHAR                  NOT NULL,
    type_id    VARCHAR                  NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)