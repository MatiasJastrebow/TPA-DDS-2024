package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Provincia;
import models.entities.personas.users.Usuario;

/**
 * Repositorio de usuarios.
 */

public class UsuariosRepository extends GenericRepository {

  public void guardar(Usuario usuario) {
    super.guardar(usuario);
  }

  public void modificar(Usuario usuario) {
    super.modificar(usuario);
  }

  public void eliminarFisico(Usuario usuario) {
    super.eliminarFisico(usuario);
  }

  public void eliminar(Usuario usuario) {
    super.eliminar(usuario);
  }

  /**
   * Busca un usuario por su id.
   *
   * @param id Id del usuario a buscar.
   * @return Un Optional con el usuario encontrado, o vacío si no se encontró.
   */

  public Optional<Usuario> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("El id es null");
    }
    return super.buscarPorId(id, Usuario.class);
  }

  public List<Provincia> buscarTodos() {
    return super.buscarTodos(Provincia.class);
  }

  /**
   * Busca un usuario por su nombre de usuario.
   *
   * @param nombreUsuario nombre de usuario a buscar.
   * @return Un Optional con el usuario encontrado, o vacío si no se encontró.
   */

  public Optional<Usuario> buscarPorNombreDeUsuario(String nombreUsuario) {
    String query =
        "SELECT u FROM Usuario u WHERE u.nombreUsuario =: nombreUsuario";
    List<Usuario> results = entityManager().createQuery(query, Usuario.class)
        .setParameter("nombreUsuario", nombreUsuario)
        .getResultList();
    if (results.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(results.get(0));
    }
  }

}
