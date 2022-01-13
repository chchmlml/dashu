CREATE TABLE stock(
   id INT PRIMARY KEY NOT NULL,
   name TEXT NOT NULL,
   code TEXT NOT NULL,
   industry TEXT NOT NULL,
   pb REAL,
   pb_score REAL,
   pe_ttm REAL,
   pe_ttm_score REAL,
   chg_score REAL,
   business_reputation INT,
   invisible_assets REAL,
   stock_mortgage INT,
   financial INT, 
   roe REAL,
   current REAL,
   low_current_in_year REAL,
   dividend REAL
);