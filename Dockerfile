FROM docker/compose:1.29.2

COPY compose.yaml /app/compose.yaml

CMD ["docker-compose", "-f", "/app/compose.yaml", "up"]
