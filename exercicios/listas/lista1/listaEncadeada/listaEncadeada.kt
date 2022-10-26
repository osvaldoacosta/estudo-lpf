package listaEncadeada
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

//1.f Implemente uma funções para quebrar uma lista em duas a partir de uma determinada posição
fun <T> quebraLista(lista: LinkedList<T>?, n:Int): Pair<LinkedList<T>?, LinkedList<T>?>{
    
    if(lista?.head == null) return Pair(null,null);
    
    else if(lista.head.proximo == null) return Pair(lista, null); //Nem chegou no n, e não possui mais indices para percorrer, então só retornamos a lista pra l1 e null pra l2
    else if(n == 1) { //Caso base
        val l1 = LinkedList<T>(Node(lista.head.elemento, null)); //1a lista só contendo um elemento apontando pra null
        val l2 = LinkedList<T>(Node(lista.head.proximo.elemento, lista.head.proximo.proximo)) //A 2a lista contendo o resto da lista original
        return Pair(l1,l2)
    }

    val parQuebrado = quebraLista(LinkedList<T>(Node(lista.head.proximo.elemento,lista.head.proximo.proximo)), n-1); //Recursão da lista, devolvendo 2 listas(l1 e l2)
    val l1 = LinkedList(Node(lista.head.elemento,parQuebrado.first?.head)); //Precisamos criar uma lista para l1, pois como no caso base l1 está escrito como 'head.elemento', nós meio q pulamos o primeiro node.
    val l2 = parQuebrado.second //Como a lista 2 já é devolvida, é de boa só pegar o retorno;
    return Pair(l1,l2)
    
}

//1.g Implemente uma função dividir uma lista em duas a partir de uma função de seleção.
//Tipo retirar os numeros pares de uma lista e jogar em outra

//Ex: 1,2,4 -> (1->null,2->4->null)

//TODO: refatorar essa funcao
fun <T> quebraListaFuncao(lista:LinkedList<T>?, funcao:(T) -> Boolean):Pair<LinkedList<T>?,LinkedList<T>?>{
    if(lista?.head == null) return Pair(null,null);
    else if(funcao(lista.head.elemento)){ //Caso base
        if(lista.head.proximo == null) return Pair(null, lista);
        val listaQuebrada = quebraListaFuncao(LinkedList<T>(Node(lista.head.proximo.elemento,lista.head.proximo.proximo )), funcao)
        val l1 = listaQuebrada.first;
        val l2 = LinkedList<T>(Node(lista.head.elemento, listaQuebrada.second?.head));
        return Pair(l1,l2);
    }   
    
    else if(lista.head.proximo == null) return Pair(lista, null);
    val listaQuebrada = quebraListaFuncao(LinkedList<T>(Node(lista.head.proximo.elemento,lista.head.proximo.proximo )), funcao)
    val l1 = LinkedList<T>(Node(lista.head.elemento, listaQuebrada.first?.head));
    val l2 = listaQuebrada.second;
    return Pair(l1, l2); 
    
}

//1.h Implemente uma função para mesclar duas listas ordenadas
fun <T> mescladaOrdenada(l1:LinkedList<T>?, l2:LinkedList<T>?):LinkedList<T>?{
    if(l1?.head?.proximo == null) return l2;
    else if(l2?.head?.proximo == null) return l1;
    val rec = mescladaOrdenada(LinkedList<T>(Node(l1.head.proximo.elemento, l1.head.proximo.proximo)), LinkedList<T>(Node(l2.head.proximo.elemento, l2.head.proximo.proximo)));
    return LinkedList<T>(Node(l1.head.elemento, Node(l2.head.elemento, rec?.head)));
}

//1.i Implemente pelo meno um dos métodos de ordenação vistos: Bolha, Seleção, Inserção, Mergesort ou quicksort. Para ordenar uma lista de inteiros.
fun bubbleSort(lista:LinkedList<Int>): LinkedList<Int>? {
    if(lista.head == null) return null;
    if(lista.head.proximo == null) return lista;
    else if(lista.head.elemento < lista.head.proximo.elemento){ 
        return LinkedList<Int>(Node(lista.head.elemento, bubbleSort(LinkedList<Int>(Node(lista.head.proximo.elemento, lista.head.proximo.proximo)))?.head))
    }
    return LinkedList<Int>(Node(lista.head.proximo.elemento, bubbleSort(LinkedList<Int>(Node(lista.head.elemento, lista.head.proximo.proximo)))?.head));
}

fun inverterLista(lista:LinkedList<T>):LinkedList<T>{
    if(lista.head.proximo == null) return lista;
    val a = LinkedList<T>(Node(inverterLista(lista.head.proximo).value,Node(lista.head.elemento, null)));
    return a;
}

fun main (){
    println("Lista encadeada")
    
    val lista1: LinkedList<Int> = LinkedList<Int>(Node(5,Node(2,Node(3,Node<Int>(6,null)))));
    val lista2: LinkedList<Int> = LinkedList<Int>(Node(8,Node(12,Node<Int>(6,null))));
    val lista3: LinkedList<Int> = LinkedList<Int>(Node(16,Node(5,Node(51,Node<Int>(6,null)))));

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
    val par =  {x:Int -> quebraLista(lista12,x)};

    println("Dividindo a lista mesclada na posicao 2: ${par(2)}")
    println("Dividindo a lista mesclada na posicao 1: ${par(1)}")
  
    val isPair = {x:Int -> x%2 == 0}
    println("Separando a lista mesclada em numeros impares e pares: ${quebraListaFuncao(lista12, isPair)}")
    
    println("Mesclando as listas 1 e 2 ordenamente: ${mescladaOrdenada(lista1, lista2)}")

    println("Bubble sort lista 1: ${bubbleSort(lista1)}")
}
