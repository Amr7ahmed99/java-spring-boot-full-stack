package com.springboot.testing.mockito_demo.business;

public class SomeBusinessImp {

    private DataService dataService;
    public SomeBusinessImp(DataService dataService) {
        this.dataService = dataService;
    }
    
    public int findTheGreatestFromAllData() {
        var allData= this.dataService.retrieveAllData();
        int greatest = Integer.MIN_VALUE;
        for (int value:allData){
            if (value > greatest) {
                greatest = value;
            }
        }
        return greatest;
    }

    public int[] findMinimumsInTwoArrays() {
        int[] array1 = this.dataService.retrieveAllData();
        int[] array2 = this.dataService.retrieveAllData();
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int value : array1) {
            if (value < min1) {
                min1 = value;
            }
        }

        for (int value : array2) {
            if (value < min2) {
                min2 = value;
            }
        }

        return new int[]{min1, min2};
    }
    
}
