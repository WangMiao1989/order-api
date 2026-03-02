create sequence category_id_seq;
create table m_category(
    id bigint primary key default nextval('category_id_seq'),
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
    price varchar(50),
    image bytea,
    tags varchar(100),
    display_order integer,
    create_user varchar(100),
    create_time timestamp without time zone default now(),
    update_user varchar(100),
    update_time timestamp without time zone default now()
);

create table t_discount(
    dish_id bigint not null,
    effective_start_date date,
    effective_end_date date,
    percent numeric,
    create_user varchar(100),
    create_time timestamp without time zone default now(),
    update_user varchar(100),
    update_time timestamp without time zone default now(),
    primary key(dish_id, effective_start_date)
)

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
    create_user varchar(100),
    create_time timestamp without time zone default now(),
    update_user varchar(100),
    update_time timestamp without time zone default now()
);