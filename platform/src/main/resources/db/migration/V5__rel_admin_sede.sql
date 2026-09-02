create table if not exists auth.admins_sede (
                                                id uuid primary key not null default gen_random_uuid(),
                                                id_admin uuid not null references auth.usuarios(id),
                                                id_sede uuid not null references operaciones.sedes(id),
                                                constraint un_adm_sed unique (id_admin, id_sede)
);

create or replace function fn_adm_sed ()
    returns trigger as $$
declare
    v_role text;
begin
    select usu.rol
    into v_role
    from auth.usuarios as usu
    where usu.id = NEW.id_admin;

    -- is distinct from maneja tanto valores distintos como NULLs
    if v_role is distinct from 'ROLE_ADMIN' then
        raise exception 'No se puede asociar un usuario que no es ROLE_ADMIN a una sede (Rol actual: %)', coalesce(v_role, 'SIN_ROL');
    end if;

    return NEW; -- Permite continuar con la inserción/actualización
end;
$$ language plpgsql;

create trigger tr_validar_usuario_admin
    before insert or update on auth.admins_sede
    for each row
execute function fn_adm_sed();