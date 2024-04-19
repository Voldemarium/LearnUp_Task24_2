package Stepanov.homework.Bookstore.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"orderingDetailsList"})
public class Ordering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Buyer buyer;

    @Transient
    private Integer purchase_amount = 0;

    @OneToMany(mappedBy = "ordering", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderingDetails> orderingDetailsList;

    @PrePersist
    @PreUpdate
    @PostLoad
    private void postLoad() {
        for (OrderingDetails orderingDetails : orderingDetailsList) {
          this.purchase_amount += orderingDetails.getBook().getPrice() * orderingDetails.getQuantity();
        }
    }

    public Ordering(Long id, Buyer buyer, Integer purchase_amount) {
        this.id = id;
        this.buyer = buyer;
        this.purchase_amount = purchase_amount;
    }

    @Override
    public String toString() {
        return "Ordering{" +
                "id=" + id +
                ", buyer_id=" + buyer.getId() +
                ", purchase_amount=" + purchase_amount +
                ", orderingDetailsList=" + orderingDetailsList +
                '}';
    }
}
