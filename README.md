# Microservices-demo-app

---

# Cluster Creation Guide

## Pre-requisites
1. **AWS CLI**
2. **eksctl**
3. **kubectl**

## Commands for Cluster Creation
1. **Create Cluster with EC2 Spot Instances**:
   ```bash
    eksctl create cluster --name jar-demo-cluster --region ap-south-1 --nodes 2 --nodes-min 2 --nodes-max 3 --node-type t3.medium --managed --spot

2. **Update kubeconfig**: This command updates the local kubeconfig file, enabling kubectl to authenticate and manage resources in the EKS cluster.
   ```bash
   aws eks update-kubeconfig --name jar-demo-cluster --region ap-south-1

3. **Associate IAM OIDC Provider**: Associating an IAM OIDC provider with the cluster enables IAM roles for service accounts, allowing your workloads to use IAM permissions directly.
   ```bash
   eksctl utils associate-iam-oidc-provider --cluster demo-cluster --approve
