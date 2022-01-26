-- 股票
CREATE TABLE stock
(
    id                  INTEGER PRIMARY KEY AUTOINCREMENT,
    name                TEXT NOT NULL,
    code                TEXT NOT NULL,

    industry            TEXT NOT NULL,
    pb                  REAL NOT NULL,
    pb_score            REAL NOT NULL,
    pe_ttm              REAL NOT NULL,
    pe_ttm_score        REAL NOT NULL,
    chg_score           REAL NOT NULL,
    business_reputation INT  NOT NULL,
    invisible_assets    REAL NOT NULL,
    stock_mortgage      INT  NOT NULL,
    financial           INT  NOT NULL,
    roe                 REAL NOT NULL,
    current_price       REAL NOT NULL,
    min_price           REAL NOT NULL,
    dividend            REAL NOT NULL,

    hold                REAL NOT NULL,

    created_at          INT  NOT NULL,
    updated_at          INT  NOT NULL
);

-- 大数期数
CREATE TABLE dashu
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    name       TEXT NOT NULL,
    total      REAL NOT NULL,

    created_at INT  NOT NULL,
    updated_at INT  NOT NULL
);

-- 关联
CREATE TABLE relation
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    dashu_id   INT NOT NULL,
    stock_id   INT NOT NULL,

    created_at INT NOT NULL,
    updated_at INT NOT NULL
);
