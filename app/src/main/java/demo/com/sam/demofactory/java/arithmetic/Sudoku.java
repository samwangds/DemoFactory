package demo.com.sam.demofactory.java.arithmetic;

/**
 *
 * 请判定一个数独是否有效。

 该数独可能只填充了部分数字，其中缺少的数字用 . 表示。

 9*9
 * @author SamWang(199004)
 *         2017/5/26 14:24
 */
public class Sudoku {

    public static void main(String[] args) {

        Sudoku sudoku = new Sudoku();

        String[] strs =  {".87654321","2........","3........","4........","5........","6........","7........","8........","9........"};
        char[][] testData = new char[9][9];
        for (int i = 0; i < strs.length; i++) {
            for(int j=0;j<9;j++) {
                testData[i][j] = strs[i].charAt(j);
            }
            System.out.println(strs[i]);

        }

        System.out.println(sudoku.isValidSudoku(testData));

    }


        /**
         * @param board: the board
         @return: wether the Sudoku is valid
         */
        public boolean isValidSudoku(char[][] board) {

            char[] temp;
            for (int i = 0; i < 9; i++) {
                temp = new char[9];
                for (int j = 0; j < 9; j++) {//横不重复
                    if(!checkValid(temp,board[i][j])){
                        return false;
                    }
                }

                temp = new char[9];
                for (int j = 0; j < 9; j++) {//坚不重复
                    if(!checkValid(temp,board[j][i])){
                        return false;
                    }
                }

                temp = new char[9];
                for (int j = 0; j < 9; j++) {//小9宫格不重复
                    if(!checkValid(temp,board[i%3*3+j%3][i/3*3+j/3])){
                        return false;
                    }
                }

            }
            return true;
        }

    private boolean checkValid(char[] temp,char c){
        if (charNull(c)){
            return true;
        }else{
            int n = c - '1';
            if (n < 0 || n > 8) {
                return false;
            }
            if (charNull(temp[n])) {
                temp[n] = c;
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 这个char在里没值
     * @param c
     * @return
     */
    private boolean charNull(char c) {
        return c == '\0' || c == '.';
    }


}
