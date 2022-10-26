//Criar uma lista vazia de inteiros
fun criarListaVazia():List<Int?>{
     return List<Int?>(0){_-> null}
}

//calcular o tamanho de uma lista
fun <T> size(lista:List<T>):Int
  {
  return lista.fold(0){n, e-> n+1}
  }
//Somar os valores de uma lista
 
fun somarVal(l1:List<Int>):Int{
    return l1.reduce{n, e-> e+n}
}

//Concatenar duas listas
fun <T> concat(l1:List<T>, l2:List<T>):List<T>{
    return l1+l2
}

//Remover os n primeiros elementos de uma lista
fun <T> removerNPrimeiros(l:List<T>, n:Int):List<T>{
    return List<T>(size(l)-n){idx -> l.get(n+idx)}
  }
//Remover os elementos das posições pares
fun <T> removerPares(l:List<T>):List<T?>{
    // return l.filter({x:T-> l.indexOf(x) % 2 !=0}) 
    return List((size(l)/2)) {index -> l.get(index*2+1)}
    }

//modificar o n elemento de uma lista
fun modificarEnesimo(l:List<Int>, n:Int, f:(Int)->Int):List<Int>{
    return List(size(l)){index -> if(index == n) f(l.get(index)) else l.get(index)}
}
//Calcular o maior valor de uma lista de inteiros

fun maiorValor(l:List<Int>):Int{
    // return l.reduce{n, e-> if(n>e) n else e}
    return l.fold(0){n, e-> if(n>e) n else e}
}

//Criar uma lista a partir de um elemento e o resto da lista é semelhante a listas encadedas
fun criarListaAPartir(elem:Int, tamanho:Int):List<Int>{
    return List(tamanho){i -> if(i == 0) elem else elem+i}
  }
fun main() {
  val l1:List<Int> = listOf(2,3,4,1,5,6);
  val l2:List<Int> = listOf(1,2,3)
  val l3:List<Int> = criarListaAPartir(5,10)
  val vazia = criarListaVazia();
  println(l3)


}
