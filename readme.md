# Instruction
1. Build docker image
    ```shell
    docker buildx build -t order-service:0.0.3 .
    ```
2. Add tag to image
    ```shell
    docker tag order-service:0.0.3 otuslearning/order-service:0.0.3
    ```
3. Push image
    ```shell
    docker push otuslearning/order-service:0.0.3
    ```