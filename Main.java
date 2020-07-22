package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int xcount = 0;
        int ocount = 0;

        char[][] array = new char[3][3];
        int k = 0;
        int x = 0;
        int o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = ' ';
            }
        }

        display(array);

        char turn = 'X';

        while (true) {
            System.out.print("Enter the coordinates: ");
            String xstr = scanner.next();
            String ystr = scanner.next();

            if (!isInteger(xstr) || !isInteger(ystr)) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int xpos = Integer.parseInt(xstr);
            int ypos = Integer.parseInt(ystr);

            if (!isRange(xpos) || !isRange(ypos)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (array[3 - ypos][xpos - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            array[3 - ypos][xpos - 1] = turn;
            xcount += turn == 'X' ? 1 : 0;
            ocount += turn == 'O' ? 1 : 0;

            display(array);
            turn = turn == 'X' ? 'O' : 'X';

            boolean xwin = isWin('X', array);
            boolean owin = isWin('O', array);

            String result;
            if (Math.abs(xcount - ocount) > 1 || xwin && owin) {
                result = "Impossible";
            } else if (xwin) {
                result = "X wins";
            } else if (owin) {
                result = "O wins";
            } else if (xcount + ocount == 9) {
                result = "Draw";
            } else {
                continue;
            }

            System.out.println(result);
            break;
        }
    }

    private static boolean isRange(int pos) {
        if (pos >= 1 && pos <= 3) {
            return true;
        }
        return false;
    }

    private static boolean isInteger(String input) {
        if (input.length() != 1) {
            return false;
        }
        char ch = input.charAt(0);
        if (ch >= '1' && ch <= '9') {
            return true;
        }
        return false;
    }

    private static void display(char[][] array) {
        String line1 = "| " + array[0][0] + " " + array[0][1] + " " + array[0][2] + " |";
        String line2 = "| " + array[1][0] + " " + array[1][1] + " " + array[1][2] + " |";
        String line3 = "| " + array[2][0] + " " + array[2][1] + " " + array[2][2] + " |";

        System.out.println("---------");
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println("---------");
    }

    private static boolean isWin(char ch, char[][] array) {
        int i;
        int j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3 && array[i][j] == ch; j++) {
            }
            if (j == 3) {
                return true;
            }
        }
        for (j = 0; j < 3; j++) {
            for (i = 0; i < 3 && array[i][j] == ch; i++) {
            }
            if (i == 3) {
                return true;
            }
        }
        for (i = 0; i < 3 && array[i][i] == ch; i++) {
        }
        if (i == 3) {
            return true;
        }
        for (i = 0; i < 3 && array[i][2 - i] == ch; i++) {
        }
        if (i == 3) {
            return true;
        }
        return false;
    }
}
