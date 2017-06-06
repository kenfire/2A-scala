

// Question 1
class Tweet(val user: String, val msg: String, val retweet: Int) {
  def creeFiltre(hashtag: String): Tweet => Boolean =
    tweet => tweet.msg.contains('#' + hashtag)

  override def toString() = {
    msg
  }
}

val tweet = new Tweet("kenzo", "#scala #play", 1)
val tweet2 = new Tweet("kenzo", "#scala la", 1)
val filtreScala = tweet.creeFiltre("scala")
val filtrePlay = tweet.creeFiltre("play")

val tweets: List[Tweet] = List(tweet, tweet2)


filtreScala(tweet)
filtreScala(tweet2)

tweets.filter(filtreScala)
tweets.filter(filtrePlay)

// Question 2
def puissance(x: Int, n: Int): Int = {
  def rec(x: Int, n: Int, acc: Int = 1): Int = {
    if (n == 1) x * acc
    else if (n % 2 != 0 && n > 2) rec(math.pow(x, 2).toInt, (n - 1) / 2, x)
    else rec(math.pow(x, 2).toInt, n / 2)
  }

  rec(x, n)
}
puissance(2, 2)
puissance(1, 2)
puissance(3, 1)
puissance(3, 9)
