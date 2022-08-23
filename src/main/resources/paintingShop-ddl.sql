drop table if exists "cart" cascade;
drop table if exists "person" cascade;
drop table if exists "painting" cascade;
drop table if exists "order" cascade;
drop table if exists "warehouse" cascade;
drop table if exists "painting_ordered" cascade;
drop table if exists "painting_in_cart" cascade;





/* CREATE INDEX "Key" ON  "cart" ("no_items"); */

CREATE TABLE "person" (
  "id" varchar not null,
  "username" varchar not null,
  "password" varchar not null,
  "name" varchar not null,
  "email" varchar not null,
  "is_admin" Boolean not null,
  "address_street" varchar,
  "address_city" varchar,
  "address_state" varchar,
  "address_zip" int,
  PRIMARY KEY ("id")
);

CREATE TABLE "cart" (
  "id" varchar not null,
  "no_items" int not null,
  "person_id" varchar not null,
    PRIMARY KEY ("id"),
  CONSTRAINT "FK_cart.person_id"
    FOREIGN KEY ("person_id")
      REFERENCES "person"("id")
);

CREATE TABLE "warehouse" (
  "id" varchar not null,
  "name" varchar not null,
  "address_street" varchar not null,
  "address_city" varchar not null,
  "address_state" varchar not null,
  "address_zip" int not null,
  PRIMARY KEY ("id")
);

CREATE TABLE "painting" (
  "id" varchar not null,
  "name" varchar not null,
  "author" varchar not null,
  "image" varchar not null,
  "is_available" boolean not null,
  "cost" decimal,
  "warehouse_id" varchar,
  "person_id" varchar,
  PRIMARY KEY ("id"),
  CONSTRAINT "FK_painting.warehouse_id"
    FOREIGN KEY ("warehouse_id")
      REFERENCES "warehouse"("id"),
  CONSTRAINT "FK_painting.person_id"
    FOREIGN KEY ("person_id")
      REFERENCES "person"("id")
);

CREATE TABLE "order" (
  "id" varchar not null,
  "no_items" int not null,
  "total_cost" decimal not null,
  "date" varchar not null,
  "person_id" varchar not null,
  "warehouse_id" varchar not null,
  PRIMARY KEY ("id"),
  CONSTRAINT "FK_order.person_id"
    FOREIGN KEY ("person_id")
      REFERENCES "person"("id"),
  CONSTRAINT "FK_order.warehouse_id"
    FOREIGN KEY ("warehouse_id")
      REFERENCES "warehouse"("id")
);

CREATE TABLE "painting_ordered" (
  "painting_id" varchar not null,
  "order_id" varchar not null,
  CONSTRAINT "FK_painting_ordered.order_id"
    FOREIGN KEY ("order_id")
      REFERENCES "order"("id"),
  CONSTRAINT "FK_painting_ordered.painting_id"
    FOREIGN KEY ("painting_id")
      REFERENCES "painting"("id")
);

CREATE TABLE "painting_in_cart" (
  "painting_id" varchar not null,
  "cart_id" varchar not null,
  CONSTRAINT "FK_painting_in_cart.cart_id"
    FOREIGN KEY ("cart_id")
      REFERENCES "cart"("id"),
  CONSTRAINT "FK_painting_in_cart.painting_id"
    FOREIGN KEY ("painting_id")
      REFERENCES "painting"("id")
);

