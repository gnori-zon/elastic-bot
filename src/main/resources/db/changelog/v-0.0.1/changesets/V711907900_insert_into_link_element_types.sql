-- liquibase formatted sql
-- changeset gnori:V711907900_insert_into_link_element_types

INSERT INTO link_element_types (name)
    VALUES
        ('DOCUMENT'),
        ('PHOTO'),
        ('VIDEO'),
        ('URL')