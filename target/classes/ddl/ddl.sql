create sequence category_id_seq;
create sequence dish_id_seq;

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