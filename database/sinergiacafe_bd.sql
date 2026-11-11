-- ============================================================
--  BASE DE DATOS INTEGRAL – SINERGIA CAFÉ
--  Integración de Aplicaciones
--  Evaluación Integral
--  Prof. Mario Antonio Huapalla Morales
--  Motor: MySQL 8.0
-- ============================================================

DROP DATABASE IF EXISTS sinergiacafe_bd;
CREATE DATABASE sinergiacafe_bd
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_spanish_ci;

USE sinergiacafe_bd;

-- ============================================================
-- MICROSERVICIO 1: MS-Clientes
-- ============================================================

CREATE TABLE tipo_cliente (
  id_tipo_cliente   INT           NOT NULL AUTO_INCREMENT,
  nombre            VARCHAR(50)   NOT NULL,
  descripcion       VARCHAR(200)  NULL,
  descuento_pct     DECIMAL(5,2)  NOT NULL DEFAULT 0.00,
  activo            TINYINT(1)    NOT NULL DEFAULT 1,
  PRIMARY KEY (id_tipo_cliente)
) COMMENT='Catálogo de tipos de cliente: regular, frecuente, VIP';

CREATE TABLE cliente (
  id_cliente        INT           NOT NULL AUTO_INCREMENT,
  id_tipo_cliente   INT           NOT NULL DEFAULT 1,
  nombres           VARCHAR(100)  NOT NULL,
  apellidos         VARCHAR(100)  NOT NULL,
  dni               CHAR(8)       NOT NULL,
  telefono          VARCHAR(15)   NULL,
  correo            VARCHAR(100)  NULL,
  fecha_nacimiento  DATE          NULL,
  fecha_registro    DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  activo            TINYINT(1)    NOT NULL DEFAULT 1,
  PRIMARY KEY (id_cliente),
  UNIQUE KEY uq_cliente_dni (dni),
  CONSTRAINT fk_cliente_tipo FOREIGN KEY (id_tipo_cliente)
    REFERENCES tipo_cliente(id_tipo_cliente)
) COMMENT='Datos personales y segmentación de clientes';

CREATE TABLE historial_visita (
  id_historial_visita INT          NOT NULL AUTO_INCREMENT,
  id_cliente          INT          NOT NULL,
  fecha_visita        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  consumo_total       DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  observaciones       VARCHAR(300) NULL,
  PRIMARY KEY (id_historial_visita),
  CONSTRAINT fk_hv_cliente FOREIGN KEY (id_cliente)
    REFERENCES cliente(id_cliente)
) COMMENT='Registro de cada visita y consumo del cliente';

CREATE TABLE programa_fidelizacion (
  id_programa_fidelizacion INT     NOT NULL AUTO_INCREMENT,
  nombre              VARCHAR(100) NOT NULL,
  descripcion         VARCHAR(300) NULL,
  puntos_por_sol      DECIMAL(5,2) NOT NULL DEFAULT 1.00,
  min_puntos_canje    INT          NOT NULL DEFAULT 50,
  activo              TINYINT(1)   NOT NULL DEFAULT 1,
  fecha_inicio        DATE         NOT NULL,
  fecha_fin           DATE         NULL,
  PRIMARY KEY (id_programa_fidelizacion)
) COMMENT='Programas de fidelización para clientes frecuentes y VIP';

CREATE TABLE beneficio_cliente (
  id_beneficio_cliente INT         NOT NULL AUTO_INCREMENT,
  id_cliente           INT         NOT NULL,
  id_programa_fidelizacion INT     NOT NULL,
  puntos_acumulados    INT         NOT NULL DEFAULT 0,
  puntos_canjeados     INT         NOT NULL DEFAULT 0,
  puntos_disponibles   INT         NOT NULL DEFAULT 0,
  ultima_actualizacion DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_beneficio_cliente),
  UNIQUE KEY uq_beneficio (id_cliente, id_programa_fidelizacion),
  CONSTRAINT fk_bc_cliente FOREIGN KEY (id_cliente)
    REFERENCES cliente(id_cliente),
  CONSTRAINT fk_bc_programa FOREIGN KEY (id_programa_fidelizacion)
    REFERENCES programa_fidelizacion(id_programa_fidelizacion)
) COMMENT='Saldo de puntos acumulados, canjeados y disponibles por cliente';

-- ============================================================
-- MICROSERVICIO 2: MS-Pedidos
-- ============================================================

CREATE TABLE estado_pedido (
  id_estado_pedido  INT           NOT NULL AUTO_INCREMENT,
  nombre            VARCHAR(50)   NOT NULL,
  descripcion       VARCHAR(200)  NULL,
  PRIMARY KEY (id_estado_pedido)
) COMMENT='Catálogo de estados del pedido';

CREATE TABLE pedido (
  id_pedido         INT           NOT NULL AUTO_INCREMENT,
  id_cliente        INT           NULL,
  id_empleado       INT           NOT NULL COMMENT 'colaborador que tomó el pedido',
  id_estado_pedido  INT           NOT NULL DEFAULT 1,
  fecha_pedido      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  observaciones     VARCHAR(300)  NULL,
  PRIMARY KEY (id_pedido),
  CONSTRAINT fk_pedido_cliente FOREIGN KEY (id_cliente)
    REFERENCES cliente(id_cliente),
  CONSTRAINT fk_pedido_estado FOREIGN KEY (id_estado_pedido)
    REFERENCES estado_pedido(id_estado_pedido)
) COMMENT='Cabecera del pedido con cliente, empleado y estado';

CREATE TABLE detalle_pedido (
  id_detalle_pedido INT           NOT NULL AUTO_INCREMENT,
  id_pedido         INT           NOT NULL,
  id_producto       INT           NOT NULL,
  cantidad          INT           NOT NULL DEFAULT 1,
  precio_unitario   DECIMAL(10,2) NOT NULL,
  observaciones     VARCHAR(200)  NULL,
  PRIMARY KEY (id_detalle_pedido),
  CONSTRAINT fk_dp_pedido FOREIGN KEY (id_pedido)
    REFERENCES pedido(id_pedido)
) COMMENT='Detalle de productos, cantidades y precios por pedido';

-- ============================================================
-- MICROSERVICIO 3: MS-Produccion
-- ============================================================

CREATE TABLE categoria_insumo (
  id_categoria_insumo INT         NOT NULL AUTO_INCREMENT,
  nombre              VARCHAR(80) NOT NULL,
  descripcion         VARCHAR(200) NULL,
  PRIMARY KEY (id_categoria_insumo)
) COMMENT='Clasificación de insumos: lácteos, granos, frutas, embutidos, limpieza';

CREATE TABLE insumo (
  id_insumo           INT          NOT NULL AUTO_INCREMENT,
  id_categoria_insumo INT          NOT NULL,
  nombre              VARCHAR(120) NOT NULL,
  unidad_medida       VARCHAR(20)  NOT NULL COMMENT 'kg, lt, und, etc.',
  activo              TINYINT(1)   NOT NULL DEFAULT 1,
  PRIMARY KEY (id_insumo),
  CONSTRAINT fk_insumo_categoria FOREIGN KEY (id_categoria_insumo)
    REFERENCES categoria_insumo(id_categoria_insumo)
) COMMENT='Catálogo de insumos utilizados en la preparación de productos';

