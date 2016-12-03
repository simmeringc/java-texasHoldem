package com.team7.texasHoldem.view;

public class ComboBoxParameters {

    private Integer[] cacheSizeRange;
    private String[] replacementAlgorithms;

    public ComboBoxParameters(Integer[] cacheSizeRange, String[] replacementAlgorithms) {
        this.cacheSizeRange = cacheSizeRange;
        this.replacementAlgorithms = replacementAlgorithms;
    }

    public void setCacheSizeRange(int range) {
        cacheSizeRange = new Integer[range+1];
        for (int i=0;i<=range;i++) {
            cacheSizeRange[i] = i;
            System.out.println(cacheSizeRange[i]);
        }
    }
    public void setReplacementAlgorithms(String[] stringArray) {
        replacementAlgorithms = stringArray;
    }
    public Integer[] getCacheSizeRange() { return cacheSizeRange; }
    public String[] getReplacementAlgorithms () {
        return replacementAlgorithms;
    }
}
