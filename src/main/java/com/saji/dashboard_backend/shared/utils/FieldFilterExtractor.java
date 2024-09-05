package com.saji.dashboard_backend.shared.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.saji.dashboard_backend.shared.dtos.ValueFilter;

public class FieldFilterExtractor implements FilterExtractor<Collection<ValueFilter>> {

    @Override
    public Collection<ValueFilter> getFilters(Map<String, Object> filters) {
        Map<String, ValueFilter> temp = new HashMap<>();

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (ValueFilterValidator.isValueFilter(key)) {
                String keyNumber = ValueFilterValidator.extractKey(key);
                if (!temp.containsKey(keyNumber)) {
                    temp.put(keyNumber, new ValueFilter());
                }
                if (ValueFilterValidator.isValueFilterField(key)) {
                    temp.get(keyNumber).setField(value.toString());
                } else if (ValueFilterValidator.isValueFilterOperator(key)) {
                    temp.get(keyNumber).setOperator(value.toString());
                } else if (ValueFilterValidator.isValueFilterValue(key)) {
                    temp.get(keyNumber).setValue(value);
                }
            }
        }

        return temp.values();
    }

}