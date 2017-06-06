object Logique {

  // une interprétation associe une valeur de vérité à une proposition
  // Par exemple, on pourrai avoir l'interprétation ssuivante :
  // Map ( "A" -> true , "B" -> true, "C" -> false, "D" -> true)
  type Interpretation = Map[String, Boolean]

  // une base de connaissance est une liste d'expressions
  type BaseConnaissance = List[Expression]

  // un théorème est une expression
  type Theoreme = Expression

  // une expression logique se compose de constantes, de propositions,
  // et des opérateurs et, ou, non, ainsi que l'implication et l'équivalence
  sealed trait Expression

  case class Constante(valeur: Boolean) extends Expression

  case class Proposition(nom: String) extends Expression

  case class Non(Exp: Expression) extends Expression

  case class Ou(ExpGauche: Expression, ExpDroite: Expression) extends Expression

  case class Et(ExpGauche: Expression, ExpDroite: Expression) extends Expression

  case class Implique(ExpGauche: Expression, ExpDroite: Expression) extends Expression

  case class Equivalent(ExpGauche: Expression, ExpDroite: Expression) extends Expression

  // Question 1 :
  // la fonction d'évalution doit permettre calculer la valeur de vérite
  // d'une expression, étant donnée une interprétation
  def evaluation(expr: Expression, interp: Interpretation): Boolean = expr match {
    case Constante(valeur) => valeur
    case Proposition(nom) => interp(nom)
    case Non(expression) => !evaluation(expression, interp)
    case Ou(gauche, droite) => evaluation(gauche, interp) || evaluation(droite, interp)
    case Et(gauche, droite) => evaluation(gauche, interp) && evaluation(droite, interp)
    case Implique(gauche, droite) => evaluation(Ou(Non(gauche), droite), interp)
    case Equivalent(gauche, droite) => evaluation(Et(Implique(gauche,droite),Implique(droite,gauche)), interp)
  }

  // Question 2 :
  // doit renvoyer l'ensemble des noms des propositions comprises dans
  // une liste d'expression
  def ensembleProposition(l: List[Expression]): Set[String] = {
    def rec(l: List[Expression], acc: Set[String]): Set[String] = {
      if (l.isEmpty) acc
      else l.head match {
        case Proposition(nom) => rec(l.tail,acc + nom)
        case _ => rec(l.tail,acc)
      }
    }
    rec(l, Set())
  }


  //Question 3 :
  // renvoyer la liste de toutes les interprétations possibles étant donnée
  // l'ensemble des propositions possibles
  def listeInterpretation(e: Set[String]): List[Interpretation] = ???


  // question 4 :
  // définir si une expression est une tautologie, et si elle est consistante

  def tautologie(expr: Expression): Boolean = ???

  def consistante(expr: Expression): Boolean = ???


  // Question 5 :
  // définissez la fonction permettant d'afficher une expression
  def affichage(expr: Expression): String = ???

  // Le tableau de vérité
  class Tableau(val BC: BaseConnaissance, val Th: Theoreme) {

    override def toString = {
      "Base de connaissances :\n" +
        BC.zipWithIndex.map(p => s"(${p._2 + 1}) " + affichage(p._1)).mkString("\n") +
        "\nThéorème :\n" + affichage(Th)
    }

    // un ligne du tableau de vérité
    class ligne(val valeurInter: List[Boolean], val valeurBC: List[Boolean], val valeurTh: Boolean) {

    }

    // Question 6 :
    // définir les propositions ainsi que toutes les lignes du tableau de vérité
    val propositions: Set[String] = ???

    val lignes: List[ligne] = ???

    //Question 7 :
    // Définir la fonction qui renvoie vrai si la base de connaissance prouve le théorème
    def preuve(): Boolean = ???

    //Question 8 :
    // définir une méthode qui affiche tout ou parti du tableau de vérité
    def toStringSelectif(f: ligne => Boolean) = ???

  }


  // Question 9 : BONUS
  // à partir d'une chaine de caractère, renvoyé, si possible, l'expression correspondante :
  def parseExpression(expr: String): Option[Expression] = ???

}







