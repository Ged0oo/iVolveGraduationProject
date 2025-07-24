# ---- Stage 1: Build environment ----
FROM python:3.11-slim AS builder

WORKDIR /app

RUN apt-get update && apt-get install -y build-essential

COPY app/requirements.txt .
RUN pip install --upgrade pip && pip install --user -r requirements.txt

# ---- Stage 2: Production image ----
FROM python:3.11-slim

WORKDIR /app

COPY app/ .

COPY --from=builder /root/.local /root/.local
ENV PATH=/root/.local/bin:$PATH

EXPOSE 5000

CMD ["python", "app.py"]

