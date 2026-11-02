# SIICOG 27

Sistema de Contabilidad Gubernamental, ejercicio 2027. Se construyó a partir del árbol de trabajo
de **difem26**: el commit `187183a` más 17 archivos con cambios sin commit (al 2026-10-19, módulos de
adquisiciones, unidades de medida y reportes CONAC). Conserva sus pantallas, reportes y reglas
contables; lo que cambia es todo el stack, que ahora es Java 25 con las versiones actuales de cada
componente.

## Stack

| Componente | difem26 | siicog27 |
|---|---|---|
| Java | 8 | **25** |
| Plataforma | Java EE 7 (`javax.*`) | **Jakarta EE 11** (`jakarta.*`) |
| Servidor | WildFly 9 (WAR) | **Tomcat 11.0.27** embebido, o WAR en Tomcat 11 |
| Spring | Spring 4.2 + XML | **Spring Boot 4.1.2** / Spring Framework 7.0.10 |
| Seguridad | Spring Security 4.1 (XML) | **Spring Security 7.1.2** (Java config) |
| JPA | Hibernate 4.3 / Spring Data 1.9 | **Hibernate 7.4.13** / Spring Data JPA 4.1.2 |
| Vista | JSF 2.2 (Mojarra 2.2) | **Jakarta Faces 4.1** (Mojarra 4.1.18) vía **JoinFaces 6.1.2** |
| Componentes | PrimeFaces 5.2 + Extensions 3.2 | **PrimeFaces 16.0.1** + Extensions 16.0.1 |
| Utilidades JSF | OmniFaces 2.3 | **OmniFaces 5.5.5** |
| Reportes | JasperReports 6.20 + functions 6.3 (JAR suelto) | **JasperReports 7.1.0** modular |
| Groovy (reportes) | 2.4.7 | **5.1.3** |
| Consultas tipadas | QueryDSL 3.7 (`com.mysema`) | **QueryDSL 7.7** (fork de OpenFeign) |
| Excel | POI 4.1.2 | **POI 5.5.1** |
| Base de datos | DB2 por JNDI | DB2 por `spring.datasource.*`, driver **jcc 12.1.5.0** |

## Diseño SIICOG

La interfaz sigue el prototipo `sicog-pantallas.html` (paleta ITBM, tipografía IBM Plex). Fase 1, ya
aplicada a todo el sistema:

| Pieza | Archivo |
|---|---|
| Marco: barra superior, menú lateral, contenido y pie | `WEB-INF/layout/layout.xhtml`, `header.xhtml`, `menu.xhtml`, `footer.xhtml` |
| Estilos del marco y ajustes sobre las 660 vistas | `resources/siicog/siicog.css` |
| Tema de PrimeFaces `siicog` (saga-blue con las variables de la paleta SIICOG) | `resources/primefaces-siicog/theme.css`, generado por `herramientas/tema/generar_tema_siicog.py` |
| Login e inicio | `login.xhtml` + `resources/siicog/login.css`, `home.xhtml` |
| Comportamiento (menú colapsable, página activa, retícula, recordar usuario) | `resources/siicog/siicog.js` |
| Logos y fuentes IBM Plex (licencia SIL OFL, incluidas en el proyecto) | `resources/siicog/img`, `resources/siicog/fonts` |

- **Menú lateral.** Sale del mismo `TC_MENU_NIVELES`. `MenuMB.getGrupos()` reparte los módulos de primer
  nivel en Contabilidad, Presupuesto y finanzas, Reportes y Administración y les asigna un icono por
  nombre; un módulo nuevo que no esté en esa tabla aparece en "Otros módulos".
- **Accesos rápidos del inicio.** Pólizas, Catálogo de cuentas, Balanza, Control presupuestario e
  Ingresos propios; solo se muestran los que el usuario tiene en su menú.
- **Ejercicio fiscal** de `CONCTB.ANOEMP` del sector; **versión** del `pom.xml`; **minutos de sesión** de
  `server.servlet.session.timeout`.
- `herramientas/tema/aplicar_paleta_vistas.py` cambia el dorado y el guinda de difem26 que las vistas
  tenían en estilos en línea.
- **Iconos de jQuery UI.** Las vistas usan 48 iconos `ui-icon-*` (unas 1,800 veces: botones con
  `icon="ui-icon-disk"`, X de borrar, lápices). PrimeFaces 16 solo les da glifo dentro de sus componentes,
  así que se veían vacíos. `resources/siicog/iconos-jqueryui.css` (generado por
  `herramientas/tema/generar_iconos_jqueryui.py`) los muestra con su equivalente de PrimeIcons.
- **Glyphicons de Bootstrap** (lupas de 13 vistas): se redeclara su fuente en `siicog.css` con la ruta
  correcta.

Fase 2 (12 de 13; Presupuesto queda para después): rediseñar una por una las 13 pantallas del prototipo con la estructura de tarjetas,
sin cambiar sus beans. Las piezas comunes (`sg-page-h`, `sg-card`, `sg-field`, `sg-totals`,
`sg-tabla-ancha`…) están en `siicog.css`, sección *Piezas de pantalla*.

