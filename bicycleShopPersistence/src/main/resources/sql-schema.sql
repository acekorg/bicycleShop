-- Product table
CREATE TABLE "PRODUCT"(
    "id" BIGINT NOT NULL,
    "productType" VARCHAR(255) NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "brand" VARCHAR(255) NOT NULL,
    "model" VARCHAR(255) NOT NULL,
    "stock" BIGINT NOT NULL
);
ALTER TABLE
    "PRODUCT" ADD PRIMARY KEY("id");

-- Parts table
CREATE TABLE "PART"(
    "id" BIGINT NOT NULL,
    "partType" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "basePrice" DECIMAL(8, 2) NOT NULL,
    "stock" BIGINT NOT NULL
);
ALTER TABLE
    "PART" ADD PRIMARY KEY("id");

-- Part combinations surcharges
CREATE TABLE "PART_COMBINATIONS_PRICING"(
    "id" BIGINT NOT NULL,
    "mainPartId" BIGINT NOT NULL,
    "pairedPartId" BIGINT NOT NULL,
    "surcharge" DECIMAL(8, 2) NOT NULL,
    "productId" BIGINT NOT NULL
);
ALTER TABLE
    "PART_COMBINATIONS_PRICING" ADD PRIMARY KEY("id");

-- Part combinations invalidity
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

-- Foreign keys definitions
ALTER TABLE
    "PRODUCT_PART" ADD CONSTRAINT "product_part_productid_foreign" FOREIGN KEY("productId") REFERENCES "PRODUCT"("id");
ALTER TABLE
    "PART_COMBINATIONS_INVALIDITY" ADD CONSTRAINT "part_combinations_invalidity_productid_foreign" FOREIGN KEY("productId") REFERENCES "PRODUCT"("id");
ALTER TABLE
    "PART_COMBINATIONS_INVALIDITY" ADD CONSTRAINT "part_combinations_invalidity_firstpartid_foreign" FOREIGN KEY("firstPartId") REFERENCES "PART"("id");
ALTER TABLE
    "PART_COMBINATIONS_PRICING" ADD CONSTRAINT "part_combinations_pricing_mainpartid_foreign" FOREIGN KEY("mainPartId") REFERENCES "PART"("id");
ALTER TABLE
    "PART_COMBINATIONS_INVALIDITY" ADD CONSTRAINT "part_combinations_invalidity_secondpartid_foreign" FOREIGN KEY("secondPartId") REFERENCES "PART"("id");
ALTER TABLE
    "PART_COMBINATIONS_PRICING" ADD CONSTRAINT "part_combinations_pricing_productid_foreign" FOREIGN KEY("productId") REFERENCES "PRODUCT"("id");
ALTER TABLE
    "PRODUCT_PART" ADD CONSTRAINT "product_part_partid_foreign" FOREIGN KEY("partId") REFERENCES "PART"("id");
ALTER TABLE
    "PART_COMBINATIONS_PRICING" ADD CONSTRAINT "part_combinations_pricing_pairedpartid_foreign" FOREIGN KEY("pairedPartId") REFERENCES "PART"("id");

-- Forbid invalid entries for PRODUCT and PART types. Options must match ProductType.java and PartType.java
ALTER TABLE
    "PRODUCT" ADD CONSTRAINT product_type_check CHECK (productType IN ('BICYCLE'));
ALTER TABLE
    "PART" ADD CONSTRAINT part_type_check CHECK (partType IN ('FRAME_TYPE', 'FRAME_FINISH', 'WHEELS', 'RIM_COLOR', 'CHAIN'));

-- Search optimizations

-- Table PART_COMBINATIONS_INVALIDITY is always searched by productId.
CREATE INDEX idx_part_combination_product_id ON customers (productId);
