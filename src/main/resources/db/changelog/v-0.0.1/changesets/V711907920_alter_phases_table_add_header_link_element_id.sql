-- liquibase formatted sql
-- changeset splitStatements:false gnori:V711907920_alter_phases_table_add_header_link_element_id

ALTER TABLE phases
    ADD COLUMN header_link_element_id UUID REFERENCES link_elements (id);

CREATE FUNCTION not_url() RETURNS trigger AS
$not_url$
BEGIN
    -- Проверить, что указаны имя сотрудника и зарплата
    IF 'url' ILIKE (SELECT let.name
                    FROM link_element_types let
                    WHERE let.id = (SELECT le.type_id
                                    FROM link_elements le
                                    WHERE le.id = NEW.header_link_element_id)) THEN

        RAISE EXCEPTION 'header_link_element_id cannot references link_element with url type';
    END IF;
    RETURN NEW;
END;
$not_url$ LANGUAGE plpgsql;

CREATE TRIGGER not_url
    BEFORE INSERT OR UPDATE
    ON phases
    FOR EACH ROW
EXECUTE PROCEDURE not_url();