CREATE TABLE receta (
  id_receta         INT           NOT NULL AUTO_INCREMENT,
  id_producto       INT           NOT NULL,
  instrucciones     TEXT          NOT NULL,
  tiempo_prep_min   INT           NOT NULL COMMENT 'tiempo estimado en minutos',
  PRIMARY KEY (id_receta),
  UNIQUE KEY uq_receta_producto (id_producto)
) COMMENT='Receta de preparación por producto, con instrucciones y tiempo estimado';

CREATE TABLE receta_insumo (
  id_receta         INT           NOT NULL,
  id_insumo         INT           NOT NULL,
  cantidad          DECIMAL(10,3) NOT NULL,
  PRIMARY KEY (id_receta, id_insumo),
  CONSTRAINT fk_ri_receta FOREIGN KEY (id_receta)
    REFERENCES receta(id_receta),
  CONSTRAINT fk_ri_insumo FOREIGN KEY (id_insumo)
    REFERENCES insumo(id_insumo)
) COMMENT='Tabla intermedia N:M entre receta e insumo con cantidades';

-- ============================================================
-- MICROSERVICIO 4: MS-Ventas
-- ============================================================

CREATE TABLE tipo_comprobante (
  id_tipo_comprobante INT         NOT NULL AUTO_INCREMENT,
  nombre              VARCHAR(60) NOT NULL,
  descripcion         VARCHAR(200) NULL,
  PRIMARY KEY (id_tipo_comprobante)
) COMMENT='Catálogo de tipos de comprobante: boleta, factura';

CREATE TABLE metodo_pago (
  id_metodo_pago    INT           NOT NULL AUTO_INCREMENT,
  nombre            VARCHAR(60)   NOT NULL,
  descripcion       VARCHAR(200)  NULL,
  activo            TINYINT(1)    NOT NULL DEFAULT 1,
  PRIMARY KEY (id_metodo_pago)
) COMMENT='Catálogo de métodos de pago disponibles';

CREATE TABLE caja (
  id_caja           INT           NOT NULL AUTO_INCREMENT,
  id_empleado       INT           NOT NULL COMMENT 'colaborador responsable de la apertura',
  fecha_apertura    DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  monto_inicial     DECIMAL(10,2) NOT NULL,
  fecha_cierre      DATETIME      NULL,
  estado            ENUM('abierta','cerrada') NOT NULL DEFAULT 'abierta',
  PRIMARY KEY (id_caja)
) COMMENT='Apertura y cierre de caja por turno';

CREATE TABLE cierre_caja (
  id_cierre_caja    INT           NOT NULL AUTO_INCREMENT,
  id_caja           INT           NOT NULL,
  id_metodo_pago    INT           NOT NULL,
  monto_registrado  DECIMAL(10,2) NOT NULL,
  diferencia        DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  observaciones     VARCHAR(300)  NULL,
  PRIMARY KEY (id_cierre_caja),
  CONSTRAINT fk_cc_caja FOREIGN KEY (id_caja)
    REFERENCES caja(id_caja),
  CONSTRAINT fk_cc_metodo FOREIGN KEY (id_metodo_pago)
    REFERENCES metodo_pago(id_metodo_pago)
) COMMENT='Cuadre de cierre de caja por método de pago, con diferencias';

CREATE TABLE comprobante (
  id_comprobante      INT           NOT NULL AUTO_INCREMENT,
  id_pedido           INT           NOT NULL,
  id_tipo_comprobante INT           NOT NULL,
  id_metodo_pago      INT           NOT NULL,
  serie               VARCHAR(10)   NOT NULL,
  numero              VARCHAR(10)   NOT NULL,
  subtotal            DECIMAL(10,2) NOT NULL,
  igv                 DECIMAL(10,2) NOT NULL,
  total               DECIMAL(10,2) NOT NULL,
  fecha_emision       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_comprobante),
  UNIQUE KEY uq_comprobante (serie, numero),
  CONSTRAINT fk_comp_pedido FOREIGN KEY (id_pedido)
    REFERENCES pedido(id_pedido),
  CONSTRAINT fk_comp_tipo FOREIGN KEY (id_tipo_comprobante)
    REFERENCES tipo_comprobante(id_tipo_comprobante),
  CONSTRAINT fk_comp_metodo FOREIGN KEY (id_metodo_pago)
    REFERENCES metodo_pago(id_metodo_pago)
) COMMENT='Comprobantes de pago emitidos por venta, con IGV';

-- ============================================================
-- MICROSERVICIO 5: MS-Inventario
-- ============================================================

CREATE TABLE categoria_producto (
  id_categoria_producto INT       NOT NULL AUTO_INCREMENT,
  nombre                VARCHAR(80) NOT NULL,
  descripcion           VARCHAR(200) NULL,
  PRIMARY KEY (id_categoria_producto)
) COMMENT='Clasificación de productos: bebidas calientes, frías, sándwiches, snacks';

CREATE TABLE producto (
  id_producto           INT          NOT NULL AUTO_INCREMENT,
  id_categoria_producto INT          NOT NULL,
  nombre                VARCHAR(120) NOT NULL,
  descripcion           VARCHAR(300) NULL,
  precio                DECIMAL(10,2) NOT NULL,
  disponible            TINYINT(1)   NOT NULL DEFAULT 1,
  PRIMARY KEY (id_producto),
  CONSTRAINT fk_prod_categoria FOREIGN KEY (id_categoria_producto)
    REFERENCES categoria_producto(id_categoria_producto)
) COMMENT='Catálogo de productos ofrecidos por la cafetería';

CREATE TABLE stock (
  id_stock          INT           NOT NULL AUTO_INCREMENT,
  id_insumo         INT           NOT NULL,
  cantidad_actual   DECIMAL(10,3) NOT NULL DEFAULT 0.000,
  cantidad_minima   DECIMAL(10,3) NOT NULL DEFAULT 0.000,
  ultima_actualizacion DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_stock),
  UNIQUE KEY uq_stock_insumo (id_insumo),
  CONSTRAINT fk_stock_insumo FOREIGN KEY (id_insumo)
    REFERENCES insumo(id_insumo)
) COMMENT='Nivel actual de existencias por insumo';

CREATE TABLE movimiento_stock (
  id_movimiento_stock INT          NOT NULL AUTO_INCREMENT,
  id_stock            INT          NOT NULL,
  id_empleado         INT          NOT NULL,
  tipo_movimiento     ENUM('entrada','salida') NOT NULL,
  cantidad            DECIMAL(10,3) NOT NULL,
  motivo              VARCHAR(200) NOT NULL COMMENT 'compra, uso en producción, merma',
  fecha_movimiento    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_movimiento_stock),
  CONSTRAINT fk_ms_stock FOREIGN KEY (id_stock)
    REFERENCES stock(id_stock)
) COMMENT='Entradas, salidas y consumos internos de insumos';

-- ============================================================
-- MICROSERVICIO 6: MS-Abastecimiento
-- ============================================================

CREATE TABLE proveedor (
  id_proveedor      INT           NOT NULL AUTO_INCREMENT,
  razon_social      VARCHAR(200)  NOT NULL,
  ruc               VARCHAR(11)   NOT NULL,
  contacto          VARCHAR(100)  NULL,
  telefono          VARCHAR(15)   NULL,
  correo            VARCHAR(100)  NULL,
  direccion         VARCHAR(300)  NULL,
  activo            TINYINT(1)    NOT NULL DEFAULT 1,
  PRIMARY KEY (id_proveedor),
  UNIQUE KEY uq_proveedor_ruc (ruc)
) COMMENT='Datos de proveedores: nombre, RUC, contacto y condiciones';

