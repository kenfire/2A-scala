object EnsemblesFonctions {

  // On représente des ensembles à l'aide de fonctions Int => Boolean
  type Ensemble = Int => Boolean

  // indique si un ensemble contient un élément
  def contient(s: Ensemble, elem: Int): Boolean = s(elem)

  // Renvoie un ensemble sous forme de chaîne de caractères
  val limite = 100

  def chaine(s: Ensemble): String = {
    val xs = for (i <- -limite to limite if contient(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  // affiche un ensemble
  def afficheEnsemble(s: Ensemble) {
    println(chaine(s))
  }


  // méthodes à définir ci-dessous


  // définir une méthode qui renvoie un singleton
  def singleton(elem: Int): Ensemble = {
    def truc(e: Int): Boolean = e == elem

    truc
  }

  def singleton2(elem: Int): Ensemble = e => elem == e

  // définir une méthode qui renvoie l'union de deux ensembles
  def union(s: Ensemble, t: Ensemble): Ensemble = e => s(e) || t(e)

  // définir une méthode renvoyant l'intersection de deux ensembles
  def intersection(s: Ensemble, t: Ensemble): Ensemble = e => s(e) && t(e)

  // définir une méthode diff qui renvoie la différence de deux ensembles (dans s, pas dans t)
  def difference(s: Ensemble, t: Ensemble): Ensemble = e => s(e) && !t(e)

  //définir une méthode filtre, qui renvoie le sous ensemble pour lequel p est vraie
  def filtrer(s: Ensemble, p: Int => Boolean): Ensemble = e => s(e) && p(e)

  // définir une méthode pourTout, qui vérifie si p est vrai pour tout élément de s
  //  def pourTout(s: Ensemble, p: Int => Boolean): Boolean = {
  //    val seq = for (i <- -limite to limite if contient(s, i)) yield i
  //    val vect = for (i <- seq if p(i)) yield i
  //    vect.nonEmpty
  //  }

  def pourTout(s: Ensemble, p: Int => Boolean): Boolean = (-limite to limite).filter(s).forall(p)


  // définir une méthode ilExistequi renvoie vrai si un élément renvoie vraie pour p
  //  def ilExiste(s: Ensemble, p: Int => Boolean): Boolean = {
  //    val seq = for (i <- -limite to limite if contient(s, i)) yield i
  //    val vect = for (i <- seq if p(i)) yield i
  //    vect.nonEmpty
  //  }
  def ilExiste(s: Ensemble, p: Int => Boolean): Boolean = (-limite to limite).filter(s).exists(p)


  // définir une fonction image qui renvoie l'ensemble image de s
  def image(s: Ensemble, f: Int => Int): Ensemble = y => ilExiste(s, x => f(x) == y)
}
