-- liquibase formatted sql
-- changeset gnori:V21_create_phases_users_subscription_table

CREATE TABLE phases_users_subscription
(
    id         UUID PRIMARY KEY                       DEFAULT gen_random_uuid(),
    phase_id   UUID REFERENCES phases (id)   NOT NULL,
    user_id    UUID REFERENCES tg_users (id) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE      NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)