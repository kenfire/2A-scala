import EnsemblesFonctions._

object testEnsemblesFonctions {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(100); 

  val ensemblePair = (x:Int) => x%2==0;System.out.println("""ensemblePair  : Int => Boolean = """ + $show(ensemblePair ));$skip(35); 
  
  afficheEnsemble(ensemblePair)}
  
}
