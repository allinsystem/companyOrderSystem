create table IF NOT EXISTS t_customers_master
(
    customer_id numeric NOT NULL,
    customer_name varchar(50) NOT NULL,
    PRIMARY KEY (customer_id)
);

create table IF NOT EXISTS t_sequence_id
(
    sequence_type int NOT NULL,
    sequence_id numeric NOT NULL,
    PRIMARY KEY (sequence_type)
);

create table IF NOT EXISTS t_employee_master
(
    employee_id numeric NOT NULL,
    employee_name varchar(50) NOT NULL,
    employee_type varchar(50) NOT NULL,
    PRIMARY KEY (employee_id)
);

create table IF NOT EXISTS t_invoice_form
(
    invoice_id numeric NOT NULL,
    customer_id numeric NOT NULL,
    employee_id numeric NOT NULL,
    invoice_date date NOT NULL,
    project_name varchar(50) NOT NULL,
    count int NOT NULL,
    price decimal(15,2)  NOT NULL,
    PRIMARY KEY (invoice_id),
    FOREIGN KEY(customer_id)
        REFERENCES t_customers_master(customer_id),
    FOREIGN KEY(employee_id)
        REFERENCES t_employee_master(employee_id)
);

create table IF NOT EXISTS t_order_form
(
    order_id numeric NOT NULL,
    customer_id numeric NOT NULL,
    employee_id numeric NOT NULL,
    order_date date NOT NULL,
    minTime int NOT NULL,
    maxTime int NOT NULL,
    count int NOT NULL,
    price decimal(15,2)  NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY(customer_id)
        REFERENCES t_customers_master(customer_id),
    FOREIGN KEY(employee_id)
        REFERENCES t_employee_master(employee_id)
);