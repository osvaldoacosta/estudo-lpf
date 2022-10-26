class Lista<T>(val elem:T, val prox:Lista<T>?){
    override fun toString():String{
        return "${elem} -> ${prox}"
      }
  }
/*
Em uma lista "RunLength" os elementos são armazenados em pares de (Valores,
Numero de Repetições). Este tipo de lista é útil na representação compactada de listas
caracterizadas pela repetição do mesmo elemento. Por exemplo a lista:
listOf(1,1,1,2,3,3,3,3,3,0,0,0,0,0,0,0,0,0)
pode ser presentada pela lista RunLength:
listOf(Run(1,3), Run(2,1), Run(3,5), Run(0,9))
Escreva uma função para compactar uma lista encadeada em uma lista RunLength e outra para
descompactar uma lista RunLength.
*/
fun <T> compacta(lista:Lista<T>?):Lista<Pair<T, Int>>?{
    if(lista == null) return null;

    //Vê qts mesmos elementos tem nessa lista
    fun aux1(l:Lista<T>) : Int {
        
        if(l.elem == lista.elem){
            if(l.prox == null) return 1
            return 1 + aux1(l.prox)
        }
        else if(l.prox == null) return 0;
        return aux1(l.prox);
    }
    
    val par = Pair(lista.elem, aux1(lista));
    return Lista(par, compacta(lista.prox)); 
}

fun <T> descompacta(lista:Lista<Pair<T,Int>>?): Lista<T>?{
    if(lista == null) return null;
    fun aux1(n:Int):Lista<T>{
        if(n == lista.elem.second){
           return Lista(lista.elem.first, null); 
        } 
        return Lista(lista.elem.first, aux1(n+1));
    }
    val l = Lista(aux1(1).elem, descompacta(lista.prox));
    return l;
}

fun main (){
    val l1 = Lista(1,Lista(2,Lista(3,Lista(3, Lista(3,null)))));
    println(l1)
    val comp = compacta(l1)
    println(comp)
    println(descompacta(comp))
}