| Pantalla | Vista | Estado |
|---|---|---|
| Mantenimiento de Pólizas | `views/polizas/mantenimientoPolizas3.xhtml` | Rediseñada. Mismos 100 enlaces al bean, ids y `update`; los diálogos conservan su estructura |
| Catálogo de cuentas | `views/catalogs/catalog_maintenance_accounts2.xhtml` | Rediseñada. Mismos `update`, listeners e ids; en modo lectura: nivel con insignia, chips Últ. nivel / Nat., cifras y códigos en monoespaciada |
| Centros de costo | `views/catalogs/catalog_dependencies_central.xhtml` (opción "Dependencias") | Rediseñada. Mismos `update`, listeners e ids; editar y borrar en la columna Acciones |
| Saldos iniciales | `views/utilerias/saldosiniciales.xhtml` | Rediseñada. Mismos `update`, listeners e ids, y las clases que usa `saldos_iniciales.js` para mostrar los campos paso a paso |
| Cierre contable | `views/polizas/afectacionPolizas.xhtml` (opción "Afectación") | Rediseñada. Mismos `update`, listeners e ids; el estado de los pasos sale de `afectacionPolizaMB.activarButton` |
| Pólizas por usuario | `views/consultas/polizasByUsuario.xhtml` | Rediseñada. La vista previa (texto del bean) se muestra además como tabla con resumen (`siicogTablaDeTexto` en `siicog.js`). Se corrigieron dos fallas previas: el `onclick` llamaba `show()` sobre un área de texto y el `update` apuntaba a un componente que no existía hasta la primera vista previa (ahora actualiza el contenedor `previaTexto`) |
| Ingresos propios | `views/ingresos/ingresoConsulta.xhtml` | Rediseñada. Mismos `update`, listeners e ids; lista y detalle en tarjetas (lado a lado desde 1600px), status como chip y total al pie |
| Control presupuestario | `views/controlPresupuestario/control.xhtml` | Rediseñada sin tocar componentes (ya tenía la estructura FAM/PAD > área > acciones > contratos): encabezado y estilos acotados `sg-control`. El acordeón de áreas y las pestañas de acciones no se pudieron ver con datos: `TC_ACCIONES`, `TW_ACCION_CONTRATO` y `TW_OBRAS` están vacías en `difdb26` |
| Datos de impresión | `views/utilerias/datosimpresion.xhtml` | Rediseñada la primera pestaña (Ente público y Firmas de los reportes con columna Estado: Libre / Sin firmante / Asignada). Las pestañas Firmas por reporte, Logos y Puestos conservan su contenido con el estilo general: el prototipo todavía no las diseña |
| Balanza de comprobación | `views/reports/RF00914.xhtml` | Filtros (Mes con nombre, Cuentas con saldo 0 No/Sí), Generar y exportación Excel/TXT en una tarjeta; el PDF se muestra en el visor oscuro del prototipo, con aviso mientras no hay reporte. Generar ya produce el PDF con Jasper 7: enero de 2026 sale en 9 páginas con datos de difdb26 |
| Usuarios | `views/users/users.xhtml` | Nuevo / Editar / Eliminar en el encabezado (mismos ids y acciones; Eliminar en rojo), tabla con User ID y Usuario CTL en monoespaciado y columna Estado (chip Activo / Eliminado en lugar del ícono), pie con total de usuarios. Diálogos con textos acentuados (Contraseña, Rol, Configuración de menús) e íconos nuevos. Del prototipo no se agregan búsqueda, filtro por tipo ni «Restablecer contraseña»: el bean no los tiene (la búsqueda del modelo solo compara el User ID exacto) |
| Contra-recibos | `views/contratos/contrarecibos.xhtml` | Encabezado con accesos a Proveedores y Folios; «Datos del contra-recibo» (chip Nuevo / Edición) y «Facturas» lado a lado, con total de facturas; lista de contra-recibos con buscador en la tarjeta, chip de Estado (Vigente / Cancelado), acciones con íconos y reporte del día + Excel al pie; el PDF se muestra en el visor oscuro. Corregido: la espera usaba `PF('blockUIWidget').show()` (no existe; ahora `block()`/`unblock()`), la validación de fecha usaba un `g` inexistente (ahora un aviso propio) y Guardar no actualizaba los mensajes. En difdb26 no hay proveedores ni folio del año, así que no se pudo guardar un contra-recibo de prueba |
| Presupuesto | — | Pendiente |

**Catálogos homologados** (2026-10-30, `herramientas/tema/homologar_catalogos.py`). Todas las vistas de
`views/catalogs` y las tres de catálogos de Contratos (Proveedores, Personal y Áreas, Giros y Subgiros)
siguen el diseño de Dependencias:

- Encabezado con migas y título, y «Nuevo registro» a la derecha.
- Tarjeta «Buscar …» con las etiquetas arriba de los campos, y Buscar / Limpiar.
- Tarjeta de la tabla:
  - Claves en monoespaciada.
  - Columna «Acciones» con el lápiz y la X roja.
  - Pie «Exportar:» con Excel · TXT · PDF · CSV como botones.
- Diálogos de borrar con Cancelar / Aceptar a la derecha.

El script hace la parte mecánica: copia cada campo y botón tal cual, con los mismos id, EL, validadores,
`p:ajax`, `update` y `widgetVar`. Se rehicieron a mano las vistas con otra estructura:

