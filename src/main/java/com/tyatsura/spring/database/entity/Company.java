package com.tyatsura.spring.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code @NamedQuery} may be used only for Entity or Mapped class. This not so easy to understand because repo level
 * can't see what we uses for some methods.<br>
 * here 'name' parameter is consists of Entity name and method name in Repository. Instead using of parameter name we
 * also may use index (?1) and in this case we will not have any problems with naming of parameters. If we set here
 * another argument name than in repo method we should specify {@code @Param("anotherArgName")} in this repo method
 * near argument. <br>
 * Named Query will be called before PartTree as it have more priority (PartTree will not be called)
 */
@NamedQuery(name = "Company.findByName", query = "SELECT c from Company c WHERE lower(c.name) = lower(:name2)")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "company")
public class Company implements BaseEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locales", joinColumns = @JoinColumn(name = "company_id"))
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String, String> locales = new HashMap<>();
}
