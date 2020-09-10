-- This file has fixed name and location
INSERT INTO Customer (accnum, name, amount) VALUES
(1234, 'Alice Wonderland', 1000),
(2345, 'Bill Bates', 10000),
(3456, 'Corunsho Alakija', 100000);

INSERT INTO BankTransaction (transactionUUID,accnumFrom,accnumTo,transactionMessage,amount) VALUES
('7e64f25c-f366-11ea-adc1-0242ac120002',9999,8888,'AppTransfer',5000);