- Matriz de ingresos: el acordeón pasa a dos tarjetas, y la selección de cuenta ahora actualiza
  `:form:detalles`.
- Matriz de Indicadores - Nuevo: «Correcto» pasa a «Generar».
- Las tres de Contratos: tarjeta de datos y tarjeta de lista con buscador, como Contra-recibos.
- Las leyendas (Naturaleza, Periodo, Tipo de indicador) quedan como nota en la tarjeta de búsqueda.

«Nuevo registro» se comporta igual que antes: en Naturaleza de Gasto, Flujo de Efectivo, Estructura
Programática y Fuentes de Financiamiento sigue pidiendo la contraseña de administrador.
`catalog_maintenance_suppliers_contractors.xhtml` no se tocó, porque el menú ya tiene la versión de
Contratos.

Correcciones de paso:

- En Proveedores, el «NO» del diálogo de borrar cerraba un diálogo que no existe.
- En Personal, la columna «Teléfono» mostraba la unidad administrativa; ahora se llama así.
- En Niveles de Cuentas, «Acredora» pasa a «Acreedora».
- El PDF del Clasificador por fuentes de financiamiento no se generaba: `FuenteFGobiernoMB` pide
  `reports/fuentesfGobierno.jasper`, que no existía, tampoco en difem26. Se creó `fuentesfGobierno.jrxml`
  (Jasper 7, carta) a partir de `CatalogoCuentasContables.jrxml`, con encabezado institucional, ejercicio y
  las columnas de `FUENTESF_GOBIERBO`, y se compiló su `.jasper`. Hoy el reporte sale en 5 páginas.

Verificado:

- Las 28 vistas abren sin errores de JavaScript.
- En Programas, Naturaleza de Gasto, Tipos de Cuentas, Clasificador por rubros, las matrices, Personal y
  Giros: Buscar, Limpiar, paginar, editar y cancelar, el diálogo de borrar, el de contraseña y Generar
  funcionan.
- Las 8 descargas probadas (Excel, TXT, PDF, CSV) llegan.

Dos vistas fuera del menú ya fallaban antes del cambio:

- `catalog_matrix_indicators`: falta la columna `INSTRUMENTO` en la base (`SQLCODE=-206`).
- `catalog_topics_progress`: el diálogo usa `#{catalogTopicsProgressMB.bean}`, que el bean no tiene.

En el Clasificador por fuentes de financiamiento hay textos como «AutÃ³nomos»: así están guardados en la
base.


Fase 3 (en curso): estandarizar las pantallas de **Reportes** (`views/reports`, 325 vistas) con el marco de
la Balanza de Comprobación (`RF00914.xhtml`). Lo hace `herramientas/tema/estandarizar_reportes.py`
(`--aplicar --respaldo DIR`); solo cambia presentación:

- El `p:panel` principal (con `header`) pasa a `h:panelGroup` `sg-page sg-reporte` con encabezado
  «Reportes» + título y una tarjeta (`sg-card-b sg-rep`) con el contenido. Conserva el id.
- Los enlaces de exportación con imagen (excel/txt/pdf/csv.png) pasan a botones `sg-btn-sm` con ícono y
  texto; «Correcto» pasa a «Generar» con ícono; el panel `inlinePdf` usa el visor oscuro (`sg-visor`).
- Las rejillas de filtros de difem26 se conservan; en `siicog.css` (`.sg-rep`) se alinean a la izquierda,
  sin bordes, y lo que no cabe se desplaza dentro de la tarjeta.

Resultado (2026-10-23): 322 vistas convertidas, con los mismos EL, ids, listeners, `update`, `process`
y `widgetVar` que antes, XML bien formado y comentarios intactos. Las 104 del menú de `itbmadminc` se
abrieron en Chrome sin errores de JavaScript ni desbordes. No se convirtieron (quedan para revisar a mano):
`cuentapublica/ingreso_egreso_cuenta_publica.xhtml` (no tiene panel con título) y
`tabulador-sueldos-trimestral-reporte.xhtml` (su panel tiene `widgetVar`).

**Visor oscuro como el de la Balanza** (2026-10-24, `herramientas/tema/visor_reportes.py`).
`inlinePdf` llevaba la clase `sg-visor`, pero no se veía hasta generar y quedaba dentro del cuerpo de la
tarjeta. Ahora cada vista lo tiene igual que la Balanza:

- `inlinePdf` va dentro de un `h:panelGroup id="visor"` al pie de la tarjeta, siempre visible.
- Mientras no hay reporte, el visor muestra el aviso «Elige los filtros y pulsa Generar para ver el
  reporte».
- Los `update` que apuntaban a `inlinePdf` ahora apuntan a `visor`.

Resultado: se convirtieron 226 vistas, con XML válido y el mismo EL e ids salvo el aviso y el visor, y
las 104 del menú abren sin errores de JavaScript.

No llevan visor las vistas que solo exportan (Excel o TXT) o que tienen el visor comentado. Tampoco
`cuentapublica/cuenta_publica.xhtml`, cuyos visores están dentro de un acordeón.

**Una sola ventana de espera** (2026-10-24, `herramientas/tema/unificar_espera.py`). difem26 tenía tres
ventanas distintas:

