import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LinkedList<T extends Comparable> {
    private Node<T>head;
    public Node<T> creatNewNode(T value){
        return new Node<T>(value);
    }

    //=================================Inserting the values==========================================================================
    public void insertInput(String file){  //This method reads the values from the file and insert them to the list
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Source.txt"));
            String[] strVal=reader.readLine().split(",");
            for (int i = 0; i<strVal.length;i++){
                T val=(T) Integer.valueOf(strVal[i]);
                if (!search(val)){
                insertToEnd(val);
                }
            }
            reader.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //=============================================================================================================
    public boolean isEmpty(){
        return head==null;
    }
    public void insertToFront(T value){         //creating a new node and inserting it to the beginning of the list
        Node<T> newNode=creatNewNode(value);
        if(isEmpty()){
            head=newNode;
        } else {
            newNode.next=head;
            head=newNode;
        }
    }
    //=============================================================================================================
    public void insertToEnd(T value){       //creating a new node and inserting it to the end of the list
        Node<T> newNode = creatNewNode(value);
        if (isEmpty()){
            head=newNode;
        }else {
            Node<T> iterator=head;
            while (iterator.next!=null){
                iterator=iterator.next;
            }
            iterator.next=newNode;
        }
    }
    //=======================================Display all Nodes=======================  ===========================
    public void display(){
        Node<T>iterator=head;
        while (iterator!=null){
            System.out.print(iterator.Value + " ");
            iterator=iterator.next;
        }
        System.out.println("");
    }
    //===========================Searching for a group of values from a text file (First version)==============
    public void searchFile(String fileName){
    try {
        BufferedReader reader =new BufferedReader(new FileReader(fileName));
        String[] stInt=reader.readLine().split(",");
        int searchCounter=0;
        for (int i=0;i<stInt.length;i++){
            T val=(T)Integer.valueOf(stInt[i]);
            searchCounter+=1;   //we're accessing to a node (head)
            Node<T>iterator=head;
            if (isEmpty()){
                break;
            }
            while (iterator!=null){
                searchCounter+=1; //were accessing value here
                if (iterator.Value.compareTo(val)==0){
                    break;
                }
                iterator=iterator.next;
                    searchCounter+=1;

            }
        }
        System.out.println("The Total Memory accesses for V1 Search Method = "+searchCounter);
        System.out.println("The Average for V1 Search Method = "+searchCounter/stInt.length);

        reader.close();

    } catch (Exception e) {
        System.out.println(e);
    }}
    //================================Deleting a node by it's value===========================================================
    public void delete(T val){
        if (isEmpty()){
            return;
        }
        Node<T>iterator=head;
        Node<T>prev=head;
        while (iterator!=null){
            if (iterator.Value.compareTo(val)==0){
                if (iterator==head){                       //first node
                    head=iterator.next;

                } else if (iterator.next==null) {          // last node
                    prev.next=null;
                }else{                                     //Other Cases
                    prev.next=iterator.next;
                }
            }
            prev=iterator;
            iterator=iterator.next;
        }
    }
    //=================================Search for existence of anode by it's value==========================================================
    public boolean search( T value){
        Node<T>iterator=head;
        if (isEmpty()){
            return false;
        }
        while (iterator!=null){
            if (iterator.Value.compareTo(value)==0){
                return true;
            }
            iterator=iterator.next;
        }
        return false;
    }
    //===============================Moving certain node to be the head of the list==================================
    public int makeFirst(Node<T> node,Node<T> prev){
        int memoryaccesses=0;
        if(head.next==null||node==head){ // if the node was the head
            memoryaccesses++;
            return memoryaccesses;

        }else{
            prev.next=node.next;
            node.next=head;
            head=node;
            memoryaccesses+=5;
            return memoryaccesses;
        }

    }
    //============================The second version of searching file=================================================
    public void searchFileV2(String fileName){ //memory accesses are the number of times we access to a value of a node or when we access to  anode
        try {
            BufferedReader reader =new BufferedReader(new FileReader(fileName));
            String[] stInt=reader.readLine().split(",");

            int memoryaccesses=0;
            for (int i=0;i<stInt.length;i++){
                memoryaccesses+=2; //we're accessing to a node (head) 2 times
                Node<T>iterator=head;
                Node<T>previous=head;
                T val = (T)Integer.valueOf(stInt[i]);
                if (isEmpty()){
                    break;
                }
                while (iterator!=null){
                    memoryaccesses+=1; //were accessing value here
                    if (iterator.Value.compareTo(val)==0){
                        memoryaccesses+=makeFirst(iterator,previous);
                        break;
                    }
                    previous=iterator;
                    iterator=iterator.next;
                    memoryaccesses+=2;
                }

            }
            System.out.println("The Total Memory accesses for V2 Search Method = "+memoryaccesses);
            System.out.println("The Average for V2 Search Method = "+memoryaccesses/stInt.length);
            reader.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //========================================================================================================
    public int count(){
    int counter=0;
    Node<T>iterator=head;
    while (iterator!=null){
        counter+=1;
        iterator=iterator.next;
    }
    return counter;
    }
    //=======================Swapping method for two nodes==================================================
    public void swapTwo(Node<T> prev,Node<T> node,Node<T>nxtNode){
        if (node==head){
            node.next=nxtNode.next;
            nxtNode.next=node;
            head=nxtNode;
        }else {
            prev.next=node.next;
            node.next=nxtNode.next;
            nxtNode.next=node;
        }
    }
    //=========================bubble sorting method===============================================
    public void bubblesort(){
        boolean innerloop=true;
        boolean outerloop =true; //this boolean to see if there is any swapping happening if not then it wont check again
        while(outerloop){
            outerloop=false;
            Node<T>iterator=head;
            Node<T>previous=head;
            Node<T>tempPrev = null;
            while (iterator.next!=null){
                innerloop=false;
                if (iterator.Value.compareTo(iterator.next.Value)==1){
                    tempPrev=iterator.next;
                    swapTwo(previous,iterator,iterator.next);
                    innerloop=true;
                    outerloop=true;
                }
                if (innerloop){
                    if (previous==iterator){
                        previous=tempPrev;
                    }else if (previous.next!=iterator){
                        previous=previous.next;
                    }
                }else{
                previous=iterator;
                iterator=iterator.next;}
            }
        }
    }
}
