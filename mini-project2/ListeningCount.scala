import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object ListeningCount {
 def FILE_NAME:String = "count_results_";
 def main(args:Array[String]) {
 val savefile= "/opt/spark/spark-2.0.2-bin-hadoop2.7/user_artists.dat"
 val conf = new SparkConf().setAppName("Spark Exercise 2: artists Listing Count Program").setMaster("local");
 val sc = new SparkContext(conf);
 val textFile = sc.textFile("user_artists.dat").cache()
 val ListeningCounts = textFile.map(line => line.split("\\s+")).map(
                                        arr => (arr(1),arr(2).toInt)).reduceByKey(new org.apache.spark.HashPartitioner(2), (x,y) => x+y)

 ListeningCounts.saveAsTextFile(savefile); //save final output in the local working directory.
 println("Listing Count program running results are successfully saved.");
 sc.stop()
 }
}
