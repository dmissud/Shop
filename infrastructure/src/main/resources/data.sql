delete from order_entity_items;
delete from order_entity;
delete from customer_entity_roles;
delete from customer_entity;

INSERT INTO customer_entity (id, password, user_name) VALUES
(-1,   '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Admin');
-- Password=admin

insert into customer_entity_roles(customer_entity_id, roles) values
(-1,'ROLE_ADMIN')
;
