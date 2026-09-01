alter table auth.usuarios
    drop column created_at,
    drop column updated_at;

alter table auth.perfiles
    drop column created_at,
    drop column updated_at;

