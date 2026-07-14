# 🔐 SCMC - Sistema de Cifrado Multi-Capa

> Simulador de cifrado y descifrado basado en **Permutaciones**, **Funciones Biyectivas** y **Aritmética Modular**, desarrollado como proyecto académico de **Matemáticas Discretas II**.

<p align="center">
  <img src="./assets/logo.png" width="180" alt="SCMC Logo">
</p>

<p align="center">

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?style=for-the-badge&logo=springboot)
![React](https://img.shields.io/badge/React-19-61DAFB?style=for-the-badge&logo=react)
![TypeScript](https://img.shields.io/badge/TypeScript-5-blue?style=for-the-badge&logo=typescript)
![Vite](https://img.shields.io/badge/Vite-7-purple?style=for-the-badge&logo=vite)

</p>

---

## 🌐 Demo

### Aplicación desplegada

👉 **https://scmc-blush.vercel.app/**

---

# 📖 Descripción

SCMC (**Sistema de Cifrado Multi-Capa**) es una aplicación web que implementa un algoritmo de cifrado de doble capa para demostrar la aplicación práctica de conceptos de Matemáticas Discretas en criptografía.

El proceso de cifrado combina dos transformaciones reversibles:

- Permutaciones (Grupo Simétrico)
- Aritmética Modular (Grupo Cíclico)

Posteriormente, el sistema aplica las transformaciones inversas para recuperar exactamente el mensaje original.

---

# 🚀 Características

- 🔐 Cifrado mediante permutaciones.
- ➕ Cifrado mediante desplazamiento modular.
- 🔄 Descifrado completo.
- 📊 Auditoría paso a paso de cada transformación.
- 🧮 Validación matemática de los parámetros.
- 📱 Interfaz moderna y responsive.
- ⚡ API REST desarrollada con Spring Boot.

---

# 🧠 Fundamentos Matemáticos

Este proyecto implementa conceptos vistos durante el curso de Matemáticas Discretas II, entre ellos:

- Funciones biyectivas
- Funciones inversas
- Permutaciones
- Grupo Simétrico (Sn)
- Composición de funciones
- Aritmética Modular
- Grupo Cíclico
- Transformaciones reversibles

---

# ⚙️ Funcionamiento

```
Mensaje Original
       │
       ▼
Permutación
       │
       ▼
Transformación Modular
       │
       ▼
Mensaje Cifrado
       │
       ▼
Transformación Modular Inversa
       │
       ▼
Permutación Inversa
       │
       ▼
Mensaje Original
```

---

# 🛠️ Tecnologías

## Frontend

- React 19
- TypeScript
- Vite
- CSS3

## Backend

- Java 21
- Spring Boot
- Spring Web
- Maven

---

# 📂 Arquitectura

```
SCMC
│
├── frontend/
│   ├── React
│   ├── TypeScript
│   └── Vite
│
├── backend/
│   ├── Spring Boot
│   ├── Controllers
│   ├── Services
│   ├── DTO
│   └── Models
│
└── README.md
```

---

# 🔑 Algoritmo

## Cifrado

1. Dividir el mensaje en bloques.
2. Aplicar la permutación definida por el usuario.
3. Aplicar desplazamiento modular.
4. Construir el mensaje cifrado.

## Descifrado

1. Aplicar el desplazamiento inverso.
2. Calcular la permutación inversa.
3. Reordenar los bloques.
4. Recuperar el mensaje original.

---

# 📷 Capturas

## Página principal

> Agrega aquí una captura de la aplicación.

---

## Cifrado

> Agrega aquí una captura del proceso de cifrado.

---

## Descifrado

> Agrega aquí una captura del proceso de descifrado.

---

# 🚀 Instalación

## Clonar el repositorio

```bash
git clone https://github.com/TU-USUARIO/scmc.git
```

---

## Backend

```bash
cd backend
mvn spring-boot:run
```

Servidor:

```
http://localhost:8080
```

---

## Frontend

```bash
cd frontend
npm install
npm run dev
```

Servidor:

```
http://localhost:5173
```

---

# 📡 API

## Cifrar

```
POST /api/cipher/encrypt
```

Ejemplo

```json
{
  "message": "Hola Mundo",
  "blockSize": 4,
  "permutation": [1,3,2,4],
  "shift": 5
}
```

---

## Descifrar

```
POST /api/cipher/decrypt
```

---

# 🎯 Objetivos

- Aplicar conceptos de Matemáticas Discretas.
- Implementar un sistema de cifrado reversible.
- Comprender el uso de funciones inversas.
- Relacionar teoría matemática con desarrollo de software.

---

# 📈 Posibles mejoras

- Cifrado por múltiples rondas.
- Más algoritmos de sustitución.
- Historial de operaciones.
- Exportación de resultados.
- Comparación entre algoritmos clásicos.
- Visualización matemática de las transformaciones.

---

# 👨‍💻 Autor

**Ángel David Suescun Romero**

Estudiante de Ingeniería de Sistemas e Informática.

Universidad Nacional de Colombia

---

# 📄 Licencia

Proyecto desarrollado con fines académicos para la asignatura **Matemáticas Discretas II**.
