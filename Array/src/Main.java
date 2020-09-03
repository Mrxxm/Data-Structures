public class Main {

    public static void main(String[] args) {
        Array arrObj = new Array(20);

        for (int i = 0; i < 20; i++) {
            arrObj.addLast(i + 1);
        }
        System.out.println(arrObj);

        arrObj.remove(19);


        System.out.println(arrObj.get(19));
        System.out.println(arrObj);
    }
}
