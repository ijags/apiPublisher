package com.sample.stream.dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.stream.model.Order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/orders")
public class EventDispatcherController {

	private final StreamBridge streamBridge;
	
	private final static String OUTPUT_BINDING = "streambridgeOrders-out-0";
	
	@Autowired
	public EventDispatcherController(StreamBridge bridge) {
		this.streamBridge = bridge;
	}
	
	@PostMapping("/publish")
	public ResponseEntity<Void> postOrders(@RequestBody Order order) {
		System.out.println("Received order message: " + order.toString());
		boolean sent = streamBridge.send(OUTPUT_BINDING,order);
		if(!sent) {
			throw new MessageDeliveryException(order.toString());
		}
		System.out.println("Published message...");
		return ResponseEntity.ok().build();
	}
	
}