- Al generar: el `pe:blockUI` que cada vista declaraba con su imagen y texto (`indice.png`,
  `please_wait.gif`, «Por favor espere...», «Please wait...»).
- Al descargar Excel o TXT: el diálogo «Descargando...» del layout, con una barra animada.
- En cargas y procesos: diálogos «Status» propios.

Ahora la única es el `pe:blockUI` `blockUIWidget` del layout: una tarjeta con un reloj de arena y un
anillo que gira, el título «Procesando su solicitud» y el texto «Esto puede tardar unos segundos. Por
favor espere.». Los estilos son `.sg-espera` en `siicog.css`. El giro se mantiene aunque el sistema tenga
activado «reducir movimiento».

- Se quitó la declaración propia de 369 vistas y los 21 diálogos `statusDialog`.
- `start()`/`stop()` de `gem.js`, que usan las descargas con `PrimeFaces.monitorDownload`, y las llamadas
  a `PF('statusDialog')` ahora usan `PF('blockUIWidget').block()`/`.unblock()`.
- **Una vista nueva no debe declarar su propio `pe:blockUI`; basta con llamar a `PF('blockUIWidget')`.**
- **Caché del navegador.** `siicog.css`, `siicog.js` y `gem.js` se piden con `?v=#{siicog.recursos}`,
  que cambia en cada arranque. Antes solo llevaban la versión del pom, o nada en el caso de `gem.js`. Así
  el navegador podía conservar el `gem.js` anterior, cuyo `start()` llamaba al diálogo ya retirado; el
  error cancelaba el envío y las descargas Excel/TXT no hacían nada. Además, `start()`/`stop()` ya no
  fallan si la ventana no existe.

Verificado: el recorrido del menú sigue igual (187 OK), las 104 vistas de reportes y las 24 que usaban
`statusDialog` abren sin errores de JavaScript, y Generar y Excel muestran la misma ventana.

Las 4 vistas cuyo `p:remoteCommand` se llama `onload` (`catalog_maintenance_accounts`, `cuenta_csaldos`,
`edo_a_e_presup_integrado`, `edo_a_i_presup_integrado`) usan `start()`/`stop()`. Ese nombre lo vuelve
`window.onload`, y con jQuery 4 la carga de la página puede terminar antes de que se cree `blockUIWidget`
en `$(function)`. `PF('blockUIWidget').block()` fallaba y no se pedían los datos iniciales; ya pasaba así
en difem26.

**Orden de los botones** (2026-10-25, `herramientas/tema/orden_botones.py`). En todas las vistas el
orden es: 1. Generar, 2. Excel, 3. TXT, 4. los demás (PDF, CSV, Vista previa...), en su orden original.
«Correcto», el Generar de Cuenta pública y de los reportes municipales, cuenta como Generar. El script
solo intercambia botones que comparten el mismo contenedor, o celdas con un botón cada una, así que las
columnas de los `panelGrid` no cambian; no toca los botones de diálogos ni los ocultos. Se reordenaron 135
vistas; `Mpbrm08c` se ajustó a mano porque su Excel estaba fuera del grupo. Verificado en el navegador:
las 139 vistas (las reordenadas y las 4 de `onload`) abren sin errores de JavaScript, los botones aparecen
en ese orden y las descargas siguen funcionando.

Vistas de reportes que ya fallaban y no están en el menú de `itbmadminc` (errores de bean o componente,
no del marco): `ServiciosPersonalesCategoria`, `eaepecaldfTxt`, `eaepecfldfTxt` (propiedad `reporte` que
el bean no tiene), `proteccionCivil` (`idAnio` en `CompetenciaLaboralGenericDTO`), `cldc` (valor de la
tabla), `conciliacion5000vs8000Detalle` / `Global` (`Setter not found for property columns`) y
`reporte50`, `reporte54`, `reporte155` (`DataTable#rowKey` obligatorio en PrimeFaces 16).

Al rediseñar, `p:rowEditor` debe quedar como hijo directo de su celda: PrimeFaces 16 escucha los clics del
lápiz con el selector `td > div.ui-row-editor > a`.

### Apariencia (temas de colores)

Panel `views/administracion/apariencia.xhtml` (2026-11-01). Lo abre el botón **Apariencia** de la barra
superior, que solo ven los administradores (`ROLE_ADMIN`). `SecurityConfig` exige ese rol para
`/views/administracion/**`, y `AparienciaService.guardar` lo vuelve a comprobar. El tema elegido vale para
todos los usuarios, también en el login.

- **Temas:** Institucional (el de siempre), Guinda, Bosque, Grafito y Personalizado. El personalizado se
  arma con un color principal y uno de acento; `Paleta` deriva los tonos y rechaza los colores con poco
  contraste (texto blanco sobre el principal por debajo de 4.5:1, o un acento casi igual al principal).
- **Cómo se pinta:** cada tema solo redefine las variables de `siicog.css` (`--marino` es el color
  principal y `--cian` el de acento en todos los temas) con `data-sg-tema` en `<html>`. Para que botones,
  tablas y diálogos de PrimeFaces cambien también, `generar_tema_siicog.py` ahora escribe en `theme.css`
  `var(--marino,#001633)`… en lugar de colores fijos. Si se regenera el tema, conservar ese mapeo. Un
  color de marca nuevo en el CSS debe usar las variables, no el valor.
