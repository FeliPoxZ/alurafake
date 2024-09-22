CREATE TABLE Course(
    code varchar(50) NOT NULL,
    name varchar(100) NOT NULL,
    emailInstrutor varchar(50) NOT NULL,
    description text NOT NULL,
    status enum('ACTIVE', 'INACTIVE') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ACTIVE',
    dateInactivation datetime,
    PRIMARY KEY (code),
    CONSTRAINT UC_Code UNIQUE (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;