public class Kujundid {
    public static void ruut(int n) {
        int muutuja = n;
        int count = 0;

        while (muutuja > 0) {
            if (count == 0 || count == n - 1) {
                for (int i = 0; i < n; i++) {
                    System.out.print("#");
                }
                System.out.println();
            } else {
                System.out.print("#");
                for (int i = 0; i < n - 2; i++) {
                    System.out.print(" ");
                }
                System.out.println("#");
            }
            count += 1;
            muutuja -= 1;
        }
        System.out.println();
    }

    static void romb(int n) {
        n = n * 2;
        int muutuja = n;
        int count = 0;
        int keskmine = 1;
        boolean keskel = true;

        while (muutuja > 0) {
            if (count == 0 || count == n - 2) {
                for (int i = 0; i < (n / 2) - 1; i++) {
                    System.out.print(" ");
                }
                System.out.print("#");
                for (int i = 0; i < (n / 2) - 1; i++) {
                    System.out.print(" ");
                }
                System.out.println();
            } else if ((count == n / 2 - 1) && keskel) {
                System.out.print("#");

                for (int i = 0; i < keskmine; i++) {
                    System.out.print(" ");
                }

                System.out.print("#");

                keskel = false;
                count = (n / 2) - 1;
                muutuja = (n / 2);

                System.out.println();

            } else if (count < n / 2) {
                for (int i = 0; i < (n / 2 - count) - 1; i++) {
                    System.out.print(" ");
                }
                System.out.print("#");
                for (int i = 0; i < keskmine; i++) {
                    System.out.print(" ");
                }
                System.out.print("#");
                for (int i = 0; i < (n / 2 - count) - 1; i++) {
                    System.out.print(" ");
                }
                keskmine += 2;
                System.out.println();

            } else {
                keskmine -= 2;
                for (int i = 0; i < count - n / 2 + 1; i++) {
                    System.out.print(" ");
                }
                System.out.print("#");
                if (count != n - 1) {
                    for (int i = 0; i < keskmine; i++) {
                        System.out.print(" ");
                    }
                    System.out.print("#");
                }
                for (int i = 0; i < count - n / 2 + 1; i++) {
                    System.out.print(" ");
                }
                System.out.println();
            }
            count += 1;
            muutuja -= 1;
        }
        System.out.println();
    }

    static void telk(int n) {
        int muutuja = n;
        int count = 0;
        int keskmine = -1;
        int jooksev = 0;

        if (n > 1) {
            for (int i = 1; i < n - 1; i++) {
                jooksev += 2;
            }
        }

        int ala = (n * 2) + 4 + (jooksev);

        if (n == 1) {
            ala = 4;
        }

        while (muutuja > 0) {

            if (count == 0) {
                for (int i = 0; i < (ala) / 2; i++) {
                    System.out.print(" ");
                }
                System.out.print("#");
                System.out.println();
            } else {

                for (int i = 0; i < (ala - (n * 2 + keskmine) - 1); i++) {
                    System.out.print(" ");
                }

                for (int i = 0; i < count + 1; i++) {
                    System.out.print("#");
                }

                for (int i = 0; i < keskmine; i++) {
                    System.out.print(" ");
                }

                for (int i = 0; i < count + 1; i++) {
                    System.out.print("#");
                }
                System.out.println();
            }
            keskmine += 2;
            count += 1;
            muutuja -= 1;
        }
        for (int i = 0; i < ala + 1; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

}


