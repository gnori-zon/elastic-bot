-- liquibase formatted sql
-- changeset gnori:V16_create_phase_actions_table

CREATE TABLE phase_actions
(
    id                               UUID PRIMARY KEY                            DEFAULT gen_random_uuid(),
    short_id                         SERIAL UNIQUE                      NOT NULL,
    phase_id                         UUID REFERENCES phases (id)        NOT NULL,
    name                             VARCHAR                            NOT NULL,
    redirect_phase_id                UUID REFERENCES phases (id),
    display_condition_named_query_id UUID REFERENCES named_queries (id),
    action_named_query_id            UUID REFERENCES named_queries (id) NOT NULL,
    created_at                       TIMESTAMP WITH TIME ZONE           NOT NULL DEFAULT now(),
    updated_at                       TIMESTAMP WITH TIME ZONE,
    CONSTRAINT phase_id_name_unique UNIQUE (phase_id, name)
)