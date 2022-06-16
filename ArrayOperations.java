import java.util.ArrayList;

public class ArrayOperations {
    public static void main(String[] args) {
        int[] array1 = new int[]{1,2,5,7};
        int[] array2 = new int[]{2,4,5,7,9,11};
        findUnion(array1,array2);
        findIntersect(array1,array2);
        aMinusB(array1,array2,"A - B: ");
        aMinusB(array2,array1,"B - A: ");
    }

    private static void aMinusB(int[] array1, int[] array2,String msg) {
        ArrayList<Integer> output = new ArrayList<>();
        for(int i=0;i<array1.length;i++){
            boolean flag = true;
            for(int j=0;j<array2.length;j++){
                if(array1[i]==array2[j]){
                    flag = false;
                }
            }
            if(flag){
                output.add(array1[i]);
            }
        }
        System.out.println(msg+output);
    }



    private static void findIntersect(int[] array1, int[] array2) {
        ArrayList<Integer> output = new ArrayList<>();
        for(int i=0;i<array1.length;i++){
            for(int j=0;j<array2.length;j++){
                if(array1[i]==array2[j]){
                    output.add(array1[i]);
                }
            }
        }
        System.out.println("Intersect: "+output);
    }



    private static void findUnion(int[] array1, int[] array2) {

        ArrayList<Integer> output = new ArrayList<>();
        int startIndex = 0;
        for(int i=0;i<array1.length;i++){
            for(int j=startIndex;j<array2.length;j++){
                if(array1[i]==array2[j]){
                    startIndex = j+1;
                }
                if(array1[i]>array2[j]){
                    output.add(array2[j]);
                    startIndex = j+1;
                }
            }
            output.add(array1[i]);
        }
        for(int i = startIndex;i<array2.length;i++){
            output.add(array2[i]);
        }
        System.out.println("Union: "+output);

    }
}
