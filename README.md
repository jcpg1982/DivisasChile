# Reto Técnico - Indicadores Económicos 📊

Aplicación Android nativa que consume indicadores económicos desde la API pública [exchangerate.host](https://exchangerate.host/), con login/registro de usuarios en base local.

---

## 🚀 Tecnologías
- Kotlin  
- MVVM (Model - View - ViewModel)  
- Retrofit (consumo de API)  
- Room (persistencia de usuarios)  
- Dagger (inyección de dependencias)  
- Modularización por features  
- MPAndroidChart (gráficos)  

---

## 📦 Funcionalidades
- **Login / Registro / Logout** con persistencia en Room.  
- **Validaciones** de formulario y manejo de errores en login/registro.  
- **Primera carga**: mostrar valores de USD, EUR y CLP del día actual.  
- **Filtro por fecha**: consultar indicadores económicos en una fecha seleccionada.  
- **Gráfico de fluctuación**: mostrar evolución de los últimos 6 meses hasta la fecha seleccionada.  

---

## 🔗 API usada
Base URL:  
```kotlin
const val BASE_URL = "https://api.exchangerate.host/"