CREATE TABLE contrato_proveedor (
  id_contrato_proveedor INT        NOT NULL AUTO_INCREMENT,
  id_proveedor          INT        NOT NULL,
  fecha_inicio          DATE       NOT NULL,
  fecha_fin             DATE       NULL,
  condiciones           VARCHAR(300) NULL,
  activo                TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id_contrato_proveedor),
  CONSTRAINT fk_cp_proveedor FOREIGN KEY (id_proveedor)
    REFERENCES proveedor(id_proveedor)
) COMMENT='Contratos vigentes con proveedores: vigencia y condiciones';

CREATE TABLE orden_compra (
  id_orden_compra   INT           NOT NULL AUTO_INCREMENT,
  id_proveedor      INT           NOT NULL,
  id_empleado       INT           NOT NULL COMMENT 'colaborador que genera la orden',
  fecha_orden       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  monto_total       DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  estado            ENUM('borrador','enviada','confirmada','recibida','cancelada') NOT NULL DEFAULT 'borrador',
  PRIMARY KEY (id_orden_compra),
  CONSTRAINT fk_oc_proveedor FOREIGN KEY (id_proveedor)
    REFERENCES proveedor(id_proveedor)
) COMMENT='Órdenes de compra emitidas a proveedores';

CREATE TABLE detalle_orden_compra (
  id_detalle_orden_compra INT      NOT NULL AUTO_INCREMENT,
  id_orden_compra         INT      NOT NULL,
  id_insumo               INT      NOT NULL,
  cantidad                DECIMAL(10,3) NOT NULL,
  precio_unitario         DECIMAL(10,2) NOT NULL,
  subtotal                DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id_detalle_orden_compra),
  CONSTRAINT fk_doc_orden FOREIGN KEY (id_orden_compra)
    REFERENCES orden_compra(id_orden_compra),
  CONSTRAINT fk_doc_insumo FOREIGN KEY (id_insumo)
    REFERENCES insumo(id_insumo)
) COMMENT='Insumos, cantidades y precios por orden de compra';

CREATE TABLE recepcion_mercaderia (
  id_recepcion_mercaderia INT      NOT NULL AUTO_INCREMENT,
  id_orden_compra         INT      NOT NULL,
  id_empleado             INT      NOT NULL COMMENT 'colaborador que recibe',
  fecha_recepcion         DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  conforme                TINYINT(1) NOT NULL DEFAULT 1,
  observaciones           VARCHAR(300) NULL,
  PRIMARY KEY (id_recepcion_mercaderia),
  CONSTRAINT fk_rm_orden FOREIGN KEY (id_orden_compra)
    REFERENCES orden_compra(id_orden_compra)
) COMMENT='Recepción y verificación de mercadería recibida';

-- ============================================================
-- MICROSERVICIO 7: MS-Mantenimiento
-- ============================================================

CREATE TABLE tipo_equipo (
  id_tipo_equipo    INT           NOT NULL AUTO_INCREMENT,
  nombre            VARCHAR(80)   NOT NULL,
  descripcion       VARCHAR(200)  NULL,
  PRIMARY KEY (id_tipo_equipo)
) COMMENT='Catálogo de tipos de equipo: máquinas de café, refrigeradoras, licuadoras';

CREATE TABLE equipo (
  id_equipo         INT           NOT NULL AUTO_INCREMENT,
  id_tipo_equipo    INT           NOT NULL,
  nombre            VARCHAR(120)  NOT NULL,
  modelo            VARCHAR(100)  NULL,
  fecha_adquisicion DATE          NULL,
  estado            ENUM('operativo','en_mantenimiento','inoperativo') NOT NULL DEFAULT 'operativo',
  PRIMARY KEY (id_equipo),
  CONSTRAINT fk_equipo_tipo FOREIGN KEY (id_tipo_equipo)
    REFERENCES tipo_equipo(id_tipo_equipo)
) COMMENT='Equipos e instalaciones de la cafetería';

CREATE TABLE tipo_mantenimiento (
  id_tipo_mantenimiento INT        NOT NULL AUTO_INCREMENT,
  nombre                VARCHAR(60) NOT NULL,
  descripcion           VARCHAR(200) NULL,
  PRIMARY KEY (id_tipo_mantenimiento)
) COMMENT='Catálogo de tipos de mantenimiento: preventivo, correctivo';

CREATE TABLE mantenimiento (
  id_mantenimiento      INT        NOT NULL AUTO_INCREMENT,
  id_equipo             INT        NOT NULL,
  id_tipo_mantenimiento INT        NOT NULL,
  id_empleado           INT        NOT NULL COMMENT 'colaborador responsable',
  fecha_mantenimiento   DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  costo                 DECIMAL(10,2) NULL,
  observaciones         VARCHAR(300) NULL,
  PRIMARY KEY (id_mantenimiento),
  CONSTRAINT fk_mant_equipo FOREIGN KEY (id_equipo)
    REFERENCES equipo(id_equipo),
  CONSTRAINT fk_mant_tipo FOREIGN KEY (id_tipo_mantenimiento)
    REFERENCES tipo_mantenimiento(id_tipo_mantenimiento)
) COMMENT='Historial de mantenimientos preventivos y correctivos por equipo';

-- ============================================================
-- MICROSERVICIO 8: MS-RecursosHumanos
-- ============================================================

CREATE TABLE cargo (
  id_cargo          INT           NOT NULL AUTO_INCREMENT,
  nombre            VARCHAR(80)   NOT NULL,
  descripcion       VARCHAR(200)  NULL,
  PRIMARY KEY (id_cargo)
) COMMENT='Catálogo de cargos: barista, cajero, mozo, administrador, limpieza';

CREATE TABLE turno (
  id_turno          INT           NOT NULL AUTO_INCREMENT,
  nombre            VARCHAR(50)   NOT NULL,
  hora_inicio       TIME          NOT NULL,
  hora_fin          TIME          NOT NULL,
  PRIMARY KEY (id_turno)
) COMMENT='Turnos de trabajo disponibles en la cafetería';

CREATE TABLE empleado (
  id_empleado       INT           NOT NULL AUTO_INCREMENT,
  id_cargo          INT           NOT NULL,
  id_turno          INT           NOT NULL,
  nombres           VARCHAR(100)  NOT NULL,
  apellidos         VARCHAR(100)  NOT NULL,
  dni               CHAR(8)       NOT NULL,
  telefono          VARCHAR(15)   NULL,
  correo            VARCHAR(100)  NULL,
  fecha_ingreso     DATE          NOT NULL,
  estado_laboral    ENUM('activo','inactivo') NOT NULL DEFAULT 'activo',
  PRIMARY KEY (id_empleado),
  UNIQUE KEY uq_empleado_dni (dni),
  CONSTRAINT fk_emp_cargo FOREIGN KEY (id_cargo)
    REFERENCES cargo(id_cargo),
  CONSTRAINT fk_emp_turno FOREIGN KEY (id_turno)
    REFERENCES turno(id_turno)
) COMMENT='Datos personales y laborales del personal';

