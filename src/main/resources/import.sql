-- CREATE CLIENT

INSERT INTO CLIENT VALUES ('1 Bvd des tchecoslovaques 69007  Lyon', '1987-10-24', 'Dupont','Jean', '0676985412');

-- CREATE BANK_ACCOUNT
INSERT INTO COMPTE_BANCAIRE VALUES ( NOW(), 900, 1);

-- CREATE OPERATION
INSERT INTO OPERATION VALUES( NOW(), 1000,'DEPOT', 1);
INSERT INTO OPERATION VALUES(NOW(),-100, 'RETRAIT', 1);