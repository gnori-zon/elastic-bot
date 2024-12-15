-- liquibase formatted sql
-- changeset gnori:V6_create_node_content_url_data_table

CREATE TABLE node_content_url_data
(
    node_content_id UUID references node_contents (id),
    url_data_id     UUID references url_data (id),
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at      TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (node_content_id, url_data_id)
)