CREATE TABLE asistencia (
  id_asistencia     INT           NOT NULL AUTO_INCREMENT,
  id_empleado       INT           NOT NULL,
  fecha             DATE          NOT NULL,
  hora_entrada      TIME          NULL,
  hora_salida       TIME          NULL,
  observaciones     VARCHAR(200)  NULL,
  PRIMARY KEY (id_asistencia),
  UNIQUE KEY uq_asistencia (id_empleado, fecha),
  CONSTRAINT fk_asis_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado)
) COMMENT='Registro diario de asistencia del personal';

CREATE TABLE evaluacion_desempeno (
  id_evaluacion_desempeno INT      NOT NULL AUTO_INCREMENT,
  id_empleado             INT      NOT NULL,
  fecha_evaluacion        DATE     NOT NULL,
  puntaje                 DECIMAL(4,2) NOT NULL COMMENT 'puntaje de 0.00 a 5.00',
  criterios               VARCHAR(300) NULL,
  observaciones           VARCHAR(300) NULL,
  PRIMARY KEY (id_evaluacion_desempeno),
  CONSTRAINT fk_ed_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado)
) COMMENT='Evaluaciones periódicas de desempeño del personal';

CREATE TABLE rol (
  id_rol            INT           NOT NULL AUTO_INCREMENT,
  nombre            VARCHAR(60)   NOT NULL,
  descripcion       VARCHAR(200)  NULL,
  activo            TINYINT(1)    NOT NULL DEFAULT 1,
  PRIMARY KEY (id_rol)
) COMMENT='Roles del sistema para control de acceso';

CREATE TABLE permiso (
  id_permiso        INT           NOT NULL AUTO_INCREMENT,
  modulo            VARCHAR(80)   NOT NULL COMMENT 'nombre del módulo o microservicio',
  accion            VARCHAR(50)   NOT NULL COMMENT 'crear, leer, actualizar, eliminar',
  descripcion       VARCHAR(200)  NULL,
  PRIMARY KEY (id_permiso)
) COMMENT='Catálogo de permisos del sistema por módulo y acción';

CREATE TABLE rol_permiso (
  id_rol            INT           NOT NULL,
  id_permiso        INT           NOT NULL,
  PRIMARY KEY (id_rol, id_permiso),
  CONSTRAINT fk_rp_rol FOREIGN KEY (id_rol)
    REFERENCES rol(id_rol),
  CONSTRAINT fk_rp_permiso FOREIGN KEY (id_permiso)
    REFERENCES permiso(id_permiso)
) COMMENT='Tabla intermedia N:M entre rol y permiso';

CREATE TABLE usuario (
  id_usuario        INT           NOT NULL AUTO_INCREMENT,
  id_empleado       INT           NOT NULL,
  id_rol            INT           NOT NULL,
  username          VARCHAR(60)   NOT NULL,
  password_hash     VARCHAR(255)  NOT NULL,
  activo            TINYINT(1)    NOT NULL DEFAULT 1,
  fecha_creacion    DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  ultimo_acceso     DATETIME      NULL,
  PRIMARY KEY (id_usuario),
  UNIQUE KEY uq_usuario_username (username),
  UNIQUE KEY uq_usuario_empleado (id_empleado),
  CONSTRAINT fk_usr_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado),
  CONSTRAINT fk_usr_rol FOREIGN KEY (id_rol)
    REFERENCES rol(id_rol)
) COMMENT='Credenciales de acceso al sistema por empleado';

CREATE TABLE auditoria (
  id_auditoria      BIGINT        NOT NULL AUTO_INCREMENT,
  id_usuario        INT           NOT NULL,
  modulo            VARCHAR(80)   NOT NULL,
  accion            VARCHAR(100)  NOT NULL,
  tabla_afectada    VARCHAR(80)   NULL,
  id_registro       INT           NULL COMMENT 'id del registro afectado',
  detalle           TEXT          NULL,
  fecha_hora        DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_auditoria),
  CONSTRAINT fk_aud_usuario FOREIGN KEY (id_usuario)
    REFERENCES usuario(id_usuario)
) COMMENT='Bitácora de operaciones realizadas por los usuarios del sistema';

-- ============================================================
-- MICROSERVICIO 9: MS-Administracion
-- ============================================================

CREATE TABLE categoria_gasto (
  id_categoria_gasto INT          NOT NULL AUTO_INCREMENT,
  nombre             VARCHAR(80)  NOT NULL,
  descripcion        VARCHAR(200) NULL,
  PRIMARY KEY (id_categoria_gasto)
) COMMENT='Categorías de gasto: servicios, insumos, personal, mantenimiento, administrativo';

CREATE TABLE documento_administrativo (
  id_documento_administrativo INT NOT NULL AUTO_INCREMENT,
  tipo_documento     VARCHAR(80)  NOT NULL,
  fecha_documento    DATE         NOT NULL,
  descripcion        VARCHAR(300) NOT NULL,
  id_empleado        INT          NOT NULL COMMENT 'responsable del documento',
  PRIMARY KEY (id_documento_administrativo),
  CONSTRAINT fk_da_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado)
) COMMENT='Documentos internos de gestión administrativa';

CREATE TABLE gasto (
  id_gasto           INT          NOT NULL AUTO_INCREMENT,
  id_categoria_gasto INT          NOT NULL,
  fecha_gasto        DATE         NOT NULL,
  monto              DECIMAL(10,2) NOT NULL,
  descripcion        VARCHAR(300) NOT NULL,
  PRIMARY KEY (id_gasto),
  CONSTRAINT fk_gasto_categoria FOREIGN KEY (id_categoria_gasto)
    REFERENCES categoria_gasto(id_categoria_gasto)
) COMMENT='Gastos operativos de la cafetería';

-- ============================================================
-- MICROSERVICIO 10: MS-Finanzas
-- ============================================================

CREATE TABLE presupuesto (
  id_presupuesto    INT           NOT NULL AUTO_INCREMENT,
  area              VARCHAR(80)   NOT NULL COMMENT 'área o categoría presupuestada',
  periodo           VARCHAR(20)   NOT NULL COMMENT 'ej. 2024-07',
  monto_asignado    DECIMAL(10,2) NOT NULL,
  monto_ejecutado   DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (id_presupuesto)
) COMMENT='Planificación presupuestaria por área y período';

CREATE TABLE cuenta_por_pagar (
  id_cuenta_por_pagar INT         NOT NULL AUTO_INCREMENT,
  id_proveedor        INT         NOT NULL,
  id_orden_compra     INT         NULL,
  monto               DECIMAL(10,2) NOT NULL,
  fecha_vencimiento   DATE        NOT NULL,
  estado              ENUM('pendiente','pagada','vencida') NOT NULL DEFAULT 'pendiente',
  PRIMARY KEY (id_cuenta_por_pagar),
  CONSTRAINT fk_cpp_proveedor FOREIGN KEY (id_proveedor)
    REFERENCES proveedor(id_proveedor),
  CONSTRAINT fk_cpp_orden FOREIGN KEY (id_orden_compra)
    REFERENCES orden_compra(id_orden_compra)
) COMMENT='Obligaciones pendientes de pago a proveedores';

