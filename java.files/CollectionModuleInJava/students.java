package CollectionModuleInJava;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class students {
    int roll;
    String name;
    double marks;
    students(String name,int roll,double marks){
        this.name=name;
        this.roll=roll;
        this.marks=marks;
    }
}
class obg{
    static ArrayList<Integer> fillarr(int[] arr,int n){
     ArrayList<Integer> li=new ArrayList<>();
     for(int i=0;i<n;i++){
         li.add(arr[i]);
     }
     return li;
    }
    public static void main(String[] args) {
        List<String> li=new ArrayList<>();
        li.add("hello");
        li.add("bello");
        li.add("gello");
        li.add("jello");
        li.add("rello");
        Iterator<String> iter=li.iterator();
        while(iter.hasNext()){
            System.out.print(iter.next()+" ");
        }
        System.out.println();
        List<students> st=new ArrayList<>();
        st.add(new students("ram",1,67.7));
        st.add(new students("shyam",2,56.9));
        st.add(new students("madahv",3,67.0));
        st.add(new students("himan",4,679.7));
        st.add(new students("geloy",5,674.9));
        st.add(new students("karl",6,787));
        Iterator<students> itera= st.iterator();
        while(itera.hasNext()){
            System.out.println(itera.next().name+" "+itera.next().roll+" "+itera.next().marks);
        }
        int arr[]={1,2,5,3,7,1,9,35,98,657,3232,323};
        int n=arr.length;
        ArrayList<Integer> arr2=fillarr(arr,n);
        System.out.println(arr2);
    }
}
