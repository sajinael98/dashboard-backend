package com.saji.dashboard_backend.shared.specifications;

import java.util.Collection;

import org.springframework.data.jpa.domain.Specification;

import com.saji.dashboard_backend.shared.dtos.ValueFilter;

import jakarta.persistence.criteria.Predicate;

public class EntitySpecification<T> {
    public static <T> Specification<T> findList(Collection<ValueFilter> valueFilters) {
        return (root, cq, cb) -> {
            Predicate finalPredicate = null;
            for (ValueFilter condition : valueFilters) {
                Predicate predicate = cb.equal(root.get(condition.getField()), condition.getValue());
                if (finalPredicate == null) {
                    finalPredicate = predicate;
                } else {
                    finalPredicate = cb.and(finalPredicate, predicate);
                }
            }
            
            return finalPredicate;
        };
    }
}