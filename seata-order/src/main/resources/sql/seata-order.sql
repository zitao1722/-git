CREATE TABLE `order_tbl`
(
    `id`           int NOT NULL,
    `product_id`   int NOT NULL,
    `total_amount` int NOT NULL,
    `status`       int DEFAULT '100'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
