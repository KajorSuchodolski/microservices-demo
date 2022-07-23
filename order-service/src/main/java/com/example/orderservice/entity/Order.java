package com.example.orderservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "orders",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id", "client_id"}, name = "order_id_client_id_unique"),
        indexes = @Index(name = "order_id_client_id_index", columnList = "id, client_id")
)
@NoArgsConstructor
@ToString
public class Order {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Getter
    @Setter
    private UUID id;

    @Version
    @Column(name = "version", nullable = false)
    @Basic(optional = false)
    @Getter
    @Setter
    private Long version;

    @Column(name = "created_at", nullable = false)
    @Getter
    @Setter
    private Instant createdAt;

    @Column(name = "client_id", nullable = false)
    @Getter
    @Setter
    private UUID clientId;
}
