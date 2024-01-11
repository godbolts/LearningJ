import java.util.*;

public class Moonutamine{

    public static int[] teePaarisPaaritud(int[] a) {
        int n = a.length;
        int jarjekord = 0;
        for (int value : a) {
            if (value % 2 == 0) {
                jarjekord++;
            }
        }
        int[] shifts = new int[jarjekord];
        int j = -1;
        int index = -1;
        int koht = 0;
        for (int i = 0; i < n; i++) {
            koht++;
            if (a[i] % 2 == 0) {
                koht--;
                j++;
                index++;
                int temp = a[i];
                for (int k = i; k > j; k--) {
                    a[k] = a[k - 1];
                }
                a[j] = temp;
                shifts[index] = koht;
            }
        }
        return shifts;
    }



   public static int[] teeAlgne(int[] b, int[] nihe){
    int n = b.length;
    int k = nihe.length;

   	  for (int i = n - 1; i >= 0; i--) {
             if (b[i] % 2 == 0) {
                 k--;
                 int temp = b[i];
                 int index = i + nihe[k];
                 int lükkatav = b[index];
                 b[index] = temp;
                 for (int j = index - 1; j >= 0; j-- ) {
                     if (i == j) {
                         b[j] = lükkatav;
                         break;
                     }
                     int ajutine = b[j];
                     b[j] = lükkatav;
                     lükkatav = ajutine;
                 }
             }
      }
         return b;
   }

    //public static int mituKordaAlgseni(int[] a, int[] nihe) {
    //    int korrad = 0;




    //    return korrad;
     //   }



}