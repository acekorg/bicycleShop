-- Product table
CREATE TABLE "PRODUCT"(
      "id" BIGINT NOT NULL,
      "productType" VARCHAR(255) NOT NULL,
      "description" VARCHAR(255) NOT NULL,
      "brand" VARCHAR(255) NOT NULL,
      "model" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "PRODUCT" ADD PRIMARY KEY("id");

-- Parts table
CREATE TABLE "PART"(
       "id" BIGINT NOT NULL,
       "partType" VARCHAR(255) NOT NULL,
       "name" VARCHAR(255) NOT NULL,
       "basePrice" DECIMAL(8, 2) NOT NULL
);
ALTER TABLE
    "PART" ADD PRIMARY KEY("id");

-- Part combinations surcharges

CREATE TABLE "PART_COMBINATIONS_PRICING"(
        "id" BIGINT NOT NULL,
        "firstPartId" BIGINT NOT NULL,
        "secondPartId" BIGINT NOT NULL,
        "surcharge" DECIMAL(8, 2) NOT NULL,
        "productId" BIGINT NOT NULL
);
ALTER TABLE
    "PART_COMBINATIONS_PRICING" ADD PRIMARY KEY("id");

-- Part combinations disallowance
CREATE TABLE "PART_COMBINATIONS_INVALIDITY"(
       "id" BIGINT NOT NULL,
       "firstPartId" BIGINT NOT NULL,
       "secondPartId" BIGINT NOT NULL,
       "productId" BIGINT NOT NULL
);
ALTER TABLE
    "PART_COMBINATIONS_INVALIDITY" ADD PRIMARY KEY("id");

-- Product and parts have N:N relation. This is the joint table
CREATE TABLE "PRODUCT_PART"(
       "id" BIGINT NOT NULL,
       "productId" BIGINT NOT NULL,
       "partId" BIGINT NOT NULL
);
ALTER TABLE
    "PRODUCT_PART" ADD PRIMARY KEY("id");
ALTER TABLE
    "PRODUCT_PART" ADD CONSTRAINT "product_part_productid_foreign" FOREIGN KEY("productId") REFERENCES "PRODUCT"("id");
ALTER TABLE
    "PART_COMBINATIONS_INVALIDITY" ADD CONSTRAINT "part_combinations_invalidity_productid_foreign" FOREIGN KEY("productId") REFERENCES "PRODUCT"("id");
ALTER TABLE
    "PART_COMBINATIONS_INVALIDITY" ADD CONSTRAINT "part_combinations_invalidity_firstpartid_foreign" FOREIGN KEY("firstPartId") REFERENCES "PART"("id");
ALTER TABLE
    "PART_COMBINATIONS_PRICING" ADD CONSTRAINT "part_combinations_pricing_firstpartid_foreign" FOREIGN KEY("firstPartId") REFERENCES "PART"("id");
ALTER TABLE
    "PART_COMBINATIONS_INVALIDITY" ADD CONSTRAINT "part_combinations_invalidity_secondpartid_foreign" FOREIGN KEY("secondPartId") REFERENCES "PART"("id");
ALTER TABLE
    "PART_COMBINATIONS_PRICING" ADD CONSTRAINT "part_combinations_pricing_productid_foreign" FOREIGN KEY("productId") REFERENCES "PRODUCT"("id");
ALTER TABLE
    "PRODUCT_PART" ADD CONSTRAINT "product_part_partid_foreign" FOREIGN KEY("partId") REFERENCES "PART"("id");
ALTER TABLE
    "PART_COMBINATIONS_PRICING" ADD CONSTRAINT "part_combinations_pricing_secondpartid_foreign" FOREIGN KEY("secondPartId") REFERENCES "PART"("id");G" ADD CONSTRAINT "part_combinations_pricing_second_part_id_foreign" FOREIGN KEY("second_part_id") REFERENCES "PARTS"("id");