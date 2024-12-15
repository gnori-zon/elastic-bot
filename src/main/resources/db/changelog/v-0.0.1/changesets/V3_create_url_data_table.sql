-- liquibase formatted sql
-- changeset gnori:V3_create_url_data_table

CREATE TABLE url_data
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    url        VARCHAR                  NOT NULL,
    type       VARCHAR                  NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)