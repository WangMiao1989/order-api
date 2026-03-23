create sequence category_id_seq;
create table m_category(
    id bigint primary key default nextval('category_id_seq'),
    type varchar(20),
    name varchar(50),
    description varchar(500),
    icon varchar(50),
    display_order integer,
    create_user varchar(100),
    create_time timestamp without time zone default now(),
    update_user varchar(100),
    update_time timestamp without time zone default now()
);

create sequence dish_id_seq;
create table m_dish(
    id bigint primary key default nextval('dish_id_seq'),
    name varchar(50),
    description varchar(500),
    category_id bigint,
    price numeric,
    image bytea,
    tag bigint,
    display_order integer,
    create_user varchar(100),
    create_time timestamp without time zone default now(),
    update_user varchar(100),
    update_time timestamp without time zone default now()
);

create sequence discount_id_seq;
create table t_discount(
    id bigint primary key default nextval('discount_id_seq'),
    dish_id bigint not null,
    rate numeric,
    start_date date,
    end_date date,
    create_user varchar(100),
    create_time timestamp without time zone default now(),
    update_user varchar(100),
    update_time timestamp without time zone default now()
);

create sequence table_id_seq;
create table t_table_info(
    table_id bigint primary key default nextval('table_id_seq'),
    table_no varchar(20) not null,
    customer_cnt integer not null,
    start_time timestamp without time zone not null,
    end_time timestamp without time zone,
    create_user varchar(100),
    create_time timestamp without time zone default now(),
    update_user varchar(100),
    update_time timestamp without time zone default now()
);

create sequence order_id_seq;
create table t_order_info(
    order_id bigint primary key default nextval('order_id_seq'),
    table_id bigint not null,
    order_detail jsonb,
    is_paid boolean default false,
    paid_time timestamp without time zone,
    create_user varchar(100),
    create_time timestamp without time zone default now(),
    update_user varchar(100),
    update_time timestamp without time zone default now()
);

create table m_user(
    user_id varchar(20) primary key,
    password varchar(100),
    user_name varchar(50),
    user_type varchar(10),
    status varchar(10) default 'active',
    token varchar(50),
    expires_time timestamp without time zone,
    last_login_time timestamp without time zone
);

create table m_table(
    table_no varchar(20) primary key,
    floor varchar(20),
    type varchar(20),
    capacity integer
);

create table m_permission(
    user_type varchar(10),
    menu_key varchar(20),
    primary key(user_type,menu_key)
);

CREATE OR REPLACE VIEW public.v_dining_orders AS 
 SELECT tti.table_no,
    toi.order_id,
    toi.create_time AS order_time,
    toi.is_paid,
    toi.paid_time,
    (jsonb_array_elements(toi.order_detail) ->> 'dishId'::text)::integer AS dish_id,
    (jsonb_array_elements(toi.order_detail) ->> 'name'::text) AS name,
    (jsonb_array_elements(toi.order_detail) ->> 'price'::text) AS price,
    (jsonb_array_elements(toi.order_detail) ->> 'quantity'::text) AS quantity,
    (jsonb_array_elements(toi.order_detail) ->> 'isServed'::text) AS is_served,
    ((jsonb_array_elements(toi.order_detail) ->> 'servedTime'::text))::timestamp without time zone AS served_time
   FROM (public.t_table_info tti
     LEFT JOIN public.t_order_info toi ON ((tti.table_id = toi.table_id)))
  WHERE (tti.end_time IS NULL);

create or replace view public.v_paid_order as
SELECT 
    tti.table_no,
    tti.start_time,
    tti.end_time,
    (jsonb_array_elements(toi.order_detail) ->> 'dishId'::text)::integer AS dish_id,
    (jsonb_array_elements(toi.order_detail) ->> 'name'::text) AS name,
    (jsonb_array_elements(toi.order_detail) ->> 'price'::text)::numeric AS price,
    (jsonb_array_elements(toi.order_detail) ->> 'quantity'::text)::numeric AS quantity
from 
    public.t_table_info tti
    inner join public.t_order_info toi ON tti.table_id = toi.table_id and toi.is_paid = true;
  