CREATE TABLE reporte_financiero (
  id_reporte_financiero INT       NOT NULL AUTO_INCREMENT,
  periodo               VARCHAR(20) NOT NULL COMMENT 'ej. 2024-07',
  ingresos              DECIMAL(12,2) NOT NULL DEFAULT 0.00,
  egresos               DECIMAL(12,2) NOT NULL DEFAULT 0.00,
  utilidad_neta         DECIMAL(12,2) NOT NULL DEFAULT 0.00,
  fecha_generacion      DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_reporte_financiero)
) COMMENT='Reportes de resultados económicos por período';

-- ============================================================
-- MICROSERVICIO 11: MS-Calidad
-- ============================================================

CREATE TABLE estandar_calidad (
  id_estandar_calidad INT         NOT NULL AUTO_INCREMENT,
  nombre              VARCHAR(120) NOT NULL,
  descripcion         VARCHAR(300) NULL,
  aplicable_a         VARCHAR(80) NOT NULL COMMENT 'producto, insumo, servicio',
  PRIMARY KEY (id_estandar_calidad)
) COMMENT='Estándares definidos para productos, insumos y servicios';

CREATE TABLE inspeccion (
  id_inspeccion     INT           NOT NULL AUTO_INCREMENT,
  id_empleado       INT           NOT NULL COMMENT 'responsable de la inspección',
  fecha_inspeccion  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  area_inspeccionada VARCHAR(80)  NOT NULL,
  resultado_general ENUM('conforme','no_conforme','observado') NOT NULL DEFAULT 'conforme',
  PRIMARY KEY (id_inspeccion),
  CONSTRAINT fk_insp_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado)
) COMMENT='Inspecciones realizadas: fecha, área, responsable y resultado';

CREATE TABLE resultado_inspeccion (
  id_resultado_inspeccion INT     NOT NULL AUTO_INCREMENT,
  id_inspeccion           INT     NOT NULL,
  id_estandar_calidad     INT     NOT NULL,
  cumple                  TINYINT(1) NOT NULL DEFAULT 1,
  observaciones           VARCHAR(300) NULL,
  PRIMARY KEY (id_resultado_inspeccion),
  CONSTRAINT fk_ri2_inspeccion FOREIGN KEY (id_inspeccion)
    REFERENCES inspeccion(id_inspeccion),
  CONSTRAINT fk_ri2_estandar FOREIGN KEY (id_estandar_calidad)
    REFERENCES estandar_calidad(id_estandar_calidad)
) COMMENT='Hallazgos por criterio evaluado en cada inspección';

CREATE TABLE tipo_incidencia (
  id_tipo_incidencia INT          NOT NULL AUTO_INCREMENT,
  nombre             VARCHAR(80)  NOT NULL,
  descripcion        VARCHAR(200) NULL,
  PRIMARY KEY (id_tipo_incidencia)
) COMMENT='Catálogo de tipos de incidencia: producto defectuoso, falla de equipo, queja';

CREATE TABLE incidencia (
  id_incidencia      INT          NOT NULL AUTO_INCREMENT,
  id_tipo_incidencia INT          NOT NULL,
  id_empleado        INT          NOT NULL COMMENT 'colaborador que registra la incidencia',
  fecha_incidencia   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  area               VARCHAR(80)  NOT NULL,
  descripcion        VARCHAR(300) NOT NULL,
  estado             ENUM('abierta','en_proceso','resuelta') NOT NULL DEFAULT 'abierta',
  PRIMARY KEY (id_incidencia),
  CONSTRAINT fk_inc_tipo FOREIGN KEY (id_tipo_incidencia)
    REFERENCES tipo_incidencia(id_tipo_incidencia),
  CONSTRAINT fk_inc_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado)
) COMMENT='Incidencias operativas detectadas en la cafetería';

CREATE TABLE encuesta_satisfaccion (
  id_encuesta_satisfaccion INT    NOT NULL AUTO_INCREMENT,
  id_cliente               INT    NULL,
  fecha_encuesta           DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  canal                    VARCHAR(60) NOT NULL COMMENT 'presencial, web, redes sociales',
  puntuacion_general       DECIMAL(3,2) NOT NULL COMMENT 'puntaje 0.00 a 5.00',
  PRIMARY KEY (id_encuesta_satisfaccion),
  CONSTRAINT fk_es_cliente FOREIGN KEY (id_cliente)
    REFERENCES cliente(id_cliente)
) COMMENT='Encuestas de satisfacción aplicadas a clientes';

CREATE TABLE respuesta_encuesta (
  id_respuesta_encuesta INT       NOT NULL AUTO_INCREMENT,
  id_encuesta_satisfaccion INT    NOT NULL,
  pregunta              VARCHAR(300) NOT NULL,
  puntuacion            DECIMAL(3,2) NOT NULL COMMENT 'puntaje 0.00 a 5.00',
  comentario            VARCHAR(300) NULL,
  PRIMARY KEY (id_respuesta_encuesta),
  CONSTRAINT fk_re_encuesta FOREIGN KEY (id_encuesta_satisfaccion)
    REFERENCES encuesta_satisfaccion(id_encuesta_satisfaccion)
) COMMENT='Respuestas por criterio de cada encuesta de satisfacción';

-- ============================================================
-- MICROSERVICIO 12: MS-Marketing
-- ============================================================

CREATE TABLE campana_marketing (
  id_campana_marketing INT        NOT NULL AUTO_INCREMENT,
  nombre               VARCHAR(120) NOT NULL,
  descripcion          VARCHAR(300) NULL,
  canal                VARCHAR(100) NULL COMMENT 'redes sociales, correo, etc.',
  fecha_inicio         DATE       NOT NULL,
  fecha_fin            DATE       NOT NULL,
  presupuesto          DECIMAL(10,2) NULL,
  PRIMARY KEY (id_campana_marketing)
) COMMENT='Campañas publicitarias y promocionales de la cafetería';

CREATE TABLE promocion (
  id_promocion         INT         NOT NULL AUTO_INCREMENT,
  id_campana_marketing INT         NOT NULL,
  descripcion          VARCHAR(300) NOT NULL,
  tipo_descuento       ENUM('porcentaje','monto_fijo','producto_gratis') NOT NULL,
  valor_descuento      DECIMAL(10,2) NOT NULL,
  fecha_inicio         DATE        NOT NULL,
  fecha_fin            DATE        NOT NULL,
  activo               TINYINT(1)  NOT NULL DEFAULT 1,
  PRIMARY KEY (id_promocion),
  CONSTRAINT fk_promo_campana FOREIGN KEY (id_campana_marketing)
    REFERENCES campana_marketing(id_campana_marketing)
) COMMENT='Promociones activas por campaña, con descuento y vigencia';

-- ============================================================
-- FK DIFERIDAS (tablas que se referencian mutuamente)
-- ============================================================

ALTER TABLE pedido
  ADD CONSTRAINT fk_pedido_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado);

ALTER TABLE detalle_pedido
  ADD CONSTRAINT fk_dp_producto FOREIGN KEY (id_producto)
    REFERENCES producto(id_producto);

ALTER TABLE receta
  ADD CONSTRAINT fk_receta_producto FOREIGN KEY (id_producto)
    REFERENCES producto(id_producto);

ALTER TABLE caja
  ADD CONSTRAINT fk_caja_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado);

ALTER TABLE orden_compra
  ADD CONSTRAINT fk_oc_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado);

