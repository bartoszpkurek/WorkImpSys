-- USE THIS QUERY TO SELECT FROM SYS.SYSCONSTRAINTS TABLE ALL CONSTRAINTS AND CREATE QUERY TO DROP THEM
-- COPY THE QUERY FROM RESULT INTO COMMAND BOX AND RUN

SELECT
'ALTER TABLE '||S.SCHEMANAME||'.'||T.TABLENAME||' DROP CONSTRAINT '||C.CONSTRAINTNAME||';'
FROM
    SYS.SYSCONSTRAINTS C,
    SYS.SYSSCHEMAS S,
    SYS.SYSTABLES T
WHERE
    C.SCHEMAID = S.SCHEMAID
AND
    C.TABLEID = T.TABLEID
AND
S.SCHEMANAME = 'WIS'

SELECT 'DELETE FROM ' || schemaname ||'.' || tablename || ';'
FROM SYS.SYSTABLES
INNER JOIN SYS.SYSSCHEMAS ON SYS.SYSTABLES.SCHEMAID = SYS.SYSSCHEMAS.SCHEMAID
where schemaname='WIS';