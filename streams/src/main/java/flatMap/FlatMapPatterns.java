package flatMap;

import static flatMap.FlattenArraysOfArrays.flattenNestedArray;
import static flatMap.FlattenListOfList.flattenNestedList;
import static flatMap.FlattenMaps.flattenNestedMap;

public class FlatMapPatterns {

    public static void main(String[] args) {

        // 1. Flattening nested structures
        flattenNested(DataType.ARRAY);
        flattenNested(DataType.LIST);
        flattenNested(DataType.MAP);
        flattenNested(DataType.SET);
        flattenNested(DataType.STREAM);

    }


    public static void flattenNested(DataType dataType) {

        switch (dataType) {
            case ARRAY:
                System.out.println("Show the array example");
                flattenNestedArray();
                break;
            case LIST:
                System.out.println("Show the list example");
                flattenNestedList();
                break;
            case MAP:
                System.out.println("Show the map example");
                flattenNestedMap();
                // Implement flattening for maps
                break;
            case SET:
                // Implement flattening for sets
                break;
            case STREAM:
                // Implement flattening for streams
                break;
            default:
                throw new IllegalArgumentException("Unsupported data type");
        }
    }

}

enum DataType {
    ARRAY, LIST, MAP, SET, STREAM
}