ALTER TABLE recepcion_mercaderia
  ADD CONSTRAINT fk_rm_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado);

ALTER TABLE mantenimiento
  ADD CONSTRAINT fk_mant_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado);

ALTER TABLE movimiento_stock
  ADD CONSTRAINT fk_ms_empleado FOREIGN KEY (id_empleado)
    REFERENCES empleado(id_empleado);

-- ============================================================
-- DATOS DE PRUEBA
-- ============================================================

-- tipo_cliente
INSERT INTO tipo_cliente (nombre, descripcion, descuento_pct) VALUES
('Regular',   'Cliente nuevo o con pocas visitas',           0.00),
('Frecuente', 'Cliente con más de 5 visitas al mes',         5.00),
('VIP',       'Cliente premium con consumo mensual alto',   10.00);

-- cliente
INSERT INTO cliente (id_tipo_cliente, nombres, apellidos, dni, telefono, correo, fecha_nacimiento) VALUES
(2, 'Diego',    'Salazar Núñez',     '45231678', '987654321', 'dsalazar@gmail.com',   '1995-03-12'),
(1, 'Camila',   'Reyes Ponce',       '52341890', '976543210', 'creyes@hotmail.com',   '1998-07-25'),
(3, 'Andrea',   'Quevedo Sosa',      '61234567', '965432109', 'aquevedo@gmail.com',   '1990-11-08'),
(2, 'Renzo',    'Aguirre Bravo',     '73456789', '954321098', 'raguirre@outlook.com', '1993-05-20'),
(1, 'Fiorella', 'Cabrera Soto',      '84567890', '943210987', 'fcabrera@gmail.com',   '1996-09-14');

-- programa_fidelizacion
INSERT INTO programa_fidelizacion (nombre, descripcion, puntos_por_sol, min_puntos_canje, fecha_inicio) VALUES
('Sinergia Puntos', 'Programa de puntos por consumo en Sinergia Café', 1.00, 50, '2024-01-01');

-- beneficio_cliente
INSERT INTO beneficio_cliente (id_cliente, id_programa_fidelizacion, puntos_acumulados, puntos_canjeados, puntos_disponibles) VALUES
(1, 1, 120, 50, 70),
(3, 1, 200, 0, 200),
(4, 1, 60,  0, 60);

-- historial_visita
INSERT INTO historial_visita (id_cliente, fecha_visita, consumo_total, observaciones) VALUES
(1, '2024-07-10 09:30:00', 18.50, NULL),
(3, '2024-07-11 16:00:00', 25.00, 'Pidió para llevar'),
(4, '2024-07-12 08:15:00', 12.00, NULL);

-- categoria_producto
INSERT INTO categoria_producto (nombre, descripcion) VALUES
('Bebidas calientes', 'Café, té, chocolate caliente'),
('Bebidas frías',     'Frappés, jugos, té helado'),
('Sándwiches',        'Sándwiches y wraps'),
('Snacks',            'Pasteles, galletas y panes dulces');

-- producto
INSERT INTO producto (id_categoria_producto, nombre, descripcion, precio) VALUES
(1, 'Espresso',           'Café espresso tradicional',                 6.50),
(1, 'Capuchino',          'Espresso con leche vaporizada y espuma',    9.00),
(1, 'Chocolate Caliente', 'Chocolate caliente con leche entera',       8.50),
(2, 'Frappé de Café',     'Café helado con hielo y leche',            12.00),
(2, 'Limonada Frozen',    'Limonada frappe con hierbabuena',          10.00),
(3, 'Sándwich de Pollo',  'Sándwich de pollo con vegetales frescos',  14.00),
(3, 'Wrap Vegetariano',   'Wrap integral con vegetales y palta',      13.00),
(4, 'Croissant',          'Croissant de mantequilla horneado',         7.00),
(4, 'Cheesecake',         'Porción de cheesecake de fresa',           11.00);

-- categoria_insumo
INSERT INTO categoria_insumo (nombre, descripcion) VALUES
('Lácteos',          'Leche, crema y derivados'),
('Granos y café',    'Café en grano, molido y granos secos'),
('Frutas',           'Frutas frescas para bebidas y postres'),
('Embutidos y panes','Panes, embutidos y quesos para sándwiches'),
('Materiales de limpieza', 'Insumos de limpieza e higiene');

-- insumo
INSERT INTO insumo (id_categoria_insumo, nombre, unidad_medida) VALUES
(2, 'Café en grano',       'kg'),
(1, 'Leche entera',        'lt'),
(1, 'Crema de leche',      'lt'),
(3, 'Limón',               'kg'),
(3, 'Fresa',               'kg'),
(4, 'Pan ciabatta',        'und'),
(4, 'Pechuga de pollo',    'kg'),
(4, 'Queso crema',         'kg'),
(5, 'Detergente líquido',  'lt');

-- receta
INSERT INTO receta (id_producto, instrucciones, tiempo_prep_min) VALUES
(1, 'Moler el café y extraer espresso de 25-30 ml con máquina espresso.', 3),
(2, 'Extraer espresso, vaporizar leche y verter formando capa de espuma.', 4),
(4, 'Preparar espresso, enfriar, mezclar con hielo y leche en licuadora.', 5);

-- receta_insumo
INSERT INTO receta_insumo (id_receta, id_insumo, cantidad) VALUES
(1, 1, 0.020),
(2, 1, 0.020),
(2, 2, 0.150),
(3, 1, 0.020),
(3, 2, 0.100),
(3, 3, 0.050);

-- tipo_comprobante
INSERT INTO tipo_comprobante (nombre, descripcion) VALUES
('Boleta de Venta', 'Comprobante para consumidores finales'),
('Factura',         'Comprobante para empresas con RUC');

-- metodo_pago
INSERT INTO metodo_pago (nombre, descripcion) VALUES
('Efectivo',           'Pago en efectivo en soles'),
('Tarjeta de Crédito', 'Visa, Mastercard'),
('Tarjeta de Débito',  'Tarjeta de débito bancaria'),
('Yape',               'Billetera digital Yape - BCP'),
('Plin',               'Billetera digital Plin');

-- area / cargo
INSERT INTO cargo (nombre, descripcion) VALUES
('Barista',       'Preparación de bebidas y atención de barra'),
('Cajero',        'Cobro y emisión de comprobantes'),
('Mozo',          'Atención al cliente en mesas'),
('Administrador', 'Gestión general de la cafetería'),
('Almacenero',    'Control de inventario y recepción de insumos');

-- turno
INSERT INTO turno (nombre, hora_inicio, hora_fin) VALUES
('Mañana',   '07:00:00', '14:00:00'),
('Tarde',    '14:00:00', '21:00:00'),
('Completo', '07:00:00', '21:00:00');

-- empleado
INSERT INTO empleado (id_cargo, id_turno, nombres, apellidos, dni, telefono, correo, fecha_ingreso) VALUES
(4, 3, 'Mariana',  'Gutiérrez Loja',  '31456789', '981234567', 'mgutierrez@sinergia.pe', '2021-01-10'),
(1, 1, 'Bruno',    'Espinoza Reyes',  '42567890', '972345678', 'bespinoza@sinergia.pe', '2022-03-15'),
(1, 2, 'Valeria',  'Núñez Castro',    '53678901', '963456789', 'vnunez@sinergia.pe',    '2022-06-01'),
(2, 1, 'Jorge',    'Maldonado Vera',  '64789012', '954567890', 'jmaldonado@sinergia.pe','2021-09-20'),
(3, 2, 'Daniela',  'Ramos Quiroz',    '75890123', '945678901', 'dramos@sinergia.pe',    '2023-02-05'),
(5, 1, 'Sergio',   'Fonseca Iturri',  '86901234', '936789012', 'sfonseca@sinergia.pe',  '2021-05-12');