- **Dónde se guarda:** `TC_PARAMETROS`, clave `SIICOG_APARIENCIA`; el valor es
  `tema=…;principal=#…;acento=#…;usuario=…;fecha=…`. La aplicación crea el renglón la primera vez que se
  guarda. Sin renglón, o con un valor dañado, se usa Institucional. Se lee de memoria y se refresca de la
  base cada 30 s, por si otra instancia lo cambió.
- **Clientes conectados:** `siicog.js` consulta `GET /apariencia` (`AparienciaServlet`, público, JSON)
  al volver a la pestaña y cada minuto, y cambia el tema sin recargar. La consulta va sin cookies, para
  no mantener viva la sesión.
- Pruebas: `AparienciaTest`. Verificado en el navegador con `itbmadminc`: elegir y aplicar cada tema,
  aviso con un principal claro, una segunda pestaña cambia sola, y Catálogos, Usuarios (con el diálogo),
  el inicio y el login con el tema Guinda, sin errores de JavaScript. Al terminar se dejó Institucional.

## Compilar y ejecutar

Requiere JDK 25 y Maven 3.9.

```bash
mvn clean verify                     # compila, valida los 232 repositorios y empaqueta
java -jar target/siicog27.war        # Tomcat embebido en http://localhost:8080/siicog27
```

El WAR también se puede desplegar en un **Tomcat 11** externo o en **WildFly 41** (ver abajo).

**Eclipse.** Si se importa el proyecto, Eclipse compila en `target/classes` sin el procesador de
anotaciones de QueryDSL. Las clases quedan con «Unresolved compilation problems» (`QTcImagenesEntAdmin
cannot be resolved`) y el inicio de sesión falla. Para evitarlo, active *Maven → Annotation Processing →
Automatically configure JDT APT* en Eclipse, o empaquete siempre con `mvn clean package`.

Variables de entorno:

| Variable | Uso | Por defecto |
|---|---|---|
| `SIICOG27_DB_URL` | URL JDBC de DB2 | `jdbc:db2://localhost:50000/SIICOG27` |
| `SIICOG27_DB_USER` / `SIICOG27_DB_PASSWORD` | credenciales | — |
| `SPRING_DATASOURCE_JNDI_NAME` | usar un recurso JNDI de Tomcat en lugar de la URL | — |
| `SIICOG27_DB_POOL` | tamaño del pool | 20 |
| `SIICOG27_PORT` | puerto HTTP | 8080 |
| `SIICOG27_LOG_DIR` | carpeta de logs | `logs` |
| `SIICOG27_FACES_STAGE` | `production` / `development` | `production` |
| `SIICOG27_CONTENIDO_WEB` | con `java -jar`, carpeta donde se expande el contenido web (reportes) | `%TEMP%\siicog27-web` |

El perfil `dev` (`--spring.profiles.active=dev`) activa el SQL en el log y el modo desarrollo de Faces.

> **Ruta con espacios.** `mvn spring-boot:run` falla si el repositorio Maven está en una ruta con
> espacios (p. ej. `C:\Users\Nombre Apellido\.m2`). Es un problema de Tomcat al leer los JAR desde
> el repositorio. Use `java -jar target/siicog27.war`, o un `-Dmaven.repo.local` sin espacios.

### Desplegar en WildFly 41

Probado con WildFly 41.0.1.Final sobre Java 25 (2026-10-24): el mismo recorrido del menú da el mismo
resultado que con `java -jar` (187 opciones OK; las 12 con 404 son las pendientes) y las pantallas
probadas en Chrome (reportes, Usuarios, Contra-recibos) no marcan errores.

1. `mvn clean package` y copiar `target/siicog27.war` a `standalone/deployments/`.
2. Definir la conexión antes de arrancar WildFly: `SIICOG27_DB_URL`, `SIICOG27_DB_USER` y
   `SIICOG27_DB_PASSWORD` como variables de entorno, o un datasource de WildFly y
   `SPRING_DATASOURCE_JNDI_NAME`.
3. Memoria: el valor de fábrica (`-Xmx512M` en `bin/standalone.conf.bat`) no alcanza para el arranque.
   En el WildFly de esta máquina se subió a `-Xms512M -Xmx2048M` (respaldo en
   `standalone.conf.bat.original`).
4. Filtro de deserialización: WildFly limita los arreglos a 100 000 elementos (`maxarray` en
   `bin/jdk.serialFilter`). El `.jasper` de `GlobalRangoMeses` (reporte `RF009120`) trae uno de más de
   200 000 y no cargaba. En esta máquina se subió `maxarray` a 1 000 000, sin cambiar los demás límites
   ni las clases bloqueadas; el original está en `jdk.serialFilter.original`.

En esta máquina, los pasos 1 y 2 los hace `iniciar-wildfly.bat` después de `mvn package`. El script
copia el WAR, arranca WildFly con Java 25 y define las variables `SIICOG27_DB_*`; las que ya estén
definidas no las cambia. La contraseña no está en el script: la lee al arrancar del datasource
`DIFEMDS_26` de WildFly 9, o la pide si no la encuentra. `detener-wildfly.bat` lo detiene, y también
se puede usar Ctrl+C en la ventana. Si `jboss-cli.bat` se usa directo, necesita `JAVA_HOME` con
Java 25.

