//////////////////////////////////////////////////////////////////////////////////////////
NOTE: Q5-Q7 assume the training set text files were used and the generated text output files reflect this.
//////////////////////////////////////////////////////////////////////////////////////////

To compile the files in the Q5 directory enter then following:

hadoop com.sun.tools.javac.Main HastagCount.java
jar cf HC.jar HastagCount*.class
hadoop com.sun.tools.javac.Main TopTen.java
jar cf T10.jar TopTen*.class

To run Q5 follow these instructions:

First run HastagCount as this will find how many times each hashtag is used:

hadoop jar HC.jar HastagCount <INPUT> <OUTPUT>

Then run TopTen as this will take the output from HastagCount and rank the top 10 hashtags:

hadoop jar T10.jar TopTen <INPUT (output of HastagCount)> <OUTPUT>

The output of TopTen will be the top 10 most repeated hashtag along with the number of times that they were used.

//////////////////////////////////////////////////////////////////////////////////////////

To compile the files in the Q6 directory enter then following:

hadoop com.sun.tools.javac.Main DatesCount.java
jar cf DC.jar DatesCount*.class
hadoop com.sun.tools.javac.Main TopTen.java
jar cf T10.jar TopTen*.class

To run Q6 follow these instructions:

First run DatesCount as this will find how many tweets each date generated:

hadoop jar DC.jar DatesCount <INPUT> <OUTPUT>

Then run TopTen as this will take the output from DatesCount and rank the top 10 dates:

hadoop jar T10.jar TopTen <INPUT (output of DatesCount)> <OUTPUT>

The output of TopTen will be the top 10 with the most amount of tweets along with the number of times that they were used.

//////////////////////////////////////////////////////////////////////////////////////////

To compile the files in the Q7 directory enter then following:

hadoop com.sun.tools.javac.Main CityCount.java
jar cf CC.jar CityCount*.class
hadoop com.sun.tools.javac.Main TopTen.java
jar cf T10.jar TopTen*.class

To run Q7 follow these instructions:

First run CityCount as this will find how many tweets each city generated:

hadoop jar CC.jar CityCount <INPUT> <OUTPUT>

Then run TopTen as this will take the output from CityCount and rank the top 10 cities:

hadoop jar T10.jar TopTen <INPUT (output of CityCount)> <OUTPUT>

The output of TopTen will be the top 10 with the most amount of tweets along with the number of times that they were used.

Assumption:
Entries that have the city and entries that have the city and the state are treated as different (ex: Birmingham is not the same as Birmingham, AL). This is because Birmingham by itself is ambiguous, it could be Birmingham (AL), Birmingham (CT), Birmingham (NY), etc -- all of which are real places. In short, entries that have the same city but only one lists a state could actually be refering to different places, hence why I decided to differentiate between the two.

//////////////////////////////////////////////////////////////////////////////////////////
