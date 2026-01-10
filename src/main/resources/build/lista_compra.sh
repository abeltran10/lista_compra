#!/bin/bash

# Ruta de los JARs
JAVA_FX=/usr/share/openjfx/lib
# Ruta típica de las librerías nativas en Debian 11/12
NATIVE_LIBS=/usr/lib/x86_64-linux-gnu/jni

java \
-Djava.library.path="$NATIVE_LIBS" \
--module-path "$JAVA_FX" \
--add-modules javafx.controls,javafx.fxml,javafx.graphics \
-jar "$HOME/lista_compra-v1.0.1.jar"



