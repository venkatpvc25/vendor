CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE account (
    pk_account_id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
    phone VARCHAR(20) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password_hash TEXT NOT NULL,
    role VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING_APPROVAL' CHECK (
        status IN (
            'DISABLED',
            'ACTIVE',
            'PENDING_APPROVAL',
        )
    ),
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE vendor (
    pk_vendor_id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
    fk_account_id UUID UNIQUE NOT NULL,
    name VARCHAR(150) NOT NULL,
    category VARCHAR(50),
    address TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING_APPROVAL' CHECK (
        status IN (
            'PENDING_APPROVAL',
            'ACTIVE',
            'SUSPENDED'
        )
    ),
    CONSTRAINT fk_vendor_account FOREIGN KEY (fk_account_id) REFERENCES account (pk_account_id) ON DELETE CASCADE
);