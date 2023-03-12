insert into role (id , name)
values('65f43bc4-e22e-4bfb-ac32-6e9fe10a7ce5','USER')
ON CONFLICT DO NOTHING;

insert into role (id , name)
values('d7a89e84-847a-498b-b68a-a33cfbe4a39e','ADMIN')
ON CONFLICT DO NOTHING;