--Part 1
SELECT *
FROM job;

SELECT
TABLE_CATALOG,
TABLE_SCHEMA,
TABLE_NAME,
COLUMN_NAME,
DATA_TYPE
FROM INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME = 'job';
--Part 2

SELECT name
FROM employer
WHERE location = "St. Louis City";

--Part 3

DROP TABLE job;

--Part 4

SELECT * FROM skill
INNER JOIN job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_id IS NOT NULL
ORDER BY name ASC;
