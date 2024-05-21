-- liquibase formatted sql
-- changeset splitStatements:false gnori:V711907930_alter_phases_table_add_trigger_only_one_without_parent

CREATE FUNCTION only_one_without_parent() RETURNS trigger AS
$only_one_without_parent$
BEGIN
    -- Проверить, что указаны имя сотрудника и зарплата
    IF NEW.parent_id IS NUll AND (SELECT count(*) > 0
                                  FROM phases
                                  WHERE parent_id IS NULL) THEN

        RAISE EXCEPTION 'only one phase can have no parent element.';
    END IF;
    RETURN NEW;
END;
$only_one_without_parent$ LANGUAGE plpgsql;

CREATE TRIGGER only_one_without_parent
    BEFORE INSERT OR UPDATE
    ON phases
    FOR EACH ROW
EXECUTE PROCEDURE only_one_without_parent();