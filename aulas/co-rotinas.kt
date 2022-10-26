//A co-routina aguarda ser descartada, volta ao estado do ultimo ponto que foi chamada


fun c(n:Int)= sequence{
  yield(n+1)
  yield(n+3)
}

fun main(){
  println(c(2).toList())

  println(c(3).toList())
}
