-- 股票
CREATE TABLE stock
(
    id                  INTEGER PRIMARY KEY AUTOINCREMENT,
    name                TEXT NOT NULL default '',
    code                TEXT NOT NULL default '',
    industry            TEXT NOT NULL default '',
    dashu_id            INT  NOT NULL default 0,
    pb                  REAL NOT NULL default 0.0,
    pb_score            REAL NOT NULL default 0.0,
    pe_ttm              REAL NOT NULL default 0.0,
    pe_ttm_score        REAL NOT NULL default 0.0,
    chg_score           REAL NOT NULL default 0.0,
    business_reputation TEXT NOT NULL default '',
    invisible_assets    TEXT NOT NULL default '',
    stock_mortgage      TEXT NOT NULL default '',
    financial           TEXT NOT NULL default '',
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