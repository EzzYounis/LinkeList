import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main <T>{
    public  static void main(String[] args) {
        System.out.println("Hello world!");
        LinkedList newnode = new LinkedList();
        newnode.insertInput("Source.txt");
        newnode.searchFile("Search.txt");
        newnode.searchFileV2("Search.txt");
        System.out.println("The list before sorting");
        newnode.display();
        newnode.bubblesort();
        System.out.println("The list after sorting");
        newnode.display();

}}