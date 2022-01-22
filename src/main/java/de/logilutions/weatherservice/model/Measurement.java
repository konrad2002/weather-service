package de.logilutions.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(nullable = false, name = "timestamp")
    private LocalDateTime timestamp;

    @Column(nullable = false, name = "value")
    private double value;

    @JoinColumn(nullable = false, name = "fk_unit_id")
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    private Unit unit;
}
