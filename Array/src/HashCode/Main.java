package HashCode;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        int a = 42;
        System.out.println(((Integer)a).hashCode());

        int b= -42;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String d = "xxm";
        System.out.println((d).hashCode());

        HashSet<Integer> set = new HashSet<>();

        HashMap<String, Integer> map = new HashMap<>();
    }
}
