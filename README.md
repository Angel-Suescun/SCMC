# 📋 README - Algoritmo de Encriptación SCMC

## Introducción

Este documento explica con detalle técnico cómo funciona el algoritmo de encriptación implementado en el servicio `EncryptService`. El algoritmo es **híbrido** y aplica tres transformaciones criptográficas en secuencia:

1. **Padding** (acolchamiento)
2. **Permutación** (reordenamiento)
3. **Cifrado Modular** (desplazamiento con aritmética modular)

---

## 🔑 Concepto General del Flujo

El algoritmo de encriptación sigue este flujo secuencial:

```
Mensaje Original
      ↓
  [PADDING]  → Ajusta el mensaje a múltiplos del tamaño de bloque
      ↓
[PERMUTACIÓN] → Reordena caracteres dentro de cada bloque
      ↓
[CIFRADO MODULAR] → Desplaza cada carácter usando aritmética módulo 256
      ↓
Mensaje Encriptado
```

Cada fase transforma el resultado de la anterior, acumulando complejidad criptográfica.

---

## 📊 DTOs (Estructura de Datos)

### `EncryptRequest` (Entrada)
```java
public record EncryptRequest (
  String message,              // El texto a encriptar
  Integer blockSize,           // Tamaño del bloque (ej: 4, 8, 16)
  List<Integer> permutation,   // Índices para reordenar (ej: [2,0,3,1])
  Integer shift                // Cantidad de desplazamiento (ej: 5, -3)
)
```

**Ejemplo de entrada:**
- `message` = `"Hola"`
- `blockSize` = `4`
- `permutation` = `[1, 0, 3, 2]` (intercambia pares de caracteres)
- `shift` = `3`

### `EncryptResponse` (Salida)
```java
public class EncryptResponse {
  String originalMessage;      // El texto original
  String paddedMessage;        // Después de padding
  String permutedMessage;      // Después de permutación
  String encryptedMessage;     // Resultado final
  Integer blockSize;           // Parámetro usado
  List<Integer> permutation;   // Parámetro usado
  Integer shift;               // Parámetro usado (normalizado)
  List<AuditStep> audit;       // Registro de cada fase
}
```

Retorna **todos los estados intermedios** para auditoría y debugging.

---

## 🔐 Fases del Algoritmo

### **FASE 1: PADDING (Acolchamiento)**

**Propósito:** Asegurar que la longitud del mensaje sea divisible por el `blockSize`.

**Código:**
```java
public String encrypt(String message, Integer blockSize) {
  StringBuilder paddedMessage = new StringBuilder(message);
  while (paddedMessage.length() % blockSize != 0) {
    paddedMessage.append('~');  // Carácter de relleno
  }
  return paddedMessage.toString();
}
```

**Ejemplo:**
```
Entrada: "Hola" (4 caracteres), blockSize = 4
Salida:  "Hola" (ya es múltiplo de 4, sin cambios)

Entrada: "Hola" (4 caracteres), blockSize = 8
Salida:  "Hola~~~~" (rellena a 8 caracteres con '~')
```

**Matemáticamente:**
```
long = |mensaje|
mientras (long % blockSize ≠ 0):
  mensaje += '~'
  long += 1
```

---

### **FASE 2: PERMUTACIÓN (Reordenamiento)**

**Propósito:** Reordenar los caracteres dentro de cada bloque según una secuencia de índices.

**Código:**
```java
public String encrypt(String paddedMessage, List<Integer> permutation, Integer blockSize) {
  List<String> blocks = splitIntoBlocks(paddedMessage, blockSize);
  StringBuilder result = new StringBuilder();
  
  for (String block : blocks) {
    result.append(applyPermutation(block, permutation));
  }
  return result.toString();
}

private String applyPermutation(String block, List<Integer> permutation) {
  StringBuilder result = new StringBuilder();
  for (Integer index : permutation) {
    result.append(block.charAt(index));  // Toma carácter en posición 'index'
  }
  return result.toString();
}
```

**Ejemplo paso a paso:**

Entrada: `"Hola~~~~"` (con blockSize = 4, permutation = `[1, 0, 3, 2]`)

```
Bloque 1: "Hola"
  - Índice 0 (permutation[0]=1): block[1] = 'o'
  - Índice 1 (permutation[1]=0): block[0] = 'H'
  - Índice 2 (permutation[2]=3): block[3] = 'a'
  - Índice 3 (permutation[3]=2): block[2] = 'l'
  Resultado: "oHal"

Bloque 2: "~~~~"
  - Siguiendo permutation [1,0,3,2]: ~~~~
  Resultado: "~~~~"

Salida final: "oHal~~~~"
```

**Matemáticamente (por bloque):**
```
nuevo_bloque[i] = bloque_original[permutation[i]]
```

Esto es una **permutación de índices**: reordenamos posiciones en lugar de valores.

---

### **FASE 3: CIFRADO MODULAR (Caesar Cipher Modular)**

**Propósito:** Desplazar cada carácter en el alfabeto ASCII usando aritmética módulo 256.

