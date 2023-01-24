# MagicKitchen

App recetas de comida

## Funcionamiento de la app

https://user-images.githubusercontent.com/35742627/214179025-9c36e022-f507-472d-9bd7-48afd3228eb2.mp4

## Design pattern

Patrones de diseño Creacionales nos ayudan a delegar las instancias de nuestras clase, el mismo encapsula el conocimiento de las clases y oculta como se crean y se instancia.
 - Singleton

Patrones Estructurales nos ayudan a tener una base solida de estructura en nuestro código.
 - Adapter

Patrones de comportamiento nos indica como es la comunicación entre objetos
 - Observer

## Librerias externas

Para la injección de dependencias:    
 - implementation "com.google.dagger:hilt-android:2.41"
 - implementation 'androidx.test:runner:1.4.0'

Para los componentes visuales:
 - implementation 'androidx.appcompat:appcompat:1.3.0'
 - implementation 'com.google.android.material:material:1.4.0'
 - implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
 - implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
 - implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
 - implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.5.1"
 - implementation 'androidx.core:core-splashscreen:1.0.0'

Para la carga de imagenes en la aplicación:    
 - implementation "com.github.bumptech.glide:glide:4.11.0"

Para el consumo de servicio se hace uso de las librerias:
 - implementation "com.squareup.okhttp3:logging-interceptor:4.7.2"
 - implementation "com.squareup.retrofit2:converter-gson:2.9.0"
 - implementation "com.squareup.retrofit2:retrofit:2.9.0"

Para el uso del sdk de google maps:
 - implementation 'com.google.android.gms:play-services-maps:18.0.2'
 - implementation 'com.google.maps.android:android-maps-utils:2.2.0'

## Otros

Para detectar fugas de memoria en la aplicación
 - debugImplementation 'com.squareup.leakcanary:leakcanary-android:2.9.1'

## Solución de arquitectura

Se hace uso de la arquitectura MVVM con una implementación adaptada con DDD, ya que se crean varias capas donde cada un va a tener una relación y asi hacer uso de los principios SOLID.

La primera capa llamada application, es donde se encontrara la estructura del llamado de la vista a los viewmodels y se comunican através de un livedata y estados visuales como mostrar cargando, mostrar error o mostrar los datos en la vida.

La segunda capa llamada domain, es donde se encuentran todos los modelos, repositorios donde se transforma la información necesaria para enviarla a la vista, y también hace los llamados a la infraestructura para traer la información, también se encuentra toda la logica de negocio importante para el funcionamiento de la aplicación.

y por ultimo en la carpeta de infraestructure, es donde se hace la implementación de todas la dependencias de terceros como un llamado a un servicio rest o el consumo a una base de datos.

En el gradle.properties se guardan las variables para agregarlas al código productivo y asegurar las propiedades, la base del consumo de servicios se encripta en base64 y cuando se envia a la construcción del objeto del consumo de servicios se desencripta y asi se obtiene el valor original

## Buenas prácticas

Se utilizan nombres que idenfican las funcionalidades de las clases, metodos que tengan una responsabilidad unica, invertir las dependencias para no depender de una clase en concreto si no de un contrato. También se inyectan las dependencias por el contructor y las funciones para que se faciliten las pruebas unitarias en cada clase, así, se dejan solo los métodos públicos (accesibles) y los privados los cuales son propios para el funcionamiento único de la clase

## Pruebas automatizadas

Para la pruebas automatizadas se hace uso del patron When Given Then, que permite separar la prueba en tres partes donde cuando ocurra un evento dada ciertas condiciones entonces deberia ocurrir cierta respuesta, también gracias al lenguaje Kotlin se facilita el nombrado de las funciones en un lenguaje mas natural y asi de esa manera tener una fuente de la verdad y que las pruebas automatizadas sean la documentación del codigo.

Para las pruebas automatizadas se hace uso de las siguientes dependencias:
 - testImplementation 'io.kotest:kotest-assertions-core:4.6.3'
 - testImplementation "io.mockk:mockk:1.12.0"
 - testImplementation "io.mockk:mockk-android:1.12.0"
 - testImplementation 'androidx.test.ext:junit:1.1.3'
 - testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2"
 - testImplementation "androidx.arch.core:core-testing:2.1.0"


## Novedades de la plataforma

 - Android Jetpack
 - Corrutinas de kotlin  
