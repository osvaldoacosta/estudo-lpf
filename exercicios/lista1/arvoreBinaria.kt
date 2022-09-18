//2.a Implemente o tipo de dado para árvore binária para elementos genéricos
class Node<T>(val elem: T, val esquerda:Node<T>?, val direita:Node<T>?){
    override fun toString():String{
        return "${elem} -> (${esquerda} , ${direita})"
    }
}


class BinaryTree<T>(val head:Node<T>){
    override fun toString():String{
        return head.toString();
      }
  }

//2.b Para uma árvore de inteiros escreva uma função verificar se é uma árvore de busca binária
fun isBinaryTree(bt:BinaryTree<Int>): Boolean {
    //Talvez dê pra melhorar isso
    if(bt.head.esquerda == null){
        if(bt.head.direita == null || bt.head.direita.elem >= bt.head.elem) return true;
        return false;
    }
    else if(bt.head.direita == null){
        if(bt.head.esquerda.elem <= bt.head.elem) return true;
        return false;
    }
    
    val bte = isBinaryTree(BinaryTree(Node(bt.head.esquerda.elem, bt.head.esquerda.esquerda, bt.head.esquerda.direita)));
    val btd = isBinaryTree(BinaryTree(Node(bt.head.direita.elem, bt.head.direita.esquerda, bt.head.direita.direita)));

    return bte && btd;
}

fun main (){
    val arvore:BinaryTree<Int> = BinaryTree(Node(1,Node(2,null,null),Node(3,null,null)));

    val arvore1:BinaryTree<Int> = BinaryTree(Node(2,Node(5,Node(4,null,null),null),Node(3,Node(0,null,null),Node(5,null,null))));

    val arvore2:BinaryTree<Int> = BinaryTree(Node(1,null,null));
    println("A arvore ${arvore} é de busca binaria?: ${isBinaryTree(arvore)}");

    println("A arvore ${arvore1} é de busca binaria?: ${isBinaryTree(arvore1)}");

    println("A arvore ${arvore2} é de busca binaria?: ${isBinaryTree(arvore2)}");
}

