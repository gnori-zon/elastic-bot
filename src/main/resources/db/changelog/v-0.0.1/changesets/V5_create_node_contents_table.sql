-- liquibase formatted sql
-- changeset gnori:V5_create_node_contents_table

CREATE TABLE node_contents
(
    id              UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    headerUrlDataId UUID references url_data (id),
    textContentId   UUID references text_contents (id),
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at      TIMESTAMP WITH TIME ZONE
)