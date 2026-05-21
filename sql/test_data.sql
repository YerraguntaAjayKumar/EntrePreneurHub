-- Initial Data for EntrepreneurHub
-- Execute this AFTER running schema.sql to pre-populate test data

-- Insert Admin User (No approval needed)
INSERT INTO users(email, password, fname, lname, role, status) 
VALUES('admin@hub.com', 'admin123', 'Admin', 'Hub', 'ADMIN', 'APPROVED');

-- Insert Test Entrepreneurs (Requires approval)
INSERT INTO users(email, password, fname, lname, role, status) 
VALUES('john@test.com', 'pass123', 'John', 'Smith', 'ENTREPRENEUR', 'PENDING');
INSERT INTO users(email, password, fname, lname, role, status) 
VALUES('sarah@test.com', 'pass123', 'Sarah', 'Johnson', 'ENTREPRENEUR', 'PENDING');

-- Insert Test Investors (Requires approval)
INSERT INTO users(email, password, fname, lname, role, status) 
VALUES('jane@test.com', 'pass123', 'Jane', 'Doe', 'INVESTOR', 'PENDING');
INSERT INTO users(email, password, fname, lname, role, status) 
VALUES('mark@test.com', 'pass123', 'Mark', 'Wilson', 'INVESTOR', 'PENDING');

-- Commit the inserts
COMMIT;

-- Verify data
SELECT user_id, email, role, status FROM users;