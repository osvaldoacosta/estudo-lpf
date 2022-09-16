//1.a Implemente o tipo de dado lista ligadas para um tipo de dado genérico. Defina manualmente métodos equals e toString

class Node<T> (val elemento: T, val proximo:Node<T>?){
    override fun toString():String {
        return "${elemento} -> ${proximo}"
    }

    fun equals(no:Node<T>):Boolean{
        if(no.elemento != elemento) return false;
        else if(proximo != null && no.proximo != null) return proximo.equals(no.proximo)
        return true;
    }
}

class LinkedList<T>(val head:Node<T>?){
    override fun toString(): String = head.toString();
    
    fun equals(lista:LinkedList<T>?):Boolean{
        if (lista != null){
          if(lista.head == null){
              if(head == null) return true;
              else return false;
          }
          else if(head == null) return false;
          else if(head.equals(lista.head)) return true;
        }
        return false;
    }

}


//1.b Implemente uma função para calcular o número de elementos de uma lista
fun <T>calculaElemento(lista:LinkedList<T>?):Int{
    if(lista?.head == null) return 0;
    else if(lista.head.proximo == null) return 1;
    else return 1 + calculaElemento(LinkedList<T>(Node(lista.head.proximo.elemento, lista.head.proximo.proximo)));
}

//1.c Implemente uma função para retornar o ultimo elemento de uma lista (se ele existir)
fun <T>retornaUltimoElemento(lista:LinkedList<T>?): T?{
    if(lista?.head == null) return null;
    else if(lista.head.proximo == null) return lista.head.elemento;
    else return retornaUltimoElemento(LinkedList(Node(lista.head.proximo.elemento, lista.head.proximo.proximo)));
}


//1.d Implemente uma função para concatenar duas listas ligadas.
fun <T> concatenaLista(l1:LinkedList<T>?, l2:LinkedList<T>?) : LinkedList<T>?{
    if(l1?.head == null) return l2;
    else if(l2?.head == null) return l1;
    else if(l1.head.proximo == null) return LinkedList(Node(l1.head.elemento, l2.head));
    return LinkedList<T>( Node( l1.head.elemento,concatenaLista<T>( LinkedList<T>( Node<T>( l1.head.proximo.elemento, l1.head.proximo.proximo )), l2)?.head));
} 

//1.e Implemente uma função para remover o n-ésimo valor de uma lista
fun <T> removeEnesimo(lista:LinkedList<T>?, n:Int) : LinkedList<T>?{
    if(lista?.head == null || lista.head.proximo == null) return null;
    else if(n == 1){
        return LinkedList<T>(Node(lista.head.proximo.elemento,lista.head.proximo.proximo));
    }

    return LinkedList<T>(Node(lista.head.elemento, removeEnesimo(LinkedList(Node(lista.head.proximo.elemento, lista.head.proximo.proximo)),n-1)?.head));
  }
fun main (){
    println("Lista encadeada")


    val lista1: LinkedList<Int> = LinkedList<Int>(Node(1,Node(2,Node(3,Node<Int>(6,null)))));
    val lista2: LinkedList<Int> = LinkedList<Int>(Node(1,Node(3,Node<Int>(6,null))));
    val lista3: LinkedList<Int> = LinkedList<Int>(Node(1,Node(2,Node(3,Node<Int>(6,null)))));

  

    println("Lista 1: ${lista1}");
    println("Lista 2: ${lista2}");
    println("Lista 3: ${lista3}");

  
    println("lista 1 = lista 2?: ${lista1.equals(lista2)}"); 
    println("lista 1 = lista 3?: ${lista1.equals(lista3)}");

    println("elementos da lista1: ${calculaElemento<Int>(lista1)}");
    println("ultimo elemento da lista 1: ${retornaUltimoElemento(lista1)}");
    val lista12 = concatenaLista(lista1,lista2);
    println("lista1 + lista2 : ${lista12}");
    
    println("Removendo o 2o valor dessa lista concatenada: ${removeEnesimo(lista12, 2)}")
}
