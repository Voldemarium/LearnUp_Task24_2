package Stepanov.homework.Bookstore.service;

import Stepanov.homework.Bookstore.entity.OrderingDetails;
import Stepanov.homework.Bookstore.repository.OrderingDetailsRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderingDetailsService {

    private final OrderingDetailsRepository orderingDetailsRepository;

    public OrderingDetailsService(OrderingDetailsRepository orderingDetailsRepository) {
        this.orderingDetailsRepository = orderingDetailsRepository;
    }

    public OrderingDetails createOrderingDetails(OrderingDetails orderingDetails) {
        return orderingDetailsRepository.save(orderingDetails);
    }

    public List<OrderingDetails> getOrderingDetails() {
        return orderingDetailsRepository.findAll();
    }

    public OrderingDetails getOrderingDetailsById(Long id) {
        return orderingDetailsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
