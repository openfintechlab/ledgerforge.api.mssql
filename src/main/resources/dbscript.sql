
DELETE FROM LEDFOR_TRANSACTION;
DELETE FROM LEDFOR_ACCOUNT;
DELETE FROM LEDFOR_TRANSACTIONTYPE;
DELETE FROM LEDFOR_ISOCURRENCIES;


DROP TABLE IF EXISTS LEDFOR_ACCOUNT;
DROP TABLE IF EXISTS LEDFOR_TRANSACTION;
DROP TABLE IF EXISTS LEDFOR_TRANSACTIONTYPE;
DROP TABLE IF EXISTS LEDFOR_ISOCURRENCIES;

-- Create LEDFOR_TRANSACTIONTYPE table
CREATE TABLE LEDFOR_TRANSACTIONTYPE (
    transactionCode NVARCHAR(64) PRIMARY KEY,
    description NVARCHAR(255),
    corelCode NVARCHAR(255),
    recordHash NVARCHAR(255),
    createdon DATETIME DEFAULT GETDATE(),
    updatedon DATETIME DEFAULT GETDATE(),    
);

-- Create LEDFOR_ISOCURRENCIES table
CREATE TABLE LEDFOR_ISOCURRENCIES (
    currency_code_iso NVARCHAR(3) PRIMARY KEY,
    currency_name NVARCHAR(255),
    recordHash NVARCHAR(255),
    decimal_denom int,
    createdon DATETIME DEFAULT GETDATE(),
    updatedon DATETIME DEFAULT GETDATE()    

);


-- Create LEDFOR_ACCOUNT table
CREATE TABLE LEDFOR_ACCOUNT (
	instrumentID NVARCHAR(64) PRIMARY KEY,
    instrumentNumber NVARCHAR(64),
    instrumentType NVARCHAR(128),
    instrunmentToken NVARCHAR(255),    
    instrumentStandNumber NVARCHAR(64),
    providerId NVARCHAR(64),
    linkedTo NVARCHAR(64),
    instrumentHash NVARCHAR(255),
    personID NVARCHAR(32),   
    personType NVARCHAR(64),             
    currency_code_iso NVARCHAR(3),
    status NVARCHAR(50),
    recordHash NVARCHAR(255),
    createdon DATETIME DEFAULT GETDATE(),
    updatedon DATETIME DEFAULT GETDATE(),    
    CONSTRAINT UQ_LEDFOR_ACCOUNT_InstNum_PersonID UNIQUE (instrumentNumber, personID),
    FOREIGN KEY (currency_code_iso) REFERENCES LEDFOR_ISOCURRENCIES(currency_code_iso)
);

-- Create LEDFOR_TRANSACTION table
CREATE TABLE LEDFOR_TRANSACTION (
    instrumentID NVARCHAR(64),
    transaction_id NVARCHAR(64) PRIMARY KEY,
    source_trasnaction_ref NVARCHAR(128),
    corel_trasnaction_id NVARCHAR(128),
    transactionCode NVARCHAR(64),
    type NVARCHAR(128),
    crdrIndicator NVARCHAR(3),
    merchantDetails NVARCHAR(255),    
    amount DECIMAL(18, 5),
    currency_code_iso NVARCHAR(3),
    description NVARCHAR(255),
    transaction_datetime NVARCHAR(255),
    processingCode NVARCHAR(255),
    recordHash NVARCHAR(255),
    createdon DATETIME DEFAULT GETDATE(),
    updatedon DATETIME DEFAULT GETDATE(),    
    FOREIGN KEY (instrumentID) 	REFERENCES LEDFOR_ACCOUNT(instrumentID),
    FOREIGN KEY (currency_code_iso) REFERENCES LEDFOR_ISOCURRENCIES(currency_code_iso),
    FOREIGN KEY (transactionCode) 	REFERENCES LEDFOR_TRANSACTIONTYPE(transactionCode)

);





