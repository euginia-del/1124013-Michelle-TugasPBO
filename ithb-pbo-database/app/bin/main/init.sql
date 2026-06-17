-- ============================================================
-- Enums
-- ============================================================

CREATE TYPE "user_type" AS ENUM (
    'MAHASISWA',
    'DOSEN_TETAP',
    'DOSEN_HONORER',
    'STAFF'
);

CREATE TYPE "user_status" AS ENUM (
    'AKTIF',
    'NONAKTIF',
    'CUTI'
);


-- ============================================================
-- Tables
-- ============================================================

CREATE TABLE IF NOT EXISTS "jurusan" (
    "kode_jurusan"  VARCHAR(10)     NOT NULL,
    "nama"          VARCHAR(100)    NOT NULL,
    PRIMARY KEY ("kode_jurusan")
);

CREATE TABLE IF NOT EXISTS "user" (
    "nomor_induk"       VARCHAR(20)     NOT NULL,
    "email"             VARCHAR(255)    NOT NULL,
    "password"          VARCHAR(255)    NOT NULL,
    "status"            "user_status"   NOT NULL DEFAULT 'AKTIF',
    "type"              "user_type"     NOT NULL,
    "nama"              VARCHAR(100)    NOT NULL,
    "tanggal_masuk"     DATE            NOT NULL,
    "tanggal_keluar"    DATE            NULL DEFAULT NULL,

    -- karyawan fields (dosen_tetap, dosen_honorer, staff)
    "gaji_pokok"        DECIMAL(15,2)   NULL DEFAULT NULL,  -- dosen_tetap, staff
    "honor_per_sks"     DECIMAL(15,2)   NULL DEFAULT NULL,  -- dosen_tetap, dosen_honorer

    -- mahasiswa fields
    "kode_jurusan"      VARCHAR(10)     NULL DEFAULT NULL,

    PRIMARY KEY ("nomor_induk"),
    UNIQUE ("email"),
    CONSTRAINT "fk_user_jurusan" FOREIGN KEY ("kode_jurusan")
        REFERENCES "jurusan" ("kode_jurusan")
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT "chk_tanggal" CHECK (
        "tanggal_keluar" IS NULL OR "tanggal_keluar" > "tanggal_masuk"
    ),
    CONSTRAINT "chk_mahasiswa_fields" CHECK (
        "type" != 'MAHASISWA' OR "kode_jurusan" IS NOT NULL
    ),
    CONSTRAINT "chk_dosen_tetap_fields" CHECK (
        "type" != 'DOSEN_TETAP' OR ("gaji_pokok" IS NOT NULL AND "honor_per_sks" IS NOT NULL)
    ),
    CONSTRAINT "chk_staff_fields" CHECK (
        "type" != 'STAFF' OR "gaji_pokok" IS NOT NULL
    ),
    CONSTRAINT "chk_dosen_honorer_fields" CHECK (
        "type" != 'DOSEN_HONORER' OR "honor_per_sks" IS NOT NULL
    )
);

