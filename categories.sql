USE budgettracker;
CREATE TABLE categories (
`id` int(10) unsigned NOT NULL AUTO_INCREMENT,
`title` varchar(255) DEFAULT NULL,
`category` varchar(255) DEFAULT NULL,
`readable` varchar(255) DEFAULT NULL,
PRIMARY KEY(`id`)
);

INSERT INTO categories (`title`, `category`, `readable`) VALUES
("salary", "income", "Salary/Wages"),
("incomerother", "income", "Other Income"),
("mortgage", "housing", "Mortgage"),
("rent", "housing", "Rent"),
("utilities", "housing", "Utilities"),
("billother", "housing", "Other"),
("carpay", "transport", "Car Payments"),
("carins", "transport", "Car Insurance"),
("gas", "transport", "Gas"),
("repairs", "transport", "Repairs"),
("studentloan", "education", "Student Loan"),
("schoolsupply", "education", "School Supplies"),
("tuition", "education", "Tuition"),
("textbooks", "education", "Textbooks"),
("groceries", "personal", "Groceries"),
("clothing", "personal", "Clothing"),
("entertainment", "personal", "Entertainment"),
("medical", "personal", "Medical Fees"),
("personalother", "personal", "Other");