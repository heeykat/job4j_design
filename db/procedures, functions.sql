create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_2', 'producer_2', 15, 32);

select * from products;

create
or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;

		delete from products
		where id = u_id and count <= 0;
		end if;
		
        if
            tax > 0 THEN
            update products
            set price = price + price * tax;
        end if;
    END;
$$;

call update_data(10, 0, 1);
call update_data(5, 0, 1);

drop procedure update_data(u_count integer, tax float, u_id integer);
delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

create
or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

select f_insert_data('product_1', 'producer_1', 25, 50);

select * from products;

create
or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
			
            select into result count
            from products
            where id = u_id;
			
			if result <= 0 then
			delete from products
			where id = u_id;
			end if;
        end if;
		RETURN COALESCE(result, 0);
    end;
$$;

SELECT f_update_data(20, 0, 1);
SELECT f_update_data(5, 0, 1);