
fun avalEager(){
  val x = 1+2;
  val y = 3*2;
  println("x:${x} y:${y}")

  val soma = run {x+y}
  println("${soma}")
}
fun avalLazy(){
    val x:Int by lazy {1+2}
    val y:Int by lazy {3*2}
    println("x:${x} y:${y}")
    val soma by lazy{x+y};
    println("${soma}")
}
fun main (){
  avalLazy();
  avalEager();
}