-- asistencia
INSERT INTO asistencia (id_empleado, fecha, hora_entrada, hora_salida, observaciones) VALUES
(2, '2024-07-15', '06:55:00', '14:05:00', NULL),
(3, '2024-07-15', '14:00:00', '21:00:00', NULL),
(4, '2024-07-15', '06:58:00', '14:00:00', NULL),
(5, '2024-07-15', '14:05:00', '21:10:00', 'Tardanza leve');

-- evaluacion_desempeno
INSERT INTO evaluacion_desempeno (id_empleado, fecha_evaluacion, puntaje, criterios, observaciones) VALUES
(2, '2024-06-30', 4.50, 'Calidad de preparación, atención al cliente, puntualidad', 'Muy buen desempeño general'),
(4, '2024-06-30', 4.20, 'Precisión en cobro, atención al cliente', 'Cumple con los estándares esperados');

-- rol
INSERT INTO rol (nombre, descripcion) VALUES
('Administrador', 'Acceso total al sistema'),
('Cajero',        'Acceso a ventas, pagos y comprobantes'),
('Barista',       'Acceso a producción e inventario'),
('Mozo',          'Acceso a pedidos');

-- permiso
INSERT INTO permiso (modulo, accion, descripcion) VALUES
('MS-Pedidos',     'crear',      'Registrar nuevos pedidos'),
('MS-Pedidos',     'leer',       'Consultar pedidos activos'),
('MS-Ventas',      'crear',      'Registrar pagos y emitir comprobantes'),
('MS-Ventas',      'leer',       'Consultar comprobantes y cierres de caja'),
('MS-Inventario',  'leer',       'Consultar stock de insumos'),
('MS-Inventario',  'actualizar', 'Registrar movimientos de inventario'),
('MS-RecursosHumanos', 'leer',   'Consultar información del personal'),
('MS-RecursosHumanos', 'crear',  'Crear usuarios y asignar roles');

-- rol_permiso
INSERT INTO rol_permiso (id_rol, id_permiso) VALUES
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),
(2,3),(2,4),
(3,5),(3,6),
(4,1),(4,2);

-- usuario
INSERT INTO usuario (id_empleado, id_rol, username, password_hash) VALUES
(1, 1, 'mgutierrez', SHA2('Admin2024*',  256)),
(2, 3, 'bespinoza',  SHA2('Barista2024*',256)),
(4, 2, 'jmaldonado', SHA2('Caja2024*',   256)),
(5, 4, 'dramos',     SHA2('Mozo2024*',   256));

-- categoria_gasto
INSERT INTO categoria_gasto (nombre, descripcion) VALUES
('Servicios',     'Agua, luz, internet, alquiler'),
('Insumos',       'Compra de insumos y materia prima'),
('Personal',      'Pagos y beneficios al personal'),
('Mantenimiento', 'Gastos por mantenimiento de equipos'),
('Administrativo','Gastos administrativos generales');

-- gasto
INSERT INTO gasto (id_categoria_gasto, fecha_gasto, monto, descripcion) VALUES
(1, '2024-07-05', 850.00, 'Pago de alquiler del local - julio'),
(1, '2024-07-05', 120.00, 'Pago de servicio de luz'),
(4, '2024-07-08',  90.00, 'Mantenimiento de máquina de café');

-- documento_administrativo
INSERT INTO documento_administrativo (tipo_documento, fecha_documento, descripcion, id_empleado) VALUES
('Contrato de alquiler', '2024-01-01', 'Renovación anual de contrato de alquiler del local', 1),
('Memo interno',         '2024-07-01', 'Comunicado de nuevos horarios de turno', 1);

-- presupuesto
INSERT INTO presupuesto (area, periodo, monto_asignado, monto_ejecutado) VALUES
('Insumos',  '2024-07', 2000.00, 1450.00),
('Servicios','2024-07', 1200.00, 970.00);

-- proveedor
INSERT INTO proveedor (razon_social, ruc, contacto, telefono, correo, direccion) VALUES
('Distribuidora Café Andino S.A.C.', '20512345678', 'Ana Castillo', '014512345', 'ventas@cafeandino.pe', 'Av. Arequipa 1500, Lima'),
('Lácteos del Valle E.I.R.L.',       '20623456789', 'Pedro Salas',  '014623456', 'pedidos@lacteosvalle.pe', 'Jr. Junín 320, Lima');

-- contrato_proveedor
INSERT INTO contrato_proveedor (id_proveedor, fecha_inicio, fecha_fin, condiciones) VALUES
(1, '2024-01-01', '2024-12-31', 'Entrega semanal, pago a 15 días'),
(2, '2024-01-01', '2024-12-31', 'Entrega bisemanal, pago al contado');

-- orden_compra
INSERT INTO orden_compra (id_proveedor, id_empleado, fecha_orden, monto_total, estado) VALUES
(1, 1, '2024-07-08 09:00:00', 450.00, 'recibida'),
(2, 1, '2024-07-09 10:00:00', 180.00, 'confirmada');

-- detalle_orden_compra
INSERT INTO detalle_orden_compra (id_orden_compra, id_insumo, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 15.000, 30.00, 450.00),
(2, 2, 30.000,  6.00, 180.00);

-- recepcion_mercaderia
INSERT INTO recepcion_mercaderia (id_orden_compra, id_empleado, fecha_recepcion, conforme, observaciones) VALUES
(1, 6, '2024-07-09 08:30:00', 1, 'Mercadería recibida conforme'),
(2, 6, '2024-07-10 08:30:00', 1, NULL);

-- cuenta_por_pagar
INSERT INTO cuenta_por_pagar (id_proveedor, id_orden_compra, monto, fecha_vencimiento, estado) VALUES
(1, 1, 450.00, '2024-07-23', 'pendiente'),
(2, 2, 180.00, '2024-07-10', 'pagada');

-- reporte_financiero
INSERT INTO reporte_financiero (periodo, ingresos, egresos, utilidad_neta) VALUES
('2024-06', 8500.00, 5200.00, 3300.00);

-- tipo_equipo
INSERT INTO tipo_equipo (nombre, descripcion) VALUES
('Máquina de café',  'Equipos de extracción de espresso'),
('Refrigeradora',    'Equipos de refrigeración y conservación'),
('Licuadora',        'Equipos de licuado para bebidas frías');

-- equipo
INSERT INTO equipo (id_tipo_equipo, nombre, modelo, fecha_adquisicion, estado) VALUES
(1, 'Máquina espresso principal', 'La Marzocco Linea Mini', '2021-02-01', 'operativo'),
(2, 'Refrigeradora de vitrina',   'Samsung RV-200',          '2021-02-01', 'operativo'),
(3, 'Licuadora industrial',       'Vitamix Pro 750',         '2022-05-10', 'en_mantenimiento');

