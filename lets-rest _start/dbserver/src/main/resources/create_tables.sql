/**
 * Author:  t2g8
 * Created: 03.04.2022
 */

CREATE TABLE IF NOT EXISTS scores (
  score_id          INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
  score_short       VARCHAR(20)  UNIQUE,
  score_name        VARCHAR(100) NOT NULL,
  score_description VARCHAR(500) ,
  score_table       VARCHAR(100),
  score_field       VARCHAR(100),
  score_stereotype  VARCHAR(50),
  score_factor      DECIMAL(4,2) NOT NULL DEFAULT 1, 
  score_createdAt   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
)
;

CREATE TABLE IF NOT EXISTS samples (
  sample_id         INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
  sample_score_id   LONG NOT NULL REFERENCES scores(score_id) ON DELETE CASCADE,
  sample_rows       LONG NOT NULL,
  sample_rowsfailed LONG NOT NULL,
  sample_value      DECIMAL(5,2) NOT NULL,
  sample_createdAt  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
)
;