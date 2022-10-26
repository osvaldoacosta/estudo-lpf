
fun avalEager(){
  val x = 1+2;
  val y = 3*2;
  println("x:${x} y:${y}")

  val soma = run {x+y}
  println("${soma}")

  val l = listOf(1,2,3,4,5)
}
fun avalLazy(){
    val x:Int by lazy {1+2}
    val y:Int by lazy {3*2}
    println("x:${x} y:${y}")
    val soma by lazy{x+y};
    println("${soma}")

    
    val s = sequenceOf(1,2,3,4,5)
    val sm = s.map{ x-> println("${x}");x*x}
    val a = sm.take(2).toList();    
    println(a)
}



fun main (){
  avalLazy();
  avalEager();
}
