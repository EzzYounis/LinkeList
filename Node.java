public class Node <T>{
    public T Value;
    public Node<T> next;
    public Node(T value){
        this.Value=value;
        this.next=null;
    }
    public String toString(){
        return String.valueOf(Value);
    }
}