Lo que hace funcionar el WAR en WildFly (con `java -jar` o Tomcat no estorba):

- `WEB-INF/jboss-deployment-structure.xml`: el WAR trae su propio Faces, Weld (CDI), Hibernate,
  validación y logging, configurados por Spring Boot; se excluyen los subsistemas de WildFly que harían
  lo mismo y los módulos que publican extensiones CDI (el Weld del WAR las encontraría y fallaría). Expone
  `jdk.net`, que usa el driver de DB2.
- `WEB-INF/jboss-web.xml`: ruta `/siicog27`.
- `WEB-INF/web.xml`: los `context-param` de Faces y PrimeFaces. Spring Boot aplica
  `server.servlet.context-parameters` de `application.yml` solo al Tomcat embebido; sin ellos en
  `web.xml`, WildFly no omitía los comentarios de las vistas (evaluaba el EL comentado, que llegaba a
  generar reportes al abrir una pantalla) ni usaba el tema `siicog`. **Si se cambia uno, cambiar los dos.**
- `config/VfsBeanArchiveHandler`: WildFly entrega los JAR con URLs `vfs:`, que Weld no sabe leer; sin
  él no se registran los beans de OmniFaces y PrimeFaces y OmniFaces no arranca.
- `config/WebConfig`: el bean de Tomcat solo se carga si Tomcat está presente.
- `pom.xml`: `spring-boot-web-server` y `tomcat-embed-el` van dentro del WAR (JoinFaces los usa también
  fuera de Tomcat).

## Qué se migró y cómo

Las herramientas de migración están en `herramientas/migracion/` y se pueden volver a ejecutar:

- `migrar_java.py`: paquetes `javax` → `jakarta`, managed beans → beans de Spring, PrimeFaces,
  QueryDSL, log4j → SLF4J y Spring Data.
- `migrar_vistas.py`: namespaces de Faces 4 y componentes retirados de PrimeFaces.
- `corregir_spring_data.py`: `findOne` / `delete` / `save`, guiado por los errores de javac.
- `migrar_atributos_pf16.py`: atributos de PrimeFaces 5 que cambiaron en la 16. `p:panelGrid` pasa a
  `layout="tabular"` (en PF16 el valor por defecto es `grid`, que cambia el aspecto y falla con columnas
  que no dividen 12), `autoUpdate` → `<p:autoUpdate/>`, `p:fileUpload` (`listener`, `dragDrop`,
  `<p:validateFile/>`), `p:column selectionMode` → `selectionBox` y `p:ajax execute` → `process`.
  También `columns="0"` en los `p:panelGrid` que arman sus filas con `p:row` (en PF16 `columns` vale 12
  por defecto y los junta en una celda), `filterMatchMode` con mayúsculas exactas (`startsWith`) y
  `layout="grid"` → `"responsive"` en `p:selectOneRadio` / `p:selectManyCheckbox`.
- **`process` sin el propio botón.** En PrimeFaces 16 un `p:commandButton` con `process="tabla"` ya no se
  procesa a sí mismo, así que su `actionListener` no se ejecuta. Pasaba en Usuarios (Nuevo y Editar no
  abrían su diálogo); se corrigió con `process="@this objects"`. Se revisaron todas las vistas: no queda
  otro botón con `action`/`actionListener` y un `process` que no lo incluya.

### Configuración

| difem26 | siicog27 |
|---|---|
| `web.xml` + `applicationContext.xml` | `Siicog27Application` + `application.yml` |
| `applicationContext-jpa.xml` (JNDI `jdbc/DIFEMDS_26`) | `config/PersistenceConfig` + `spring.datasource.*` |
| `applicationContext-security.xml` | `config/SecurityConfig` (mismas reglas, login, logout, sesiones) |
| `conf-properties.xml`, `validators/*.xml` | se cargan igual desde `config/WebConfig` |
| `apache-deltaspike.properties` (DeltaSpike) | `front.properties`, leído por `FrontProperty` sin DeltaSpike |
| `persistence.xml` | eliminado: se escanea `business.domain`, como antes |

`web.xml` sigue existiendo, reducido: Tomcat externo lo usa y OmniFaces lee de ahí las páginas de error.

### Managed beans → beans de Spring

Faces 4 eliminó `@ManagedBean`. Los 528 managed beans pasaron a ser beans de Spring con **el mismo
nombre que tenían en JSF**, así que las expresiones `#{...}` de las 665 vistas no cambian.

- `@ViewScoped` → `@Scope("view")` (lo implementa JoinFaces), `@SessionScoped` → `@Scope("session")`.
- Las 1 921 `@ManagedProperty("#{x}")` → `@Value("#{x}")`, que se resuelve por nombre igual que antes.
- Se verificó de forma estática que cada `#{x}` inyectado corresponde a un bean existente.

### Base de datos

- **Altas con id 0.** El código crea registros nuevos con `setId(0L)` (p. ej. renglones de póliza).
  Hibernate 4 los insertaba; Hibernate 7 lanza `StaleObjectStateException`. `CompatibilidadRepositoriosFactoryBean`
  pone el id en null en `save*` cuando la entidad tiene id generado con valor 0 (ninguna tabla tiene id 0).

