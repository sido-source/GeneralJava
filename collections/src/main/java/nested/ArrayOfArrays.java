package nested;

public class ArrayOfArrays {

    int[][][] threeDArr = new int[2][2][2];
    //int


    int[][] two_D_Arr = new int[3][4];
    int column = two_D_Arr[0].length;
    int row = two_D_Arr.length;


    // if we have the complex structure which does change like doesnt insert/delete some elements
    // then we can use multidimensional arrays
    // we can access the each element in o(1) time
    // but if we want to insert/delete elements, we need to re-allocate memory and copy all elements
    // this can be costly operation as the int[6][5][4][3][2][1] has to allocate memory for 6! elements

    //int[][][][] array = new int[time][depth][rows][cols];
    //int[][][][][] array = new int[situation][time][depth][rows][cols];

}
