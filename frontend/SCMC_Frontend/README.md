# 📘 Frontend SCMC

## Descripción

Frontend desarrollado con **React, TypeScript y Vite** para la visualización interactiva del proyecto **SCMC (Sistema de Cifrado Modular por Composición)**.

La aplicación permite presentar los fundamentos matemáticos, la arquitectura del algoritmo, la implementación desarrollada en Java y un simulador conectado al backend para realizar procesos de cifrado y descifrado.

---

# 🚀 Tecnologías Utilizadas

- React
- TypeScript
- Vite
- React Router DOM
- CSS3
- Lucide React

---

# 📂 Estructura del Proyecto

```text
src/

├── shared/
│   ├── components/
│   │   ├── CodeBlock/
│   │   ├── Timeline/
│   │   ├── Navbar/
│   │   └── Footer/
│   │
│   ├── layouts/
│   │   └── MainLayout/
│   │
│   └── styles/
│
├── modules/
│   │
│   ├── home/
│   │
│   ├── mathematics/
│   │
│   ├── algorithm/
│   │
│   ├── implementation/
│   │
│   └── simulator/
│
├── routes/
│
├── App.tsx
│
└── main.tsx
```

---

# 🏗 Arquitectura

El proyecto utiliza una arquitectura modular basada en funcionalidades.

Cada módulo contiene sus propias páginas, secciones y componentes específicos, mientras que los elementos reutilizables se encuentran dentro del directorio `shared`.

```text
module/

├── pages/
├── sections/
├── components/
└── styles/
```

### Ventajas

- Separación de responsabilidades.
- Mayor mantenibilidad.
- Reutilización de componentes.
- Escalabilidad del proyecto.
- Organización clara del código.

---

# 🧩 Componentes Compartidos

## CodeBlock

Componente reutilizable para mostrar fragmentos de código.

### Ejemplo

```tsx
<CodeBlock
    language="java"
    title="EncryptService.java"
    code={encryptServiceCode}
/>
```

### Funcionalidades

- Visualización de código.
- Scroll horizontal automático.
- Diseño responsive.
- Soporte para múltiples lenguajes.

---

## Timeline

Componente utilizado para representar procesos secuenciales.

### Ejemplo

```tsx
<Timeline
    items={timelineItems}
/>
```

### Estructura de datos

```tsx
const timelineItems = [

    {
        title: "Padding",
        description: "Ajuste del tamaño del bloque"
    },

    {
        title: "Permutación",
        description: "Reordenamiento de caracteres"
    }

];
```

### Funcionalidades

- Línea temporal vertical.
- Diseño responsive.
- Fácil reutilización.

---

# 📄 Módulos Implementados

## Inicio

Presentación general del proyecto SCMC.

Incluye:

- Hero principal.
- Objetivos.
- Descripción general.
- Navegación hacia las demás secciones.

---

## Fundamentos Matemáticos

Explica los conceptos matemáticos utilizados por el algoritmo.

Temas:

- Aritmética modular.
- Teoría de conjuntos.
- Permutaciones.
- Funciones biyectivas.
- Aplicaciones criptográficas.

---

## Algoritmo

Describe el funcionamiento conceptual del sistema SCMC.

Incluye:

- Flujo general.
- Entradas y salidas.
- Ejemplo de ejecución.
- Propiedades matemáticas.

---

## Implementación

Explica la implementación realizada en Java Spring Boot.

Secciones:

- Padding.
- Permutación.
- Cifrado Modular.
- Normalización del Shift.
- EncryptService.

Cada sección incluye:

- Fragmentos de código.
- Explicación funcional.
- Ejemplos prácticos.
- Relación matemática.

---

## Simulador

Permite interactuar con la API del backend.

Funciones:

- Encriptar mensajes.
- Desencriptar mensajes.
- Configurar tamaño de bloque.
- Configurar permutación.
- Configurar desplazamiento.
- Visualizar resultados.

---

# 🔄 Integración con Backend

La aplicación consume los servicios REST desarrollados en Spring Boot.

### Encriptación

```http
POST /api/encrypt
```

### Ejemplo de solicitud

```json
{
  "message": "Hola Mundo",
  "blockSize": 4,
  "permutation": [1,3,2,4],
  "shift": 5
}
```

### Ejemplo de respuesta

```json
{
  "encryptedMessage": "..."
}
```

---

# 🎨 Diseño de Interfaz

La interfaz utiliza una estética tecnológica inspirada en:

- Glassmorphism.
- Diseño futurista.
- Paleta oscura.
- Elementos neón.

### Características

- Responsive Design.
- Experiencia móvil optimizada.
- Componentes reutilizables.
- Animaciones suaves.
- Navegación intuitiva.

---

# ⚙️ Instalación

## Clonar repositorio

```bash
git clone <repository-url>
```

## Instalar dependencias

```bash
npm install
```

## Ejecutar entorno de desarrollo

```bash
npm run dev
```

## Compilar para producción

```bash
npm run build
```

## Vista previa de producción

```bash
npm run preview
```

---

# 📊 Objetivo Académico

Este frontend fue desarrollado como parte del proyecto académico **SCMC (Sistema de Cifrado Modular por Composición)** para la asignatura de **Matemáticas Discretas**.

Su propósito es:

- Explicar los fundamentos matemáticos del algoritmo.
- Visualizar la implementación desarrollada.
- Facilitar la comprensión del proceso criptográfico.
- Permitir la experimentación mediante un simulador interactivo.

---

# 👥 Equipo de Desarrollo

**SCMC — Sistema de Cifrado Modular por Composición**

Proyecto académico desarrollado para la asignatura de **Matemáticas Discretas**.

**Universidad Nacional de Colombia**