**Código:**
```java
public String encrypt(String data, int shift) {
  StringBuilder result = new StringBuilder();
  for (char character : data.toCharArray()) {
    int encryptedAscii = (character + shift) % 256;
    result.append((char) encryptedAscii);
  }
  return result.toString();
}
```

**Concepto:**
- Cada carácter tiene un valor ASCII (0-127 estándar, 0-255 extendido)
- Se suma el `shift` al valor ASCII
- Se aplica módulo 256 para mantener en rango válido
- Se convierte de vuelta a carácter

**Ejemplo:**

```
Entrada: "oHal~~~~", shift = 3

'o' (ASCII 111) → (111 + 3) % 256 = 114 % 256 = 114 → 'r'
'H' (ASCII 72)  → (72 + 3) % 256 = 75 % 256 = 75   → 'K'
'a' (ASCII 97)  → (97 + 3) % 256 = 100 % 256 = 100 → 'd'
'l' (ASCII 108) → (108 + 3) % 256 = 111 % 256 = 111 → 'o'
'~' (ASCII 126) → (126 + 3) % 256 = 129 % 256 = 129 → (carácter ASCII 129)

Salida: "rKdo" + carácter_129 + carácter_129 + ...
```

**Por qué módulo 256:**
```
ASCII_RANGE = 256 (valores 0-255)
Si shift = 150 y character = 200:
  (200 + 150) % 256 = 350 % 256 = 94 ✓ (mantiene en rango [0,255])
```

**Normalización del shift:**
```java
Integer normalizedShift = shiftService.normalizeShift(request.shift());
// Asegura que shift esté en rango [-255, 255] para evitar desbordamientos
```

---

## 🔄 Flujo Completo en `EncryptService`

```java
public EncryptResponse encrypt(EncryptRequest request) {
  
  // Validación inicial
  validationService.validateEncryptRequest(request);
  
  // 1. Normalizar shift
  Integer normalizedShift = shiftService.normalizeShift(request.shift());
  
  // 2. Aplicar PADDING
  String paddedMessage = paddingEncryptService.encrypt(
      request.message(),
      request.blockSize()
  );
  
  // 3. Aplicar PERMUTACIÓN
  String permutedMessage = permutationEncryptService.encrypt(
      paddedMessage,
      request.permutation(),
      request.blockSize()
  );
  
  // 4. Aplicar CIFRADO MODULAR
  String encryptedMessage = modularEncryptService.encrypt(
      permutedMessage,
      normalizedShift
  );
  
  // 5. Construir respuesta con todos los estados
  return new EncryptResponseBuilder()
      .setOriginalMessage(request.message())
      .setPaddedMessage(paddedMessage)
      .setPermutedMessage(permutedMessage)
      .setEncryptedMessage(encryptedMessage)
      .setBlockSize(request.blockSize())
      .setPermutation(request.permutation())
      .setShift(normalizedShift)
      .setAudit(audit)  // Estados intermedios guardados
      .build();
}
```

---

## 📝 Ejemplo Completo

**Entrada:**
```json
{
  "message": "AB",
  "blockSize": 4,
  "permutation": [3, 0, 1, 2],
  "shift": 5
}
```

**Paso 1 - PADDING:**
```
"AB" (2 chars) → "AB~~" (4 chars, divisible por blockSize=4)
```

**Paso 2 - PERMUTACIÓN:**
```
Bloque: "AB~~"
permutation = [3, 0, 1, 2]
  - pos 0: block[3] = '~'
  - pos 1: block[0] = 'A'
  - pos 2: block[1] = 'B'
  - pos 3: block[2] = '~'
Resultado: "~AB~"
```

**Paso 3 - CIFRADO MODULAR (shift = 5):**
```
'~' (126) → (126 + 5) % 256 = 131 % 256 = 131 → chr(131) = '…'
'A' (65)  → (65 + 5) % 256 = 70 % 256 = 70   → 'F'
'B' (66)  → (66 + 5) % 256 = 71 % 256 = 71   → 'G'
'~' (126) → (126 + 5) % 256 = 131 % 256 = 131 → chr(131) = '…'
Resultado: "…FG…"
```

**Salida:**
```json
{
  "originalMessage": "AB",
  "paddedMessage": "AB~~",
  "permutedMessage": "~AB~",
  "encryptedMessage": "…FG…",
  "blockSize": 4,
  "permutation": [3, 0, 1, 2],
  "shift": 5,
  "audit": [...]
}
```

---

## 🛡️ Propiedades Criptográficas

| Propiedad | Descripción | Nivel |
|-----------|-------------|-------|
| **Difusión** | La permutación mezcla caracteres dentro de bloques | ✅ Básico |
| **Confusión** | El cifrado modular transforma valores ASCII | ✅ Básico |
| **No-determinista** | Same input + same params = same output | ✅ Determinista |
| **Reversibilidad** | Transformación invertible (permutación inversa, shift negativo) | ✅ Sí |
| **Seguridad Criptográfica Fuerte** | ❌ NO - Es un cifrado educativo, no para producción |

---
