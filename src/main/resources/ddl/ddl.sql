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
    last_login_time timestamp without time zone
);

create sequence auth_id_seq;
create table t_auth(
    id bigint primary key default nextval('auth_id_seq'),
    user_id varchar(20),
    token varchar(50),
    expires_time timestamp without time zone,
    create_time timestamp without time zone default now()
);

create table m_table(
    table_no varchar(20) primary key,
    floor varchar(20),
    type varchar(20),
    capacity integer
);

CREATE OR REPLACE VIEW public.v_orders AS 
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
   FROM (t_table_info tti
     LEFT JOIN t_order_info toi ON ((tti.table_id = toi.table_id)))
  WHERE (tti.end_time IS NULL);
  
