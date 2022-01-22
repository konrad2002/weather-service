package de.logilutions.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unit")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(nullable = false, name = "key_name")
    private String key;

    @Column(nullable = false, name = "display_name")
    private String displayName;

    @Column(name = "unit_symbol")
    private String unitSymbol;
}
