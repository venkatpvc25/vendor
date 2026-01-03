-- 1. Update existing data
UPDATE account
SET
    status = 'PENDING_APPROVAL'
WHERE
    status = 'PENDING_ACTIVATION';

-- 2. Drop old constraint
ALTER TABLE account DROP CONSTRAINT IF EXISTS account_status_check;

-- 3. Change default
ALTER TABLE account
ALTER COLUMN status
SET DEFAULT 'PENDING_APPROVAL';

-- 4. Add new constraint
ALTER TABLE account
ADD CONSTRAINT account_status_check CHECK (
    status IN (
        'DISABLED',
        'ACTIVE',
        'PENDING_APPROVAL'
    )
);