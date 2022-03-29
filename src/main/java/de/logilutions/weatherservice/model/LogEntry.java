package de.logilutions.weatherservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "log_entry")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogEntry {
    @Id
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "message")
    private String message;

    @JoinColumn(nullable = false, name = "fk_log_type_id")
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    private LogType logType;
}