-- Inserting data into LEDFOR_ISOCURRENCIES table with decimal_denom
INSERT INTO LEDFOR_ISOCURRENCIES (currency_code_iso, currency_name, decimal_denom, recordHash, createdon, updatedon)
VALUES
    ('USD', 'United States Dollar', 2, NEWID(), GETDATE(), GETDATE()),
    ('EUR', 'Euro', 2, NEWID(), GETDATE(), GETDATE()),
    ('GBP', 'British Pound Sterling', 2, NEWID(), GETDATE(), GETDATE()),
    ('AUD', 'Australian Dollar', 2, NEWID(), GETDATE(), GETDATE()),
    ('CAD', 'Canadian Dollar', 2, NEWID(), GETDATE(), GETDATE()),
    ('JPY', 'Japanese Yen', 0, NEWID(), GETDATE(), GETDATE()),
    ('CHF', 'Swiss Franc', 2, NEWID(), GETDATE(), GETDATE()),
    ('CNY', 'Chinese Yuan', 2, NEWID(), GETDATE(), GETDATE()),
    ('INR', 'Indian Rupee', 2, NEWID(), GETDATE(), GETDATE()),
    ('MXN', 'Mexican Peso', 2, NEWID(), GETDATE(), GETDATE()),
    ('BRL', 'Brazilian Real', 2, NEWID(), GETDATE(), GETDATE()),
    ('RUB', 'Russian Ruble', 2, NEWID(), GETDATE(), GETDATE()),
    ('ZAR', 'South African Rand', 2, NEWID(), GETDATE(), GETDATE()),
    ('KRW', 'South Korean Won', 0, NEWID(), GETDATE(), GETDATE()),
    ('TRY', 'Turkish Lira', 2, NEWID(), GETDATE(), GETDATE()),
    ('SGD', 'Singapore Dollar', 2, NEWID(), GETDATE(), GETDATE()),
    ('HKD', 'Hong Kong Dollar', 2, NEWID(), GETDATE(), GETDATE()),
    ('NOK', 'Norwegian Krone', 2, NEWID(), GETDATE(), GETDATE()),
    ('SEK', 'Swedish Krona', 2, NEWID(), GETDATE(), GETDATE()),
    ('DKK', 'Danish Krone', 2, NEWID(), GETDATE(), GETDATE()),
    ('PLN', 'Polish Zloty', 2, NEWID(), GETDATE(), GETDATE()),
    ('AED', 'United Arab Emirates Dirham', 2, NEWID(), GETDATE(), GETDATE()),
    ('ARS', 'Argentine Peso', 2, NEWID(), GETDATE(), GETDATE()),
    ('BGN', 'Bulgarian Lev', 2, NEWID(), GETDATE(), GETDATE()),
    ('COP', 'Colombian Peso', 2, NEWID(), GETDATE(), GETDATE()),
    ('ILS', 'Israeli New Shekel', 2, NEWID(), GETDATE(), GETDATE()),
    ('IDR', 'Indonesian Rupiah', 2, NEWID(), GETDATE(), GETDATE()),
    ('THB', 'Thai Baht', 2, NEWID(), GETDATE(), GETDATE()),
    ('MYR', 'Malaysian Ringgit', 2, NEWID(), GETDATE(), GETDATE()),
    ('TWD', 'New Taiwan Dollar', 2, NEWID(), GETDATE(), GETDATE()),
    ('NZD', 'New Zealand Dollar', 2, NEWID(), GETDATE(), GETDATE()),
    ('HUF', 'Hungarian Forint', 2, NEWID(), GETDATE(), GETDATE()),
    ('PHP', 'Philippine Peso', 2, NEWID(), GETDATE(), GETDATE()),
    ('KZT', 'Kazakhstani Tenge', 2, NEWID(), GETDATE(), GETDATE()),
    ('VND', 'Vietnamese Dong', 0, NEWID(), GETDATE(), GETDATE()),
    ('CLP', 'Chilean Peso', 0, NEWID(), GETDATE(), GETDATE()),
    ('PKR', 'Pakistani Rupee', 2, NEWID(), GETDATE(), GETDATE()),
    ('SAR', 'Saudi Riyal', 2, NEWID(), GETDATE(), GETDATE()),
    ('EGP', 'Egyptian Pound', 2, NEWID(), GETDATE(), GETDATE()),
    ('QAR', 'Qatari Rial', 2, NEWID(), GETDATE(), GETDATE()),
    ('LKR', 'Sri Lankan Rupee', 2, NEWID(), GETDATE(), GETDATE()),
    ('KES', 'Kenyan Shilling', 2, NEWID(), GETDATE(), GETDATE()),
    ('OMR', 'Omani Rial', 3, NEWID(), GETDATE(), GETDATE()),
    ('TTD', 'Trinidad and Tobago Dollar', 2, NEWID(), GETDATE(), GETDATE()),
    ('JOD', 'Jordanian Dinar', 3, NEWID(), GETDATE(), GETDATE()),
    ('MAD', 'Moroccan Dirham', 2, NEWID(), GETDATE(), GETDATE()),
    ('RON', 'Romanian Leu', 2, NEWID(), GETDATE(), GETDATE()),
    ('BDT', 'Bangladeshi Taka', 2, NEWID(), GETDATE(), GETDATE()),
    ('JMD', 'Jamaican Dollar', 2, NEWID(), GETDATE(), GETDATE()),
    ('GHS', 'Ghanaian Cedi', 2, NEWID(), GETDATE(), GETDATE()),
    ('HRK', 'Croatian Kuna', 2, NEWID(), GETDATE(), GETDATE()),
    ('CZK', 'Czech Koruna', 2, NEWID(), GETDATE(), GETDATE()),
    ('ISK', 'Icelandic Krona', 2, NEWID(), GETDATE(), GETDATE()),
    ('LBP', 'Lebanese Pound', 2, NEWID(), GETDATE(), GETDATE()),
    ('MUR', 'Mauritian Rupee', 2, NEWID(), GETDATE(), GETDATE()),
    ('MZN', 'Mozambican Metical', 2, NEWID(), GETDATE(), GETDATE()),
    ('PYG', 'Paraguayan Guarani', 0, NEWID(), GETDATE(), GETDATE()),
    ('KHR', 'Cambodian Riel', 2, NEWID(), GETDATE(), GETDATE()),
    ('FJD', 'Fijian Dollar', 2, NEWID(), GETDATE(), GETDATE()),
    ('SLL', 'Sierra Leonean Leone', 2, NEWID(), GETDATE(), GETDATE()),    
    ('XOF', 'CFA Franc (West African)', 0, NEWID(), GETDATE(), GETDATE()),
    ('XPF', 'CFP Franc (Pacific)', 0, NEWID(), GETDATE(), GETDATE()),
    ('BIF', 'Burundian Franc', 0, NEWID(), GETDATE(), GETDATE()),
    ('BTN', 'Bhutanese Ngultrum', 2, NEWID(), GETDATE(), GETDATE()),    
    ('WST', 'Samoan Tala', 2, NEWID(), GETDATE(), GETDATE()),
    ('KWD', 'Kuwaiti Dinar', 3, NEWID(), GETDATE(), GETDATE()),
    ('BHD', 'Bahraini Dinar', 3, NEWID(), GETDATE(), GETDATE());




