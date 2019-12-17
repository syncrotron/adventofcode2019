//Created by Samuel Horovatin

import scala.io.Source

object Main {
  def main(args: Array[String]){
    println("The sum of the content of file " + args(0)+ " is " + Source.fromFile(args(0)).getLines.toList.map((mass: String)=> mass.toInt/3 - 2).sum)
  }
}