import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "/opt/spark/spark-2.0.2-bin-hadoop2.7/access_log.dat"
    val output="/opt/spark/final"
    val ontput2="/opt/spark/final2"
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile).cache()
    val result1 = logData.map(word=>word.split("\\s+")).filter(line=>line.contains("/assets/js/lowpro.js")).count()
    val result2 = logData.map(word=>word.split("\\s+")).filter(line=>line.contains("/favicon.ico")).count()

    val result3 = logData.map(word=>word.split("\\s+")).map(arr=>(arr(6),1)).reduceByKey(_+_).sortBy(_._2, false).take(1)
    val result4 = logDataresult3 = logData.map(word=>word.split("\\s+")).map(arr=>(arr(0),1)).reduceByKey(_+_).sortBy(_._2, false).take(1)
    println("there are %s hits in /assests/js/lorpro.js and there are %s in /favicon.ico".format(result1, result2))
    result3.toArray().foreach(println)
    result4.toArray().foreach(println)
    sc.stop()
  }
}
