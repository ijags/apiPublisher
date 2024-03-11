# Publishes message using StreamBridge

## Spring Cloud Stream - RabbitMQ

Publishes messages to RabbitMQ using an REST API endpoint exposed.

Here's the sample input message:

http://localhost:9090/orders/publish

{
    "orderNumber": 2001,
    "quantity": 5,
    "productName": "Cabinets",
    "productDesc": "Cabinets and Drawers",
    "orderStatus": "CREATED"
}

