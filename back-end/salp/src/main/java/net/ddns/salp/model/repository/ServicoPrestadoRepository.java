package net.ddns.salp.model.repository;

import net.ddns.salp.model.entity.ServicoPrestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Long> {

//    @Query("SELECT s FROM ServicoPrestado s JOIN s.cliente c WHERE UPPER( c.nome) LIKE UPPER(:nome) " +
//            "OR UPPER( c.lastName) LIKE UPPER(:sobrenome) " +
//            "AND MONTH(s.data) BETWEEN :mes AND :mesFinal AND :valor AND :sobrenome AND :descricao")
//    List<ServicoPrestado> findByFiltro(@Param("nome") String nome, @Param("mes") Integer mes, @Param("mesFinal") Integer mesFinal,
//                                       @Param("valor") BigDecimal valor, @Param("descricao") String descricao, @Param("sobrenome") String sobrenome);
}
