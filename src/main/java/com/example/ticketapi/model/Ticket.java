package com.example.ticketapi.model;

import com.example.ticketapi.enums.TicketStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Table(name = "tbl_tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo usuario no puede ir vacío")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @NotNull(message = "Debe haber una descripción")
    private String description;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateAt;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @PrePersist
    public void prePersist() { this.createAt = LocalDateTime.now(); }

    @PreUpdate
    public void preUpdate() { this.updateAt = LocalDateTime.now(); }

}
