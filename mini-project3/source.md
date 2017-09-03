#open cassandra service
cassandra -Rf

#open a new terminal to open cql shell
cqlsh --request-timeout 1200 localhost

#create the key space
CREATE KEYSPACE test WITH REPLICATION = {
     ‘class’ “ ‘SImpleStrategy’,
     ‘replication’ = 3  };

#create a table in the keyspace test
CREATE TABLE accesslog(
     Id text PRIMARY KEY,
     host text,
     request text,
     path text );

#insert data into table
COPY accesslog (id,host,request,path) FROM ‘access1.csv’ WITH header = FALSE;

#show the inserted data table
expand on
select * from accesslog;

#how many hit were made to the website item ‘/assests/img/release-schedulelogo.png’
SELECT COUNT(*) from accesslog WHERE path=’ ‘/assests/img/release-schedulelogo.png’
ALLOW FILTERING

#how many hits were made from the host ’10.207.188.188’
SELECT COUNT(*) FROM accesslog WHERE host=’10.207.188.188’

#create a user defined function to aggregate function
CREATE OR REPLACE FUNCTION state_group_and_count( state map<text, int>, type text)
CALLED ON NULL INPUT
RETURNS map<text, int>
LANGUAGE java AS ‘
Integer count = (Integer) state.get(type);
If(count==null) count=1;
else
count++;
state.put(type, count);
return state; ‘    ;

CREATE OR REPLACE AGGREGATE state_group_and_max(text)
SFUNC state_group_and_count
STYPE map<text, int>
INITICOND {};

#find the maximum count
CREATE FUNCTION maxI(current int,candidate int)
CALLED  ON NULL INPUT
RETURN int LANGUAGE java AS
' if (current==null) return candidate; else return Math.max(current, candidate);';

CREATE AGGREGATE maxAgg(int)
SFUNC maxI
STYPE int
INITCOND null;

#find the path which has the most access.
SELECT state_group_and_max(path) FROM accesslog;

#find the host which has the msot access.
SELECT state_group_and_max(host) FROM accesslog;
