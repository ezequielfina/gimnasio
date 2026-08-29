create table if not exists operaciones.profes (
    id uuid primary key default gen_random_uuid(),
    id_sede uuid not null references operaciones.sedes(id),
    nombre varchar(30),
    apellido varchar(50) not null
);

alter table operaciones.clases drop constraint if exists clases_id_profesor_fkey;

alter table operaciones.clases add constraint clases_id_profe foreign key (id_profesor) references operaciones.profes(id);