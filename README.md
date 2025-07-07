# 🕵️‍♂️ DetecThief Backend

Bienvenido al repositorio del backend de **DetecThief** 🚨, el sistema inteligente de monitoreo y detección de robos en tiempo real. Este backend está diseñado para gestionar la autenticación, administración de usuarios, manejo de cámaras y procesamiento de incidentes detectados.

---

## 📚 Índice

1. [Descripción General](#-descripcion-general)
2. [Arquitectura](#-arquitectura)
3. [Servicios Principales](#-servicios-principales)
4. [Instalación y Ejecución](#-instalacion-y-ejecucion) 
5. [Contribuciones](#-contribuciones) 

---

## 📝 Descripcion General

DetecThief es un backend modular construido en Java, orientado a microservicios. Permite la integración con sistemas de cámaras y notificación para detectar y reportar robos en espacios físicos, facilitando la gestión centralizada de alertas y usuarios.

---

## 🏛️ Arquitectura

El backend está dividido en microservicios independientes, facilitando el mantenimiento y la escalabilidad:

- **auth-service**: Gestión de autenticación y autorización 🔐
- **user-service**: Administración de usuarios 👤
- **camera-service**: Manejo de cámaras y flujos de video 📷
- **incident-service**: Procesamiento y gestión de incidentes detectados 🚨

Cada microservicio puede desplegarse y escalarse de manera independiente.

---

## ⚙️ Servicios Principales

- **Autenticación JWT** para proteger los endpoints.
- **CRUD de usuarios**, roles y permisos.
- **Integración con cámaras IP** y recepción de alertas.
- **Registro y notificación de incidentes** detectados.
- **API RESTful** para integración con clientes y aplicaciones móviles.

---

## 🚀 Instalacion y Ejecucion

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Milko0/backend-DetecThief.git
   ```
2. Accede y compila los servicios:
   ```bash
   cd backend-DetecThief/services/{nombre-del-servicio}
   ./gradlew build
   ```
3. Configura las variables de entorno necesarias (ver sección siguiente).
4. Ejecuta cada microservicio:
   ```bash
   ./gradlew bootRun
   ```
 
---

## 🤝 Contribuciones

¡Las contribuciones son bienvenidas! Por favor, abre un issue o pull request para sugerir mejoras o reportar bugs.

--- 

> Desarrollado con 💡 y dedicación por el equipo DetecThief.
