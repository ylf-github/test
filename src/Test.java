import java.util.*;

/**
 * @Author: ylf
 * @Time: 2019/7/24 下午5:42
 * @Description:
 */
public class Test {
    public static void main(String[] args){

        ArrayList<String> list=new ArrayList<>();
        list.add("ab");
        list.add("b");
        list.add("c");

        ArrayList<Object> l=new ArrayList();

        System.out.println(l);

        Arrays.stream(list.toArray()).forEach((x)->l.add(x));

        long n=list.stream().filter((x)->x.length()>1).count();

       Optional s= list.stream().limit(4).findFirst();

        long p=list.stream().skip(1).peek((x)->System.out.println(x)).count();
       // System.out.println(p);

        //System.out.println(s);

        System.out.println(l);
    }
}
