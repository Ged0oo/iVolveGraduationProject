<p align="center">
  <img src="static/logos/nti-logo.png" height="90"/>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="static/logos/ivolve-logo.png" height="90"/>
</p>

<h1 align="center">DevOps Graduation Project</h1>

<h3 align="center">In Collaboration with iVolve Technologies</h3>

<p align="center">
  Final project for the NTI DevOps program, containerizing and orchestrating a Python web app using Docker and Kubernetes.
</p>

---

## 🐳 Docker & Docker Compose

The app is containerized using **Docker** and configured for local development with **Docker Compose**.

### Project Structure

```
iVolveGraduationProject/
├── app/
│   ├── app.py
│   ├── requirements.txt
│   ├── static/
│   └── templates/
├── Dockerfile
├── docker-compose.yml
└── README.md
```

### Dockerfile (Multi-Stage)

```dockerfile
# Build dependencies
FROM python:3.11-slim AS builder
WORKDIR /app
COPY app/requirements.txt .
RUN pip install --upgrade pip && pip install --user -r requirements.txt

# Runtime image
FROM python:3.11-slim
WORKDIR /app
COPY app/ .
COPY --from=builder /root/.local /root/.local
ENV PATH=/root/.local/bin:$PATH
EXPOSE 5000
CMD ["python", "app.py"]
```

### docker-compose.yml

```yaml
version: '3.8'

services:
  flask-app:
    build:
      context: .
      dockerfile: Dockerfile
    image: mnagy156/flask-app:latest
    ports:
      - "5000:5000"
    volumes:
      - ./app:/app
    environment:
      - FLASK_ENV=development
      - FLASK_APP=app.py
    command: python app.py
```

### Run Locally

```bash
docker-compose up --build
```

Access: [http://localhost:5000](http://localhost:5000)

---

## ☁️ Docker Hub

To publish the image:

```bash
docker login
docker-compose build
docker push mnagy156/flask-app:latest
```

---

## 📦 Kubernetes Deployment (document=aion)

The app is deployed on a **local Kubernetes cluster (Minikube)** with custom YAML manifests.

### Manifests Location

```
k8s/
├── namespace.yaml
├── deployment.yaml
└── service.yaml
```

### Deployment Steps

1. **Start Minikube**
   ```bash
   minikube start
   ```

2. **Use Minikube’s Docker & Build Image**
   ```bash
   eval $(minikube docker-env)
   docker build -t ivolve-app:latest .
   ```

3. **Apply Kubernetes Manifests**
   ```bash
   kubectl apply -f k8s/namespace.yaml
   kubectl apply -f k8s/ -n ivolve
   ```

4. **Access the App**
   ```bash
   minikube service ivolve-service -n ivolve
   ```

   Output example:
   ```
   http://192.168.49.2:30007
   ```

### Notes

- `imagePullPolicy: Never` is used to run locally built images in Minikube.
- Pod and service are deployed in the custom namespace `ivolve`.

---

## ✅ Final Notes

- Ensure `app.py` has this line for container compatibility:
  ```python
  app.run(host='0.0.0.0', port=5000)
  ```

- Production environments should remove mounted volumes for better security.

---

> Maintained by Mohamed Nagy – NTI DevOps 2025
