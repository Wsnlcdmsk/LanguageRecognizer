CREATE TABLE language (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(30) NOT NULL
);

CREATE TABLE text (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      content VARCHAR(500) NOT NULL,
                      language_id BIGINT NOT NULL,
                      FOREIGN KEY (language_id) REFERENCES language(id)
);

CREATE TABLE tag (
                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                     name VARCHAR(30) NOT NULL
);

CREATE TABLE texts_tags (
                            text_id BIGINT NOT NULL,
                            tag_id BIGINT NOT NULL,
                            PRIMARY KEY (text_id, tag_id),
                            FOREIGN KEY (text_id) REFERENCES text(id),
                            FOREIGN KEY (tag_id) REFERENCES tag(id)
);