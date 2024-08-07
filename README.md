# DailyExpenceSharingApplication

### Setup and Installation Instructions

1.  _Clone the Repository_:
    git clone <https://github.com/0001Nitish/DailyExpenceSharingApplication>
    cd <DailyExpenceSharingApplication>

2.  _Install Dependencies_:
    Ensure you have Maven installed. Run the following command to install the necessary dependencies:
    mvn clean install

3.  _Database Setup_:

    - Ensure you have a running instance of a database (e.g., MySQL, PostgreSQL).
    - Update the application.properties file with your database configuration:
      properties
      spring.datasource.url=jdbc:postgres://localhost:5432/your_database
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      spring.jpa.hibernate.ddl-auto=update

      DDL

      -- Table: public.expense_participants

      -- DROP TABLE IF EXISTS public.expense_participants;

    CREATE TABLE IF NOT EXISTS public.expense_participants
    (
    expense_id bigint NOT NULL,
    user_email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT fk21rsv1c3og03b9dlvvdcrvr7t FOREIGN KEY (user_email)
    REFERENCES public.users (email) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkc65ugmdfhjd6lxjx871fjkjxp FOREIGN KEY (expense_id)
    REFERENCES public.expenses (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS public.expense_participants
    OWNER to postgres;

    -- Table: public.expense_split_amounts

    -- DROP TABLE IF EXISTS public.expense_split_amounts;

    CREATE TABLE IF NOT EXISTS public.expense_split_amounts
    (
    expense_id bigint NOT NULL,
    amount double precision,
    user_email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT expense_split_amounts_pkey PRIMARY KEY (expense_id, user_email),
    CONSTRAINT fklrsbhve4lfycw5wuaeosupqui FOREIGN KEY (expense_id)
    REFERENCES public.expenses (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS public.expense_split_amounts
    OWNER to postgres;

    -- Table: public.expenses

    -- DROP TABLE IF EXISTS public.expenses;

    CREATE TABLE IF NOT EXISTS public.expenses
    (
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    amount double precision NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT expenses_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS public.expenses
    OWNER to postgres;

         -- Table: public.expenses

    -- DROP TABLE IF EXISTS public.expenses;

    CREATE TABLE IF NOT EXISTS public.expenses
    (
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    amount double precision NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT expenses_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS public.expenses
    OWNER to postgres;

4.  _Run the Application_:
    Use the following command to start the Spring Boot application:
    mvn spring-boot:run

5.  _Access the Application_:
    Open your browser and navigate to http://localhost:8080.

6.  _Postman collection_:
    {"collection":{"info":{"\_postman_id":"1ad586c2-110b-40ed-8922-e5f4c3c3f519","name":"DailyExpenceSharing","schema":"https://schema.getpostman.com/json/collection/v2.1.0/collection.json","updatedAt":"2024-07-28T18:42:27.000Z","createdAt":"2024-07-27T17:16:07.000Z","lastUpdatedBy":"37272584","uid":"37272584-1ad586c2-110b-40ed-8922-e5f4c3c3f519"},"item":[{"name":"health endpoint","id":"e6bd6c87-d98a-4c91-a205-5828bfefca81","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[],"url":{"raw":"localhost:8080/health/","host":["localhost"],"port":"8080","path":["health",""]}},"response":[],"uid":"37272584-e6bd6c87-d98a-4c91-a205-5828bfefca81"},{"name":"add expense perc","id":"341c32b2-f07c-45ef-b290-702a8ee8424f","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"POST","header":[],"body":{"mode":"raw","raw":"{\r\n \"amount\": 5000,\r\n \"description\": \"Party\",\r\n \"splitStrategy\": \"PERCENTAGE\",\r\n \"percentages\": [50, 25, 25],\r\n \"participantEmails\": [\"abcd\", \"abcd2\", \"abcd3\"]\r\n}","options":{"raw":{"language":"json"}}},"url":{"raw":"localhost:8080/api/expenses","host":["localhost"],"port":"8080","path":["api","expenses"]}},"response":[],"uid":"37272584-341c32b2-f07c-45ef-b290-702a8ee8424f"},{"name":"add expense exact","id":"ae6d326a-bbe7-4463-9e5c-1a4dbbca1ea2","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"POST","header":[],"body":{"mode":"raw","raw":"{\r\n \"amount\": 5000,\r\n \"description\": \"Party\",\r\n \"splitStrategy\": \"EXACT\",\r\n \"amounts\": [500, 2000, 2500],\r\n \"participantEmails\": [\"abcd\", \"abcd2\", \"abcd3\"]\r\n}","options":{"raw":{"language":"json"}}},"url":{"raw":"localhost:8080/api/expenses","host":["localhost"],"port":"8080","path":["api","expenses"]}},"response":[],"uid":"37272584-ae6d326a-bbe7-4463-9e5c-1a4dbbca1ea2"},{"name":"add expense equal","id":"3af27778-7e00-48c1-8185-03fcfef73f1c","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"POST","header":[],"body":{"mode":"raw","raw":"{\r\n \"amount\": 5000,\r\n \"description\": \"Party\",\r\n \"splitStrategy\": \"EQUAL\",\r\n \"participantEmails\": [\"abcd\", \"abcd2\", \"abcd3\"]\r\n}","options":{"raw":{"language":"json"}}},"url":{"raw":"localhost:8080/api/expenses","host":["localhost"],"port":"8080","path":["api","expenses"]}},"response":[],"uid":"37272584-3af27778-7e00-48c1-8185-03fcfef73f1c"},{"name":"get expense","id":"00e3b5b4-8836-4303-aa07-036be1f97d47","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[],"url":{"raw":"localhost:8080/api/expenses","host":["localhost"],"port":"8080","path":["api","expenses"]}},"response":[],"uid":"37272584-00e3b5b4-8836-4303-aa07-036be1f97d47"},{"name":"get balancesheet down","id":"a67c4d94-a840-4412-a2b1-eed6cebe4b80","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[],"url":{"raw":"localhost:8080/api/expenses/download","host":["localhost"],"port":"8080","path":["api","expenses","download"]}},"response":[],"uid":"37272584-a67c4d94-a840-4412-a2b1-eed6cebe4b80"},{"name":"get user","id":"f2d7427c-a30a-42e5-bbf4-c2ff1e1b266d","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[],"url":{"raw":"localhost:8080/api/users/user one","host":["localhost"],"port":"8080","path":["api","users","user one"]}},"response":[],"uid":"37272584-f2d7427c-a30a-42e5-bbf4-c2ff1e1b266d"},{"name":"add user","id":"ac1724a6-839d-41d3-83a5-a64b72029bd8","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"POST","header":[],"body":{"mode":"raw","raw":"{\r\n \"email\": \"user1@example.com\",\r\n \"name\": \"User One\",\r\n \"mobile\": \"1234567890\"\r\n}","options":{"raw":{"language":"json"}}},"url":{"raw":"localhost:8080/api/users","host":["localhost"],"port":"8080","path":["api","users"]}},"response":[],"uid":"37272584-ac1724a6-839d-41d3-83a5-a64b72029bd8"},{"name":"overall expence","id":"86ea97a7-3eeb-4008-b94f-8214acb166e2","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[],"url":{"raw":"localhost:8080/api/expenses/overall","host":["localhost"],"port":"8080","path":["api","expenses","overall"]}},"response":[],"uid":"37272584-86ea97a7-3eeb-4008-b94f-8214acb166e2"},{"name":"individual expense","id":"a3f145db-c191-42c9-9e3f-4fbd77d7c1de","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[],"url":{"raw":"localhost:8080/api/expenses/individual/user1","host":["localhost"],"port":"8080","path":["api","expenses","individual","user1"]}},"response":[],"uid":"37272584-a3f145db-c191-42c9-9e3f-4fbd77d7c1de"}]}}
