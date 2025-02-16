# Service Level-Access Control using RBAC

RBAC is implemented to control which Kubernetes resources each microservice can access. This ensures security by granting only the necessary permissions to each service.

## User Service

✅ Can read Kubernetes services and secrets.  
❌ Cannot modify or delete anything.  

**Test permissions:**  
```sh
kubectl auth can-i get services --as=system:serviceaccount:default:default  
kubectl auth can-i delete services --as=system:serviceaccount:default:default  
```

## Order Service

✅ Can create and fetch orders. 
❌ Cannot delete orders. 

**Test permissions:**  
```sh
kubectl auth can-i create orders --as=system:serviceaccount:default:default  
kubectl auth can-i delete orders --as=system:serviceaccount:default:default  
```

## Payment Service

✅ Can read orders.
❌ Cannot create or modify orders.

**Test permissions:**  
```sh
kubectl auth can-i get orders --as=system:serviceaccount:default:default  
kubectl auth can-i create orders --as=system:serviceaccount:default:default 
```

---

# Testing RBAC in Running Pods

```sh
kubectl exec -it <order-service-pod-name> -- sh

curl -X GET http://order-service.default.svc.cluster.local:8083/api/v1/orders

curl -X DELETE http://order-service.default.svc.cluster.local:8083/api/v1/orders/1
```

```sh
kubectl exec -it <payment-service-pod-name> -- sh

curl -X DELETE http://order-service.default.svc.cluster.local:8083/api/v1/orders/1
```
