CREATE TABLE Registration(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    dateRegistration datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    courseID varchar(50) NOT NULL,
    userID bigint(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UC_UserCourse UNIQUE (userID, courseID),
    FOREIGN KEY (userID) REFERENCES User(id),
    FOREIGN KEY (courseID) REFERENCES Course(code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;