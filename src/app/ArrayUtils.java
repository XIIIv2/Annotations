package app;

public class ArrayUtils {

    @MethodInfo(
            name = "mergeSort",
            returnType = "int[]",
            desc = "Sorts an array."
    )
    @Author(
            firstName = "Kirill",
            lastName = "Popov"
    )
    public int[] mergeSort(int[] arrayA) {
        if (arrayA == null) {
            return null;
        }

        if (arrayA.length < 2) {
            return arrayA;
        }

        int[] arrayB = new int[arrayA.length / 2];
        System.arraycopy(arrayA, 0, arrayB, 0, arrayA.length / 2);

        int[] arrayC = new int[arrayA.length - arrayA.length / 2];
        System.arraycopy(arrayA, arrayA.length / 2, arrayC, 0, arrayA.length - arrayA.length / 2);

        arrayB = mergeSort(arrayB);
        arrayC = mergeSort(arrayC);

        return mergeArray(arrayB, arrayC);
    }

    @MethodInfo(
            name = "mergeArray",
            returnType = "int[]",
            desc = "Merges an array."
    )
    @Author(
            firstName = "Kirill",
            lastName = "Popov"
    )
    private int[] mergeArray(int[] arrayA, int[] arrayB) {
        int[] result = new int[arrayA.length + arrayB.length];
        int i = 0;
        int positionA = 0;
        int positionB = 0;

        while (positionA < arrayA.length && positionB < arrayB.length) {
            result[i++] = arrayA[positionA] < arrayB[positionB] ? arrayA[positionA++] : arrayB[positionB++];
        }

        while (positionA < arrayA.length) {
            result[i++] = arrayA[positionA++];
        }

        while (positionB < arrayB.length) {
            result[i++] = arrayB[positionB++];
        }

        return result;
    }

    @MethodInfo(
            name = "binarySearch",
            returnType = "int",
            desc = "Performs a binary search on a sorted array."
    )
    @Author(
            firstName = "Kirill",
            lastName = "Popov"
    )
    public int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int middleIndex = (low + high) / 2;
            int middleValue = array[middleIndex];

            if (target == middleValue) {
                return middleIndex;
            }

            if (target < middleValue) {
                high = middleIndex - 1;
            } else {
                low = middleIndex + 1;
            }
        }

        return -(low + 1);
    }
}