-- tipo_mantenimiento
INSERT INTO tipo_mantenimiento (nombre, descripcion) VALUES
('Preventivo', 'Mantenimiento programado regular'),
('Correctivo', 'Mantenimiento por falla detectada');

-- mantenimiento
INSERT INTO mantenimiento (id_equipo, id_tipo_mantenimiento, id_empleado, fecha_mantenimiento, costo, observaciones) VALUES
(1, 1, 1, '2024-07-01 09:00:00', 90.00, 'Limpieza y calibración programada'),
(3, 2, 1, '2024-07-08 10:00:00', 60.00, 'Reemplazo de cuchilla dañada');

-- estandar_calidad
INSERT INTO estandar_calidad (nombre, descripcion, aplicable_a) VALUES
('Temperatura de bebidas calientes', 'Las bebidas calientes deben servirse entre 65°C y 75°C', 'producto'),
('Frescura de insumos lácteos',      'Los insumos lácteos no deben exceder su fecha de vencimiento', 'insumo');

-- inspeccion
INSERT INTO inspeccion (id_empleado, fecha_inspeccion, area_inspeccionada, resultado_general) VALUES
(1, '2024-07-10 10:00:00', 'Barra de preparación', 'conforme'),
(1, '2024-07-12 10:00:00', 'Almacén de insumos',   'observado');

-- resultado_inspeccion
INSERT INTO resultado_inspeccion (id_inspeccion, id_estandar_calidad, cumple, observaciones) VALUES
(1, 1, 1, 'Temperaturas dentro del rango establecido'),
(2, 2, 0, 'Se encontró un lote de leche próximo a vencer');

-- tipo_incidencia
INSERT INTO tipo_incidencia (nombre, descripcion) VALUES
('Producto defectuoso', 'Producto entregado no cumple estándares'),
('Falla de equipo',     'Equipo presenta mal funcionamiento'),
('Queja de cliente',    'Cliente expresa insatisfacción');

-- incidencia
INSERT INTO incidencia (id_tipo_incidencia, id_empleado, fecha_incidencia, area, descripcion, estado) VALUES
(2, 2, '2024-07-08 09:30:00', 'Barra de preparación', 'Licuadora industrial dejó de funcionar correctamente', 'resuelta'),
(3, 4, '2024-07-11 12:00:00', 'Salón',                'Cliente reportó demora excesiva en la entrega de su pedido', 'en_proceso');

-- encuesta_satisfaccion
INSERT INTO encuesta_satisfaccion (id_cliente, fecha_encuesta, canal, puntuacion_general) VALUES
(1, '2024-07-10 09:45:00', 'presencial', 4.50),
(3, '2024-07-11 16:20:00', 'web',        4.00);

-- respuesta_encuesta
INSERT INTO respuesta_encuesta (id_encuesta_satisfaccion, pregunta, puntuacion, comentario) VALUES
(1, '¿Cómo calificaría la atención recibida?', 4.50, 'Muy amable el personal'),
(1, '¿Cómo calificaría el tiempo de espera?',  4.00, NULL),
(2, '¿Cómo calificaría la calidad del producto?', 4.00, 'Buen sabor, aunque algo frío');

-- campana_marketing
INSERT INTO campana_marketing (nombre, descripcion, canal, fecha_inicio, fecha_fin, presupuesto) VALUES
('Promo Invierno 2024', 'Campaña de bebidas calientes para temporada de frío', 'Instagram y Facebook', '2024-06-01', '2024-08-31', 600.00);

-- promocion
INSERT INTO promocion (id_campana_marketing, descripcion, tipo_descuento, valor_descuento, fecha_inicio, fecha_fin) VALUES
(1, '2x1 en bebidas calientes los miércoles', 'producto_gratis', 0.00, '2024-06-01', '2024-08-31'),
(1, '15% de descuento en combos de desayuno', 'porcentaje',     15.00, '2024-06-01', '2024-08-31');

-- estado_pedido
INSERT INTO estado_pedido (nombre, descripcion) VALUES
('Pendiente',      'Pedido registrado, aún no enviado a producción'),
('En preparación', 'Pedido enviado a producción, en proceso'),
('Listo',          'Pedido preparado, listo para entregar'),
('Entregado',      'Pedido entregado al cliente'),
('Cancelado',      'Pedido cancelado por el cliente o el sistema');

-- pedido de prueba
INSERT INTO pedido (id_cliente, id_empleado, id_estado_pedido, fecha_pedido, observaciones) VALUES
(1, 4, 4, '2024-07-15 09:00:00', NULL),
(3, 4, 2, '2024-07-15 09:30:00', 'Para llevar');

-- detalle_pedido
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario, observaciones) VALUES
(1, 2, 1,  9.00, NULL),
(1, 8, 1,  7.00, NULL),
(2, 4, 1, 12.00, NULL),
(2, 6, 1, 14.00, NULL);

-- caja
INSERT INTO caja (id_empleado, fecha_apertura, monto_inicial, estado) VALUES
(4, '2024-07-15 07:00:00', 100.00, 'cerrada');

-- comprobante
INSERT INTO comprobante (id_pedido, id_tipo_comprobante, id_metodo_pago, serie, numero, subtotal, igv, total, fecha_emision) VALUES
(1, 1, 1, 'B001', '00000050', 13.56, 2.44, 16.00, '2024-07-15 09:10:00'),
(2, 1, 4, 'B001', '00000051', 22.03, 3.97, 26.00, '2024-07-15 09:35:00');

-- cierre_caja
INSERT INTO cierre_caja (id_caja, id_metodo_pago, monto_registrado, diferencia, observaciones) VALUES
(1, 1, 16.00, 0.00, 'Cuadre correcto'),
(1, 4, 26.00, 0.00, 'Cuadre correcto');

-- stock
INSERT INTO stock (id_insumo, cantidad_actual, cantidad_minima) VALUES
(1, 8.000,  2.000),
(2, 25.000, 8.000),
(3, 5.000,  2.000),
(4, 3.000,  1.000),
(5, 2.000,  1.000),
(6, 40.000, 10.000),
(7, 6.000,  2.000),
(8, 4.000,  1.000),
(9, 10.000, 3.000);

-- movimiento_stock
INSERT INTO movimiento_stock (id_stock, id_empleado, tipo_movimiento, cantidad, motivo) VALUES
(1, 6, 'entrada', 15.000, 'Compra orden OC-001'),
(2, 6, 'entrada', 30.000, 'Compra orden OC-002'),
(1, 2, 'salida',   0.020, 'Uso en preparación de espresso'),
(2, 2, 'salida',   0.150, 'Uso en preparación de capuchino');

-- auditoria
INSERT INTO auditoria (id_usuario, modulo, accion, tabla_afectada, id_registro, detalle) VALUES
(1, 'MS-RecursosHumanos', 'crear', 'usuario',  2, 'Usuario bespinoza creado con rol Barista'),
(3, 'MS-Ventas',          'crear', 'comprobante', 1, 'Boleta B001-00000050 emitida'),
(4, 'MS-Pedidos',         'crear', 'pedido',   2, 'Nuevo pedido registrado para cliente Andrea Quevedo');

-- ============================================================
-- FIN DEL SCRIPT
-- ============================================================
SELECT 'Base de datos sinergiacafe_bd creada exitosamente con 55 tablas.' AS resultado;