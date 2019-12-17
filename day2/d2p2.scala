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
        
        //Saves template to reset from after loop
        var opcodeTemplateList = src.mkString.split(",").map(_.toInt) 

        var opcodeList = opcodeTemplateList.clone

        //1202 program alarm correction
        val searchOutput = 19690720
        var result = -1

        //loop to find noun
        for (noun <- 0 to 99){
            
            //loop to find verb
            for(verb <- 0 to 99){

                opcodeList(1) = noun
                opcodeList(2) = verb

                breakable {
                    for (index <- (0 to opcodeList.length).toList.grouped(4).map(_.head)){

                        opcodeList(index) match{
                            case 1  => opcodeList = optCodeAction(opcodeList, index+1, Math.addExact)
                            case 2  => opcodeList = optCodeAction(opcodeList, index+1, Math.multiplyExact)
                            case 99 => break
                            case _  => break
                        }
                    }  
                }
                
                if (opcodeList(0) == searchOutput){
                    println("We found it! Noun: " + noun + " Verb: " + verb)
                    result = 100*noun+verb
                    println("Final Number: " + result)
                    return
                }
                opcodeList = opcodeTemplateList.clone
            }
            opcodeList = opcodeTemplateList.clone
        }  
    }
}