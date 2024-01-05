#!/bin/bash

# Set the directory
DIRECTORY="./Kubernetes"

# Apply Kubernetes configurations in the specified order
kubectl apply -f $DIRECTORY/mqtt.yml
kubectl apply -f $DIRECTORY/zookeeper.yml
kubectl apply -f $DIRECTORY/kafka.yml
kubectl apply -f $DIRECTORY/kafka-topic-creation-job.yml
kubectl apply -f $DIRECTORY/postgres-init-configmap.yml
kubectl apply -f $DIRECTORY/postgres.yml
kubectl apply -f $DIRECTORY/mailhog.yml
kubectl apply -f $DIRECTORY/pdcs.yml
kubectl apply -f $DIRECTORY/has.yml
kubectl apply -f $DIRECTORY/ans.yml
kubectl apply -f $DIRECTORY/pms.yml
kubectl apply -f $DIRECTORY/react.yml

# Check the react-app address and open in browser
#minikube service react-app --url


# Delete all applied configurations
#kubectl delete deployments --all
#kubectl delete service --all
#kubectl delete pods --all
#kubectl delete pv --all
#kubectl delete job kafka-topic-creation
#kubectl delete statefulset --all