# Demonstrating Inter-service Communication

This document explains how microservices interact within the Kubernetes cluster and how to test their communication using a debug pod.

## Setup a Debug Pod

```sh
kubectl run debug-pod --image=alpine --restart=Never -- sleep 3600
```

Once deployed, access the pod and install `curl`:

```sh
kubectl exec -it debug-pod -- sh
apk add --no-cache curl
```

---

## Testing Microservice Communication

### 1. Create a Product using the Product Service API:

```sh
curl -X POST "http://product-service.default.svc.cluster.local:8082/api/v1/product" \
     -H "Content-Type: application/json" \
     -d '{ "name": "Phone", "price": 1500.00 }'
```

Retrieve the created product:

```sh
curl -X GET "http://product-service.default.svc.cluster.local:8082/api/v1/product/1"
```

---

### 2. Create an Order (Order Service)

Place an order for the product (assuming `userId = 1` and `productId = 2`):

```sh
curl -X POST "http://order-service.default.svc.cluster.local:8083/api/v1/orders" \
     -H "Content-Type: application/json" \
     -d '{ "userId": 1, "productId": 1, "totalPrice": 1500.00 }'
```

Retrieve the created order:

```sh
curl -X GET "http://order-service.default.svc.cluster.local:8083/api/v1/orders/2"
```

---

### 3. Process Payment (Payment Service)

Process payment for the order (assuming `orderId = 1`):

```sh
curl -X POST "http://payment-service.default.svc.cluster.local:8084/api/v1/payments" \
     -H "Content-Type: application/json" \
     -d '{ "orderId": 2, "amount": 1500.00, "status": "SUCCESS" }'
```

Retrieve payment details:

```sh
curl -X GET "http://payment-service.default.svc.cluster.local:8084/api/v1/payments/1"
```

---

## Verifying Logs

To confirm service interactions, check logs for the Order and Payment services:

```sh
kubectl logs -f deployment/order-service
kubectl logs -f deployment/payment-service
```

This ensures that all microservices communicate correctly within the cluster.


