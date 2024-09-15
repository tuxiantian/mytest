package com.tuxt.mytest.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Model {
    String modelId;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public static void main(String[] args) {
        Model model=new Model();
        model.setModelId("1731579712968331265");
        Map<Long,String> nameMap=new HashMap<>();
        nameMap.put(1731579712968331265L,"abc");
        String modelId="1731579712968331265";
        String collect = Arrays.stream(modelId.split(",")).map(Long::valueOf).map(nameMap::get).collect(Collectors.joining(","));
        System.out.println(collect);
    }


}