- Se mantienen las estrategias de nombres de Hibernate de difem26. Sin esto, Spring Boot cambiaría
  los nombres de columna a snake_case.
- `GenerationType.AUTO` → `IDENTITY` en 2 entidades. En Hibernate 4 sobre DB2, AUTO equivalía a
  IDENTITY; en Hibernate 7 pasaría a usar secuencias.
- Se corrigieron 7 consultas JPQL que Hibernate 4 toleraba y Hibernate 7 rechaza: identificadores
  sin alias, parámetros `?` sin número, comparación de una entidad con un número y `DECODE`. Cada
  corrección produce el mismo SQL que antes.
- `RepositoriosJpaTest` construye los 232 repositorios contra Hibernate 7 **sin base de datos** y
  falla si alguna consulta no es válida.

## Decisiones que conservan el comportamiento de difem26

1. **`@Transactional` sigue inactivo.** En difem26 el `<tx:annotation-driven/>` estaba comentado,
   así que los `@Transactional` de 157 servicios nunca se aplicaron: cada llamada a un repositorio o
   a `JdbcTemplate` confirma por separado. Spring Boot los activaría automáticamente, lo que
   cambiaría la lógica contable (atomicidad, rollback y procedimientos almacenados en una misma
   transacción). Quedan apagados detrás de `siicog27.transacciones-declarativas=true`. Activarlos es
   recomendable, pero con pruebas de por medio.
2. **Inyecciones rotas de difem26.** Cuatro `@ManagedProperty` apuntaban a beans inexistentes (p. ej.
   `#{cPBalancePresupuestarioMB}` cuando el bean se llama `cpBalancePresupuestarioMB`). JSF inyectaba
   `null` en silencio. Se dejaron como `@Value("#{null}")` con un comentario, para corregirlas a propósito.
3. **`enable_lazy_load_no_trans`** se conserva, porque hay código que navega relaciones LAZY fuera de
   transacción.

## Cambios de comportamiento deliberados

- **5 beans que eran singletons por error.** Cinco clases tenían `@Component` de Spring con una
  anotación de scope de JSF que Spring ignoraba. En difem26 eran instancias únicas compartidas por
  todos los usuarios. Ahora usan el scope que declaraban (`view`, `session` o `request`):
  `CantidadesPolizaCantidadReferenciaBean`, `FileDownloadManagedBean`, `IngresosCalendarizadosTxtMB`,
  `MetasCapturaManualMB` y `SalaryScaleManualMB`.
- **Contra-recibos: eliminar una factura.** En difem26 `ContrarecibosMB.deleteFactura` quitaba la factura
  elegida de la lista y luego llamaba a `deleteAll` con las que quedaban: borraba en la base las demás
  facturas del contra-recibo y no la elegida. Ahora borra solo la elegida (`delete`) y no hace nada si la
  factura aún no está guardada. Corregido el 2026-10-23.
- **Tema visual.** El tema `bootstrap` de `all-themes` 1.1.0 no existe para PrimeFaces 16. Se usa el
  tema propio `siicog` (ver *Diseño SIICOG*); se configura con `primefaces.THEME` en `application.yml`.
- **jQuery 4.** PrimeFaces 16 trae jQuery 4. Se retiraron del layout las librerías que ya no funcionan con
  él y que solo usaba la barra anterior: `bootstrap.min.js` (sigue `bootstrap.min.css` por la rejilla),
  jQuery UI 1.11, notas adhesivas (postitall, minicolors), el Growl personalizado `growl-render-gem.js`
  (que convertía los errores en un aviso modal) y `calendar_es.js` (los textos en español ahora son los
  de PrimeFaces, `locales/locale-es.js`). Tres `.bind()` en vistas pasaron a `.on()`.

## Pendiente


1. **Reportes Jasper 7. Resuelto el 2026-10-24.** Se convirtieron y recompilaron los 546 reportes
   (`reports/` y `views/reports/`) con `herramientas/conversor-jrxml`; el detalle está en su `LEEME.md`.
   Cuatro `.jasper` no tenían `.jrxml`: `Capital_de_trabajo`, `NivelunoMayoyCue`, `RF009.1.4` y
   `activoDetallado_cuenta_publica`, todos subreportes. Su fuente se recuperó del compilado.

   Para probarlos se pulsó Generar, PDF o Excel en las 104 pantallas de reportes del menú, con
   `java -jar` y con WildFly 41. Resultado:
   - 80 generan su archivo.
   - 17 piden datos antes de generar (póliza, cuenta, fecha…).
   - 6 fallan por causas anteriores a la migración, que no son de Jasper:
     - `reporte75`, `reporte76` y `rF00912011`: error en un procedimiento almacenado de DB2
       (`SQLCODE=-811` / `-302`).
     - `rf009_1_18`: el reporte no tiene firmas configuradas en `TC_REPORTES_FIRMAS` y da
       NullPointerException.
     - `rF00912012`: genera sin validar la selección y da NullPointerException.
     - `EAEPEClasificEcoTipoGasto`: el reporte espera la columna `10` y la consulta del bean la
       devuelve como `9`. Lo mismo ocurre con el `.jasper` original.
   - `consultaMovimientos` es una consulta en pantalla, no un reporte.

   **Si se modifica un `.jrxml`, hay que recompilar su `.jasper`.** La aplicación no compila reportes
   al vuelo, y los `.jasper` nuevos solo los lee Jasper 7. Desde Jaspersoft Studio 7 se hace con
   *Compile Report*; también sirve el conversor con `--jasper`.
