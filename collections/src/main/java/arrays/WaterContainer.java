package arrays;

public class WaterContainer {

    public static void main(String[] args) {
        maxArea(new int[]{1,8,6,2,5,4,8,3,7});
    }

    public static int maxArea(int[] height) {

        int left = 0;
        int right = height.length -1;
        int maxWater = 0;

        while (left < right) {
            maxWater = Math.max(Math.min(height[left], height[right]) * (right - left), maxWater);

            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxWater;
    }
}
