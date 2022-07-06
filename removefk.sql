USE budgettracker;

ALTER TABLE budgetitem
DROP CONSTRAINT budgetitem_ibfk_1,
DROP list_id;