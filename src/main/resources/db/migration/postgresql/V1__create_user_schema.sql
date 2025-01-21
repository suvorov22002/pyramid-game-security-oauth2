-- Step 1: Create the schema
CREATE SCHEMA IF NOT EXISTS pyramid_owner;

-- Step 2: Grant all privileges on the schema
GRANT ALL ON SCHEMA pyramid_owner TO postgres;

-- Step 3: Grant all privileges on all tables in the schema
GRANT ALL ON ALL TABLES IN SCHEMA pyramid_owner TO postgres;

-- Step 4: Grant all privileges on all sequences in the schema
GRANT ALL ON ALL SEQUENCES IN SCHEMA pyramid_owner TO postgres;

-- Step 5: Grant all privileges on all functions in the schema
GRANT ALL ON ALL FUNCTIONS IN SCHEMA pyramid_owner TO postgres;