package ru.yandex.yandexlavka.model.order.kvv;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import lombok.NoArgsConstructor;
import ru.yandex.yandexlavka.model.courier.CourierDto;
import ru.yandex.yandexlavka.model.order.CreateOrderDto;
import ru.yandex.yandexlavka.model.order.OrderDto;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "orders2")
public class OrderDB  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long order_id;
    Double weight;
    Integer regions;
    ArrayList<String> delivery_hours;
    @Nullable
    OffsetDateTime completed_time;
    Integer cost;
    @Nullable
    OffsetDateTime complete_time;
    @Nullable
    Long courier_id;

    @JoinColumn(name = "courier_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = CourierDto.class, fetch = FetchType.LAZY)
    private CourierDto courierDto;

    public static OrderDB getOrderDB (CreateOrderDto createOrderDto){
        OrderDB orderDB = new OrderDB();
        orderDB.weight = createOrderDto.getWeight();
        orderDB.regions = createOrderDto.getRegions();
        orderDB.delivery_hours = new ArrayList<>(createOrderDto.getDelivery_hours());
        orderDB.cost = createOrderDto.getCost();
        return orderDB;
    }
    public OrderDto getOrderDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder_id(order_id);
        orderDto.setCost(cost);
        orderDto.setRegions(regions);
        orderDto.setDelivery_hours(delivery_hours);
        orderDto.setWeight(weight);
        orderDto.setCompleted_time(completed_time);
        return orderDto;
    }
}
