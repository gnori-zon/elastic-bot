-- liquibase formatted sql
-- changeset gnori:V2_create_flow_nodes_relations_table

CREATE TABLE flow_nodes_relations(
    parent_id  UUID REFERENCES flow_nodes (id) NOT NULL,
    child_id   UUID REFERENCES flow_nodes (id) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE        NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE,

    PRIMARY KEY (parent_id, child_id),
    CONSTRAINT ref_not_self CHECK ( parent_id != child_id )
)