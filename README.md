# VendorCentral

Aplicación para la gestión de los datos de los proovedores de la división RP Fire Systems de la empresa Ruhrpumpen, lo
que permite tener una mayor eficiencia y organización de la información, teniendo una gestión mas estructurada y
centralizada de los datos de los proveedores.

## Indice

- [Requisitos del sistema](#requisitos-del-sistema)
- [Instalación y configuración](#instalación-y-configuración)
- [Ejecución de la aplicación](#ejecución-de-la-aplicación)
- [Empaquetar la aplicación](#empaquetar-la-aplicación)
- [Instalación del proyecto](#instalación-del-proyecto)
- [Estructura del proyecto](#estructura-del-proyecto)

## Requisitos del sistema

Antes de clonar y ejecutar este proyecto, asegúrate de tener lo siguiente instalado:

- **IntelliJ IDEA (Recomendado)** o cualquier otro IDE compatible con Java.
    - [Descarga IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- **JDK 21**.
    - **Recomendado**: [Temurin](https://adoptium.net/temurin/releases/?package=jdk)
    - Tambien puede ser otra distribución, incluso la que viene con IntelliJ.
- **JavaFX 21**.
    - [Descargar JavaFX 21](https://gluonhq.com/products/javafx/)
- **Scene Builder** para diseñar las interfaces gráficas.
    - [Descargar Scene Builder](https://gluonhq.com/products/scene-builder/#download)

## Instalación y configuración

### 1. Clonar el repositorio

```shell
git clone https://github.com/AitorCerecero/VendorCentral.git
cd VendorCentral
```

### 2. Abrir el proyecto en IntelliJ IDEA

1. Abre IntelliJ IDEA.
2. Selecciona "Open Project" y elige la carpeta del repositorio.
3. IntelliJ detectará automáticamente Gradle y sincronizará las dependencias.

### 3. Configurar Scene Builder en IntelliJ

1. Ve a File > Settings > Languages & Frameworks > JavaFX.
2. En Path to Scene Builder, selecciona la ubicación del ejecutable de Scene Builder.
3. Ahora puedes abrir archivos .fxml en Scene Builder con clic derecho > "Open in Scene Builder" o alternando la vista
   de Text o Scene Builder.

## Ejecución de la aplicación

Para compilar y ejecutar el proyecto, se de la siguiente manera, hay distintas maneras de hacerlo:

### 1. Configuracion semi-manual (IntelliJ IDEA)

En base a la siguiente [documentación](https://openjfx.io/openjfx-docs/) hacemos lo siguiente:

1. Vamos al **MainApplication.java** ubicado en el src del proyecto, y lo ejecutamos con `Shift + F10` en **Windows** o
   con `Control + Shift + R` en **MacOS**.
2. Nos dara error al ejecutarlo, pero nos abra creado una configuracion para ejecutar la aplicacion, llamada *
   *MainApplication** en la parte superior.
3. En esta, damos clic a `Edit Configurations...` o podemos ir a los 3 puntos de la configuracion, y luego a `Edit...`.
4. En la configuracion de MainApplication, vamos a `Modify options` y agregamos `Add VM options`.
5. Y en las VM options agregamos lo siguiente:

   Para **Windows**:
    ```text
    --module-path "\path\to\javafx-sdk-21.0.5\lib" --add-modules javafx.controls,javafx.fxml
    ```
   Para **MacOS**:
    ```text
    --module-path /path/to/javafx-sdk-21.0.6/lib --add-modules javafx.controls,javafx.fxml 
    ```
6. Finalmente damos en `Apply` y ya podemos darle en `Run`, ejecutandose con normalidad el proyecto.

### 2. Configuracion manual (IntelliJ IDEA)

1. En la parte superior del IDE, en Current File > Edit Configuration.
2. En el `+` agregamos una nueva configuración del tipo `Application`.
3. Agregamos el nombre de la configuracion, el cual puede ser `MainApplication`.
4. En modulo seleccionamos nuestro JDK 21, y en `-cp no module` seleccionamos `VendorCentral.main`.
5. En `Main class` agregamos lo siguiente:
    ```text
   com.ruhrpumpen.vendorcentral.MainApplication
    ```
6. Despues, en la configuración, vamos a `Modify options` y agregamos `Add VM options`.
7. Y en las VM options agregamos lo siguiente:

   Para **Windows**:
    ```text
    --module-path "\path\to\javafx-sdk-21.0.5\lib" --add-modules javafx.controls,javafx.fxml
    ```
   Para **MacOS**:
    ```text
    --module-path /path/to/javafx-sdk-21.0.6/lib --add-modules javafx.controls,javafx.fxml 
    ```
8. Finalmente damos en `Apply` y ya podemos darle en `Run`, ejecutandose con normalidad el proyecto.

## Empaquetar la aplicación

> [!NOTE]
> Por hacer

## Instalación del proyecto

> [!NOTE]
> Por hacer

## Estructura del proyecto

```text
/VendorCentral/
│── src/main/java/com/ruhrpumpen/vendorcentral/
│   ├── MainApplication.java   # Punto de entrada de la aplicación
│   ├── MainController.java    # Lógica de negocio
│── src/main/resources/com/ruhrpumpen/vendorcentral/
│   ├── main-view.fxml # Diseño de UI en FXML
│── build.gradle.kts      # Configuración de Gradle
│── README.md             # Este archivo 
```
