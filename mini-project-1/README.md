# Hadoop Map Reduce program execution

This project implements the installation of hadoop and cluster setup, and n-gram statistics and the statistics analysis of access log file.
this file will show you how to develope and execute hadoop program in hadoop psedu-distributed environment.



##start hadoop 

[1]open terminial shell and type the commond
'# start-all.sh'
'#jps'
make sure that datanode and namenode are abaliable

[2]save java file under the /usr/local/hadoop

### Compile and Create Jar

`cd` to the root project directory: /usr/local/hadoop
[3]Simple bash script to compile java classes and create a jar (tgc.jar)

'# /usr/local/hadoop/bin/hadoop com.sun.tools.javac.Main WordCount.java'
'# jar cf wc.jar WordCount*.class'


[4]modify the compile environment and add JAVA_HOME and HADOOP_CLASSPATH
enter .bashrc 
'# nano ~/.bashrc'
add flowing statements
export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar



## Usage

Assuming Hadoop is installed and configured properly:
Assuming that the input and output file have been created
/user/local/hadoop/wordcount/input - input directory in HDFS
/user/local/hadoop/wordcount/output - output directory in HDFS


### Add local text file to HDFS as input

/path/to/bin/hdfs dfs -put /user/local/hadoop/wordcount/input /user/local/hadoop/wordcount/output

### Run the Job
enter hadoop flle
'# cd /usr/local/haoop'
'# 'bin/hadoop jar /usr/local/hadoop／wc.jar WordCount /user/local/hadoop/wordcount/input /user/local/hadoop/wordcount/output


### Read output from HDFS

`/path/to/bin/hdfs dfs -cat /user/local/hadoop/wordcount/output/part-r-00000`
and it will show the statistics result.


