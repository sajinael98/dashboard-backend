CREATE Table IF NOT EXISTS `sysusers` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email TEXT,
    password TEXT,
    username TEXT
);

CREATE Table IF NOT EXISTS `roles` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `enabled` TINYINT(1),
    role VARCHAR(25)
);

CREATE Table IF NOT EXISTS `role_assignments`(
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES `sysusers`(id),
    FOREIGN KEY (role_id) REFERENCES `roles`(id)
);
CREATE Table IF NOT EXISTS `permissions`(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    entity VARCHAR(25),
    `create` TINYINT(1),
    `read` TINYINT(1),
    `edit` TINYINT(1),
    `delete` TINYINT(1),
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES `roles`(id)
);

CREATE Table IF NOT EXISTS `tokens`(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    token TEXT,
    expired TINYINT(1),
    revoked TINYINT(1),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES `sysusers`(id)
);