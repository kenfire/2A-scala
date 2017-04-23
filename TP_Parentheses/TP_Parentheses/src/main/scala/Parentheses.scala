object Parentheses {

  /**
    * Verifie l'equilibre des parenthèses
    * @param phrase
    * @return
    */
  def equilibre(phrase: List[Char]): Boolean = {
    def count(phrase: List[Char], acc: Int): Int = {
      if (phrase.isEmpty || acc < 0l) acc
      else if (phrase.head == '(') count(phrase.tail, acc + 1)
      else if (phrase.head == ')') count(phrase.tail, acc - 1)
      else count(phrase.tail, acc)
    }

    count(phrase, 0) == 0
  }

  /**
    * Verifie l'equilibre des caracteres
    * @param co caractère ouvrant
    * @param cf caractère fermant
    * @param phrase
    * @return
    */
  def equilibreGenerique(co: Char, cf: Char)(phrase: List[Char]): Boolean = {
    // Compte le desequilibre
    def count(phrase: List[Char], acc: Int): Int = {
      if (phrase.isEmpty || acc < 0) acc
      else if (phrase.head == co) count(phrase.tail, acc + 1)
      else if (phrase.head == cf) count(phrase.tail, acc - 1)
      else count(phrase.tail, acc)
    }

    count(phrase, 0) == 0
  }


  def equilibreXml: List[Char] => Boolean = equilibreGenerique('<', '>')

}
