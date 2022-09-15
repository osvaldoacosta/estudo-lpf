//Implemente o tipo de dado lista ligadas para um tipo de dado genérico. Defina manualmente métodos equals e toString

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
//Em teoria não precisava, mas nhe ._.
class LinkedList<T>(val head:Node<T>){
    override fun toString(): String = head.toString();
    
    fun equals(lista:LinkedList<T>):Boolean{
        if(head.equals(lista.head)) return true;
        else return false;
    }

}


fun main (){
    println("Lista encadeada")


    val lista1: LinkedList<Int> = LinkedList<Int>(Node(1,Node(2,Node(3,Node<Int>(6,null)))));
    val lista2: LinkedList<Int> = LinkedList<Int>(Node(1,Node(3,Node<Int>(6,null))));
    val lista3: LinkedList<Int> = LinkedList<Int>(Node(1,Node(2,Node(3,Node<Int>(6,null)))));

  
  
  //val l1add = lista1.adicionar(4,null);
  //val ladd= lista.adicionar(1,null);
  

    println(lista1.toString());
 
 // println(l1add.toString());
  //println(ladd.toString());

  
    println(lista1.equals(lista2));

    println(lista1.equals(lista3));
}
