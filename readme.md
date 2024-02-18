# Instruction
1. Build docker image
    ```shell
    docker buildx build -t order-service:latest .
    ```
2. Add tag to image
    ```shell
    docker tag order-service:latest otuslearning/order-service:latest
    ```
3. Push image
    ```shell
    docker push otuslearning/order-service:latest
    ```