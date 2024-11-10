-- liquibase formatted sql
-- changeset splitStatements:false gnori:V3_alter_flow_nodes_table_add_trigger_only_one_start

CREATE FUNCTION only_one_start() RETURNS trigger AS
$only_one_start$
BEGIN
    IF NEW.is_start IS TRUE AND (SELECT count(*) > 0
                                  FROM flow_nodes
                                  WHERE is_start IS TRUE) THEN

        RAISE EXCEPTION 'only one phase can have no parent element.';
    END IF;
    RETURN NEW;
END;
$only_one_start$ LANGUAGE plpgsql;

CREATE TRIGGER only_one_start
    BEFORE INSERT OR UPDATE
    ON flow_nodes
    FOR EACH ROW
EXECUTE PROCEDURE only_one_start();