2. **Validación con base de datos.** Ya está verificado sin DB2: el arranque, el login, la seguridad,
   los recursos estáticos y los repositorios. Falta probar con una copia de la base: login real,
   pantallas y procesos de cierre.
3. **Revisión visual de PrimeFaces 5 → 16.** Las etiquetas retiradas se sustituyeron
   (`pe:inputNumber`/`pe:keyFilter` → núcleo, `p:editor` → `p:textEditor`,
   `p:inputSwitch` → `p:toggleSwitch`, `p:separator` → `p:divider`). Los atributos que cambiaron no
   dan error: Faces los ignora en silencio. Hay que revisar las pantallas por módulos, en especial
   `p:dataExporter`, `p:calendar`, las tablas paginadas y el menú (`ExtendedMenuRenderer` se
   reconstruyó sobre PrimeFaces 16).
4. **`getRealPath`. Resuelto el 2026-10-24.** 11 clases leen reportes y escriben temporales con rutas
   físicas de `webapp`. WildFly y Tomcat externo expanden el WAR, así que ahí funcionan. Con `java -jar`
   devolvían null y ningún reporte se generaba.

   Ahora, al arrancar con `java -jar`, la aplicación expande el contenido web del WAR (sin las clases ni
   las bibliotecas) en `SIICOG27_CONTENIDO_WEB`, que por defecto es `%TEMP%\siicog27-web`. Tomcat
   embebido usa esa carpeta como raíz; esto lo hacen `config/ContenidoWebExpandido` y `WebConfig`.
   Solo vuelve a expandir cuando cambia el WAR, y tarda unos 2 s.
5. **Contadores identity atrasados en `difdb26`.** 53 de 229 columnas identity tienen su siguiente valor
   por debajo del máximo existente (p. ej. `POLIEN` 21512 contra 27039, `POLIDE` y `CUENTA` en 1), así que
   cualquier alta en esas tablas fallaba con llave duplicada (`SQLCODE=-803`). Venía de una restauración
   o carga de la base, no del código. **Corregido el 2026-10-23** con
   `ALTER TABLE … ALTER COLUMN ID RESTART WITH <máximo + 1>` en las 53. Si se vuelve a restaurar una copia,
   hay que repetirlo.
6. **Contraseñas fijas en el código** (como en difem26): `PolizaMantenimientoMB3.validaPass` (eliminar pólizas),
   `SaldosInicialesMB.validatePass` (clave de acceso de Saldos iniciales) y la constante `PASS` que piden
   al dar «Nuevo registro» `CatalogNatureExpenditureMB`, `CatalogMaintenanceFlowMB`, `CatalogProgramStructureMB`
   y `CatalogFinancialSourcesMB`. Conviene moverlas a configuración o a `TC_PARAMETROS`.
7. **Referencias rotas que ya traían las vistas.** Varias vistas usan beans que no existían en difem26
   (p. ej. `argacuenta58MB`, `registrReportesMB`, `rf001027MB`, `comparadorMB`). No se modificaron.
8. **Opciones del menú que apuntan a páginas inexistentes** (pendiente de revisar por el usuario). En el
   recorrido del 2026-10-23 con el usuario `itbmadminc` (199 opciones), 12 opciones de `TC_MENU_NIVELES`
   dan 404 porque la vista no existe, tampoco en difem26. Hay que corregir su `URL` o quitarlas del menú:

   | URL en `TC_MENU_NIVELES` | Observación |
   |---|---|
   | `/views/egresos/egresoConsulta.xhtml` | no existe la carpeta `views/egresos` |
   | `/views/egresos/egresoCarga.xhtml` | no existe la carpeta `views/egresos` |
   | `/views/egresos/reporteEgresos.xhtml` | no existe la carpeta `views/egresos` |
   | `/views/catalogs/catalog_areas.xhtml` | |
   | `/views/reports/edoActividades2.xhtml` | existe `edoActividades.xhtml` |
   | `/views/reports/REDOSITFIN2.xhtml` | existe `REDOSITFIN.xhtml` |
   | `/views/reports/estadoanaliticoactivo.xhtml` | |
   | `/views/reports/estadoflujoefectivocomparativo.xhtml` | |
   | `/views/reports/estadoCambiosSFBCComparativo.xhtml` | |
   | `/views/reports/estadoVariacionHaciendaPublicaPatrimonioComparativo.xhtml` | |
   | `/views/cuentaPublica/programaProyectoPresupuestrio.xhtml ` | la URL trae un espacio al final |
   | `/views/cuentaPublica/categoriaProgramtica.xhtml ` | la URL trae un espacio al final |

   El resto del menú abre sin errores. El recorrido solo revisa la carga inicial de cada pantalla, no
   sus acciones (buscar, guardar, generar reportes).
