import org.apache.spark.{SparkConf, SparkContext}
//import org.apache.spark.sql.SparkSession

object Top10Users {
  def main(args: Array[String]) {
//    val logFile = "/home/ethanpark/spark-3.0.0-preview2-bin-hadoop2.7/README.md" // Should be some file on your system
//    val spark = SparkSession.builder.appName("Top 10 Users").getOrCreate()
/*
    val logData = spark.read.textFile(logFile).cache()

    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
*/
    println("CL args passed: " + args(0) + " " + args(1) + " " + args(2))

    val conf = new SparkConf()
    val sc = new SparkContext(conf)

    /***Process Tweets Data***/

    val tweetData = sc.textFile(args(0))
//    val input = sc.textFile("/home/ethanpark/training_set_tweets.txt")

    // read in each line of file
    val tweetDataLines = tweetData.flatMap(line=>line.split("\n"))

    // filter list to remove any entries with incorrect dates
    val correctDate = tweetDataLines.filter(x => x.contains("\t2009-09-16") == true ||
                                                x.contains("\t2009-09-17") == true ||
                                                x.contains("\t2009-09-18") == true ||
                                                x.contains("\t2009-09-19") == true ||
                                                x.contains("\t2009-09-20") == true )

    // remove all but user id from each entry
    val tweetUsers = correctDate.map(line=>line.split("\t").head)

    /***Process Users Data***/

    val userData = sc.textFile(args(1))

    // read in each line of file
    val userDataLines = userData.flatMap(line=>line.split("\n"))

    // filter list to remove any entries with incorrect city
    val correctCity = userDataLines.filter(x => x.contains("\tLos Angeles") == true)

    // remove all but user id from each entry
    val userUsers = correctCity.map(line=>line.split("\t").head)

    /*** Find Users that Tweeted the Most ***/

    // count how many times each user is referenced for each list
    val tweetUsersCount = tweetUsers.map(x => (x, 1)).reduceByKey( (x,y) => x + y )
    val userUsersCount = userUsers.map(x => (x, 1)).reduceByKey( (x,y) => x + y )

//    tweetUsersCount.coalesce(1).saveAsTextFile("/home/ethanpark/tweetUsersCount.txt")
//    userUsersCount.coalesce(1).saveAsTextFile("/home/ethanpark/userUsersCount.txt")

    // create a list of the counts for all valid users
    // these would be all users in tweetUsersCount (users with the correct date)
    // minus the users with the incorrect city (i.e. user is not in userUsersCount)
    val allValidUsers = (tweetUsersCount.cogroup(userUsersCount)
        .filter{case (k, (vals1, vals2)) => vals1.nonEmpty && vals2.nonEmpty }
        .map{case (k, (vals1, vals2)) => (k, vals1.toArray)}
        .flatMapValues(identity[Array[Int]]))
    allValidUsers.collect()

//    println("tweetUsers:" + tweetUsers.count())
//    println("userUsers:" + userUsers.count())
//    println("allValidUsers:" + allValidUsers.count())

    // sort mentions in descending order 
    // top(10) makes it show only the first 10 results
    val sortedCount = allValidUsers.map( x => (x._2, x._1) ).sortByKey(false,1).top(10)

    // output result to terminal and write to output file
    val writeRdd = sc.parallelize(sortedCount)
    writeRdd.coalesce(1).saveAsTextFile(args(2))
//    writeRdd.saveAsTextFile("/home/ethanpark/popular_mentions.txt")
    sortedCount.foreach(println)

//    spark.stop()
  }
}
