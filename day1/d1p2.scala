import scala.io.Source

object Main {

  def fuelReccurssion(fuel: Int): Int =
    if(fuel < 12) return fuel else fuelReccurssion((fuel/3).floor.toInt - 2) + fuel


  def main(args: Array[String]): Unit =
    println("The sum of the content of file " + args(0)+ " is " + Source.fromFile(args(0)).getLines.toList.map((mass: String) => fuelReccurssion(mass.toInt)-mass.toInt).sum)
}