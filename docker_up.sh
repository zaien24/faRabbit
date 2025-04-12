docker-compose -f ./docker/docker-compose.yml up -d

docker exec -it rabbitmq-2 rabbitmqctl stop_app
docker exec -it rabbitmq-2 rabbitmqctl reset
docker exec -it rabbitmq-2 rabbitmqctl join_cluster rabbit@rabbitmq-1
docker exec -it rabbitmq-2 rabbitmqctl start_app

docker exec -it rabbitmq-3 rabbitmqctl stop_app
docker exec -it rabbitmq-3 rabbitmqctl reset
docker exec -it rabbitmq-3 rabbitmqctl join_cluster rabbit@rabbitmq-1
docker exec -it rabbitmq-3 rabbitmqctl start_app
