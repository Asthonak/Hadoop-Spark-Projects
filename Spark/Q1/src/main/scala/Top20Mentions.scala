import org.apache.spark.{SparkConf, SparkContext}
//import org.apache.spark.sql.SparkSession

object Top20Mentions {
  def main(args: Array[String]) {
//    val logFile = "/home/ethanpark/spark-3.0.0-preview2-bin-hadoop2.7/README.md" // Should be some file on your system
//    val spark = SparkSession.builder.appName("Top 20 Mentions").getOrCreate()
/*
    val logData = spark.read.textFile(logFile).cache()

    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
*/
    println("CL args passed: " + args(0) + " " + args(1))

    val conf = new SparkConf()
    val sc = new SparkContext(conf)

    val input = sc.textFile(args(0))
//    val input = sc.textFile("/home/ethanpark/training_set_tweets.txt")

    // read in each line of file
    val lines = input.flatMap(line=>line.split("\n"))

    // remove all punctuation
    val noPunctuation = lines.map(x => x.replaceAll("[,.!?:;]",""))

    // break down lines into individual words
    // use "distinct" so that mentions are only added once for each line
    val words = noPunctuation.flatMap(line=>line.split("\\s+").distinct)

    // filter words to only include mentions
    val mentions = words.filter(x => x.startsWith("@") == true)

    // count how many times each mention is referenced
    val count = mentions.map(x => (x, 1)).reduceByKey( (x,y) => x + y )

    // sort mentions in descending order 
    // top(20) makes it show only the first 20 results
    val sortedCount = count.map( x => (x._2, x._1) ).sortByKey(false,1).top(20)

    // output result to terminal and write to output file
    val writeRdd = sc.parallelize(sortedCount)
    writeRdd.coalesce(1).saveAsTextFile(args(1))
//    writeRdd.saveAsTextFile("/home/ethanpark/popular_mentions.txt")
    sortedCount.foreach(println)

//    spark.stop()
  }
}
