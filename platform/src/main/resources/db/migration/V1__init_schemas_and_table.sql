-- ==========================================
-- 1. ESQUEMA GEO (Debe ir primero por las FK)
-- ==========================================
CREATE SCHEMA IF NOT EXISTS geo;

CREATE TABLE IF NOT EXISTS geo.provincias (
                                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                              provincia VARCHAR(75) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS geo.partidos (
                                            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                            partido VARCHAR(100) NOT NULL,
                                            id_provincia UUID NOT NULL REFERENCES geo.provincias(id),
                                            CONSTRAINT un_partido_provincia UNIQUE (partido, id_provincia)
);

CREATE TABLE IF NOT EXISTS geo.localidades (
                                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                               localidad VARCHAR(100) NOT NULL,
                                               id_partido UUID NOT NULL REFERENCES geo.partidos(id),
                                               CONSTRAINT un_localidad_partido UNIQUE (localidad, id_partido)
);


-- ==========================================
-- 2. ESQUEMA AUTH (Usuarios y Perfiles)
-- ==========================================
CREATE SCHEMA IF NOT EXISTS auth;

CREATE TYPE auth.roles AS ENUM ('ROLE_ADMIN', 'ROLE_USUARIO', 'ROLE_PROFE');

CREATE TABLE IF NOT EXISTS auth.usuarios (
                                             id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                             username VARCHAR(30) NOT NULL UNIQUE,
                                             password VARCHAR(255) NOT NULL,
                                             email VARCHAR(75) NOT NULL UNIQUE,
                                             is_enabled BOOLEAN NOT NULL DEFAULT FALSE,
                                             rol auth.roles NOT NULL,
                                             created_at TIMESTAMP NOT NULL DEFAULT now(),
                                             updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS auth.perfiles (
                                             id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                             id_usuario UUID NOT NULL REFERENCES auth.usuarios(id) UNIQUE,
                                             nombre VARCHAR(30) NOT NULL,
                                             apellido VARCHAR(50) NOT NULL,
                                             dni VARCHAR(11) NOT NULL UNIQUE,
                                             id_localidad UUID NOT NULL REFERENCES geo.localidades(id),
                                             created_at TIMESTAMP NOT NULL DEFAULT now(),
                                             updated_at TIMESTAMP NOT NULL DEFAULT now()
);


-- ==========================================
-- 3. ESQUEMA COMERCIAL (Suscripciones y Pagos)
-- ==========================================
CREATE SCHEMA IF NOT EXISTS comercial;

CREATE TABLE IF NOT EXISTS comercial.planes (
                                                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                nombre VARCHAR(50) NOT NULL UNIQUE,
                                                is_enabled BOOLEAN NOT NULL DEFAULT TRUE,
                                                id_plan_padre UUID REFERENCES comercial.planes(id),
                                                permisos JSONB NOT NULL DEFAULT '{}'::jsonb
);

CREATE TABLE IF NOT EXISTS comercial.planes_valorizados (
                                                            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                            id_plan UUID NOT NULL REFERENCES comercial.planes(id),
                                                            precio_mensual NUMERIC(10, 2) NOT NULL,
                                                            precio_anual NUMERIC(12, 2) NOT NULL,
                                                            fecha_desde DATE NOT NULL DEFAULT CURRENT_DATE,
                                                            fecha_hasta DATE
);

CREATE TYPE comercial.tipo_plan AS ENUM ('MENSUAL', 'ANUAL');
CREATE TYPE comercial.estado_membresia AS ENUM ('ACTIVA', 'VENCIDA', 'CANCELADA');

CREATE TABLE IF NOT EXISTS comercial.membresias (
                                                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                    id_usuario UUID NOT NULL REFERENCES auth.usuarios(id),
                                                    id_plan_valorizado UUID NOT NULL REFERENCES comercial.planes_valorizados(id),
                                                    fecha_compra DATE NOT NULL DEFAULT CURRENT_DATE,
                                                    fecha_vencimiento DATE NOT NULL,
                                                    tipo comercial.tipo_plan NOT NULL,
                                                    estado comercial.estado_membresia NOT NULL DEFAULT 'ACTIVA'
);

CREATE TYPE comercial.metodo_pago AS ENUM ('TARJETA_CREDITO', 'TARJETA_DEBITO', 'MERCADOPAGO', 'EFECTIVO');
CREATE TYPE comercial.estado_pago AS ENUM ('PENDIENTE', 'APROBADO', 'RECHAZADO');

CREATE TABLE IF NOT EXISTS comercial.pagos (
                                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                               id_membresia UUID NOT NULL REFERENCES comercial.membresias(id),
                                               monto NUMERIC(10, 2) NOT NULL,
                                               metodo comercial.metodo_pago NOT NULL,
                                               estado comercial.estado_pago NOT NULL DEFAULT 'PENDIENTE',
                                               fecha_pago TIMESTAMP NOT NULL DEFAULT now(),
                                               transaccion_id VARCHAR(100)
);


-- ==========================================
-- 4. ESQUEMA OPERACIONES (Sedes y Clases)
-- ==========================================
CREATE SCHEMA IF NOT EXISTS operaciones;

CREATE TYPE operaciones.tipo_sede AS ENUM ('CORE', 'PULSE', 'APEX');

CREATE TABLE IF NOT EXISTS operaciones.sedes (
                                                 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                 nombre VARCHAR(30) NOT NULL UNIQUE,
                                                 tipo_sede operaciones.tipo_sede NOT NULL DEFAULT 'CORE',
                                                 is_enabled BOOLEAN NOT NULL DEFAULT TRUE,
                                                 id_localidad UUID NOT NULL REFERENCES geo.localidades(id),
                                                 direccion VARCHAR(150)
);

CREATE TYPE operaciones.dia AS ENUM ('LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO');

CREATE TABLE IF NOT EXISTS operaciones.aperturas_sedes (
                                                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                           id_sede UUID NOT NULL REFERENCES operaciones.sedes(id),
                                                           dia operaciones.dia NOT NULL,
                                                           horario_apertura TIME NOT NULL,
                                                           horario_clausura TIME,
                                                           CONSTRAINT un_ape_sed UNIQUE (id_sede, dia)
);

CREATE TABLE IF NOT EXISTS operaciones.espacios (
                                                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                    nombre VARCHAR(30) NOT NULL,
                                                    cupo_maximo INT NOT NULL DEFAULT 20,
                                                    id_sede UUID NOT NULL REFERENCES operaciones.sedes(id),
                                                    CONSTRAINT un_er_nombre UNIQUE (nombre, id_sede)
);

CREATE TABLE IF NOT EXISTS operaciones.disciplinas (
                                                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                       nombre VARCHAR(50) NOT NULL UNIQUE,
                                                       descripcion VARCHAR
);

CREATE TABLE IF NOT EXISTS operaciones.clases (
                                                  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                  id_disciplina UUID NOT NULL REFERENCES operaciones.disciplinas(id),
                                                  id_profesor UUID REFERENCES auth.usuarios(id),
                                                  dia operaciones.dia NOT NULL,
                                                  horario_inicio TIME NOT NULL,
                                                  duracion_minutos INT CHECK ( duracion_minutos IN (30, 60, 90, 120) )
);

CREATE TYPE operaciones.estado_sesion AS ENUM ('PROGRAMADA', 'EN_CURSO', 'FINALIZADA', 'CANCELADA', 'REPROGRAMADA');

CREATE TABLE IF NOT EXISTS operaciones.sesiones (
                                                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                    id_clase UUID NOT NULL REFERENCES operaciones.clases(id),
                                                    id_espacio UUID NOT NULL REFERENCES operaciones.espacios(id),
                                                    fecha DATE NOT NULL,
                                                    estado operaciones.estado_sesion NOT NULL DEFAULT 'PROGRAMADA',
                                                    CONSTRAINT un_sesion_espacio_fecha UNIQUE (id_espacio, fecha, id_clase)
);

CREATE TABLE IF NOT EXISTS operaciones.inscripciones_recurrentes (
                                                                     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                                     id_usuario UUID NOT NULL REFERENCES auth.usuarios(id),
                                                                     id_clase UUID NOT NULL REFERENCES operaciones.clases(id),
                                                                     CONSTRAINT un_usuario_clase_recurrente UNIQUE (id_usuario, id_clase)
);

CREATE TABLE IF NOT EXISTS operaciones.inscripciones_sesion (
                                                                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                                id_usuario UUID NOT NULL REFERENCES auth.usuarios(id),
                                                                id_sesion UUID NOT NULL REFERENCES operaciones.sesiones(id),
                                                                asistio BOOLEAN,
                                                                CONSTRAINT un_usuario_sesion UNIQUE (id_usuario, id_sesion)
);