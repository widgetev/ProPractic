--Сначала заменить имя на ID из новой таблицы
UPDATE products p
SET type = pt.id
FROM prducts_type pt WHERE p.type = pt.name;

--Потом сменить тип колонки
ALTER TABLE products
    ALTER COLUMN type TYPE BIGINT
        USING type::bigint;