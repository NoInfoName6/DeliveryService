package ru.yandex.yandexlavka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.order.CompleteOrderRequestDto;
import ru.yandex.yandexlavka.model.order.CreateOrderRequest;
import ru.yandex.yandexlavka.model.order.OrderAssignResponse;
import ru.yandex.yandexlavka.model.order.OrderDto;
import ru.yandex.yandexlavka.services.OrdersService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController {
    @Autowired
    OrdersService ordersService;
    @PostMapping("")
    public ResponseEntity<List<OrderDto>> createOrders(@RequestBody CreateOrderRequest createOrderRequest) {
            return ResponseEntity.ok(ordersService.createOrders(createOrderRequest));
    }
    @GetMapping("/{order_id}")
    public ResponseEntity<OrderDto> getOrders(@PathVariable long order_id){
            return ResponseEntity.ok(ordersService.getOrderById(order_id));
    }
    @GetMapping("")
    public ResponseEntity<List<OrderDto>> getOrders(@RequestParam(required = false, defaultValue = "1") Integer limit, @RequestParam(required = false, defaultValue = "0") Integer offset){
            return ResponseEntity.ok(ordersService.getOrdersResponse(limit, offset));
    }
    @PostMapping("/complete")
    public ResponseEntity<List<OrderDto>> completeOrders (@RequestBody CompleteOrderRequestDto completeOrderRequestDto){
            return ResponseEntity.ok(ordersService.completeOrders(completeOrderRequestDto));

    }
    @PostMapping("/assign")
    public ResponseEntity<?> ordersAssign (@RequestParam(required = false) LocalDate date){
            if (date == null) date = LocalDate.now();
            ArrayList<OrderAssignResponse> orderAssignResponseArrayList = new ArrayList<>();

            return ResponseEntity.accepted().body(orderAssignResponseArrayList);
    }
}
