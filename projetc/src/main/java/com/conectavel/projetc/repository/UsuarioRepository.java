package com.conectavel.projetc.repository;

import com.conectavel.projetc.dto.FiltrarDto;
import com.conectavel.projetc.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT idUsuario from Usuario WHERE emailUsuario = :email AND senhaUsuario = :senha")
    Long findIdByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);

    Usuario findByEmailUsuarioAndSenhaUsuario(String emailUsuario, String senhaUsuario);

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.tipoPerfil.idTipoPerfil = :tipo")
    long countByProfileType(@Param("tipo") long tipoPerfil);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET emailUsuario = :email WHERE u.idUsuario = :id")
    void setEmailUsuarioById(@Param("email") String email, @Param("id") Long tipo);

    @Query("SELECT new com.conectavel.projetc.dto.FiltrarDto(u.idUsuario, u.nomeUsuario, u.fotoPerfilPath) FROM Usuario u JOIN u.habilidades h WHERE h.idHabilidade = :valorHabilidade")
    List<FiltrarDto> filtraPrestadorDeServico(@Param("valorHabilidade") Long valorHabilidade);
}
