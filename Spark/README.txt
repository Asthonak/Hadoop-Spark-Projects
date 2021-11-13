//////////////////////////////////////////////////////////////////////////////////////////

To compile the files in the Q1 directory enter then following while in the Q1 directory:

sbt package

NOTE: ensure you modify build.sbt to your desired spark and scala versions

To run Q1 enter the following while in the Q1 directory:

spark-submit --class "Top20Mentions" --master local[4] <TARGET_JAR> <INPUT> <OUTPUT>

Examples:

spark-submit --class "Top20Mentions" --master local[4] target/scala-2.12/top-20-mentions_2.12-1.0.jar /home/ethanpark/training_set_tweets.txt /home/ethanpark/popular_mentions.txt

spark-submit --class "Top20Mentions" --master local[4] target/scala-2.12/top-20-mentions_2.12-1.0.jar hdfs://localhost:9000/homework1/training_set_tweets.txt hdfs://localhost:9000/HW2_1_test1

//////////////////////////////////////////////////////////////////////////////////////////

To compile the files in the Q2 directory enter then following while in the Q2 directory:

sbt package

NOTE: ensure you modify build.sbt to your desired spark and scala versions

To run Q2 enter the following while in the Q2 directory:

spark-submit --class "Top10Retweets" --master local[4] <TARGET_JAR> <INPUT> <OUTPUT>

Examples:

spark-submit --class "Top10Retweets" --master local[4] target/scala-2.12/top-10-retweets_2.12-1.0.jar /home/ethanpark/training_set_tweets.txt /home/ethanpark/most_retweeted_users.txt

spark-submit --class "Top10Retweets" --master local[4] target/scala-2.12/top-10-retweets_2.12-1.0.jar hdfs://localhost:9000/homework1/training_set_tweets.txt hdfs://localhost:9000/HW2_2_test1

Assumption: It was assumed that constraints from problem 1 DO NOT apply to problem 2

//////////////////////////////////////////////////////////////////////////////////////////

To compile the files in the Q3 directory enter then following while in the Q3 directory:

sbt package

NOTE: ensure you modify build.sbt to your desired spark and scala versions

To run Q3 enter the following while in the Q3 directory:

spark-submit --class "Top10Users" --master local[4] <TARGET_JAR> <TWEETS_INPUT> <USERS_INPUT> <OUTPUT>

Examples:

spark-submit --class "Top10Users" --master local[4] target/scala-2.12/top-10-users_2.12-1.0.jar /home/ethanpark/training_set_tweets.txt /home/ethanpark/training_set_users.txt /home/ethanpark/most_tweeted_users.txt

spark-submit --class "Top10Users" --master local[4] target/scala-2.12/top-10-users_2.12-1.0.jar hdfs://localhost:9000/homework1/training_set_tweets.txt hdfs://localhost:9000/homework1/training_set_users.txt hdfs://localhost:9000/HW2_3_test1

//////////////////////////////////////////////////////////////////////////////////////////
