//Created by Samuel Horovatin

import scala.io.Source
import scala.util.control.Breaks._
import scala.runtime.ScalaRunTime._

object main{

    def optCodeAction(codeList: Array[Int], index: Int, action: (Int, Int) => Int) : Array[Int] = {
        codeList(codeList(index+2)) = action(codeList(codeList(index)), codeList(codeList(index+1)))
        return codeList
    }


    def main(args: Array[String]): Unit ={

        //This opens the input file, splits it at every "," and puts it in a list

        val src = Source.fromFile(args(0))
        var opcodeList = src.mkString.split(",").map(_.toInt)
        
        //1202 program alarm correction
        opcodeList(1) = 12
        opcodeList(2) = 2

        //Loops through the length every 4 elements
        breakable {
            for (index <- (0 to opcodeList.length).toList.grouped(4).map(_.head)){
                opcodeList(index) match{
                    case 1  => opcodeList = optCodeAction(opcodeList, index+1, Math.addExact)
                    case 2  => opcodeList = optCodeAction(opcodeList, index+1, Math.multiplyExact)
                    case 99 => break
                    case _  => println("Unkown Opcode (" + index + "): Oh no, something must have gone wrong!")
                }
            }
        }
        println("Final Number: " + opcodeList(0))
    }

}