package ru.yandex.yandexlavka.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yandex.yandexlavka.model.order.OrderDto;
import ru.yandex.yandexlavka.model.order.kvv.OrderDB;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public interface OrdersRepositoryInterface2 extends JpaRepository<OrderDB, Long> {

    @Query(value = "SELECT o.order_id, o.cost, o.delivery_hours, o.regions, o.weight, o.completed_time, o.complete_time, o.courier_id FROM orders2 o offset :offset rows fetch first :limit rows only;", nativeQuery = true)
    List<OrderDB> findAllOffsetLimit(
            @Param("offset") int offset,
            @Param("limit") int limit);

    @Modifying
    @Transactional
    @Query (value = "UPDATE orders2 SET completed_time= :completed_time WHERE order_id= :order_id ;",  nativeQuery = true)
    void completedOrder (
            @Param("completed_time") OffsetDateTime completed_time,
            @Param("order_id") long order_id
    );

    @Query(nativeQuery = true, value = "select " +
            "o.order_id, o.cost, o.delivery_hours, o.regions, o.weight, o.completed_time, o.complete_time, o.courier_id " +
            "from orders2 o " +
            "where courier_id = :courier_id " +
            "AND completed_time>= :startDate AND completed_time< :endDate ;")
    List<OrderDB> getForCourierMetaInfo (@Param("courier_id") long courier_id,
                                          @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate);
}