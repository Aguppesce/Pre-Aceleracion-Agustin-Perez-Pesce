package com.alkemy.disney.disney.repository.specification;

import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.Gender;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {
    public Specification<Movie> getByFilters(MovieFiltersDTO movieFiltersDTO){

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(movieFiltersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                        "%" + movieFiltersDTO.getTitle().toLowerCase() + "%"
                                )
                        );
            }

            if(!CollectionUtils.isEmpty(movieFiltersDTO.getGender())){
                Join<Movie, Gender> join = root.join("movieGender", JoinType.INNER);
                Expression<String> idGender = join.get("id");
                predicates.add(idGender.in(movieFiltersDTO.getGender()));
            }

            query.distinct(true);

            String orderByField = "title";
            query.orderBy(
                    movieFiltersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