CREATE TABLE IF NOT EXISTS "matakuliah" (
    "kode_matakuliah"   VARCHAR(10)     NOT NULL,
    "nama"              VARCHAR(100)    NOT NULL,
    "sks"               INTEGER         NOT NULL,
    "kode_jurusan"      VARCHAR(10)     NULL DEFAULT NULL,
    PRIMARY KEY ("kode_matakuliah"),
    CONSTRAINT "fk_matakuliah_jurusan" FOREIGN KEY ("kode_jurusan")
        REFERENCES "jurusan" ("kode_jurusan")
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS "mahasiswa_matakuliah" (
    "nomor_induk"       VARCHAR(20)     NOT NULL,
    "kode_matakuliah"   VARCHAR(10)     NOT NULL,
    "nilai"             DECIMAL(5,2)    NULL DEFAULT NULL,
    PRIMARY KEY ("nomor_induk", "kode_matakuliah"),
    CONSTRAINT "fk_mhs_mk_user" FOREIGN KEY ("nomor_induk")
        REFERENCES "user" ("nomor_induk") ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT "fk_mhs_mk_matakuliah" FOREIGN KEY ("kode_matakuliah")
        REFERENCES "matakuliah" ("kode_matakuliah") ON UPDATE NO ACTION ON DELETE CASCADE
);


-- ============================================================
-- Seed Data
-- ============================================================

INSERT INTO "jurusan" ("kode_jurusan", "nama") VALUES
    ('IF', 'Informatika'),
    ('SI', 'Sistem Informasi');

INSERT INTO "matakuliah" ("kode_matakuliah", "nama", "sks", "kode_jurusan") VALUES
    ('IF-101', 'Algoritma dan Pemrograman',      3, 'IF'),
    ('IF-102', 'Struktur Data',                  3, 'IF'),
    ('IF-103', 'Basis Data',                     3, 'IF'),
    ('IF-104', 'Pemrograman Berorientasi Objek', 3, 'IF'),
    ('IF-105', 'Jaringan Komputer',             3, 'IF'),
    ('SI-201', 'Sistem Informasi Manajemen',     3, 'SI'),
    ('SI-202', 'Analisis Proses Bisnis',         3, 'SI'),
    ('SI-203', 'Kewirausahaan',                  3, 'SI');

INSERT INTO "user" (
    "nomor_induk", "email", "password", "status", "type", "nama",
    "tanggal_masuk", "tanggal_keluar", "gaji_pokok", "honor_per_sks", "kode_jurusan"
) VALUES
    -- Mahasiswa

('22001', 'denji@student.ac.id', 'pass1', 'AKTIF', 'MAHASISWA', 'Denji', '2022-08-01', NULL, NULL, NULL, 'IF'),
('22002', 'power@student.ac.id', 'pass2', 'AKTIF', 'MAHASISWA', 'Power', '2022-08-01', NULL, NULL, NULL, 'IF'),
('22003', 'aki@student.ac.id', 'pass3', 'AKTIF', 'MAHASISWA', 'Aki Hayakawa', '2022-08-01', NULL, NULL, NULL, 'IF'),
('23001', 'beam@student.ac.id', 'pass4', 'AKTIF', 'MAHASISWA', 'Beam', '2023-08-01', NULL, NULL, NULL, 'SI'),

-- Dosen Tetap

('DT001', 'kishibe@univ.ac.id', 'pass123', 'AKTIF', 'DOSEN_TETAP', 'Kishibe', '2018-01-01', NULL, 5000000, 50000, NULL),
('DT002', 'quanxi@univ.ac.id', 'pass456', 'AKTIF', 'DOSEN_TETAP', 'Quanxi', '2019-01-01', NULL, 6000000, 60000, NULL),

-- Dosen Honorer

('DH001', 'himeno@univ.ac.id', 'pass789', 'AKTIF', 'DOSEN_HONORER', 'Himeno', '2022-01-01', NULL, NULL, 40000, NULL),

-- Staff

('STF001', 'makima@univ.ac.id', 'staff123', 'AKTIF', 'STAFF', 'Makima', '2020-01-01', NULL, 3000000, NULL, NULL),
('STF002', 'arai@univ.ac.id', 'staff456', 'AKTIF', 'STAFF', 'Arai', '2021-01-01', NULL, 2500000, NULL, NULL);

INSERT INTO "mahasiswa_matakuliah" ("nomor_induk", "kode_matakuliah", "nilai") VALUES
    ('22001', 'IF-101', 90),
    ('22001', 'IF-102', 73),
    ('22001', 'IF-103', 85),
    ('22002', 'IF-101', 56),
    ('22002', 'IF-103', 23),
    ('22002', 'IF-104', 40),
    ('22003', 'IF-101', 85),
    ('22003', 'IF-102', 80),
    ('22003', 'IF-105', 88),
    ('23001', 'SI-201', 55),
    ('23001', 'SI-202', 60),
    ('23001', 'SI-203', 50);
    ('22002', 'IF-101', 56),
    ('22002', 'IF-103', 23),
    ('22002', 'IF-104', 40),
    ('22003', 'IF-101', 85),
    ('22003', 'IF-102', 80),
    ('22003', 'IF-105', 88),
    ('23001', 'SI-201', 55),
    ('23001', 'SI-202', 60),
    ('23001', 'SI-205', 50);