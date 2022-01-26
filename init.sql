-- 股票
CREATE TABLE stock
(
    id                  INTEGER PRIMARY KEY AUTOINCREMENT,
    name                TEXT NOT NULL default '',
    code                TEXT NOT NULL default '',

    industry            TEXT NOT NULL default '',
    pb                  REAL NOT NULL default 0.0,
    pb_score            REAL NOT NULL default 0.0,
    pe_ttm              REAL NOT NULL default 0.0,
    pe_ttm_score        REAL NOT NULL default 0.0,
    chg_score           REAL NOT NULL default 0.0,
    business_reputation INT  NOT NULL default 0,
    invisible_assets    REAL NOT NULL default 0.0,
    stock_mortgage      INT  NOT NULL default 0,
    financial           INT  NOT NULL default 0,
    roe                 REAL NOT NULL default 0.0,
    current_price       REAL NOT NULL default 0.0,
    min_price           REAL NOT NULL default 0.0,
    bug_price           REAL NOT NULL default 0.0,
    dividend            REAL NOT NULL default 0.0,

    hold                REAL NOT NULL default 0.0,

    created_at          INT  NOT NULL default 0,
    updated_at          INT  NOT NULL default 0
);

-- 大数期数
CREATE TABLE dashu
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    name       TEXT NOT NULL default '',
    total      REAL NOT NULL default 0.0,

    created_at INT  NOT NULL default 0,
    updated_at INT  NOT NULL default 0
);

-- 关联
CREATE TABLE relation
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    dashu_id   INT NOT NULL default 0,
    stock_id   INT NOT NULL default 0,

    created_at INT NOT NULL default 0,
    updated_at INT NOT NULL default 0
);
