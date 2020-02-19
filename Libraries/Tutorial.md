# Tutorial para crear librerías en Android

## Crear un módulo como android library


## En el build.gradle del proyecto añadir el siguiente classpath en el dependencies

	dependencies {
            classpath 'digital.wup:android-maven-publish:3.6.2'
	}

## Después en el build.gradle de la librería aplicar el plugin:

apply plugin: 'digital.wup.android-maven-publish'

## Y crear una publication

publishing {
    publications {
        mavenAar(MavenPublication) {
            from components.android
            groupId 'xyz.eliothmonroy'
            artifactId 'testlibrary'
            version "1.0.0"
        }
    }
}


Finalmente para publicar la librería localmente, usar gradle con el comando publishToMavenLocal


# Tutorial para implementar librerías locales en Android

