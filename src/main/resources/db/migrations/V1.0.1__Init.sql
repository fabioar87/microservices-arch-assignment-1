create schema if not exists customerservice;

create TABLE IF NOT EXISTS customerservice.customer (
    id uuid NOT NULL DEFAULT random_uuid(),
    username varchar(64),
    first_name varchar(16),
    last_name varchar(16),
    email varchar(48),
    phone varchar(24),
    customer_status varchar(16),
    PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS customerservice."order" (
    id uuid NOT NULL DEFAULT random_uuid(),
    customer_id uuid NOT NULL,
    order_date timestamp,
    total numeric(16, 4) DEFAULT 0 NOT NULL,
    status varchar(24),
    FOREIGN KEY(customer_id)
    REFERENCES customerservice.customer(id),
    PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS customerservice.product (
    id uuid NOT NULL DEFAULT random_uuid(),
    name varchar(56) NOT NULL,
    base_price numeric(16, 4) DEFAULT 0 NOT NULL,
    image_url varchar(256),
    PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS customerservice.item (
    id uuid NOT NULL DEFAULT random_uuid(),
    product_id uuid NOT NULL,
    quantity numeric(8, 0),
    FOREIGN KEY(product_id)
    REFERENCES customerservice.product(id),
    PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS customerservice.order_item (
    id uuid NOT NULL DEFAULT random_uuid(),
    order_id uuid NOT NULL,
    item_id uuid NOT NULL,
    FOREIGN KEY (order_id)
    REFERENCES customerservice."order"(id),
    FOREIGN KEY(item_id)
    REFERENCES customerservice.item(id)
);

/*
    Data seed
    Products: Data center hardware components
 */
insert into customerservice.customer (id, username, first_name, last_name, email, phone, customer_status) values('a1b9b31d-e73c-4112-af7c-b68530f38222', 'tech_guru42', 'Alice', 'Johnson', 'alice.johnson@example.com', '+1-555-1234', 'ACTIVE');
insert into customerservice.customer (id, username, first_name, last_name, email, phone, customer_status) values('a1b9b31d-e73c-4112-af7c-b68530f38223', 'datacenter_master', 'Bob', 'Williams', 'bob.williams@example.com', '+1-555-5678', 'ACTIVE');

/* New products */
insert into customerservice.product values ('6d62d909-f957-430e-8689-b5129c0bb75e', 'Intel Xeon Platinum 8260', 4500.00, 'https://example.com/images/xeon_8260.jpg');
insert into customerservice.product values ('a0a4f044-b040-410d-8ead-4de0446aec7e', 'AMD EPYC 7763', 5800, 'https://example.com/images/epyc_7763.jpg');
insert into customerservice.product values ('808a2de1-1aaa-4c25-a9b9-6612e8f29a38', 'NVIDIA A100 Tensor Core GPU', 9500, 'https://example.com/images/nvidia_a100.jpg');
insert into customerservice.product values ('510a0d7e-8e83-4193-b483-e27e09ddc34d', 'Seagate Exos X18 18TB HDD', 400, 'https://example.com/images/seagate_x18.jpg');
insert into customerservice.product values ('03fef6ac-1896-4ce8-bd69-b798f85c6e0b', 'Samsung PM9A3 3.84TB NVMe SSD', 1200, 'https://example.com/images/samsung_pm9a3.jpg');
insert into customerservice.product values ('d3588630-ad8e-49df-bbd7-3167f7efb246', 'Dell PowerEdge R750 Server Rack', 7800, 'https://example.com/images/dell_r750.jpg');
insert into customerservice.product values ('819e1fbf-8b7e-4f6d-811f-693534916a8b', 'HPE ProLiant DL380 Gen10 Server', 6200, 'https://example.com/images/hpe_dl380.jpg');
insert into customerservice.product values ('3395a42e-2d88-40de-b95f-e00e1502085b', 'APC Smart-UPS SRT 6000VA', 2500, 'https://example.com/images/apc_srt6000.jpg');
insert into customerservice.product values ('3395a43e-2d88-40de-b95f-e00e1502085b', 'Cisco Nexus 9300 Series Switch', 9000, 'https://example.com/images/cisco_9300.jpg');
insert into customerservice.product values ('837ab141-399e-4c1f-9abc-bace40296bac', 'Juniper MX204 Router', 12000, 'https://example.com/images/juniper_mx204.jpg');

/* Orders and aggregated items */
insert into customerservice."order"(id, customer_id, order_date, total, status) values('0a59ba9f-629e-4445-8129-b9bce1985d6a','a1b9b31d-e73c-4112-af7c-b68530f38222', current_timestamp, 10000.00, 'CREATED');
INSERT INTO customerservice.item values('a7384042-e4aa-4c93-85ae-31a346dad703', 'd3588630-ad8e-49df-bbd7-3167f7efb246', 1);
INSERT INTO customerservice.item values('a7384042-e4aa-4c93-85ae-31a346dad704', '6d62d909-f957-430e-8689-b5129c0bb75e', 1);
INSERT INTO customerservice.item values('a7384042-e4aa-4c93-85ae-31a346dad705', '3395a42e-2d88-40de-b95f-e00e1502085b', 1);

/* Inserting the relationship between orders and items */
INSERT INTO customerservice.order_item values
    ('66682caa-1234-34ed-a134-ff281f564e3b', '0a59ba9f-629e-4445-8129-b9bce1985d6a', 'a7384042-e4aa-4c93-85ae-31a346dad703'),
    ('66682caa-a6d8-46ed-a173-ff822f754e1c', '0a59ba9f-629e-4445-8129-b9bce1985d6a', 'a7384042-e4aa-4c93-85ae-31a346dad704'),
    ('efeefa71-2760-412a-9ec8-0a040d90f02c', '0a59ba9f-629e-4445-8129-b9bce1985d6a', 'a7384042-e4aa-4c93-85ae-31a346dad705');